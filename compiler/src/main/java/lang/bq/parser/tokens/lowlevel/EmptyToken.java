package lang.bq.parser.tokens.lowlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;

public class EmptyToken implements Token {
    @Override
    public TokenType type() {
        return TokenType.EMPTY;
    }

    @Override
    public String toString() {
        return "EMPTY";
    }

    @Override
    public boolean equals(Object o) {
        return this == o || this.getClass() == o.getClass();
    }
}
