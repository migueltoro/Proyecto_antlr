package lsi.ast.pattern;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.RecordComponent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

import lsi.ast.linear.LinearFactor;

public final class ASTPattern {

    private static final Map<Class<?>, Map<String, Accessor>> ACCESSOR_CACHE = new ConcurrentHashMap<>();

    private ASTPattern() {
    }

    public static List<Match> findAll(Object root, Pattern pattern) {
        Objects.requireNonNull(pattern, "pattern cannot be null");
        List<Match> matches = new ArrayList<>();
        Set<Object> visited = Collections.newSetFromMap(new IdentityHashMap<>());
        findAllRecursive(root, pattern, matches, visited);
        return matches;
    }

    public static Optional<Match> findFirst(Object root, Pattern pattern) {
        Objects.requireNonNull(pattern, "pattern cannot be null");
        Set<Object> visited = Collections.newSetFromMap(new IdentityHashMap<>());
        return findFirstRecursive(root, pattern, visited);
    }

    public static boolean exists(Object root, Pattern pattern) {
        return findFirst(root, pattern).isPresent();
    }

    public static FieldPattern field(String name, Pattern pattern) {
        return new FieldPattern(name, pattern);
    }

    public static Pattern any() {
        return WildcardPattern.INSTANCE;
    }

    public static Pattern value(Object expected) {
        return new ValuePattern(expected);
    }

    public static Pattern predicate(Predicate<Object> predicate) {
        return new PredicatePattern(predicate);
    }

    public static Pattern capture(String name, Pattern innerPattern) {
        return new CapturePattern(name, innerPattern);
    }

    public static Pattern capture(String name) {
        return new CapturePattern(name, any());
    }

    public static Pattern type(Class<?> expectedType, FieldPattern... fields) {
        return new TypePattern(expectedType, null, Arrays.asList(fields));
    }

    public static Pattern type(Class<?> expectedType, Predicate<Object> predicate, FieldPattern... fields) {
        return new TypePattern(expectedType, predicate, Arrays.asList(fields));
    }

    public static Pattern list(boolean allowExtraElements, Pattern... elementPatterns) {
        return new ListPattern(allowExtraElements, Arrays.asList(elementPatterns));
    }

    public static Pattern anyElement(Pattern elementPattern) {
        return new AnyElementPattern(elementPattern);
    }

    public static Pattern allOf(Pattern... patterns) {
        return new AllOfPattern(Arrays.asList(patterns));
    }

    public static Pattern anyOf(Pattern... patterns) {
        return new AnyOfPattern(Arrays.asList(patterns));
    }

    private static void findAllRecursive(Object node, Pattern pattern, List<Match> out, Set<Object> visited) {
        if (node == null) {
            return;
        }
        if (!isScalar(node.getClass()) && !visited.add(node)) {
            return;
        }

        Bindings initialBindings = new Bindings();
        if (match(pattern, node, initialBindings)) {
            out.add(new Match(node, initialBindings.toImmutable()));
        }

        for (Object child : childrenOf(node)) {
            findAllRecursive(child, pattern, out, visited);
        }
    }

    private static Optional<Match> findFirstRecursive(Object node, Pattern pattern, Set<Object> visited) {
        if (node == null) {
            return Optional.empty();
        }
        if (!isScalar(node.getClass()) && !visited.add(node)) {
            return Optional.empty();
        }

        Bindings initialBindings = new Bindings();
        if (match(pattern, node, initialBindings)) {
            return Optional.of(new Match(node, initialBindings.toImmutable()));
        }
        for (Object child : childrenOf(node)) {
            Optional<Match> fromChild = findFirstRecursive(child, pattern, visited);
            if (fromChild.isPresent()) {
                return fromChild;
            }
        }
        return Optional.empty();
    }

