package lang.bq.parser.modules;

import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;

public interface ParserModule {
    boolean isNext(Token token);
    Token parse(Tokenizer tokenizer);
}
