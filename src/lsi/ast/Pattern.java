package lsi.ast;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public sealed interface Pattern permits Pattern.Node, Pattern.Capture, Pattern.Wildcard,
        Pattern.Constant, Pattern.PGuard {

    record Node(Class<?> type, List<Pattern> children) implements Pattern {
        public Node {
            Objects.requireNonNull(type, "type cannot be null");
            children = List.copyOf(Objects.requireNonNull(children, "children cannot be null"));
        }

        public static Node of(Class<?> type, List<Pattern> children) {
            Objects.requireNonNull(type, "type cannot be null");
            Objects.requireNonNull(children, "children cannot be null");
            return new Node(type, children);
        }
    }

    record Capture(String id, Pattern body) implements Pattern {
        public Capture {
            Objects.requireNonNull(id, "id cannot be null");
            Objects.requireNonNull(body, "body cannot be null");
        }

        public static Capture of(String id, Pattern body) {
            Objects.requireNonNull(id, "id cannot be null");
            Objects.requireNonNull(body, "body cannot be null");
            return new Capture(id, body);
        }
    }

    record Wildcard() implements Pattern {
        public static Wildcard of() {
            return new Wildcard();
        }
    }

    record Constant(Object value) implements Pattern {
        public Constant {
            Objects.requireNonNull(value, "value cannot be null");
        }

        public static Constant of(Object value) {
            Objects.requireNonNull(value, "value cannot be null");
            return new Constant(value);
        }
    }

    record PGuard(Pattern pattern, Predicate<Map<String, Node>> condition) implements Pattern {
        public PGuard {
            Objects.requireNonNull(pattern, "pattern cannot be null");
            Objects.requireNonNull(condition, "condition cannot be null");
        }

        public static PGuard of(Pattern pattern, Predicate<Map<String, Node>> condition) {
            Objects.requireNonNull(pattern, "pattern cannot be null");
            Objects.requireNonNull(condition, "condition cannot be null");
            return new PGuard(pattern, condition);
        }
    }
}
