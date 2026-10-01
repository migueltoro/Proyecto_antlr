package lsi.ast;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import lsi.ast.Pattern.Capture;
import lsi.ast.Pattern.Constant;
import lsi.ast.Pattern.Node;
import lsi.ast.Pattern.PGuard;
import lsi.ast.Pattern.Wildcard;

public final class PatternPrinter {
    private PatternPrinter() {
    }

    public static String print(Pattern pattern) {
        Objects.requireNonNull(pattern, "pattern cannot be null");
        if (pattern instanceof Wildcard) {
            return "_";
        }
        if (pattern instanceof Constant constant) {
            return formatValue(constant.value());
        }
        if (pattern instanceof Capture capture) {
            return "?" + capture.id() + "@" + print(capture.body());
        }
        if (pattern instanceof PGuard guard) {
            return "guard(" + print(guard.pattern()) + ", <predicate>)";
        }
        if (pattern instanceof Node node) {
            return node.type().getSimpleName() + "(" + formatPatterns(node.children()) + ")";
        }
        throw new IllegalArgumentException("Unsupported pattern type: " + pattern.getClass().getName());
    }

    public static String printTree(Pattern pattern) {
        Objects.requireNonNull(pattern, "pattern cannot be null");
        StringBuilder result = new StringBuilder();
        appendPatternTree(pattern, result, 0);
        return result.toString();
    }

    public static String printValue(Object value) {
        return formatValue(value);
    }

    private static void appendPatternTree(Pattern pattern, StringBuilder result, int indent) {
        if (pattern instanceof Wildcard) {
            appendLine(result, indent, "_");
        } else if (pattern instanceof Constant constant) {
            appendLine(result, indent, formatValue(constant.value()));
        } else if (pattern instanceof Capture capture) {
            appendLine(result, indent, "?" + capture.id() + ":");
            appendPatternTree(capture.body(), result, indent + 1);
        } else if (pattern instanceof PGuard guard) {
            appendLine(result, indent, "guard:");
            appendPatternTree(guard.pattern(), result, indent + 1);
        } else if (pattern instanceof Node node) {
            appendLine(result, indent, node.type().getSimpleName());
            for (int i = 0; i < node.children().size(); i++) {
                appendLine(result, indent + 1, "[" + i + "]:");
                appendPatternTree(node.children().get(i), result, indent + 2);
            }
        } else {
            throw new IllegalArgumentException("Unsupported pattern type: " + pattern.getClass().getName());
        }
    }

    private static String formatPatterns(List<Pattern> patterns) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < patterns.size(); i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(print(patterns.get(i)));
        }
        return result.toString();
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String text) {
            return "\"" + text.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
        }
        if (value instanceof Character character) {
            return "'" + character + "'";
        }
        if (value instanceof Node node) {
            if (node.type() == Void.class) {
                return "null";
            }
            return node.type().getSimpleName() + "(" + formatPatterns(node.children()) + ")";
        }
        if (value instanceof Iterable<?> iterable) {
            StringBuilder result = new StringBuilder("[");
            Iterator<?> iterator = iterable.iterator();
            while (iterator.hasNext()) {
                if (result.length() > 1) {
                    result.append(", ");
                }
                result.append(formatValue(iterator.next()));
            }
            return result.append(']').toString();
        }
        if (value.getClass().isArray()) {
            StringBuilder result = new StringBuilder("[");
            for (int i = 0; i < Array.getLength(value); i++) {
                if (i > 0) {
                    result.append(", ");
                }
                result.append(formatValue(Array.get(value, i)));
            }
            return result.append(']').toString();
        }
        if (value.getClass().isRecord()) {
            return formatRecord(value);
        }
        return Objects.toString(value);
    }

    private static String formatRecord(Object value) {
        RecordComponent[] components = value.getClass().getRecordComponents();
        StringBuilder result = new StringBuilder(value.getClass().getSimpleName()).append('[');
        for (int i = 0; i < components.length; i++) {
            if (i > 0) {
                result.append(", ");
            }
            RecordComponent component = components[i];
            result.append(component.getName()).append('=');
            try {
                result.append(formatValue(component.getAccessor().invoke(value)));
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException(
                        "Unable to read AST component '" + component.getName() + "'", e);
            }
        }
        return result.append(']').toString();
    }

    private static void appendLine(StringBuilder result, int indent, String text) {
        result.append("  ".repeat(indent)).append(text).append('\n');
    }
}
