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

import static lang.bq.syntax.Operators.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExpressionModuleTest {
    private static final ExpressionToken answer = new ExpressionToken(
            of("+"),
            new NumberToken(0),
            new ExpressionToken(
                    of("*"),
                    new ExpressionToken(
                            of("+"),
                            new NumberToken(1),
                            new ExpressionToken(of("-"), new NumberToken(2), new NumberToken(3))
                    ),
                    new ExpressionToken(of("-"),
                            new ExpressionToken(of("-"), new NumberToken(4),
                                    new ExpressionToken(of("/"), new NumberToken(5), new NumberToken(6))
                            ),
                            new NumberToken(7)
                    )
            )
    );

    private final Tokenizer tokenizer = new Tokenizer(new CharStream("0 + (1 + (2 - 3)) * (4 - 5 / 6 - 7)"));
    private final ExpressionModule module = new ExpressionModule();

    @Test
    void parse() {
        assertTrue(module.isNext(new StringToken(TokenType.PUNCTUATION,"("), Context.FUNCTION));
        tokenizer.next();
        assertEquals(answer, module.parse(tokenizer, this::parseToken));
    }

    private Token parseToken(Context context){
        if(tokenizer.peek() != null && tokenizer.peek().type() == TokenType.PUNCTUATION &&
                ((StringToken) tokenizer.peek()).value.equals("(")){
            tokenizer.next();
            return module.parse(tokenizer, this::parseToken);
        }

        return tokenizer.next();
    }
}