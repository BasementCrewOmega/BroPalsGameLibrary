/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Jonathon Prehn and Kevin Prehn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package bropals.lib.simplegame;

import bropals.lib.simplegame.logger.ErrorLogger;
import static java.awt.event.KeyEvent.*;
import static org.lwjgl.glfw.GLFW.*;
/**
 * The library's key codes to use.
 * @author Jonathon
 */
public class KeyCode {
    
    public static final int 
        KEY_1 = 0,
        KEY_2 = 1,
        KEY_3 = 2,
        KEY_4 = 3,
        KEY_5 = 4,
        KEY_6 = 5,
        KEY_7 = 6,
        KEY_8 = 7,
        KEY_9 = 8,
        KEY_0 = 9,
        KEY_HYPHEN = 10,
        KEY_EQUALS_SIGN = 11,
        KEY_BACKSPACE = 12,
        KEY_TAB = 13,
        KEY_Q = 14,
        KEY_TILDE = 15,
        KEY_ESCAPE = 16,
        KEY_F1 = 17,
        KEY_F2 = 18,
        KEY_F3 = 19,
        KEY_F4 = 20,
        KEY_F5 = 21,
        KEY_F6 = 22,
        KEY_F7 = 23,
        KEY_F8 = 24,
        KEY_F9 = 25,
        KEY_F10 = 26,
        KEY_F11 = 27,
        KEY_F12 = 28,
        KEY_INSERT = 29,
        KEY_DELETE = 30,
        KEY_CAPS_LOCK = 31,
        KEY_A = 32,
        KEY_S = 33,
        KEY_D = 34,
        KEY_F = 35,
        KEY_G = 36,
        KEY_H = 37,
        KEY_J = 38,
        KEY_K = 39,
        KEY_L = 40,
        KEY_SEMICOLON = 41,
        KEY_APOSTROPHE = 42,
        KEY_ENTER = 43,
        KEY_W = 44,
        KEY_E = 45,
        KEY_R = 46,
        KEY_T = 47,
        KEY_Y = 48,
        KEY_U = 49,
        KEY_I = 50,
        KEY_O = 51,
        KEY_P = 52,
        KEY_LEFT_SQUARE_BRACKET = 53,
        KEY_RIGHT_SQUARE_BRACKET = 54,
        KEY_BACK_SLASH = 55,
        KEY_LEFT_SHIFT = 56,
        KEY_Z = 57,
        KEY_X = 58,
        KEY_C = 59,
        KEY_V = 60,
        KEY_B = 61,
        KEY_N = 62,
        KEY_M = 63,
        KEY_COMMA = 64,
        KEY_PERIOD = 65,
        KEY_FORWARD_SLASH = 66,
        KEY_RIGHT_SHIFT = 67,
        KEY_LEFT_CONTROL = 68,
        KEY_ALT = 69,
        KEY_SPACE = 70,
        KEY_RIGHT_CONTROL = 71,
        KEY_UP = 72,
        KEY_DOWN = 73,
        KEY_LEFT = 74,
        KEY_RIGHT = 75,
        KEY_PAGE_UP = 76,
        KEY_PAGE_DOWN = 77,
        KEY_END = 78,
        KEY_NUM_LOCK = 79,
        KEY_NUMPAD_ASTERISK = 80,
        KEY_NUMPAD_HYPHEN = 81,
        KEY_NUMPAD_7 = 82,
        KEY_NUMPAD_8 = 83,
        KEY_NUMPAD_9 = 84,
        KEY_NUMPAD_4 = 85,
        KEY_NUMPAD_5 = 86,
        KEY_NUMPAD_6 = 87,
        KEY_NUMPAD_1 = 88,
        KEY_NUMPAD_2 = 89,
        KEY_NUMPAD_3 = 90,
        KEY_NUMPAD_0 = 91,
        KEY_NUMPAD_PERIOD = 92,
        KEY_NUMPAD_ENTER = 93,
        KEY_NUMPAD_PLUS = 94,
        KEY_PAUSE = 95,
        KEY_PRINT_SCREEN = 96,
        KEY_HOME = 97
    ;
    
