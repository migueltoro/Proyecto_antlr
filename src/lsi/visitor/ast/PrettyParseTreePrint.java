package lsi.visitor.ast;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * 
 * PrettyPrintVisitor recorre el ParseTree y construye una representación
 * 
 * legible e indentada del árbol mostrando nombres de reglas y tokens.
 * 
 * Colocar en: src/ls/visitor/ast/PrettyPrintVisitor.java
 * 
 * Paquete: ls.visitor.ast
 */
public class PrettyParseTreePrint extends AbstractParseTreeVisitor<Void> {
    private final Parser parser;
    private final StringBuilder sb = new StringBuilder();
    private int indent = 0;
    private final boolean showTerminals;

    public PrettyParseTreePrint(Parser parser) {
        this(parser, true);
    }

    public PrettyParseTreePrint(Parser parser, boolean showTerminals) {
        this.parser = parser;
        this.showTerminals = showTerminals;
    }

    private void indent() {
        for (int i = 0; i < indent; i++)
            sb.append(" ");
    }

    private String ruleName(int idx) {
        if (parser != null) {
            String[] rn = parser.getRuleNames();
            if (idx >= 0 && idx < rn.length)
                return rn[idx];
        }
        return "rule#" + idx;
    }

    @Override
    public Void visitChildren(RuleNode node) {
        if (node instanceof ParserRuleContext) {
            ParserRuleContext ctx = (ParserRuleContext) node;
            indent();
            sb.append(ruleName(ctx.getRuleIndex()));
            // resumen del texto del subárbol (truncado si es largo)
            String txt = ctx.getText();
            if (txt != null && !txt.isEmpty()) {
                String s = txt.length() > 60 ? txt.substring(0, 60) + "..." : txt;
                sb.append(" : ").append(escape(s));
            }
            sb.append("\n");

            indent++;
            for (int i = 0; i < node.getChildCount(); i++) {
                ParseTree child = node.getChild(i);
                child.accept(this);
            }
            indent--;
            return null;
        } else {
            return super.visitChildren(node);
        }
    }

    @Override
    public Void visitTerminal(TerminalNode node) {
        if (!showTerminals)
            return null;
        Token t = node.getSymbol();
        indent();
        String name = null;
        if (parser != null) {
            Vocabulary vocab = parser.getVocabulary();
            name = vocab.getSymbolicName(t.getType());
        }
        if (name == null)
            name = "<" + t.getType() + ">";
        sb.append(name).append(" '").append(escape(t.getText())).append("'\n");
        return null;
    }

    @Override
    public Void visitErrorNode(ErrorNode node) {
        indent();
        sb.append("<ERROR> '").append(escape(node.getText())).append("'\n");
        return null;
    }

    private String escape(String s) {
        if (s == null)
            return "";
        return s.replace("\n", "\n").replace("\r", "\r").replace("\t", "\t");
    }

    public String getResult() {
        return sb.toString();
    }

    /**
     * 
     * Conveniencia para imprimir directamente desde un ParseTree y Parser.
     */
    public static String prettyPrint(ParseTree tree, Parser parser) {
        PrettyParseTreePrint v = new PrettyParseTreePrint(parser);
        tree.accept(v);
        return v.getResult();
    }
}
