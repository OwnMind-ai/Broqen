package lang.bq.parser.modules;

import lang.bq.parser.Context;
import lang.bq.parser.ModuleAccessor;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.parser.tokens.highlevel.ScriptToken;
import lang.bq.syntax.DelimiterFlags;
import lang.bq.syntax.Punctuations;

public class ScriptModule implements ParserModule, Delimiter<Token>{
    private static final ExpressionModule expressionModule = new ExpressionModule();

    @Override
    public boolean isNext(Token token, Context context) {
        return (context == Context.GLOBAL || context == Context.NODE) &&
                token.is(TokenType.PUNCTUATION, Punctuations.CURLY_START);
    }

    @Override
    public Token parse(Tokenizer tokenizer, ModuleAccessor accessor) {
        Token[] instructions = this.delimited(
                tokenizer,
                () -> expressionModule.parseInstruction(tokenizer, accessor)
        ).toArray(new Token[0]);

        return new ScriptToken(instructions);
    }

    @Override
    public Context nextContext() {
        return null;
    }

    @Override
    public Flags flags() {
        return DelimiterFlags.CODE_BLOCK.flags();
    }
}
