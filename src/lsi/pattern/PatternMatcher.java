package lsi.pattern;

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

import lsi.pattern.Pattern.Variable;
import lsi.pattern.Pattern.Constant;
import lsi.pattern.Pattern.Node;
import lsi.pattern.Pattern.Guard;
import lsi.pattern.Pattern.Wildcard;

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
        return switch (pattern) {
            case Wildcard ignored -> true;
            case Constant constant -> Objects.equals(constant.value(), candidate);
            case Variable capture -> {
                Bindings trial = bindings.copy();
                boolean matched = match(capture.body(), candidate, trial)
                        && trial.bind(capture.id(), asNode(candidate));
                if (matched) {
                    bindings.replaceWith(trial);
                }
                yield matched;
            }
            case Guard guard -> {
                Bindings trial = bindings.copy();
                boolean matched = match(guard.pattern(), candidate, trial)
                        && guard.condition().test(trial.snapshot());
                if (matched) {
                    bindings.replaceWith(trial);
                }
                yield matched;
            }
            case Node nodePattern -> {
                if (candidate == null || !isInstance(nodePattern.type(), candidate)) {
                    yield false;
                }
                List<Object> children = componentsOf(candidate);
                if (children.size() != nodePattern.children().size()) {
                    yield false;
                }

                Bindings trial = bindings.copy();
                boolean matched = true;
                for (int i = 0; i < children.size(); i++) {
                    if (!match(nodePattern.children().get(i), children.get(i), trial)) {
                        matched = false;
                        break;
                    }
                }
                if (matched) {
                    bindings.replaceWith(trial);
                }
                yield matched;
            }
        };
    }

    private static List<Object> childrenOf(Object candidate) {
        if (candidate == null || isScalar(candidate.getClass())) {
            return List.of();
        }
        return switch (candidate) {
            case Iterable<?> iterable -> {
                List<Object> children = new ArrayList<>();
                iterable.forEach(children::add);
                yield children;
            }
            case Object[] array -> new ArrayList<>(java.util.Arrays.asList(array));
            default -> {
                if (!candidate.getClass().isArray()) {
                    yield candidate.getClass().isRecord() ? componentsOf(candidate) : List.of();
                }
                List<Object> children = new ArrayList<>(Array.getLength(candidate));
                for (int i = 0; i < Array.getLength(candidate); i++) {
                    children.add(Array.get(candidate, i));
                }
                yield children;
            }
        };
    }

    private static List<Object> componentsOf(Object candidate) {
        return switch (candidate) {
            case Iterable<?> iterable -> {
                List<Object> children = new ArrayList<>();
                iterable.forEach(children::add);
                yield children;
            }
            case Object[] array -> new ArrayList<>(java.util.Arrays.asList(array));
            default -> {
                if (candidate.getClass().isArray()) {
                    List<Object> children = new ArrayList<>(Array.getLength(candidate));
                    for (int i = 0; i < Array.getLength(candidate); i++) {
                        children.add(Array.get(candidate, i));
                    }
                    yield children;
                }
                List<Accessor> accessors = ACCESSORS.computeIfAbsent(candidate.getClass(),
                        PatternMatcher::accessorsOf);
                List<Object> children = new ArrayList<>(accessors.size());
                for (Accessor accessor : accessors) {
                    children.add(accessor.read(candidate));
                }
                yield children;
            }
        };
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
        return switch (value) {
            case Iterable<?> iterable -> {
                List<Pattern> children = new ArrayList<>();
                iterable.forEach(child -> children.add(asPatternValue(child)));
                yield Node.of(value.getClass(), children);
            }
            case Object[] array -> {
                List<Pattern> children = new ArrayList<>(array.length);
                for (Object child : array) {
                    children.add(asPatternValue(child));
                }
                yield Node.of(value.getClass(), children);
            }
            default -> {
                Class<?> type = value.getClass();
                if (type.isArray()) {
                    List<Pattern> children = new ArrayList<>(Array.getLength(value));
                    for (int i = 0; i < Array.getLength(value); i++) {
                        children.add(asPatternValue(Array.get(value, i)));
                    }
                    yield Node.of(type, children);
                }
                if (type.isRecord()) {
                    List<Pattern> children = new ArrayList<>();
                    for (Object child : componentsOf(value)) {
                        children.add(asPatternValue(child));
                    }
                    yield Node.of(type, children);
                }
                yield Node.of(type, List.of(Constant.of(value)));
            }
        };
    }

    private static Pattern asPatternValue(Object value) {
        if (value == null) {
            return Node.of(Void.class, List.of());
        }
        return switch (value) {
            case Iterable<?> ignored -> asNode(value);
            case Object[] ignored -> asNode(value);
            default -> value.getClass().isRecord() || value.getClass().isArray()
                    ? asNode(value)
                    : Constant.of(value);
        };
    }

    private static boolean isInstance(Class<?> expectedType, Object value) {
        if (expectedType.isInstance(value)) {
            return true;
        }
        return expectedType.isPrimitive() && switch (value) {
            case Boolean ignored -> expectedType == boolean.class;
            case Byte ignored -> expectedType == byte.class;
            case Short ignored -> expectedType == short.class;
            case Integer ignored -> expectedType == int.class;
            case Long ignored -> expectedType == long.class;
            case Float ignored -> expectedType == float.class;
            case Double ignored -> expectedType == double.class;
            case Character ignored -> expectedType == char.class;
            default -> false;
        };
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
