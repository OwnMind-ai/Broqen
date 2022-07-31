package lang.bq.parser.modules;

import lang.bq.parser.Context;
import lang.bq.parser.ModuleAccessor;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.parser.tokens.highlevel.ArgumentToken;
import lang.bq.parser.tokens.highlevel.FunctionToken;
import lang.bq.parser.tokens.highlevel.ScriptToken;
import lang.bq.parser.tokens.lowlevel.IdentifierToken;
import lang.bq.parser.tokens.lowlevel.StringToken;
import lang.bq.syntax.DelimiterFlags;

//TODO: Make it parametrized
public class FunctionModule implements ParserModule, Delimiter<ArgumentToken>  {
    private static final ScriptModule scriptModule = new ScriptModule();

    @Override
    public boolean isNext(Token token, Context context) {
        return (context == Context.GLOBAL || context == Context.NODE) &&
                token.anyType(TokenType.PRIMITIVE, TokenType.IDENTIFIER);
    }

    @Override
    public Token parse(Tokenizer tokenizer, ModuleAccessor accessor) {
        Token returnType = tokenizer.next();

        assert tokenizer.peek().type() == TokenType.IDENTIFIER;
        String name = ((IdentifierToken) tokenizer.next()).value;

        ArgumentToken[] arguments = this.delimited(
                tokenizer, () -> this.parseArgument(tokenizer)
        ).toArray(new ArgumentToken[0]);

        ScriptToken script = (ScriptToken) scriptModule.parse(tokenizer, accessor);

        return new FunctionToken(returnType, name, arguments, script);
    }

    private ArgumentToken parseArgument(Tokenizer tokenizer){
        // TODO: Change to TypeToken
        assert tokenizer.peek().anyType(TokenType.IDENTIFIER, TokenType.PRIMITIVE);
        Token type = tokenizer.next();

        assert tokenizer.peek().type() == TokenType.IDENTIFIER;
        String name = ((IdentifierToken) tokenizer.next()).value;

        return new ArgumentToken(type, name);
    }

    @Override
    public Context nextContext() {
        return null;
    }

    @Override
    public Flags flags() {
        return DelimiterFlags.FUNCTION_ARGS.flags();
    }
}
