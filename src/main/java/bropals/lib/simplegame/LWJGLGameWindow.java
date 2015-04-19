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
import bropals.lib.simplegame.lwjgl.LWJGLContext;
import bropals.lib.simplegame.state.GameState;
import java.awt.GraphicsDevice;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import org.lwjgl.glfw.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glFlush;
import org.lwjgl.opengl.GLContext;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Not yet working.
 * 
 * The LWJGL Game window implementation. Instead of the given render object
 * being a Graphics object, it is a LWJGLContext object.
 * 
 * @author Jonathon
 */
public class LWJGLGameWindow implements GameWindow {

    private boolean fullscreen;
    private long[] videoModes;
    private long nativeVideoMode;
    private long window;
    private GLFWKeyCallback keyCallback;
    private GLFWMouseButtonCallback mouseButtonCallback;
    private GLFWErrorCallback errorCallback;
    private ScreenResolution resolution;
    private Point storedMousePosition;
    private GameStateRunner runner;
    private boolean requestingToClose = false;
    private LWJGLContext context;
    
    /**
     * Creates a new LWJGL Game window with the specified properties.
     * @param title the title of the window
     * @param screenWidth the width of the window drawing surface
     * @param screenHeight the height of the window drawing surface
     * @param fullscreen whether or not this surface will be in fullscreen mode
     */
    public LWJGLGameWindow(String title, int screenWidth, int screenHeight, boolean fullscreen) {
        initLWJGL();
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
        resolution = new ScreenResolution(screenWidth, screenHeight);
        this.fullscreen = fullscreen;
        initVideoModes();
        if (this.fullscreen) {
            throw new UnsupportedOperationException("The current version of LWJGLGameWindow does not support fullscreen");
        } else {
            window = glfwCreateWindow(screenWidth, screenHeight, 
                    title, NULL, NULL);
            glfwMakeContextCurrent(window);
            glfwShowWindow(window);
        }
        setupInputHandling();
        GLContext.createFromCurrent();
        glfwSwapInterval(1);
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        storedMousePosition = new Point();
        context = new LWJGLContext(this);
    }
    
    /**
     * Creates a new windowed LWJGL Game window with the specified title and
     * screen size.
     * @param title the title of the window
     * @param screenWidth the width of the window drawing surface
     * @param screenHeight the height of the window drawing surface
     */
    public LWJGLGameWindow(String title, int screenWidth, int screenHeight) {
        this(title, screenWidth, screenHeight, false);
    }
    
    /**
     * Creates a new windowed 640x480 LWJGL Game window with the given title.
     * @param title the title of the window
     */
    public LWJGLGameWindow(String title) {
        this(title, 640, 480, false);
    }
    
    /**
     * Currently not implemented.
     * @param icon
     */
    @Override
    public void setIcon(BufferedImage icon) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supportsResolution(int screenWidth, int screenHeight) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supportsResolution(ScreenResolution resolution) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ScreenResolution[] getSupportedScreenResolutionList(GraphicsDevice device) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isRequestingToClose() {
        return glfwWindowShouldClose(window) == GL_TRUE || requestingToClose;
    }

    @Override
    public boolean isFullscreen() {
        return fullscreen;
    }

    @Override
    public boolean isWindowed() {
        return !fullscreen;
    }

    @Override
    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
    }

    @Override
    public void setWindowed(boolean windowed) {
        fullscreen = !windowed;
    }

    @Override
    public boolean setScreenResolution(ScreenResolution screenResolution) {
        if (supportsResolution(resolution)) {
            resolution = screenResolution;
            glfwSetWindowSize(window, resolution.getScreenWidth(), resolution.getScreenHeight());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public GameCursor getGameCursor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setGameCursor(GameCursor gameCursor) {
        //NEED TO IMPLEMENT
        //1. Convert BufferedImage in the gameCursor to the format described
        //   in the GLFW documentation
        //2. Use glfwCreateCursor() to make the cursor and set it to the window
        throw new UnsupportedOperationException("Setting the game cursor is not yet implemented");
    }

    @Override
    public void destroy() {
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    @Override
    public int getScreenWidth() {
        return resolution.getScreenWidth();
    }

    @Override
    public int getScreenHeight() {
        return resolution.getScreenHeight();
    }

    @Override
    public void requestToClose() {
        requestingToClose = true;
    }

    @Override
    public ScreenResolution getScreenResolution() {
        return resolution;
    }

    @Override
    public void flushInput() {
        glfwPollEvents();
    }

    @Override
    public void giveGameStateRunner(GameStateRunner runner) {
        this.runner = runner;
    }

    @Override
    public void renderState(GameState state) {
        glClear(GL_COLOR_BUFFER_BIT);
        state.render(context);
        glFlush();
        glfwSwapBuffers(window);
    }

    @Override
    public Point getMousePosition() {
        return storedMousePosition;
    }
    
    /**
     * Initialize GLFW and the OpenGL context.
     */
    private void initLWJGL() {
        if (glfwInit() != GL_TRUE) {
            ErrorLogger.println("Can't init GLFW (internal LWJGL)");
        }
    }
    
    private void initVideoModes() {
        ByteBuffer byteNativeVideoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        nativeVideoMode = byteNativeVideoMode.getLong();
    }
    
    private void setupInputHandling() {
        glfwSetKeyCallback(window, new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (action == GLFW_PRESS) {
                    runner.keyPressed(KeyCode.convertLWJGLCodeToBroPalsCode(key));
                } else if (action == GLFW_RELEASE) {
                    runner.keyPressed(KeyCode.convertLWJGLCodeToBroPalsCode(key));
                }
            }
        });
        glfwSetCursorPosCallback(window, new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double mouseX, double mouseY) {
                storedMousePosition.setLocation(mouseX, mouseY
                );
            }
        });
        glfwSetMouseButtonCallback(window, new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                if (action == GLFW_PRESS) {
                    Point p = getMousePosition();
                    runner.mousePressed(button, p.x, p.y);
                } else if (action == GLFW_RELEASE) {
                    Point p = getMousePosition();
                    runner.mouseReleased(button, p.x, p.y);
                }
            }
        });
    }
    
    /**
     * Gets the context to load textures and draws with OpenGL graphics.
     * @return the context to draw.
     */
    public LWJGLContext getContext() {
        return context;
    }

    @Override
    public void registerQuitHandler(QuitHandler handler) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void unregisterQuitHandler(QuitHandler handler) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
