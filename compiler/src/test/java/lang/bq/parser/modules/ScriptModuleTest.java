package lang.bq.parser.modules;

import lang.bq.parser.tokenizer.CharStream;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.highlevel.ExpressionToken;
import lang.bq.parser.tokens.highlevel.ScriptToken;
import lang.bq.parser.tokens.lowlevel.IdentifierToken;
import lang.bq.parser.tokens.lowlevel.NumberToken;
import lang.bq.syntax.Operators;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScriptModuleTest {

    @Test
    void parse() {
        Tokenizer tokenizer = new Tokenizer(new CharStream(
                "{ a = 2; 2; 3; }"
        ));
        ScriptModule module = new ScriptModule();

        tokenizer.next();
        assertEquals(
                new ScriptToken(new Token[]{
                    new ExpressionToken(Operators.ASSIGN, new IdentifierToken("a"), new NumberToken(2)),
                    new NumberToken(2),
                    new NumberToken(3)
                }),
                module.parse(tokenizer, c -> tokenizer.next())
        );
    }
}