package lsi.ast.matching;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.RecordComponent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import lsi.ast.linear.LinearExpr;
import lsi.ast.matching.PatternAST.AllOfPattern;
import lsi.ast.matching.PatternAST.AnyElementPattern;
import lsi.ast.matching.PatternAST.AnyOfPattern;
import lsi.ast.matching.PatternAST.AnyPattern;
import lsi.ast.matching.PatternAST.FieldPattern;
import lsi.ast.matching.PatternAST.LinearExprVariable;
import lsi.ast.matching.PatternAST.ListPattern;
import lsi.ast.matching.PatternAST.NodePattern;
import lsi.ast.matching.PatternAST.Pattern;
import lsi.ast.matching.PatternAST.PredicatePattern;
import lsi.ast.matching.PatternAST.ValuePattern;
import lsi.ast.matching.PatternAST.VariablePattern;

/**
 * Recorre un AST real y unifica sus nodos con un {@link PatternAST.Pattern}.
 */
public final class PatternASTMatcher {

    private static final Map<Class<?>, Map<String, Accessor>> ACCESSORS =
            new ConcurrentHashMap<>();

    public List<Match> findAll(Object root, Pattern pattern) {
        Objects.requireNonNull(pattern, "pattern cannot be null");
        List<Match> matches = new ArrayList<>();
        Set<Object> visited = Collections.newSetFromMap(new IdentityHashMap<>());
        findAll(root, pattern, matches, visited);
        return matches;
    }

    public Optional<Match> findFirst(Object root, Pattern pattern) {
        Objects.requireNonNull(pattern, "pattern cannot be null");
        Set<Object> visited = Collections.newSetFromMap(new IdentityHashMap<>());
        return findFirst(root, pattern, visited);
    }

    public boolean exists(Object root, Pattern pattern) {
        return findFirst(root, pattern).isPresent();
    }

    private void findAll(Object node, Pattern pattern, List<Match> matches,
            Set<Object> visited) {
        if (node == null || (!isScalar(node.getClass()) && !visited.add(node))) {
            return;
        }
        Bindings bindings = new Bindings();
        if (match(pattern, node, bindings)) {
            matches.add(new Match(node, bindings.immutable()));
        }
        for (Object child : childrenOf(node)) {
            findAll(child, pattern, matches, visited);
        }
    }

    private Optional<Match> findFirst(Object node, Pattern pattern,
            Set<Object> visited) {
        if (node == null || (!isScalar(node.getClass()) && !visited.add(node))) {
            return Optional.empty();
        }
        Bindings bindings = new Bindings();
        if (match(pattern, node, bindings)) {
            return Optional.of(new Match(node, bindings.immutable()));
        }
        for (Object child : childrenOf(node)) {
            Optional<Match> match = findFirst(child, pattern, visited);
            if (match.isPresent()) {
                return match;
            }
        }
        return Optional.empty();
    }

