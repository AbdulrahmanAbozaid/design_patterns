
public class PaintCommand implements Command {

    private final Canvas canvas;
    private final int x, y;
    private final PaintColor color;
    private PaintColor previous;

    public PaintCommand(Canvas canvas, int x, int y, PaintColor color) {
        this.canvas = canvas;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public void execute() {
        this.previous = canvas.getColor(x, y);
        this.canvas.setColor(x, y, color);
    }

    @Override
    public void undo() {
        this.canvas.setColor(x, y, previous);
    }

    @Override
    public String getName() {
        return "Paint (" + x + "," + y + ") " + color;
    }
}
