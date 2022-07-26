package lang.bq.parser.modules;

import lang.bq.parser.CharStream;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.TokenType;
import lang.bq.parser.tokens.highlevel.ExpressionToken;
import lang.bq.parser.tokens.lowlevel.NumberToken;
import lang.bq.parser.tokens.lowlevel.StringToken;
import lang.bq.syntax.Operators;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SignModuleTest {

    @Test
    void parse() {
        final Operators minus = Operators.of("-");
        SignModule module = new SignModule();
        Tokenizer number = new Tokenizer(new CharStream("-1"));
        number.next();

        Tokenizer bracket = new Tokenizer(new CharStream("-("));
        bracket.next();

        assertEquals(new ExpressionToken(minus, new NumberToken(0), new NumberToken(1)),
                module.parse(number, c -> number.next()));

        assertEquals(new ExpressionToken(minus, new NumberToken(0), new StringToken(TokenType.PUNCTUATION, "(")),
                module.parse(bracket, c -> bracket.next()));
    }
}