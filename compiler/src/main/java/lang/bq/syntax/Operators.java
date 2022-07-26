package lang.bq.syntax;

public enum Operators {
    ADD("+", 4),
    SUBTRACT("-", 4),
    MULTIPLY("*", 3),
    DIVIDE("/", 3),
    POWER("**", 2),
    MOD("%", 3),

    EQUAL("==", 7),
    NOT_EQUAL("!=", 7),
    GREATER(">", 6),
    LESS("<", 6),
    GREATER_EQUAL(">=", 6),
    LESS_EQUAL("<=", 6),
    AND("&&", 11),
    OR("||", 12),
    NOT("!", 2),

    FETCH("->", 15),
    LAMBDA("=>", 13),
    INLINE_IF("?", 13),
    INLINE_ELSE(":", 13),

    XOR("^", 9),
    BITWISE_RIGHT(">>", 5),
    BITWISE_LEFT("<<", 5),
    BITWISE_AND("&", 8),
    BITWISE_OR("|", 10),
    BITWISE_NOT("~", 8),

    ASSIGN("=", 14),
    INCREMENT("++", 1),
    DECREMENT("--", 1),
    ADD_ASSIGN("+=", 14),
    SUB_ASSIGN("-=", 14),
    MULTI_ASSIGN("*=", 14),
    DIV_ASSIGN("/=", 14),
    MOD_ASSIGN("%=", 14),
    AND_ASSIGN("&=", 14),
    OR_ASSIGN("|=", 14);

    public static final short MAX_OPERATOR_LENGTH = 2;

    public static Operators of(String operator){
        if(operator.length() > MAX_OPERATOR_LENGTH) return null;

        for(Operators op : Operators.values())
            if (op.representation().equals(operator)) return op;

        return null;
    }


    private final String representation;
    private final int priority;

    Operators(String representation, int priority) {
        this.representation = representation;
        this.priority = priority;
    }

    public int priority(){ return this.priority; }
    public String representation(){ return this.representation; }

    public boolean equals(String representation){
        return this.representation.equals(representation);
    }
}
