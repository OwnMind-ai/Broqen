package lang.bq.parser.modules;

import lang.bq.parser.Context;
import lang.bq.parser.ModuleAccessor;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.parser.tokens.highlevel.CallToken;
import lang.bq.parser.tokens.lowlevel.IdentifierToken;
import lang.bq.parser.tokens.lowlevel.StringToken;
import lang.bq.syntax.DelimiterFlags;
import lang.bq.syntax.Punctuations;

public class CallModule implements ForkedModule, Delimiter<Token> {
    private String name;
    @Override
    public boolean isNextForked(Token token, Context context) {
        return (context == Context.FUNCTION || context == Context.EXPRESSION)
                && token.is(TokenType.PUNCTUATION, Punctuations.PARENTHESES_START);
    }

    @Override
    public Token parse(Tokenizer tokenizer, ModuleAccessor accessor) {
        assert name != null;
        return new CallToken(
                name,
                this.delimited(tokenizer, () -> accessor.parse(Context.EXPRESSION)).toArray(new Token[0])
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
        this.name = ((IdentifierToken) token).value;
    }

    @Override
    public boolean isNext(Token token, Context context) {
        return false;
    }
}
