package lang.bq.parser.modules;

import lang.bq.parser.Context;
import lang.bq.parser.ModuleAccessor;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;

public class ForkModule implements ParserModule{
    private final TokenType trigger;
    private Context context;

    private final ForkedModule[] modules;

    public ForkModule(TokenType trigger, Context context, ForkedModule... modules) {
        this.trigger = trigger;
        this.context = context;
        this.modules = modules;
    }

    @Override
    public boolean isNext(Token token, Context context) {
        return this.context == context && this.trigger == token.type();
    }

    @Override
    public Token parse(Tokenizer tokenizer, ModuleAccessor accessor) {
        Token previous = tokenizer.next();

        for (ForkedModule module : this.modules) {
            if (module.isNextForked(tokenizer.peek(), context)) {
                module.setPrevious(previous);
                Token result = module.parse(tokenizer, accessor);
                if(module.nextContext() != null) this.context = module.nextContext();

                return result;
            }
        }

        return null;
    }

    @Override
    public Context nextContext() {
        return context;
    }
}
