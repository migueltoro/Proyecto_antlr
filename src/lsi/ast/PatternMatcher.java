package lsi.ast;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
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

import lsi.ast.Pattern.Capture;
import lsi.ast.Pattern.Constant;
import lsi.ast.Pattern.Node;
import lsi.ast.Pattern.PGuard;
import lsi.ast.Pattern.Wildcard;

public final class PatternMatcher {
    private static final Map<Class<?>, List<Accessor>> ACCESSORS = new ConcurrentHashMap<>();

    public List<Match> findAll(Object root, Pattern pattern) {
        Objects.requireNonNull(pattern, "pattern cannot be null");
        List<Match> matches = new ArrayList<>();
        Set<Object> visited = Collections.newSetFromMap(new IdentityHashMap<>());
        findAll(root, pattern, matches, visited);
        return List.copyOf(matches);
    }

    public Optional<Match> findFirst(Object root, Pattern pattern) {
        Objects.requireNonNull(pattern, "pattern cannot be null");
        Set<Object> visited = Collections.newSetFromMap(new IdentityHashMap<>());
        return findFirst(root, pattern, visited);
    }

    public boolean exists(Object root, Pattern pattern) {
        return findFirst(root, pattern).isPresent();
    }

    private void findAll(Object candidate, Pattern pattern, List<Match> matches, Set<Object> visited) {
        if (candidate == null || (!isScalar(candidate.getClass()) && !visited.add(candidate))) {
            return;
        }

        Bindings bindings = new Bindings();
        if (match(pattern, candidate, bindings)) {
            matches.add(new Match(candidate, bindings.snapshot()));
        }
        for (Object child : childrenOf(candidate)) {
            findAll(child, pattern, matches, visited);
        }
    }

    private Optional<Match> findFirst(Object candidate, Pattern pattern, Set<Object> visited) {
        if (candidate == null || (!isScalar(candidate.getClass()) && !visited.add(candidate))) {
            return Optional.empty();
        }

        Bindings bindings = new Bindings();
        if (match(pattern, candidate, bindings)) {
            return Optional.of(new Match(candidate, bindings.snapshot()));
        }
        for (Object child : childrenOf(candidate)) {
            Optional<Match> result = findFirst(child, pattern, visited);
            if (result.isPresent()) {
                return result;
            }
        }
        return Optional.empty();
    }

    private boolean match(Pattern pattern, Object candidate, Bindings bindings) {
        if (pattern instanceof Wildcard) {
            return true;
        }
        if (pattern instanceof Constant constant) {
            return Objects.equals(constant.value(), candidate);
        }
        if (pattern instanceof Capture capture) {
            Bindings trial = bindings.copy();
            if (!match(capture.body(), candidate, trial)
                    || !trial.bind(capture.id(), asNode(candidate))) {
                return false;
            }
            bindings.replaceWith(trial);
            return true;
        }
        if (pattern instanceof PGuard guard) {
            Bindings trial = bindings.copy();
            if (!match(guard.pattern(), candidate, trial)
                    || !guard.condition().test(trial.snapshot())) {
                return false;
            }
            bindings.replaceWith(trial);
            return true;
        }
        if (pattern instanceof Node nodePattern) {
            if (candidate == null || !isInstance(nodePattern.type(), candidate)) {
                return false;
            }
            List<Object> children = componentsOf(candidate);
            if (children.size() != nodePattern.children().size()) {
                return false;
            }

            Bindings trial = bindings.copy();
            for (int i = 0; i < children.size(); i++) {
                if (!match(nodePattern.children().get(i), children.get(i), trial)) {
                    return false;
                }
            }
            bindings.replaceWith(trial);
            return true;
        }
        throw new IllegalArgumentException("Unsupported pattern type: " + pattern.getClass().getName());
    }

    private static List<Object> childrenOf(Object candidate) {
        if (candidate == null || isScalar(candidate.getClass())) {
            return List.of();
        }
        if (candidate instanceof Iterable<?> iterable) {
            List<Object> children = new ArrayList<>();
            iterable.forEach(children::add);
            return children;
        }
        if (candidate.getClass().isArray()) {
            List<Object> children = new ArrayList<>(Array.getLength(candidate));
            for (int i = 0; i < Array.getLength(candidate); i++) {
                children.add(Array.get(candidate, i));
            }
            return children;
        }
        return candidate.getClass().isRecord() ? componentsOf(candidate) : List.of();
    }

