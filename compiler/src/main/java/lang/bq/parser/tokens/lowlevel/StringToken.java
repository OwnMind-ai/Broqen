package lang.bq.parser.tokens.lowlevel;

import lang.bq.parser.tokens.IToken;
import lang.bq.parser.tokens.TokenType;

public class StringToken implements IToken {
    private final TokenType type;
    public final String value;

    public StringToken(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public TokenType type() {
        return type;
    }

    @Override
    public String toString() {
        return "StringToken{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
