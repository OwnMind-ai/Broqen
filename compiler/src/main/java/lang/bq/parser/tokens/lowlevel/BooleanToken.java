package lang.bq.parser.tokens.lowlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;

public class BooleanToken implements Token {
    public final boolean value;

    public BooleanToken(boolean value) {
        this.value = value;
    }

    @Override
    public TokenType type() {
        return TokenType.BOOLEAN;
    }

    @Override
    public boolean is(TokenType type, Object value) {
        return this.type() == type && value.equals(this.value);
    }

    @Override
    public String toString() {
        return "BooleanToken{" +
                "value=" + value +
                '}';
    }
}
