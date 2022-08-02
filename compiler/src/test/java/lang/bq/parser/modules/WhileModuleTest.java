package lang.bq.parser.modules;

import lang.bq.parser.tokenizer.CharStream;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.highlevel.ExpressionToken;
import lang.bq.parser.tokens.highlevel.ScriptToken;
import lang.bq.parser.tokens.highlevel.WhileToken;
import lang.bq.parser.tokens.lowlevel.NumberToken;
import lang.bq.syntax.Operators;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WhileModuleTest {

    @Test
    void parse() {
        Tokenizer tokenizer = new Tokenizer(new CharStream("while(1 > 2){ 1; }"));
        WhileModule module = new WhileModule();

        tokenizer.next();
        assertEquals(
                new WhileToken(
                        new ExpressionToken(Operators.GREATER, new NumberToken(1), new NumberToken(2)),
                        new ScriptToken(new Token[]{new NumberToken(1)})
                ),
                module.parse(tokenizer, c -> tokenizer.next())
        );
    }
}