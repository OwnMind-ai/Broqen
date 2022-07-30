package lang.bq.parser.modules;

import lang.bq.parser.tokenizer.CharStream;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.parser.tokens.highlevel.ArgumentToken;
import lang.bq.parser.tokens.highlevel.FunctionToken;
import lang.bq.parser.tokens.highlevel.ScriptToken;
import lang.bq.parser.tokens.lowlevel.PrimitiveToken;
import lang.bq.parser.tokens.lowlevel.StringToken;
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

        assertEquals(
                new FunctionToken(
                        new PrimitiveToken(Primitives.VOID),
                        "main",
                        new ArgumentToken[]{
                                new ArgumentToken(new PrimitiveToken(Primitives.INT), "a"),
                                new ArgumentToken(new StringToken(TokenType.IDENTIFIER, "Object"), "b")
                        },
                        new ScriptToken(new Token[0])
                ),
                module.parse(tokenizer, c -> tokenizer.next())
        );
    }
}