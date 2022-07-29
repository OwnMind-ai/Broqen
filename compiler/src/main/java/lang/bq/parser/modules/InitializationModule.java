package lang.bq.parser.modules;

import lang.bq.parser.Context;
import lang.bq.parser.ModuleAccessor;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.parser.tokens.highlevel.ObjectInitializationToken;
import lang.bq.parser.tokens.highlevel.PrimitiveInitializationToken;
import lang.bq.parser.tokens.lowlevel.OperatorToken;
import lang.bq.parser.tokens.lowlevel.PrimitiveToken;
import lang.bq.parser.tokens.lowlevel.StringToken;
import lang.bq.syntax.Operators;
import lang.bq.syntax.Primitives;

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
        //TODO: Looks like unsafe code
        if(this.type == null) this.type = tokenizer.next();   // Reads primitive type

        assert tokenizer.peek().type() == TokenType.IDENTIFIER;
        String name = ((StringToken) tokenizer.next()).value;
        Token initialValue = null;

        if(tokenizer.peek().type() == TokenType.OPERATOR &&
                ((OperatorToken) tokenizer.peek()).value == Operators.ASSIGN){
            tokenizer.next();
            initialValue = accessor.parse(Context.EXPRESSION);
        }

        //TODO: Double checking, need to be fixed
        if(this.type.type() == TokenType.PRIMITIVE){
            Primitives primitive = ((PrimitiveToken) this.type).primitive;
            return new PrimitiveInitializationToken(primitive, name, initialValue);
        } else {
            String object = ((StringToken) this.type).value;
            return new ObjectInitializationToken(object, name, initialValue);
        }
    }

    @Override
    public Context nextContext() {
        return null;
    }
}
