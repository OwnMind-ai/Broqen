package lang.bq.parser.tokens.highlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.syntax.Keywords;

import java.util.Arrays;
import java.util.Objects;

public class FunctionToken implements Token{
    public final Keywords[] parameters;
    public final TypeToken returnType;
    public final String name;
    public final ArgumentToken[] structure;

    public final ScriptToken body;

    public FunctionToken(Keywords[] parameters, TypeToken returnType, String name, ArgumentToken[] structure, ScriptToken body) {
        this.parameters = parameters;
        this.returnType = returnType;
        this.name = name;
        this.structure = structure;
        this.body = body;
    }

    @Override
    public TokenType type() {
        return TokenType.FUNCTION;
    }

    @Override
    public String toString() {
        return "FunctionToken(" +
                returnType +
                ", " + name +
                ", " + Arrays.toString(structure) +
                ", " + body +
                ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FunctionToken that = (FunctionToken) o;
        return Objects.equals(returnType, that.returnType) && Objects.equals(name, that.name) && Arrays.equals(structure, that.structure) && Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(returnType, name, body);
        result = 31 * result + Arrays.hashCode(structure);
        return result;
    }
}