    private static List<Object> componentsOf(Object candidate) {
        if (candidate instanceof Iterable<?> iterable) {
            List<Object> children = new ArrayList<>();
            iterable.forEach(children::add);
            return children;
        }
        if (candidate.getClass().isArray()) {
            List<Object> children = new ArrayList<>(Array.getLength(candidate));
            for (int i = 0; i < Array.getLength(candidate); i++) {
                children.add(Array.get(candidate, i));
            }
            return children;
        }
        List<Accessor> accessors = ACCESSORS.computeIfAbsent(candidate.getClass(), PatternMatcher::accessorsOf);
        List<Object> children = new ArrayList<>(accessors.size());
        for (Accessor accessor : accessors) {
            children.add(accessor.read(candidate));
        }
        return children;
    }

    private static List<Accessor> accessorsOf(Class<?> type) {
        if (!type.isRecord()) {
            return List.of();
        }
        RecordComponent[] components = type.getRecordComponents();
        List<Accessor> accessors = new ArrayList<>(components.length);
        for (RecordComponent component : components) {
            accessors.add(new Accessor(component.getName(), component.getAccessor()));
        }
        return List.copyOf(accessors);
    }

    private static Node asNode(Object value) {
        if (value == null) {
            return Node.of(Void.class, List.of());
        }
        Class<?> type = value.getClass();
        List<Pattern> children = new ArrayList<>();
        if (value instanceof Iterable<?> iterable) {
            iterable.forEach(child -> children.add(asPatternValue(child)));
        } else if (type.isArray()) {
            for (int i = 0; i < Array.getLength(value); i++) {
                children.add(asPatternValue(Array.get(value, i)));
            }
        } else if (type.isRecord()) {
            for (Object child : componentsOf(value)) {
                children.add(asPatternValue(child));
            }
        } else {
            children.add(Constant.of(value));
        }
        return Node.of(type, children);
    }

    private static Pattern asPatternValue(Object value) {
        if (value == null) {
            return Node.of(Void.class, List.of());
        }
        return value != null && (value.getClass().isRecord()
                || value instanceof Iterable<?>
                || value.getClass().isArray())
                ? asNode(value)
                : Constant.of(value);
    }

    private static boolean isInstance(Class<?> expectedType, Object value) {
        if (expectedType.isInstance(value)) {
            return true;
        }
        if (!expectedType.isPrimitive()) {
            return false;
        }
        return (expectedType == boolean.class && value instanceof Boolean)
                || (expectedType == byte.class && value instanceof Byte)
                || (expectedType == short.class && value instanceof Short)
                || (expectedType == int.class && value instanceof Integer)
                || (expectedType == long.class && value instanceof Long)
                || (expectedType == float.class && value instanceof Float)
                || (expectedType == double.class && value instanceof Double)
                || (expectedType == char.class && value instanceof Character);
    }

    private static boolean isScalar(Class<?> type) {
        return type.isPrimitive() || Number.class.isAssignableFrom(type)
                || type == Boolean.class || type == Character.class
                || type == String.class || type.isEnum() || type == Class.class;
    }

    public record Match(Object node, Map<String, Node> bindings) {
        public Match {
            bindings = Map.copyOf(Objects.requireNonNull(bindings, "bindings cannot be null"));
        }

        public Node get(String id) {
            return bindings.get(id);
        }
    }

    private record Accessor(String name, java.lang.reflect.Method method) {
        private Object read(Object receiver) {
            try {
                return method.invoke(receiver);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException("Unable to read AST component '" + name + "'", e);
            }
        }
    }

    private static final class Bindings {
        private final Map<String, Node> values = new LinkedHashMap<>();

        private boolean bind(String id, Node value) {
            Node existing = values.putIfAbsent(id, value);
            return existing == null || existing.equals(value);
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

        private Map<String, Node> snapshot() {
            return Map.copyOf(values);
        }
    }
}