    public static int convertLWJGLCodeToBroPalsCode(int lwjglKeyCode) {
        switch(lwjglKeyCode) {
            case GLFW_KEY_0:
                return KEY_0;
            case GLFW_KEY_1:
                return KEY_1;
            case GLFW_KEY_2:
                return KEY_2;
            case GLFW_KEY_3:
                return KEY_3;
            case GLFW_KEY_4:
                return KEY_4;
            case GLFW_KEY_5:
                return KEY_5;
            case GLFW_KEY_6:
                return KEY_6;
            case GLFW_KEY_7:
                return KEY_7;
            case GLFW_KEY_8:
                return KEY_8;
            case GLFW_KEY_9:
                return KEY_9;
            case GLFW_KEY_F1:
                return KEY_F1;
            case GLFW_KEY_F2:
                return KEY_F2;
            case GLFW_KEY_F3:
                return KEY_F3;
            case GLFW_KEY_F4:
                return KEY_F4;
            case GLFW_KEY_F5:
                return KEY_F5;
            case GLFW_KEY_F6:
                return KEY_F6;
            case GLFW_KEY_F7:
                return KEY_F7;
            case GLFW_KEY_F8:
                return KEY_F8;
            case GLFW_KEY_F9:
                return KEY_F10;
            case GLFW_KEY_F11:
                return KEY_F11;
            case GLFW_KEY_F12:
                return KEY_F12;
            case GLFW_KEY_TAB:
                return KEY_TAB;
            case GLFW_KEY_Q:
                return KEY_Q;
            case GLFW_KEY_W:
                return KEY_W;
            case GLFW_KEY_E:
                return KEY_E;
            case GLFW_KEY_R:
                return KEY_R;
            case GLFW_KEY_T:
                return KEY_T;
            case GLFW_KEY_Y:
                return KEY_Y;
            case GLFW_KEY_U:
                return KEY_U;
            case GLFW_KEY_I:
                return KEY_I;
            case GLFW_KEY_O:
                return KEY_O;
            case GLFW_KEY_P:
                return KEY_P;
            case GLFW_KEY_CAPS_LOCK:
                return KEY_CAPS_LOCK;
            case GLFW_KEY_A:
                return KEY_A;
            case GLFW_KEY_S:
                return KEY_S;
            case GLFW_KEY_D:
                return KEY_D;
            case GLFW_KEY_F:
                return KEY_F;
            case GLFW_KEY_G:
                return KEY_G;
            case GLFW_KEY_H:
                return KEY_H;
            case GLFW_KEY_J:
                return KEY_J;
            case GLFW_KEY_K:
                return KEY_K;
            case GLFW_KEY_L:
                return KEY_L;
            case GLFW_KEY_SEMICOLON:
                return KEY_SEMICOLON;
            case GLFW_KEY_APOSTROPHE:
                return KEY_APOSTROPHE;
            case GLFW_KEY_ENTER:
                return KEY_ENTER;
            case GLFW_KEY_LEFT_SHIFT:
                return KEY_LEFT_SHIFT;
            case GLFW_KEY_Z:
                return KEY_Z;
            case GLFW_KEY_X:
                return KEY_X;
            case GLFW_KEY_C:
                return KEY_C;
            case GLFW_KEY_V:
                return KEY_V;
            case GLFW_KEY_B:
                return KEY_B;
            case GLFW_KEY_N:
                return KEY_N;
            case GLFW_KEY_M:
                return KEY_M;
            case GLFW_KEY_COMMA:
                return KEY_COMMA;
            case GLFW_KEY_PERIOD:
                return KEY_PERIOD;
            case GLFW_KEY_SLASH:
                return KEY_FORWARD_SLASH;
            case GLFW_KEY_BACKSLASH:
                return KEY_BACK_SLASH;
            case GLFW_KEY_RIGHT_SHIFT:
                return KEY_RIGHT_SHIFT;
            case GLFW_KEY_LEFT_CONTROL:
                return KEY_LEFT_CONTROL;
            case GLFW_KEY_RIGHT_CONTROL:
                return KEY_RIGHT_CONTROL;
            case GLFW_KEY_LEFT_ALT:
                return KEY_ALT;
            case GLFW_KEY_RIGHT_ALT:
                return KEY_ALT;
            case GLFW_KEY_SPACE:
                return KEY_SPACE;
            case GLFW_KEY_UP:
                return KEY_UP;
            case GLFW_KEY_DOWN:
                return KEY_DOWN;
            case GLFW_KEY_LEFT:
                return KEY_LEFT;
            case GLFW_KEY_RIGHT:
                return KEY_RIGHT;
            case GLFW_KEY_INSERT:
                return KEY_INSERT;
            case GLFW_KEY_DELETE:
                return KEY_DELETE;
            case GLFW_KEY_HOME:
                return KEY_HOME;
            case GLFW_KEY_END:
                return KEY_END;
            case GLFW_KEY_ESCAPE:
                return KEY_ESCAPE;
            case GLFW_KEY_EQUAL:
                return KEY_EQUALS_SIGN;
            case GLFW_KEY_PAGE_UP:
                return KEY_PAGE_UP;
            case GLFW_KEY_PAGE_DOWN:
                return KEY_PAGE_DOWN;
        }
        ErrorLogger.println("Could not recognize LWJGL key code: " + lwjglKeyCode);
        return -1;
    }
    
