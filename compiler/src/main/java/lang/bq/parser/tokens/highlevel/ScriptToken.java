package lang.bq.parser.tokens.highlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;

import java.util.Arrays;

public class ScriptToken implements Token {
    private static final TokenType type = TokenType.SCRIPT;

    public final Token[] instructions;

    public ScriptToken(Token[] instructions) {
        this.instructions = instructions;
    }

    @Override
    public TokenType type() {
        return type;
    }

    @Override
    public String toString() {
        return "ScriptToken{" +
                "instructions=" + Arrays.toString(instructions) +
                '}';
    }
}
