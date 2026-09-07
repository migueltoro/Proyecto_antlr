package lsi.ast.matching;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

import lsi.ast.linear.LinearExpr;

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

    public record VariablePattern<T>(String name, Class<T> expectedType, Pattern innerPattern)
            implements Pattern {
        public VariablePattern {
            Objects.requireNonNull(name, "name cannot be null");
            Objects.requireNonNull(expectedType, "expectedType cannot be null");
            Objects.requireNonNull(innerPattern, "innerPattern cannot be null");
        }

        /**
         * Recupera de forma tipada el subárbol instanciado para esta
         * variable en un resultado de matching. Devuelve {@code null} si el
         * patrón no declaraba esta variable o no llegó a instanciarse.
         */
        public T valueIn(PatternASTMatcher.Match match) {
            Object value = match.bindings().get(name);
            return value == null ? null : expectedType.cast(value);
        }
    }

    /**
     * Variable especializada para capturar un subárbol {@link LinearExpr}
     * completo, opcionalmente restringido por un patrón interno.
     */
    public record LinearExprVariable(String name, Pattern innerPattern) implements Pattern {
        public LinearExprVariable {
            Objects.requireNonNull(name, "name cannot be null");
            Objects.requireNonNull(innerPattern, "innerPattern cannot be null");
        }

        public LinearExpr valueIn(PatternASTMatcher.Match match) {
            Object value = match.bindings().get(name);
            return value == null ? null : LinearExpr.class.cast(value);
        }
    }

    /**
     * Describe una variable declarada dentro de un patrón: su nombre y el
     * tipo que se espera que tenga el valor instanciado.
     */
    public record VariableDeclaration(String name, Class<?> expectedType) {
        public VariableDeclaration {
            Objects.requireNonNull(name, "name cannot be null");
            Objects.requireNonNull(expectedType, "expectedType cannot be null");
        }
    }

    /**
     * Recorre un patrón y devuelve todas las variables que declara,
     * indexadas por nombre, junto con el tipo esperado de cada una.
     *
     * <p>Permite inspeccionar qué valores podrá consultar quien ejecute el
     * patrón, sin necesidad de ejecutarlo antes contra un AST.</p>
     */
    public static Map<String, VariableDeclaration> variablesOf(Pattern pattern) {
        Map<String, VariableDeclaration> variables = new LinkedHashMap<>();
        collectVariables(pattern, variables);
        return Map.copyOf(variables);
    }

    private static void collectVariables(Pattern pattern,
            Map<String, VariableDeclaration> out) {
        if (pattern instanceof VariablePattern<?> variable) {
            declare(out, variable.name(), variable.expectedType());
            collectVariables(variable.innerPattern(), out);
        } else if (pattern instanceof LinearExprVariable variable) {
            declare(out, variable.name(), LinearExpr.class);
            collectVariables(variable.innerPattern(), out);
        } else if (pattern instanceof NodePattern nodePattern) {
            for (FieldPattern field : nodePattern.fields()) {
                collectVariables(field.pattern(), out);
            }
        } else if (pattern instanceof ListPattern listPattern) {
            for (Pattern element : listPattern.elements()) {
                collectVariables(element, out);
            }
        } else if (pattern instanceof AnyElementPattern anyElement) {
            collectVariables(anyElement.element(), out);
        } else if (pattern instanceof AllOfPattern allOf) {
            for (Pattern sub : allOf.patterns()) {
                collectVariables(sub, out);
            }
        } else if (pattern instanceof AnyOfPattern anyOf) {
            for (Pattern sub : anyOf.patterns()) {
                collectVariables(sub, out);
            }
        }
    }

    private static void declare(Map<String, VariableDeclaration> out, String name,
            Class<?> expectedType) {
        VariableDeclaration declaration = new VariableDeclaration(name, expectedType);
        VariableDeclaration existing = out.putIfAbsent(name, declaration);
        if (existing != null && !existing.expectedType().equals(expectedType)) {
            throw new IllegalStateException("Variable '" + name
                    + "' declared with conflicting types: " + existing.expectedType()
                    + " and " + expectedType);
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
        return new VariablePattern<>(name, expectedType, any());
    }

    public static <T> VariablePattern<T> variable(String name, Class<T> expectedType,
            Pattern innerPattern) {
        return new VariablePattern<>(name, expectedType, innerPattern);
    }

    public static LinearExprVariable linearExprVariable(String name) {
        return new LinearExprVariable(name, any());
    }

    public static LinearExprVariable linearExprVariable(String name, Pattern innerPattern) {
        return new LinearExprVariable(name, innerPattern);
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
