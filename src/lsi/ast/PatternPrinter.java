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
        return switch (pattern) {
            case Wildcard ignored -> "_";
            case Constant constant -> formatValue(constant.value());
            case Capture capture -> "**" + capture.id() + "**" + print(capture.body());
            case PGuard guard -> "guard(" + print(guard.pattern()) + ", <predicate>)";
            case Node node -> node.type().getSimpleName() + "(" + formatPatterns(node.children()) + ")";
        };
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
        switch (pattern) {
            case Wildcard ignored -> appendLine(result, indent, "_");
            case Constant constant -> appendLine(result, indent, formatValue(constant.value()));
            case Capture capture -> {
                appendLine(result, indent, "**" + capture.id() + "**:");
                appendPatternTree(capture.body(), result, indent + 1);
            }
            case PGuard guard -> {
                appendLine(result, indent, "guard:");
                appendPatternTree(guard.pattern(), result, indent + 1);
            }
            case Node node -> {
                appendLine(result, indent, node.type().getSimpleName());
                for (int i = 0; i < node.children().size(); i++) {
                    appendLine(result, indent + 1, "[" + i + "]:");
                    appendPatternTree(node.children().get(i), result, indent + 2);
                }
            }
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
        return switch (value) {
            case null -> "null";
            case String text -> "\"" + text.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
            case Character character -> "'" + character + "'";
            case Node node -> node.type() == Void.class
                    ? "null"
                    : node.type().getSimpleName() + "(" + formatPatterns(node.children()) + ")";
            case Iterable<?> iterable -> {
                StringBuilder result = new StringBuilder("[");
                Iterator<?> iterator = iterable.iterator();
                while (iterator.hasNext()) {
                    if (result.length() > 1) {
                        result.append(", ");
                    }
                    result.append(formatValue(iterator.next()));
                }
                yield result.append(']').toString();
            }
            case Object[] array -> {
                StringBuilder result = new StringBuilder("[");
                for (int i = 0; i < array.length; i++) {
                    if (i > 0) {
                        result.append(", ");
                    }
                    result.append(formatValue(array[i]));
                }
                yield result.append(']').toString();
            }
            default -> {
                if (value.getClass().isArray()) {
                    StringBuilder result = new StringBuilder("[");
                    for (int i = 0; i < Array.getLength(value); i++) {
                        if (i > 0) {
                            result.append(", ");
                        }
                        result.append(formatValue(Array.get(value, i)));
                    }
                    yield result.append(']').toString();
                }
                yield value.getClass().isRecord() ? formatRecord(value) : Objects.toString(value);
            }
        };
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
