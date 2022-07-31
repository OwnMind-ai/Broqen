package lang.bq.parser.modules;

import lang.bq.parser.tokenizer.CharStream;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.highlevel.ArgumentToken;
import lang.bq.parser.tokens.highlevel.FunctionToken;
import lang.bq.parser.tokens.highlevel.ScriptToken;
import lang.bq.parser.tokens.highlevel.TypeToken;
import lang.bq.syntax.Keywords;
import lang.bq.syntax.Primitives;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionModuleTest {

    @Test
    void parse() {
        Tokenizer tokenizer = new Tokenizer(new CharStream(
                "void main(int a, Object b){}"
        ));
        tokenizer.next();

        FunctionModule module = new FunctionModule();
        module.setParameters(new Keywords[0]);

        assertEquals(
                new FunctionToken(
                        new Keywords[0], new TypeToken(Primitives.VOID),
                        "main",
                        new ArgumentToken[]{
                                new ArgumentToken(new TypeToken(Primitives.INT), "a"),
                                new ArgumentToken(new TypeToken("Object"), "b")
                        },
                        new ScriptToken(new Token[0])
                ),
                module.parse(tokenizer, c -> tokenizer.next())
        );
    }
}