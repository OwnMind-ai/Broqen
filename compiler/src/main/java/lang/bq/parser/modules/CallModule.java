package lang.bq.parser.modules;

import lang.bq.parser.Context;
import lang.bq.parser.ModuleAccessor;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.parser.tokens.highlevel.CallToken;
import lang.bq.parser.tokens.lowlevel.PunctuationToken;
import lang.bq.parser.tokens.lowlevel.StringToken;
import lang.bq.syntax.DelimiterFlags;
import lang.bq.syntax.Punctuations;

public class CallModule implements ForkedModule, Delimiter {
    private String name;
    @Override
    public boolean isNext(Token token, Context context) {
        return (context == Context.FUNCTION || context == Context.EXPRESSION)
                && token.equals(new PunctuationToken(Punctuations.PARENTHESES_START));
    }

    @Override
    public Token parse(Tokenizer tokenizer, ModuleAccessor accessor) {
        assert name != null;
        return new CallToken(
                name,
                this.delimited(tokenizer, () -> accessor.parse(Context.EXPRESSION))
        );
    }

    @Override
    public Context nextContext() {
        return null;
    }

    @Override
    public Flags flags() {
        return DelimiterFlags.CALL.flags();
    }

    @Override
    public void setPrevious(Token token) {
        assert token.type() == TokenType.IDENTIFIER;
        this.name = ((StringToken) token).value;
    }
}
