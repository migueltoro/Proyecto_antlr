package lsi.pattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.RecordComponent;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import lsi.ast.AST;
import lsi.pattern.PatternMatcher.Match;

public final class Transform {
    private final PatternMatcher matcher = new PatternMatcher();

    /**
     * Reemplaza cada nodo que coincide con el patrón y devuelve un AST nuevo.
     * El nodo de reemplazo debe ser compatible con el tipo AST esperado en el
     * campo donde aparece cada coincidencia.
     */
    public AST transform(AST ast, Pattern pattern, Object replacement) {
        Objects.requireNonNull(ast, "ast cannot be null");
        Objects.requireNonNull(pattern, "pattern cannot be null");
        Objects.requireNonNull(replacement, "replacement cannot be null");

        IdentityHashMap<Object, Object> replacements = new IdentityHashMap<>();
        for (Match match : matcher.findAll(ast, pattern)) {
            replacements.put(match.node(), replacement);
        }
        return (AST) transformValue(ast, AST.class, replacements);
    }

    private Object transformValue(Object value, java.lang.reflect.Type expectedType,
            IdentityHashMap<Object, Object> replacements) {
        if (value == null) {
            return null;
        }
        if (replacements.containsKey(value)) {
            Object replacement = replacements.get(value);
            validateReplacement(replacement, expectedType);
            return replacement;
        }
        if (value instanceof List<?> list) {
            java.lang.reflect.Type elementType = listElementType(expectedType);
            List<Object> transformed = new ArrayList<>(list.size());
            for (Object element : list) {
                transformed.add(transformValue(element, elementType, replacements));
            }
            return transformed;
        }
        if (!value.getClass().isRecord()) {
            return value;
        }

        RecordComponent[] components = value.getClass().getRecordComponents();
        Map<TypeVariable<?>, java.lang.reflect.Type> typeArguments =
                typeArguments(value.getClass(), expectedType);
        Object[] arguments = new Object[components.length];
        Class<?>[] parameterTypes = new Class<?>[components.length];
        for (int i = 0; i < components.length; i++) {
            RecordComponent component = components[i];
            parameterTypes[i] = component.getType();
            java.lang.reflect.Type componentType = resolveType(component.getGenericType(), typeArguments);
            arguments[i] = transformValue(read(component, value), componentType, replacements);
        }
        return constructRecord(value.getClass(), parameterTypes, arguments);
    }

    private static void validateReplacement(Object replacement, java.lang.reflect.Type expectedType) {
        Class<?> expectedClass = rawClass(expectedType);
        if (expectedClass != null && !isCompatible(expectedClass, replacement)) {
            throw new IllegalArgumentException("Replacement type " + replacement.getClass().getName()
                    + " is not compatible with " + expectedClass.getName());
        }

        if (replacement instanceof List<?> list) {
            java.lang.reflect.Type elementType = listElementType(expectedType);
            for (Object element : list) {
                if (element != null) {
                    validateReplacement(element, elementType);
                }
            }
        }
    }

    private static boolean isCompatible(Class<?> expectedType, Object value) {
        if (expectedType.isInstance(value)) {
            return true;
        }
        if (!expectedType.isPrimitive()) {
            return false;
        }
        return expectedType == boolean.class && value instanceof Boolean
                || expectedType == byte.class && value instanceof Byte
                || expectedType == short.class && value instanceof Short
                || expectedType == int.class && value instanceof Integer
                || expectedType == long.class && value instanceof Long
                || expectedType == float.class && value instanceof Float
                || expectedType == double.class && value instanceof Double
                || expectedType == char.class && value instanceof Character;
    }

    private static java.lang.reflect.Type listElementType(java.lang.reflect.Type expectedType) {
        if (expectedType instanceof ParameterizedType parameterizedType
                && parameterizedType.getRawType() instanceof Class<?> raw
                && List.class.isAssignableFrom(raw)) {
            return parameterizedType.getActualTypeArguments()[0];
        }
        return Object.class;
    }

    private static Map<TypeVariable<?>, java.lang.reflect.Type> typeArguments(
            Class<?> recordType, java.lang.reflect.Type expectedType) {
        TypeVariable<?>[] variables = recordType.getTypeParameters();
        if (variables.length == 0 || !(expectedType instanceof ParameterizedType parameterizedType)) {
            return Map.of();
        }
        java.lang.reflect.Type[] arguments = parameterizedType.getActualTypeArguments();
        Map<TypeVariable<?>, java.lang.reflect.Type> result = new IdentityHashMap<>();
        for (int i = 0; i < Math.min(variables.length, arguments.length); i++) {
            result.put(variables[i], arguments[i]);
        }
        return result;
    }

    private static java.lang.reflect.Type resolveType(java.lang.reflect.Type type,
            Map<TypeVariable<?>, java.lang.reflect.Type> typeArguments) {
        if (type instanceof TypeVariable<?> variable) {
            return typeArguments.getOrDefault(variable, Object.class);
        }
        if (type instanceof WildcardType wildcard) {
            java.lang.reflect.Type[] upperBounds = wildcard.getUpperBounds();
            return upperBounds.length == 0 ? Object.class : resolveType(upperBounds[0], typeArguments);
        }
        if (type instanceof ParameterizedType parameterizedType) {
            java.lang.reflect.Type[] actualArguments = parameterizedType.getActualTypeArguments();
            java.lang.reflect.Type[] resolvedArguments = new java.lang.reflect.Type[actualArguments.length];
            for (int i = 0; i < actualArguments.length; i++) {
                resolvedArguments[i] = resolveType(actualArguments[i], typeArguments);
            }
            return new ResolvedParameterizedType(parameterizedType.getOwnerType(),
                    parameterizedType.getRawType(), resolvedArguments);
        }
        return type;
    }

    private static Class<?> rawClass(java.lang.reflect.Type type) {
        if (type instanceof Class<?> clazz) {
            return clazz;
        }
        if (type instanceof ParameterizedType parameterizedType
                && parameterizedType.getRawType() instanceof Class<?> clazz) {
            return clazz;
        }
        return null;
    }

    private static Object read(RecordComponent component, Object receiver) {
        try {
            return component.getAccessor().invoke(receiver);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Unable to read AST component '" + component.getName() + "'", e);
        }
    }

    private static Object constructRecord(Class<?> type, Class<?>[] parameterTypes, Object[] arguments) {
        try {
            Constructor<?> constructor = type.getDeclaredConstructor(parameterTypes);
            return constructor.newInstance(arguments);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            throw new IllegalStateException("Unable to rebuild AST record " + type.getName(), e);
        }
    }

    private record ResolvedParameterizedType(
            java.lang.reflect.Type ownerType,
            java.lang.reflect.Type rawType,
            java.lang.reflect.Type[] actualTypeArguments) implements ParameterizedType {
        private ResolvedParameterizedType {
            actualTypeArguments = actualTypeArguments.clone();
        }

        @Override
        public java.lang.reflect.Type[] getActualTypeArguments() {
            return actualTypeArguments.clone();
        }

        @Override
        public java.lang.reflect.Type getRawType() {
            return rawType;
        }

        @Override
        public java.lang.reflect.Type getOwnerType() {
            return ownerType;
        }
    }
}
