
public enum PaintColor {
    EMPTY('.', 14, 14, 14, 255),
    RED('R', 220, 60, 48, 255),
    GREEN('G', 83, 229, 56, 255),
    BLUE('B', 71, 112, 229, 255);

    private final char symbol;
    private final int red;
    private final int green;
    private final int blue;
    private final int alpha;

    PaintColor(char symbol, int red, int green, int blue, int alpha) {
        this.symbol = symbol;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getR() {
        return red;
    }

    public int getG() {
        return green;
    }

    public int getB() {
        return blue;
    }

    public int getA() {
        return alpha;
    }
}
