package lang.bq.parser.modules;

import lang.bq.parser.tokenizer.CharStream;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.highlevel.ObjectInitializationToken;
import lang.bq.parser.tokens.highlevel.PrimitiveInitializationToken;
import lang.bq.parser.tokens.lowlevel.NumberToken;
import lang.bq.syntax.Primitives;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InitializationModuleTest {

    @Test
    void primitive() {
        Tokenizer tokenizer = new Tokenizer(new CharStream("int name = 12"));
        tokenizer.next();

        InitializationModule module = new InitializationModule();
        
        assertEquals(new PrimitiveInitializationToken(Primitives.INT, "name", new NumberToken(12)),
                module.parse(tokenizer, c -> tokenizer.next()));
    }

    @Test
    void object() {
        Tokenizer tokenizer = new Tokenizer(new CharStream("Object name"));

        InitializationModule module = new InitializationModule();

        tokenizer.next();
        module.setPrevious(tokenizer.next());

        assertEquals(new ObjectInitializationToken("Object", "name", null),
                module.parse(tokenizer, c -> tokenizer.next()));
    }
}