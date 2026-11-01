// A thin Java wrapper over the C library raylib, built with java.lang.foreign.
// Methods to provide (all static):
// Key codes from raylib.h (check them against the header):
//   KEY_ONE = 49, KEY_TWO = 50, KEY_THREE = 51,
//   KEY_C = 67, KEY_M = 77, KEY_Y = 89, KEY_Z = 90,
//   KEY_RIGHT = 262, KEY_LEFT = 263, KEY_DOWN = 264, KEY_UP = 265
package gfx;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

public final class Raylib {

    // Main FFI & Memory API Constants
    private static final Arena ARENA = Arena.global();
    private static final Linker LINKER = Linker.nativeLinker();
    private static final SymbolLookup RAYLIB = SymbolLookup.libraryLookup("./raylib.dll", ARENA);
    private static final StructLayout COLOR = MemoryLayout.structLayout(
            ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE,
            ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE
    );
    private static final MemorySegment COLOR_ARG = ARENA.allocate(COLOR);   // reused every call

    // Keys Constants 
    public static final int KEY_ONE = 49;
    public static final int KEY_TWO = 50;
    public static final int KEY_THREE = 51;
    public static final int KEY_C = 67;
    public static final int KEY_M = 77;
    public static final int KEY_Y = 89;
    public static final int KEY_Z = 90;
    public static final int KEY_RIGHT = 262;
    public static final int KEY_LEFT = 263;
    public static final int KEY_DOWN = 264;
    public static final int KEY_UP = 265;

    // Raylib used Methods as method handles
    private static final MethodHandle INIT_WINDOW;
    private static final MethodHandle IS_KEY_PRESSED;
    private static final MethodHandle DRAW_RECTANGLE_LINES;
    private static final MethodHandle DRAW_RECTANGLE;
    private static final MethodHandle CLEAR_BACKGROUND;
    private static final MethodHandle END_DRAWING;
    private static final MethodHandle BEGIN_DRAWING;
    private static final MethodHandle WINDOW_SHOULD_CLOSE;
    private static final MethodHandle SET_TARGET_FPS;
    private static final MethodHandle CLOSE_WINDOW;
    private static final MethodHandle SET_TRACE_LOGL_EVEL;

    // Static Initialization for the Raylib methods handles
    static {
        INIT_WINDOW = LINKER.downcallHandle(
                RAYLIB.findOrThrow("InitWindow"),
                FunctionDescriptor.ofVoid(
                        ValueLayout.JAVA_INT,
                        ValueLayout.JAVA_INT, ValueLayout.ADDRESS
                ));
        SET_TRACE_LOGL_EVEL = LINKER.downcallHandle(
                RAYLIB.findOrThrow("SetTraceLogLevel"),
                FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT));
        IS_KEY_PRESSED = LINKER.downcallHandle(
                RAYLIB.findOrThrow("IsKeyPressed"),
                FunctionDescriptor.of(
                        ValueLayout.JAVA_BOOLEAN,
                        ValueLayout.JAVA_INT
                ));
        DRAW_RECTANGLE_LINES = LINKER.downcallHandle(
                RAYLIB.findOrThrow("DrawRectangleLines"),
                FunctionDescriptor.ofVoid(
                        ValueLayout.JAVA_INT, ValueLayout.JAVA_INT,
                        ValueLayout.JAVA_INT, ValueLayout.JAVA_INT,
                        COLOR
                )
        );
        DRAW_RECTANGLE = LINKER.downcallHandle(
                RAYLIB.findOrThrow("DrawRectangle"),
                FunctionDescriptor.ofVoid(
                        ValueLayout.JAVA_INT, ValueLayout.JAVA_INT,
                        ValueLayout.JAVA_INT, ValueLayout.JAVA_INT,
                        COLOR
                )
        );
        CLEAR_BACKGROUND = LINKER.downcallHandle(
                RAYLIB.findOrThrow("ClearBackground"),
                FunctionDescriptor.ofVoid(COLOR)
        );
        END_DRAWING = LINKER.downcallHandle(
                RAYLIB.findOrThrow("EndDrawing"),
                FunctionDescriptor.ofVoid()
        );
        BEGIN_DRAWING = LINKER.downcallHandle(
                RAYLIB.findOrThrow("BeginDrawing"),
                FunctionDescriptor.ofVoid()
        );
        WINDOW_SHOULD_CLOSE = LINKER.downcallHandle(
                RAYLIB.findOrThrow("WindowShouldClose"),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN)
        );
        SET_TARGET_FPS = LINKER.downcallHandle(
                RAYLIB.findOrThrow("SetTargetFPS"),
                FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT)
        );
        CLOSE_WINDOW = LINKER.downcallHandle(
                RAYLIB.findOrThrow("CloseWindow"),
                FunctionDescriptor.ofVoid()
        );

    }

    // Private Constructor, The Raylib class is Utility one
    private Raylib() {
    }

    // Using the constant to fill color, and reuse it to avoid re-allocation every frame
    private static void fillColor(int r, int g, int b, int a) {
        COLOR_ARG.set(ValueLayout.JAVA_BYTE, 0, (byte) r);
        COLOR_ARG.set(ValueLayout.JAVA_BYTE, 1, (byte) g);
        COLOR_ARG.set(ValueLayout.JAVA_BYTE, 2, (byte) b);
        COLOR_ARG.set(ValueLayout.JAVA_BYTE, 3, (byte) a);
    }

    // Raylib Methods Handling and Masking
    public static void initWindow(int width, int height, String title) {
        try {
            INIT_WINDOW.invokeExact(width, height, ARENA.allocateFrom(title));
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public static boolean isKeyPressed(int key) {
        try {
            return (boolean) IS_KEY_PRESSED.invokeExact(key);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public static void drawRectangleLines(int x, int y, int w, int h,
            int r, int g, int b, int a) {
        try {
            fillColor(r, g, b, a);
            DRAW_RECTANGLE_LINES.invokeExact(x, y, w, h, COLOR_ARG);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public static void drawRectangle(int x, int y, int w, int h,
            int r, int g, int b, int a) {
        try {
            fillColor(r, g, b, a);
            DRAW_RECTANGLE.invokeExact(x, y, w, h, COLOR_ARG);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public static void clearBackground(int r, int g, int b, int a) {
        try {
            fillColor(r, g, b, a);
            CLEAR_BACKGROUND.invokeExact(COLOR_ARG);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public static void endDrawing() {
        try {
            END_DRAWING.invokeExact();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public static void beginDrawing() {
        try {
            BEGIN_DRAWING.invokeExact();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public static boolean windowShouldClose() {
        try {
            return (boolean) WINDOW_SHOULD_CLOSE.invokeExact();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public static void setTargetFPS(int fps) {
        try {
            SET_TARGET_FPS.invokeExact(fps);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public static void CloseWindow() {
        try {
            CLOSE_WINDOW.invokeExact();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public static void setTraceLogLevel(int logLevel) {
        try {
            SET_TRACE_LOGL_EVEL.invokeExact(logLevel);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
}
