package lang.bq.syntax;

import java.util.Arrays;
import java.util.HashSet;

public enum Keywords {
    TRUE("true"),
    FALSE("false"),

    IF("if"),
    SWITCH("switch"),
    FOR("for"),
    WHILE("while"),

    STRUCT("struct"),

    PUBLIC("public"),
    PRIVATE("private"),
    PROTECTED("protected"),

    NEW("new"),
    NODE("node"),
    SENDER("sender"),
    FEEDBACK("feedback");

    public static Keywords of(String value){
        for(Keywords keyword : Keywords.values())
            if (keyword.representation().equals(value)) return keyword;

        return null;
    }
    public final String representation;

    Keywords(String representation) {
        this.representation = representation;
    }

    public String representation(){
        return this.representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}
