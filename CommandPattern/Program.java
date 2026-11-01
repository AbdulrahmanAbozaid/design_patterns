
public class Program {

    public static void main(String[] args) {
        Canvas canvas = new Canvas(4);
        CommandHistory history = new CommandHistory();

        // Three single paints
        history.run(new PaintCommand(canvas, 0, 0, PaintColor.RED));
        history.run(new PaintCommand(canvas, 1, 1, PaintColor.GREEN));
        history.run(new PaintCommand(canvas, 2, 2, PaintColor.BLUE));
        show("After 3 paints", canvas);

        // Undo twice, then redo once
        history.undo();
        history.undo();
        show("After 2 undos", canvas);

        history.redo();
        show("After 1 redo", canvas);

        // A brand new command wipes out whatever was left to redo
        history.run(new PaintCommand(canvas, 3, 3, PaintColor.RED));
        history.redo();
        show("After new paint and a useless redo", canvas);

        // A macro: several paints that undo as ONE step
        MacroCommand fancyRow = new MacroCommand();
        for (int x = 0; x < canvas.getSize(); x++) {
            fancyRow.add(new PaintCommand(canvas, x, 0, PaintColor.BLUE));
        }
        fancyRow.add(new PaintCommand(canvas, 0, 0, PaintColor.GREEN));
        history.run(fancyRow);
        show("After macro", canvas);

        history.undo();
        show("After undoing the macro", canvas);

        // Clear everything, then bring it back
        history.run(new ClearCommand(canvas));
        show("After clear", canvas);

        history.undo();
        show("After undoing the clear", canvas);
    }

    private static void show(String title, Canvas canvas) {
        System.out.println(title + ":");
        canvas.print();
        System.out.println();
    }
}
