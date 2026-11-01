
import static gfx.Raylib.*;

public class GuiProgram {

    public static void main(String[] args) {
        int GRID = 8, CELL = 60;
        initWindow(GRID * CELL, GRID * CELL, "Command Pattern with Raylib");
        setTargetFPS(60);
        setTraceLogLevel(4);

        Canvas canvas = new Canvas(GRID);
        CommandHistory history = new CommandHistory();
        int cx = 0, cy = 0;

        while (!windowShouldClose()) {
            beginDrawing();
            clearBackground(25, 25, 25, 255);

            for (int x = 0; x < GRID; x++) {
                for (int y = 0; y < GRID; y++) {
                    PaintColor cell = canvas.getColor(x, y);
                    drawRectangle(x * CELL, y * CELL, CELL, CELL,
                            cell.getR(), cell.getG(), cell.getB(), cell.getA());
                    drawRectangleLines(x * CELL, y * CELL, CELL, CELL, 244, 67, 54, 255);
                }
            }

            // drawRectangle(t * dx, t * dy, 50, 50, 0, 228, 48, 255);
            // t++;
            drawRectangleLines(cx * (CELL) + 1, cy * (CELL) + 1, (CELL - 2), (CELL - 2), 0, 228, 48, 255);

            // Command Pattern applying to the canvas, the loop reprinting
            if (isKeyPressed(KEY_DOWN)) {
                cy++;
            } else if (isKeyPressed(KEY_UP)) {
                cy--;
            } else if (isKeyPressed(KEY_RIGHT)) {
                cx++;
            } else if (isKeyPressed(KEY_LEFT)) {
                cx--;
            } else if (isKeyPressed(KEY_ONE)) {
                history.run(new PaintCommand(canvas, cx, cy, PaintColor.RED));
            } else if (isKeyPressed(KEY_TWO)) {
                history.run(new PaintCommand(canvas, cx, cy, PaintColor.GREEN));
            } else if (isKeyPressed(KEY_THREE)) {
                history.run(new PaintCommand(canvas, cx, cy, PaintColor.BLUE));
            } else if (isKeyPressed(KEY_Z)) {
                history.undo();
            } else if (isKeyPressed(KEY_Y)) {
                history.redo();
            } else if (isKeyPressed(KEY_C)) {
                history.run(new ClearCommand(canvas));
            } else if (isKeyPressed(KEY_M)) {
                MacroCommand mc = new MacroCommand();
                for (int i = 0; i < GRID; i++) {
                    mc.add(new PaintCommand(canvas, i, cy, PaintColor.GREEN));
                }
                history.run(mc);
            }

            if (cy < 0 || cy >= GRID) {
                cy = Math.abs((cy + 8) % GRID);
            }
            if (cx < 0 || cx >= GRID) {
                cx = Math.abs((cx + 8) % GRID);
            }
            endDrawing();
        }

        CloseWindow();
    }

}
