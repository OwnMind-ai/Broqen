package lang.bq.messages;

public class ExceptionMessage {
    private static final String positionFormat = "(%d:%d)";
    private static final char pointer = '^';
    private static final short visibleRegionRange = 5;

    public final String message;
    public final String region;
    public final int line;
    public final int column;

    public ExceptionMessage(String message, String region, int line, int column) {
        this.message = message;
        this.region = region;
        this.line = line;
        this.column = column;
    }

    public void throwMessage(){
        System.out.println("\u001B[31m" + this + "\u001B[0m");
        System.exit(1);
    }

    @Override
    public String toString() {
        return this.message + " " + String.format(positionFormat, line, column) + "\n" + this.getErrorPointer();
    }

    private String getErrorPointer(){
        int start = Math.max(this.column - visibleRegionRange, 0);
        int end = Math.min(start + visibleRegionRange * 2, this.region.length());
        int pointer = this.column - start;
        String errorRegion = this.region.substring(start, end);

        return  "\t" + errorRegion + "\n\t" +
                new String(new char[pointer - 1]).replace('\0', ' ') +  // Equals to String::repeat in Java 11+
                ExceptionMessage.pointer +
                new String(new char[end - this.column]).replace('\0', ' ');
    }
}