    private boolean match(Pattern pattern, Object candidate, Bindings bindings) {
        if (pattern instanceof AnyPattern) {
            return true;
        }
        if (pattern instanceof ValuePattern value) {
            return Objects.equals(value.expected(), candidate);
        }
        if (pattern instanceof PredicatePattern predicate) {
            return predicate.predicate().test(candidate);
        }
        if (pattern instanceof VariablePattern<?> variable) {
            return bindVariable(variable.name(), variable.expectedType(),
                    candidate, bindings);
        }
        if (pattern instanceof LinearExprVariable variable) {
            return bindVariable(variable.name(), LinearExpr.class, candidate, bindings);
        }
        if (pattern instanceof NodePattern nodePattern) {
            if (candidate == null || !nodePattern.nodeType().isInstance(candidate)) {
                return false;
            }
            Bindings trial = bindings.copy();
            for (FieldPattern field : nodePattern.fields()) {
                Optional<Object> value = readField(candidate, field.name());
                if (value.isEmpty() || !match(field.pattern(), value.get(), trial)) {
                    return false;
                }
            }
            bindings.replaceWith(trial);
            return true;
        }
        if (pattern instanceof ListPattern listPattern) {
            if (!(candidate instanceof List<?> list)
                    || (!listPattern.allowExtraElements()
                            && list.size() != listPattern.elements().size())
                    || (listPattern.allowExtraElements()
                            && list.size() < listPattern.elements().size())) {
                return false;
            }
            Bindings trial = bindings.copy();
            for (int i = 0; i < listPattern.elements().size(); i++) {
                if (!match(listPattern.elements().get(i), list.get(i), trial)) {
                    return false;
                }
            }
            bindings.replaceWith(trial);
            return true;
        }
        if (pattern instanceof AnyElementPattern anyElement) {
            if (!(candidate instanceof Iterable<?> iterable)) {
                return false;
            }
            for (Object element : iterable) {
                Bindings trial = bindings.copy();
                if (match(anyElement.element(), element, trial)) {
                    bindings.replaceWith(trial);
                    return true;
                }
            }
            return false;
        }
        if (pattern instanceof AllOfPattern allOf) {
            Bindings trial = bindings.copy();
            for (Pattern subPattern : allOf.patterns()) {
                if (!match(subPattern, candidate, trial)) {
                    return false;
                }
            }
            bindings.replaceWith(trial);
            return true;
        }
        if (pattern instanceof AnyOfPattern anyOf) {
            for (Pattern subPattern : anyOf.patterns()) {
                Bindings trial = bindings.copy();
                if (match(subPattern, candidate, trial)) {
                    bindings.replaceWith(trial);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean bindVariable(String name, Class<?> expectedType, Object candidate,
            Bindings bindings) {
        if (candidate == null || !expectedType.isInstance(candidate)) {
            return false;
        }
        return bindings.bind(name, candidate);
    }

    private static List<Object> childrenOf(Object node) {
        if (isScalar(node.getClass())) {
            return List.of();
        }
        if (node instanceof Iterable<?> iterable) {
            List<Object> children = new ArrayList<>();
            iterable.forEach(children::add);
            return children;
        }
        if (node.getClass().isArray()) {
            List<Object> children = new ArrayList<>();
            for (int i = 0; i < Array.getLength(node); i++) {
                children.add(Array.get(node, i));
            }
            return children;
        }
        List<Object> children = new ArrayList<>();
        for (Accessor accessor : accessorsFor(node.getClass()).values()) {
            children.add(accessor.read(node));
        }
        return children;
    }

    private static Optional<Object> readField(Object node, String name) {
        Accessor accessor = accessorsFor(node.getClass()).get(name);
        return accessor == null ? Optional.empty() : Optional.ofNullable(accessor.read(node));
    }

    private static Map<String, Accessor> accessorsFor(Class<?> type) {
        return ACCESSORS.computeIfAbsent(type, PatternASTMatcher::buildAccessors);
    }

    private static Map<String, Accessor> buildAccessors(Class<?> type) {
        Map<String, Accessor> result = new LinkedHashMap<>();
        if (type.isRecord()) {
            for (RecordComponent component : type.getRecordComponents()) {
                result.put(component.getName(),
                        new Accessor(component.getName(), component.getAccessor()));
            }
            return result;
        }
        if (!type.getPackageName().startsWith("lsi.ast")) {
            return result;
        }
        for (Method method : type.getDeclaredMethods()) {
            if (method.getParameterCount() == 0
                    && !Modifier.isStatic(method.getModifiers())
                    && method.getReturnType() != Void.TYPE
                    && !method.isSynthetic()
                    && !"toString".equals(method.getName())
                    && !"hashCode".equals(method.getName())) {
                result.put(method.getName(), new Accessor(method.getName(), method));
            }
        }
        return result;
    }

    private static boolean isScalar(Class<?> type) {
        return type.isPrimitive() || Number.class.isAssignableFrom(type)
                || type == Boolean.class || type == Character.class
                || type == String.class || type.isEnum() || type == Class.class;
    }

    public record Match(Object node, Map<String, Object> bindings) {
        public Match {
            bindings = Map.copyOf(bindings);
        }
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
                        "Unable to read accessor '" + name + "'", e);
            }
        }
    }

    private static final class Bindings {
        private final LinkedHashMap<String, Object> values = new LinkedHashMap<>();

        private boolean bind(String name, Object value) {
            if (values.containsKey(name)) {
                return Objects.equals(values.get(name), value);
            }
            values.put(name, value);
            return true;
        }

        private Bindings copy() {
            Bindings copy = new Bindings();
            copy.values.putAll(values);
            return copy;
        }

        private void replaceWith(Bindings other) {
            values.clear();
            values.putAll(other.values);
        }

        private Map<String, Object> immutable() {
            return Map.copyOf(values);
        }
    }
}
