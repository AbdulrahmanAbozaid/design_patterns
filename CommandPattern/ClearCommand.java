
public class ClearCommand implements Command {

    private final Canvas canvas;
    private PaintColor[][] saved;

    public ClearCommand(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void execute() {
        this.saved = canvas.snapshot();
        canvas.clear();
    }

    @Override
    public void undo() {
        canvas.restore(saved);
    }

    @Override
    public String getName() {
        return "Clear canvas";
    }
}
