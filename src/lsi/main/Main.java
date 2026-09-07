package lsi.main;

import org.antlr.v4.runtime.*;
import lsi.ast.AST;
import lsi.parser.PLIModelLexer;
import lsi.parser.PLIModelParser;
import lsi.visitor.ast.ASTBuilder;
import lsi.visitor.types.ASTTypeChecker;
import lsi.listener.errors.SyntaxErrorListener;
import lsi.visitor.ast.PrettyAstPrinter;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {

        public static void main(String[] args)
                        throws Exception {

                System.out.println("MAIN ARRANCADO");

                System.out.println("ARGS = " + java.util.Arrays.toString(args));

                String inputFile = java.util.Arrays.stream(args)
                                .filter(arg -> !arg.startsWith("--"))
                                .findFirst()
                                .orElse(null);
                if (inputFile == null) {
                        System.err.println("Uso: lsi.main.Main <archivo.pli> [--patterns]");
                        return;
                }

                CharStream input = CharStreams.fromFileName(inputFile);

                PLIModelLexer lexer = new PLIModelLexer(input);

                CommonTokenStream tokens = new CommonTokenStream(lexer);

                PLIModelParser parser = new PLIModelParser(tokens);

                SyntaxErrorListener listener = new SyntaxErrorListener(inputFile);

                lexer.removeErrorListeners();
                lexer.addErrorListener(listener);

                parser.removeErrorListeners();
                parser.addErrorListener(listener);

                System.out.println("Parsing file: " + inputFile);

                ParseTree tree = parser.model();

                listener.errors();            

                if (listener.hasErrors()) {
                        return;
                }
                
                System.out.println(tree);

                ASTBuilder astBuilder = new ASTBuilder();
                AST ast = astBuilder.build(tree);

                ASTTypeChecker checker = new ASTTypeChecker();
                AST checkedAst = checker.visit(ast);
                if (checker.hasErrors()) {
                        System.err.println("Errores de tipo detectados:");
                        checker.printErrors();
                } else {
                        System.out.println("Verificación de tipos completada sin errores.");
                }

                System.out.println(PrettyAstPrinter.prettyPrint(checkedAst));

                boolean runPatternExamples = java.util.Arrays.stream(args)
                                .anyMatch("--patterns"::equals);
                if (runPatternExamples) {
                        PatternASTExamples.run(checkedAst);
                } else {
                        System.out.println("[PATTERN] Ejemplos desactivados. Usa --patterns para ejecutarlos.");
                }

        }
}
