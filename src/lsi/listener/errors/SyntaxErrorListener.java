package lsi.listener.errors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

public class SyntaxErrorListener extends BaseErrorListener {

    public SyntaxErrorListener(String fileName) {
        this.fileName = fileName;
    }

    private final List<SyntaxError> errors = new ArrayList<>();
    private String fileName;

    @Override
    public void syntaxError(
            Recognizer<?, ?> recognizer,
            Object offendingSymbol,
            int line,
            int charPositionInLine,
            String msg,
            RecognitionException e) {

        String tokenText = "<unknown>";

        if (offendingSymbol instanceof Token token) {
            tokenText = token.getText();
        }

        errors.add(
                new SyntaxError(
                        line,
                        charPositionInLine,
                        tokenText,
                        msg));
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<SyntaxError> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public void printErrors(String fileName) {

        for (SyntaxError error : errors) {

            System.err.printf(
                    "%s:%d:%d: %s%n",
                    fileName,
                    error.line(),
                    error.column(),
                    error.message());
        }
    }

    public void errors() {

        try {

            if (this.hasErrors()) {

                System.err.println(
                        "\nSyntax errors found:\n");

                this.printErrors(fileName);

                return;
            }

            System.out.println(
                    "Parsing completed successfully.");

        } catch (Exception e) {

            System.err.println(
                    "Exception while parsing:");

            e.printStackTrace();
        }
    }
}
