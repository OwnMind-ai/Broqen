package lang.bq.parser.tokens.lowlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.syntax.Operators;

public class OperatorToken implements Token {
    private static final TokenType type = TokenType.OPERATOR;

    public final Operators value;

    public OperatorToken(Operators value) {
        this.value = value;
    }

    @Override
    public TokenType type() {
        return type;
    }

    @Override
    public String toString() {
        return "OperatorToken{" +
                "value=" + value.representation() +
                '}';
    }
}
