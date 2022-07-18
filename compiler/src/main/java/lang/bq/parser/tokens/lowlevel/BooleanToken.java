package lang.bq.parser.tokens.lowlevel;

import lang.bq.parser.tokens.IToken;
import lang.bq.parser.tokens.TokenType;

public class BooleanToken implements IToken {
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
