package lang.bq.parser.tokens.lowlevel;

import lang.bq.parser.tokens.IToken;
import lang.bq.parser.tokens.TokenType;

public class NumberToken implements IToken {
    private static final TokenType type = TokenType.NUMBER;
    public final Number value;

    public NumberToken(Number value) {
        this.value = value;
    }

    @Override
    public TokenType type() {
        return type;
    }

    @Override
    public String toString() {
        return "NumberToken{" +
                "value=" + value +
                '}';
    }
}
