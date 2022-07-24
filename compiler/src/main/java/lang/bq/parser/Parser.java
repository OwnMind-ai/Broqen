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
    private Context context = Context.GLOBAL;

    public Parser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    private Token parseToken(){
        Token token = this.tokenizer.next();

        for (ParserModule module : modules) {
            if (module.isNext(token, context)) {
                Token result = module.parse(this.tokenizer, this::parseToken);
                if(module.nextContext() != null) this.context = module.nextContext();

                return result;
            }
        }

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