
import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

public class Test {

    static Arena arena = Arena.ofConfined();
    static MemoryLayout colorStruct = MemoryLayout.structLayout(
            ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE,
            ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE
    );

    public static void main(String[] args) throws Throwable {
        Linker linker = Linker.nativeLinker();
        SymbolLookup raylib = SymbolLookup.libraryLookup("./raylib.dll", arena);

        System.out.println(raylib.find("CloseWindow").isPresent());
        //   void    initWindow(int width, int height, String title)   -> C: InitWindow
        //   void    closeWindow()                                     -> C: CloseWindow
        //   void    setTargetFPS(int fps)                             -> C: SetTargetFPS
        //   boolean windowShouldClose()                               -> C: WindowShouldClose
        //   void    beginDrawing()                                    -> C: BeginDrawing
        //   void    endDrawing()                                      -> C: EndDrawing
        //   void    clearBackground(int r, int g, int b, int a)       -> C: ClearBackground(Color)
        //   void    drawRectangle(int x, int y, int w, int h,
        //                         int r, int g, int b, int a)         -> C: DrawRectangle(..., Color)
        //   void    drawRectangleLines(same parameters)               -> C: DrawRectangleLines(..., Color)
        //   boolean isKeyPressed(int key)                             -> C: IsKeyPressed
        MemorySegment title = arena.allocateFrom("Command Pattern");
        MethodHandle initWindow = linker.downcallHandle(
                raylib.findOrThrow("InitWindow"),
                FunctionDescriptor.ofVoid(
                        ValueLayout.JAVA_INT,
                        ValueLayout.JAVA_INT, ValueLayout.ADDRESS
                ));
        MethodHandle isKeyPressed = linker.downcallHandle(
                raylib.findOrThrow("IsKeyPressed"),
                FunctionDescriptor.of(
                        ValueLayout.JAVA_BOOLEAN,
                        ValueLayout.JAVA_INT
                ));
        MethodHandle drawRectangleLines = linker.downcallHandle(
                raylib.findOrThrow("DrawRectangleLines"),
                FunctionDescriptor.ofVoid(
                        ValueLayout.JAVA_INT, ValueLayout.JAVA_INT,
                        ValueLayout.JAVA_INT, ValueLayout.JAVA_INT,
                        colorStruct
                )
        );
        MethodHandle drawRectangle = linker.downcallHandle(
                raylib.findOrThrow("DrawRectangle"),
                FunctionDescriptor.ofVoid(
                        ValueLayout.JAVA_INT, ValueLayout.JAVA_INT,
                        ValueLayout.JAVA_INT, ValueLayout.JAVA_INT,
                        colorStruct
                )
        );
        MethodHandle clearBackground = linker.downcallHandle(
                raylib.findOrThrow("ClearBackground"),
                FunctionDescriptor.ofVoid(colorStruct)
        );
        MethodHandle endDrawing = linker.downcallHandle(
                raylib.findOrThrow("EndDrawing"),
                FunctionDescriptor.ofVoid()
        );
        MethodHandle beginDrawing = linker.downcallHandle(
                raylib.findOrThrow("BeginDrawing"),
                FunctionDescriptor.ofVoid()
        );
        MethodHandle windowShouldClose = linker.downcallHandle(
                raylib.findOrThrow("WindowShouldClose"),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN)
        );
        MethodHandle setTargetFPS = linker.downcallHandle(
                raylib.findOrThrow("SetTargetFPS"),
                FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT)
        );
        MethodHandle CloseWindow = linker.downcallHandle(
                raylib.findOrThrow("CloseWindow"),
                FunctionDescriptor.ofVoid()
        );

        InitWindow.invokeExact(480, 480, title);
        setTargetFPS.invokeExact(60);
        MemorySegment DGRAY = getColor(25, 25, 25, 255);

        int time = 0;
        int dx = 1, dy = 1;
        while (!((boolean) windowShouldClose.invokeExact())) {
            beginDrawing.invokeExact();
            clearBackground.invokeExact(DGRAY);

            drawRectangle.invokeExact(time * dx, time * dy, 50, 50, getColor(0, 228, 48, 255));
            time++;

            endDrawing.invokeExact();
        }
        System.out.println("INWINDOW");
        CloseWindow.invokeExact();
    }

    private static MemorySegment getColor(int r, int g, int b, int a) {
        MemorySegment color = arena.allocate(colorStruct);
        color.set(ValueLayout.JAVA_BYTE, 0, (byte) r);
        color.set(ValueLayout.JAVA_BYTE, 1, (byte) g);
        color.set(ValueLayout.JAVA_BYTE, 2, (byte) b);
        color.set(ValueLayout.JAVA_BYTE, 3, (byte) a);

        return color;
    }
}
