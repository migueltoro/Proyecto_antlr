package lsi.listener.errors;

public record SyntaxError(
        int line,
        int column,
        String token,
        String message) {

    @Override
    public String toString() {

        return String.format(
                "line %d:%d near '%s' : %s",
                line,
                column,
                token,
                message);
    }
}