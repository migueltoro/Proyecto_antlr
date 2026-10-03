package lsi.main;

import java.util.List;
//import java.util.Map;

import lsi.ast.AST;
import lsi.pattern.Pattern;
import lsi.pattern.PatternMatcher;
import lsi.pattern.PatternMatcher.Match;
import lsi.pattern.PatternPrinter;

public final class PatternASTExamples {
    private static final PatternMatcher MATCHER = new PatternMatcher();

    private PatternASTExamples() {
    }

    public static void run(AST ast) {
        printCategoryCount("Declaraciones", ast, AST.Declaration.class);
        printCategoryCount("Expresiones", ast, AST.Expression.class);
        printCategoryCount("Restricciones", ast, AST.Constraint.class);
        printCategoryCount("Cotas", ast, AST.Bound.class);
        runInitializedIntegerDeclarationExample(ast);
        runGreaterEqualZeroExample(ast);
        runIndexedVariableExample(ast);
        runNonEmptyLinearExpressionExample(ast);
    }

    private static void printCategoryCount(String description, AST ast, Class<?> category) {
        Pattern pattern = variableOfType("category", category);
        System.out.println("[PATTERN AST] " + description + ": " + MATCHER.findAll(ast, pattern).size());
    }

    private static void runInitializedIntegerDeclarationExample(AST ast) {
        Pattern integerType = Pattern.Node.of(AST.IntegerType.class, List.of());
        Pattern initializer = Pattern.Node.of(AST.IntLiteral.class,
                List.of(Pattern.Variable.of("initializerValue", Pattern.Wildcard.of())));
        Pattern declaration = Pattern.Node.of(AST.VarDeclaration.class,
                List.of(integerType, Pattern.Wildcard.of(), initializer));

        printPattern(declaration);
        List<Match> matches = MATCHER.findAll(ast, declaration);
        System.out.println("[PATTERN AST] Declaraciones Integer inicializadas: " + matches.size());
        printBindings(matches, "initializerValue");
    }

    private static void runGreaterEqualZeroExample(AST ast) {
        Pattern relation = Pattern.Guard.of(
                Pattern.Variable.of("relation", Pattern.Wildcard.of()),
                bindings -> {
                    Pattern.Node node = bindings.get("relation");
                    return node.type() == AST.RelationalConstraint.class
                            && node.children().get(1) instanceof Pattern.Constant operator
                            && operator.value() == AST.RelOperator.GE
                            && isZero(node.children().get(2));
                });

        printPattern(relation);
        List<Match> matches = MATCHER.findAll(ast, relation);
        System.out.println("[PATTERN AST] Restricciones relacionales >= 0: " + matches.size());
        printBindings(matches, "relation");
    }

    private static void runIndexedVariableExample(AST ast) {
        Pattern variable = Pattern.Guard.of(
                Pattern.Variable.of("variable", Pattern.Wildcard.of()),
                bindings -> isVariableNamedAndIndexedBy(
                        bindings.get("variable"), "x", 4));

        printPattern(variable);
        List<Match> matches = MATCHER.findAll(ast, variable);
        System.out.println("[PATTERN AST] Variables x[4]: " + matches.size());
        printBindings(matches, "variable");
    }

    private static void runNonEmptyLinearExpressionExample(AST ast) {
        Pattern expression = Pattern.Guard.of(
                Pattern.Variable.of("expression", Pattern.Wildcard.of()),
                bindings -> {
                    Pattern.Node node = bindings.get("expression");
                    return node.type() == AST.LinearExpr.class
                            && !node.children().isEmpty()
                            && node.children().get(0) instanceof Pattern.Node terms
                            && !terms.children().isEmpty();
                });

        printPattern(expression);
        List<Match> matches = MATCHER.findAll(ast, expression);
        System.out.println("[PATTERN AST] Expresiones lineales no vacías: " + matches.size());
        printBindings(matches, "expression");
    }

    private static Pattern variableOfType(String id, Class<?> type) {
        return Pattern.Guard.of(
                Pattern.Variable.of(id, Pattern.Wildcard.of()),
                bindings -> type.isAssignableFrom(bindings.get(id).type()));
    }

    private static boolean isZero(Pattern value) {
        if (!(value instanceof Pattern.Node literal) || literal.children().size() != 1
                || !(literal.children().get(0) instanceof Pattern.Constant constant)) {
            return false;
        }
        return (literal.type() == AST.IntLiteral.class && Integer.valueOf(0).equals(constant.value()))
                || (literal.type() == AST.DoubleLiteral.class && Double.valueOf(0.0).equals(constant.value()));
    }

    private static boolean isVariableNamedAndIndexedBy(Pattern.Node node, String name, int index) {
        if (node.type() != AST.Variable.class || node.children().size() != 2
                || !(node.children().get(0) instanceof Pattern.Constant variableName)
                || !name.equals(variableName.value())
                || !(node.children().get(1) instanceof Pattern.Node indexes)) {
            return false;
        }
        return indexes.children().stream().anyMatch(value -> isIntegerLiteral(value, index));
    }

    private static boolean isIntegerLiteral(Pattern value, int expected) {
        return value instanceof Pattern.Node literal
                && literal.type() == AST.IntLiteral.class
                && literal.children().size() == 1
                && literal.children().get(0) instanceof Pattern.Constant constant
                && Integer.valueOf(expected).equals(constant.value());
    }

    private static void printPattern(Pattern pattern) {
        System.out.println("[PATTERN AST] " + PatternPrinter.print(pattern));
        System.out.print(PatternPrinter.printTree(pattern));
    }

    private static void printBindings(List<Match> matches, String id) {
        for (Match match : matches) {
            System.out.println(id + " = " + PatternPrinter.printValue(match.get(id)));
        }
    }
}
