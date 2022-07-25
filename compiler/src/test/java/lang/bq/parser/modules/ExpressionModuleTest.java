package lang.bq.parser.modules;

import lang.bq.parser.CharStream;
import lang.bq.parser.Context;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.parser.tokens.highlevel.ExpressionToken;
import lang.bq.parser.tokens.lowlevel.NumberToken;
import lang.bq.parser.tokens.lowlevel.StringToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExpressionModuleTest {
    private static final ExpressionToken answer = new ExpressionToken(
            "+",
            new NumberToken(1),
            new ExpressionToken(
                    "*",
                    new ExpressionToken(
                            "+",
                            new NumberToken(2),
                            new ExpressionToken("-", new NumberToken(1), new NumberToken(3))
                    ),
                    new ExpressionToken("-",
                            new ExpressionToken("-", new NumberToken(1),
                                    new ExpressionToken("/", new NumberToken(2), new NumberToken(3))
                            ),
                            new NumberToken(1)
                    )
            )
    );

    private final Tokenizer tokenizer = new Tokenizer(new CharStream("1 + (2 + (1 - 3)) * (1 - 2 / 3 - 1)"));
    private final ExpressionModule module = new ExpressionModule();

    @Test
    void parse() {
        assertTrue(module.isNext(new StringToken(TokenType.PUNCTUATION,"("), Context.FUNCTION));
        tokenizer.next();
        assertEquals(answer, module.parse(tokenizer, this::parseToken));
    }

    private Token parseToken(){
        if(tokenizer.peek() != null && tokenizer.peek().type() == TokenType.PUNCTUATION &&
                ((StringToken) tokenizer.peek()).value.equals("(")){
            tokenizer.next();
            return module.parse(tokenizer, this::parseToken);
        }

        return tokenizer.next();
    }
}