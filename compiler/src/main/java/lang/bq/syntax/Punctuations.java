package lang.bq.syntax;

public enum Punctuations {
    DOT("."),
    COMMA(","),
    COLON(":"),
    SEMICOLON(";"),
    PARENTHESES_START("("),
    PARENTHESES_END(")"),
    SQUARE_START("["),
    SQUARE_END("]"),
    CURLY_START("{"),
    CURLY_END("}");

    private final String representation;

    Punctuations(String representation) {
        this.representation = representation;
    }

    public static final String chars = ".,:;(){}[]";

    public static Punctuations of(String operator){
        for(Punctuations punc : Punctuations.values())
            if (punc.representation().equals(operator)) return punc;

        return null;
    }

    public String representation(){ return this.representation; }

    public boolean equals(String representation){
        return this.representation.equals(representation);
    }
}
