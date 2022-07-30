package lang.bq.parser.modules;

import lang.bq.parser.tokenizer.CharStream;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.highlevel.ScriptToken;
import lang.bq.parser.tokens.lowlevel.NumberToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScriptModuleTest {

    @Test
    void parse() {
        Tokenizer tokenizer = new Tokenizer(new CharStream(
                "{ 1; 2; 3; }"
        ));
        ScriptModule module = new ScriptModule();

        tokenizer.next();
        assertEquals(
                new ScriptToken(new Token[]{
                    new NumberToken(1),
                    new NumberToken(2),
                    new NumberToken(3)
                }),
                module.parse(tokenizer, c -> tokenizer.next())
        );
    }
}