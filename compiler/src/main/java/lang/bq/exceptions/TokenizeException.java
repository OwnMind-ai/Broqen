package lang.bq.exceptions;

public class TokenizeException extends RuntimeException {
    public final int line;
    public final int column;
    private String format = "(%d:%d)";

    public TokenizeException(String message, String format, int line, int column) {
        this(message, line, column);
        this.format = format;
    }

    public TokenizeException(String message, int line, int column) {
        super(message);
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return super.getMessage() + " " + String.format(format, line, column);
    }
}
