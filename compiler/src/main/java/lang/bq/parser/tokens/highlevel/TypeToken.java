package lang.bq.parser.tokens.highlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.parser.tokens.lowlevel.IdentifierToken;
import lang.bq.parser.tokens.lowlevel.PrimitiveToken;
import lang.bq.syntax.Primitives;

import java.util.Objects;

public class TypeToken implements Token {
    public final String identifier;
    public final Primitives primitive;

    public static TypeToken of(Token token){
        switch (token.type()){
            case IDENTIFIER: return new TypeToken(((IdentifierToken) token).value);
            case PRIMITIVE: return new TypeToken(((PrimitiveToken) token).primitive);
            default: return null;
        }
    }

    public TypeToken(String identifier) {
        this.identifier = identifier;
        this.primitive = null;
    }

    public TypeToken(Primitives primitive) {
        this.identifier = null;
        this.primitive = primitive;
    }

    public boolean isPrimitive(){
        return this.primitive != null;
    }

    public boolean isObject(){
        return this.identifier != null;
    }

    @Override
    public String toString() {
        return "TypeToken{" +
                "identifier='" + identifier + '\'' +
                ", primitive=" + primitive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeToken typeToken = (TypeToken) o;
        return Objects.equals(identifier, typeToken.identifier) && primitive == typeToken.primitive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, primitive);
    }

    @Override
    public TokenType type() {
        return TokenType.TYPE;
    }
}
