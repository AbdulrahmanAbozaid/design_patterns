
import java.util.ArrayList;
import java.util.List;

public class MacroCommand implements Command {

    private final List<Command> commands;

    public MacroCommand() {
        this.commands = new ArrayList<>();
    }

    public void add(Command command) {
        commands.add(command);
    }

    @Override
    public void execute() {
        for (Command cmd : commands) {
            cmd.execute();
        }
    }

    @Override
    public void undo() {
        for (Command cmd : commands.reversed()) {
            cmd.undo();
        }
    }

    @Override
    public String getName() {
        return "Macro of " + commands.size() + " commands";
    }
}
