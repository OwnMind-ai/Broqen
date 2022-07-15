package lang.bq.parser.tokens;

public class BooleanToken implements IToken{
    private static final TokenType type = TokenType.BOOLEAN;
    public final boolean value;

    public BooleanToken(boolean value) {
        this.value = value;
    }

    @Override
    public TokenType type() {
        return type;
    }

    @Override
    public String toString() {
        return "BooleanToken{" +
                "value=" + value +
                '}';
    }
}
