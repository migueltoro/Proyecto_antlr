package lsi.ast.matching;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Árbol independiente para describir patrones sobre el AST del lenguaje.
 *
 * <p>Un {@code NodePattern} representa un subárbol AST y una variable
 * representa una parte instanciable del patrón. Las variables no forman parte
 * del AST productivo.</p>
 */
public final class PatternAST {

    private PatternAST() {
    }

    public sealed interface Pattern permits NodePattern, VariablePattern,
            LinearExprVariable, ValuePattern, AnyPattern, PredicatePattern,
            ListPattern, AnyElementPattern, AllOfPattern, AnyOfPattern {
    }

    public record NodePattern(Class<?> nodeType, List<FieldPattern> fields)
            implements Pattern {
        public NodePattern {
            Objects.requireNonNull(nodeType, "nodeType cannot be null");
            fields = List.copyOf(fields);
        }
    }

    public record FieldPattern(String name, Pattern pattern) {
        public FieldPattern {
            Objects.requireNonNull(name, "name cannot be null");
            Objects.requireNonNull(pattern, "pattern cannot be null");
        }
    }

    public record VariablePattern<T>(String name, Class<T> expectedType)
            implements Pattern {
        public VariablePattern {
            Objects.requireNonNull(name, "name cannot be null");
            Objects.requireNonNull(expectedType, "expectedType cannot be null");
        }
    }

    /**
     * Variable especializada para capturar una expresión lineal completa.
     */
    public record LinearExprVariable(String name) implements Pattern {
        public LinearExprVariable {
            Objects.requireNonNull(name, "name cannot be null");
        }
    }

    public record ValuePattern(Object expected) implements Pattern {
    }

    public enum AnyPattern implements Pattern {
        INSTANCE
    }

    public record PredicatePattern(Predicate<Object> predicate) implements Pattern {
        public PredicatePattern {
            Objects.requireNonNull(predicate, "predicate cannot be null");
        }
    }

    public record ListPattern(boolean allowExtraElements, List<Pattern> elements)
            implements Pattern {
        public ListPattern {
            Objects.requireNonNull(elements, "elements cannot be null");
            elements = List.copyOf(elements);
        }
    }

    public record AnyElementPattern(Pattern element) implements Pattern {
        public AnyElementPattern {
            Objects.requireNonNull(element, "element cannot be null");
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

    public static NodePattern node(Class<?> nodeType, FieldPattern... fields) {
        return new NodePattern(nodeType, Arrays.asList(fields));
    }

    public static FieldPattern field(String name, Pattern pattern) {
        return new FieldPattern(name, pattern);
    }

    public static <T> VariablePattern<T> variable(String name, Class<T> expectedType) {
        return new VariablePattern<>(name, expectedType);
    }

    public static LinearExprVariable linearExprVariable(String name) {
        return new LinearExprVariable(name);
    }

    public static ValuePattern value(Object expected) {
        return new ValuePattern(expected);
    }

    public static AnyPattern any() {
        return AnyPattern.INSTANCE;
    }

    public static PredicatePattern predicate(Predicate<Object> predicate) {
        return new PredicatePattern(predicate);
    }

    public static ListPattern list(boolean allowExtraElements, Pattern... elements) {
        return new ListPattern(allowExtraElements, Arrays.asList(elements));
    }

    public static AnyElementPattern anyElement(Pattern element) {
        return new AnyElementPattern(element);
    }

    public static AllOfPattern allOf(Pattern... patterns) {
        return new AllOfPattern(Arrays.asList(patterns));
    }

    public static AnyOfPattern anyOf(Pattern... patterns) {
        return new AnyOfPattern(Arrays.asList(patterns));
    }
}
