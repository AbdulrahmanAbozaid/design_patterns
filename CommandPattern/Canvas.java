
import java.util.Arrays;

// The RECEIVER: it knows how to change the picture, and nothing about commands.
// Everything is written for you except snapshot() and restore().
public class Canvas {

    private final PaintColor[][] grid;
    private final int size;

    public Canvas(int size) {
        this.size = size;
        this.grid = new PaintColor[size][size];
        clear();
    }

    public int getSize() {
        return size;
    }

    // x is the column, y is the row
    public PaintColor getColor(int x, int y) {
        return grid[y][x];
    }

    public void setColor(int x, int y, PaintColor color) {
        grid[y][x] = color;
    }

    public void clear() {
        for (PaintColor[] row : grid) {
            Arrays.fill(row, PaintColor.EMPTY);
        }
    }

    public void print() {
        for (int y = 0; y < size; y++) {
            StringBuilder line = new StringBuilder();
            for (int x = 0; x < size; x++) {
                line.append(grid[y][x].getSymbol());
            }
            System.out.println(line);
        }
    }

    // Return an INDEPENDENT copy of the whole grid.
    // Changing the canvas afterwards must not change the copy.
    public PaintColor[][] snapshot() {
        PaintColor[][] shot = new PaintColor[size][];
        for (int i = 0; i < size; i++) {
            shot[i] = grid[i].clone();
        }
        return shot;
    }

    // Put a saved grid back onto the canvas.
    public void restore(PaintColor[][] saved) {
        for (int i = 0; i < size; i++) {
            grid[i] = saved[i].clone();
        }
    }
}
