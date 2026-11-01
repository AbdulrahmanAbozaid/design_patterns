
import java.util.ArrayDeque;
import java.util.Deque;

public class CommandHistory {

    private final Deque<Command> undoStack;
    private final Deque<Command> redoStack;

    public CommandHistory() {
        this.undoStack = new ArrayDeque<>();
        this.redoStack = new ArrayDeque<>();
    }

    public void run(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
        System.out.println("Executed: " + command.getName());
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo");
            return;
        }
        Command ucmd = undoStack.pop();
        ucmd.undo();
        redoStack.push(ucmd);
        System.out.println("Undone: " + ucmd.getName());
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Nothing to redo");
            return;
        }
        Command ucmd = redoStack.pop();
        ucmd.execute();
        undoStack.push(ucmd);
        System.out.println("Redone: " + ucmd.getName());
    }

    public boolean canUndo() {
        return !this.undoStack.isEmpty();
    }

    public boolean canRedo() {
        return !this.redoStack.isEmpty();
    }
}