    private static boolean match(Pattern pattern, Object node, Bindings bindings) {
        if (pattern instanceof WildcardPattern) {
            return true;
        }
        if (pattern instanceof ValuePattern valuePattern) {
            return Objects.equals(valuePattern.expected(), node);
        }
        if (pattern instanceof PredicatePattern predicatePattern) {
            return predicatePattern.predicate().test(node);
        }
        if (pattern instanceof CapturePattern capturePattern) {
            Bindings trial = bindings.copy();
            if (!match(capturePattern.innerPattern(), node, trial)) {
                return false;
            }
            if (!trial.bind(capturePattern.name(), node)) {
                return false;
            }
            bindings.replaceWith(trial);
            return true;
        }
        if (pattern instanceof TypePattern typePattern) {
            if (node == null || !typePattern.expectedType().isInstance(node)) {
                return false;
            }

            Bindings trial = bindings.copy();
            if (typePattern.predicate() != null && !typePattern.predicate().test(node)) {
                return false;
            }
            for (FieldPattern fieldPattern : typePattern.fields()) {
                Optional<Object> fieldValue = readField(node, fieldPattern.name());
                if (fieldValue.isEmpty()) {
                    return false;
                }
                if (!match(fieldPattern.pattern(), fieldValue.get(), trial)) {
                    return false;
                }
            }
            bindings.replaceWith(trial);
            return true;
        }
        if (pattern instanceof ListPattern listPattern) {
            if (!(node instanceof List<?> list)) {
                return false;
            }
            List<Pattern> elementPatterns = listPattern.elementPatterns();
            if (!listPattern.allowExtraElements() && list.size() != elementPatterns.size()) {
                return false;
            }
            if (listPattern.allowExtraElements() && list.size() < elementPatterns.size()) {
                return false;
            }

            Bindings trial = bindings.copy();
            for (int i = 0; i < elementPatterns.size(); i++) {
                if (!match(elementPatterns.get(i), list.get(i), trial)) {
                    return false;
                }
            }
            bindings.replaceWith(trial);
            return true;
        }
        if (pattern instanceof AnyElementPattern anyElementPattern) {
            if (!(node instanceof Iterable<?> iterable)) {
                return false;
            }
            for (Object element : iterable) {
                Bindings trial = bindings.copy();
                if (match(anyElementPattern.elementPattern(), element, trial)) {
                    bindings.replaceWith(trial);
                    return true;
                }
            }
            return false;
        }
        if (pattern instanceof AllOfPattern allOfPattern) {
            Bindings trial = bindings.copy();
            for (Pattern subPattern : allOfPattern.patterns()) {
                if (!match(subPattern, node, trial)) {
                    return false;
                }
            }
            bindings.replaceWith(trial);
            return true;
        }
        if (pattern instanceof AnyOfPattern anyOfPattern) {
            for (Pattern subPattern : anyOfPattern.patterns()) {
                Bindings trial = bindings.copy();
                if (match(subPattern, node, trial)) {
                    bindings.replaceWith(trial);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private static List<Object> childrenOf(Object node) {
        if (node == null) {
            return List.of();
        }
        Class<?> nodeType = node.getClass();
        if (isScalar(nodeType)) {
            return List.of();
        }

        List<Object> children = new ArrayList<>();
        if (node instanceof Iterable<?> iterable) {
            for (Object element : iterable) {
                children.add(element);
            }
            return children;
        }
        if (nodeType.isArray()) {
            int len = Array.getLength(node);
            for (int i = 0; i < len; i++) {
                children.add(Array.get(node, i));
            }
            return children;
        }

        for (Accessor accessor : accessorsFor(nodeType).values()) {
            children.add(accessor.read(node));
        }
        return children;
    }

    private static Optional<Object> readField(Object node, String fieldName) {
        if (node == null) {
            return Optional.empty();
        }
        Accessor accessor = accessorsFor(node.getClass()).get(fieldName);
        if (accessor == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(accessor.read(node));
    }

    private static Map<String, Accessor> accessorsFor(Class<?> type) {
        return ACCESSOR_CACHE.computeIfAbsent(type, ASTPattern::buildAccessors);
    }

    private static Map<String, Accessor> buildAccessors(Class<?> type) {
        if (type.isRecord()) {
            Map<String, Accessor> accessors = new LinkedHashMap<>();
            for (RecordComponent component : type.getRecordComponents()) {
                accessors.put(component.getName(), new Accessor(component.getName(), component.getAccessor()));
            }
            return accessors;
        }

        if (type == LinearFactor.class) {
            try {
                Map<String, Accessor> accessors = new LinkedHashMap<>();
                accessors.put("coefficient", new Accessor("coefficient", type.getMethod("coefficient")));
                accessors.put("variable", new Accessor("variable", type.getMethod("variable")));
                return accessors;
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException("LinearFactor accessors are not available", e);
            }
        }

        if (!isAstType(type)) {
            return Map.of();
        }

        Map<String, Accessor> accessors = new LinkedHashMap<>();
        for (Method method : type.getDeclaredMethods()) {
            if (method.getParameterCount() != 0) {
                continue;
            }
            if (Modifier.isStatic(method.getModifiers()) || method.getReturnType() == Void.TYPE) {
                continue;
            }
            if (method.isSynthetic()) {
                continue;
            }
            String methodName = method.getName();
            if ("toString".equals(methodName) || "hashCode".equals(methodName)) {
                continue;
            }
            accessors.put(methodName, new Accessor(methodName, method));
        }
        return accessors;
    }

    private static boolean isAstType(Class<?> type) {
        Package nodePackage = type.getPackage();
        return nodePackage != null && nodePackage.getName().startsWith("lsi.ast");
    }

    private static boolean isScalar(Class<?> type) {
        return type.isPrimitive()
                || Number.class.isAssignableFrom(type)
                || Boolean.class == type
                || Character.class == type
                || String.class == type
                || Enum.class.isAssignableFrom(type)
                || Class.class == type;
    }

    public sealed interface Pattern permits WildcardPattern,
            ValuePattern,
            PredicatePattern,
            CapturePattern,
            TypePattern,
            ListPattern,
            AnyElementPattern,
            AllOfPattern,
            AnyOfPattern {
    }

    public record Match(Object node, Map<String, Object> bindings) {
        public Match {
            bindings = Map.copyOf(bindings);
        }
    }

    public record FieldPattern(String name, Pattern pattern) {
        public FieldPattern {
            Objects.requireNonNull(name, "name cannot be null");
            Objects.requireNonNull(pattern, "pattern cannot be null");
        }
    }

    public record ValuePattern(Object expected) implements Pattern {
    }

    public record PredicatePattern(Predicate<Object> predicate) implements Pattern {
        public PredicatePattern {
            Objects.requireNonNull(predicate, "predicate cannot be null");
        }
    }

    public record CapturePattern(String name, Pattern innerPattern) implements Pattern {
        public CapturePattern {
            Objects.requireNonNull(name, "name cannot be null");
            Objects.requireNonNull(innerPattern, "innerPattern cannot be null");
        }
    }

    public record TypePattern(Class<?> expectedType, Predicate<Object> predicate, List<FieldPattern> fields)
            implements Pattern {
        public TypePattern {
            Objects.requireNonNull(expectedType, "expectedType cannot be null");
            fields = List.copyOf(fields);
        }
    }

    public record ListPattern(boolean allowExtraElements, List<Pattern> elementPatterns) implements Pattern {
        public ListPattern {
            Objects.requireNonNull(elementPatterns, "elementPatterns cannot be null");
            elementPatterns = List.copyOf(elementPatterns);
        }
    }

    public record AnyElementPattern(Pattern elementPattern) implements Pattern {
        public AnyElementPattern {
            Objects.requireNonNull(elementPattern, "elementPattern cannot be null");
        }
    }

    public record AllOfPattern(List<Pattern> patterns) implements Pattern {
        public AllOfPattern {
            Objects.requireNonNull(patterns, "patterns cannot be null");
            patterns = List.copyOf(patterns);
        }
    }

    public record AnyOfPattern(List<Pattern> patterns) implements Pattern {
        public AnyOfPattern {
            Objects.requireNonNull(patterns, "patterns cannot be null");
            patterns = List.copyOf(patterns);
        }
    }

    private enum WildcardPattern implements Pattern {
        INSTANCE
    }

    private record Accessor(String name, Method method) {

        private Accessor {
            method.setAccessible(true);
        }

        private Object read(Object receiver) {
            try {
                return method.invoke(receiver);
            } catch (ReflectiveOperationException e) {
                throw new IllegalStateException(
                        "Unable to read accessor '" + name + "' from type " + receiver.getClass().getName(), e);
            }
        }
    }

    private static final class Bindings {
        private final LinkedHashMap<String, Object> bindings;

        private Bindings() {
            this.bindings = new LinkedHashMap<>();
        }

        private Bindings(LinkedHashMap<String, Object> bindings) {
            this.bindings = bindings;
        }

        private boolean bind(String name, Object value) {
            if (bindings.containsKey(name)) {
                return Objects.equals(bindings.get(name), value);
            }
            bindings.put(name, value);
            return true;
        }

        private Bindings copy() {
            return new Bindings(new LinkedHashMap<>(bindings));
        }

        private void replaceWith(Bindings other) {
            bindings.clear();
            bindings.putAll(other.bindings);
        }

        private Map<String, Object> toImmutable() {
            return Map.copyOf(bindings);
        }
    }
}
