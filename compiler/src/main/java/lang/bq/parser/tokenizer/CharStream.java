package lang.bq.parser.tokenizer;

public class CharStream{
    private final String text;

    private int position, column;
    private int line = 1;

    public CharStream(String text){
        this.text = text;
    }

    public char skip() {
        char ch = this.text.charAt(this.position++);
        if (ch == '\n') {
            this.line++;
            this.column = 0;
        } else {
            this.column++;
        }

        return ch;
    }

    public void skip(int length) {
        for (int i = 0; i < length; i++) {
            if (text.charAt(this.position + i) == '\n') {
                this.column = 0;
                this.line++;
            } else {
                column++;
            }

            this.position++;
        }
    }

    public char peek() {
        return this.text.charAt(this.position);
    }

    public String peek(int length) {
        return this.text.substring(position, Math.min(this.position + length, this.text.length()));
    }
    public boolean isNext(String value){
        for (int i = 0; i < value.length(); i++) {
            if (this.text.length() <= position + i || this.text.charAt(this.position + i) != value.charAt(i))
                return false;
        }

        return true;
    }

    public boolean eof() {
        return this.text.length() <= this.position;
    }

    public String currentLine(){
        return this.text.split("\n")[this.line - 1];
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
}
