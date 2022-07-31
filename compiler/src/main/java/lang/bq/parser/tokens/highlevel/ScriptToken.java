package lang.bq.parser.tokens.highlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;

import java.util.Arrays;

public class ScriptToken implements Token {
    public final Token[] instructions;

    public ScriptToken(Token[] instructions) {
        this.instructions = instructions;
    }

    @Override
    public TokenType type() {
        return TokenType.SCRIPT;
    }

    @Override
    public String toString() {
        return "ScriptToken{" +
                "instructions=" + Arrays.toString(instructions) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScriptToken that = (ScriptToken) o;
        return Arrays.equals(instructions, that.instructions);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(instructions);
    }
}
