package lang.bq.parser.modules;

import lang.bq.parser.tokenizer.CharStream;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.parser.tokens.highlevel.CallToken;
import lang.bq.parser.tokens.lowlevel.EmptyToken;
import lang.bq.parser.tokens.lowlevel.NumberToken;
import lang.bq.parser.tokens.lowlevel.StringToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CallModuleTest {

    @Test
    void parse() {
        Tokenizer tokenizer = new Tokenizer(new CharStream("(1, 2, 3)"));
        tokenizer.next();

        ForkedModule call = new CallModule();

        assertThrows(Error.class, () -> call.setPrevious(new EmptyToken()));

        call.setPrevious(new StringToken(TokenType.IDENTIFIER, "function"));
        Token result = call.parse(tokenizer, (c) -> tokenizer.next());

        assertEquals(
                new CallToken(
                        "function",
                        new Token[]{new NumberToken(1), new NumberToken(2),  new NumberToken(3)}
                ),
                result
        );
    }
}