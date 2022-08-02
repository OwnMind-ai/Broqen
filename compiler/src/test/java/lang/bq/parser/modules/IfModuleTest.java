package lang.bq.parser.modules;

import lang.bq.parser.tokenizer.CharStream;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.highlevel.ExpressionToken;
import lang.bq.parser.tokens.highlevel.IfToken;
import lang.bq.parser.tokens.highlevel.ScriptToken;
import lang.bq.parser.tokens.lowlevel.NumberToken;
import lang.bq.syntax.Operators;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IfModuleTest {
    @Test
    void parse() {
        Tokenizer tokenizer = new Tokenizer(new CharStream(
                "if(1 == 2) { 1; } else { 2; }"
        ));
        IfModule module = new IfModule();

        tokenizer.next();
        assertEquals(
                new IfToken(
                        new ExpressionToken(Operators.EQUAL, new NumberToken(1), new NumberToken(2)),
                        new ScriptToken(new Token[]{new NumberToken(1)}),
                        new ScriptToken(new Token[]{new NumberToken(2)})
                ),
                module.parse(tokenizer, c -> tokenizer.next())
        );
    }

    @Test
    void shortIf() {
        Tokenizer tokenizer = new Tokenizer(new CharStream(
                "if(1 == 2) 1;"
        ));
        IfModule module = new IfModule();

        tokenizer.next();
        assertEquals(
                new IfToken(
                        new ExpressionToken(Operators.EQUAL, new NumberToken(1), new NumberToken(2)),
                        new ScriptToken(new Token[]{new NumberToken(1)}),
                        null
                ),
                module.parse(tokenizer, c -> tokenizer.next())
        );
    }
}