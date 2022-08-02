package lang.bq.parser;

import lang.bq.parser.modules.ParserModule;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;

public class Parser {
    private static final ParserModule[] modules = {
            /* ...modules... */
    };
    private final Tokenizer tokenizer;

    public Parser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
        this.tokenizer.next();
    }

    private Token parseToken(Context context){
        Token token = this.tokenizer.next();

        for (ParserModule module : modules)
            if (module.isNext(token, context))
                return module.parse(this.tokenizer, this::parseToken);

        if(token.type() == TokenType.IDENTIFIER ||
           token.type() == TokenType.NUMBER     ||
           token.type() == TokenType.STRING
        ) {
            return token;
        }

        this.tokenizer.throwException("Invalid syntax");
        return null;
    }
}