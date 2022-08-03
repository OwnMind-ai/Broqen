package lang.bq.parser.modules;

import lang.bq.parser.Context;
import lang.bq.parser.ModuleAccessor;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.parser.tokens.highlevel.InitializationToken;
import lang.bq.parser.tokens.highlevel.TypeToken;
import lang.bq.parser.tokens.lowlevel.IdentifierToken;
import lang.bq.syntax.Operators;

public class InitializationModule implements ForkedModule{
    private Token type;
    @Override
    public void setPrevious(Token token) {
        this.type = token;
    }

    @Override
    public boolean isNextForked(Token token, Context context) {
        return token.type() == TokenType.IDENTIFIER;
    }

    @Override
    public boolean isNext(Token token, Context context) {
        return token.type() == TokenType.PRIMITIVE && context == Context.FUNCTION;
    }

    @Override
    public Token parse(Tokenizer tokenizer, ModuleAccessor accessor) {
        if(this.type == null) this.type = tokenizer.next();   // Reads primitive type

        assert tokenizer.peek().type() == TokenType.IDENTIFIER;
        String name = ((IdentifierToken) tokenizer.next()).value;
        Token initialValue = null;

        if(tokenizer.peek().is(TokenType.OPERATOR, Operators.ASSIGN)){
            tokenizer.next();
            initialValue = accessor.parse(Context.EXPRESSION);
        }

        this.type = null;

        return new InitializationToken(TypeToken.of(this.type), name, initialValue);
    }

    @Override
    public Context nextContext() {
        return null;
    }
}
