package lang.bq.syntax;

public enum Primitives {
    VOID("void"),
    BOOLEAN("bool"),
    CHAR("char"),
    SHORT("short"),
    INT("int"),
    LONG("long"),
    FLOAT("float"),
    DOUBLE("double"),

    STRING("string");

    public static Primitives of(String operator){
        for(Primitives primitive : Primitives.values())
            if (primitive.representation().equals(operator)) return primitive;

        return null;
    }


    private final String representation;

    Primitives(String representation) {
        this.representation = representation;
    }

    public String representation(){ return this.representation; }

    public boolean equals(String representation){
        return this.representation.equals(representation);
    }
}