    public static int convertAWTCodeToBroPalsCode(int awtKeyCode) {
        switch(awtKeyCode) {
            case VK_0: return KEY_0;
            case VK_1: return KEY_1;
            case VK_2: return KEY_2;
            case VK_3: return KEY_3;
            case VK_4: return KEY_4;
            case VK_5: return KEY_5;
            case VK_6: return KEY_6;
            case VK_7: return KEY_7;
            case VK_8: return KEY_8;
            case VK_9: return KEY_9;
            case VK_F1: return KEY_F1;
            case VK_F2: return KEY_F2;
            case VK_F3: return KEY_F3;
            case VK_F4: return KEY_F4;
            case VK_F5: return KEY_F5;
            case VK_F6: return KEY_F6;
            case VK_F7: return KEY_F7;
            case VK_F8: return KEY_F8;
            case VK_F9: return KEY_F9;
            case VK_F10: return KEY_F10;
            case VK_F11: return KEY_F11;
            case VK_F12: return KEY_F12;
            case VK_TAB: return KEY_TAB;
            case VK_BACK_SPACE: return KEY_BACKSPACE;
            case VK_Q: return KEY_Q;
            case VK_W: return KEY_W;
            case VK_E: return KEY_E;
            case VK_R: return KEY_R;
            case VK_T: return KEY_T;
            case VK_Y: return KEY_Y;
            case VK_U: return KEY_U;
            case VK_I: return KEY_I;
            case VK_O: return KEY_O;
            case VK_P: return KEY_P;
            case VK_BRACELEFT: return KEY_LEFT_SQUARE_BRACKET;
            case VK_BRACERIGHT: return KEY_RIGHT_SQUARE_BRACKET;
            case VK_BACK_SLASH: return KEY_BACK_SLASH;
            case VK_CAPS_LOCK: return KEY_CAPS_LOCK;
            case VK_A: return KEY_A;
            case VK_S: return KEY_S;
            case VK_D: return KEY_D;
            case VK_F: return KEY_F;
            case VK_G: return KEY_G;
            case VK_H: return KEY_H;
            case VK_J: return KEY_J;
            case VK_K: return KEY_K;
            case VK_L: return KEY_L;
            case VK_SEMICOLON: return KEY_SEMICOLON;
            case VK_ENTER: return KEY_ENTER;
            case VK_SHIFT: return KEY_LEFT_SHIFT;
            case VK_Z: return KEY_Z;
            case VK_X: return KEY_X;
            case VK_C: return KEY_C;
            case VK_V: return KEY_V;
            case VK_B: return KEY_B;
            case VK_N: return KEY_N;
            case VK_M: return KEY_M;
            case VK_COMMA: return KEY_COMMA;
            case VK_PERIOD: return KEY_PERIOD;
            case VK_SLASH: return KEY_FORWARD_SLASH;
            case VK_CONTROL: return KEY_LEFT_CONTROL;
            case VK_ALT: return KEY_ALT;
            case VK_SPACE: return KEY_SPACE;
            case VK_UP: return KEY_UP;
            case VK_DOWN: return KEY_DOWN;
            case VK_LEFT: return KEY_LEFT;
            case VK_RIGHT: return KEY_RIGHT;
        }
        ErrorLogger.println("Could not recognize AWT key code: " + awtKeyCode);
        return -1;
    }
}
