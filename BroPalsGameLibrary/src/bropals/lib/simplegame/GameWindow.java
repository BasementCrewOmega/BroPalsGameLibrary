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

import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import static java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Represents the drawing area.
 *
 * @author Kevin Prehn
 */
public class GameWindow {

    private Frame frame;
    private boolean fullscreen;
    private String title;
    private ScreenResolution screenResolution;
    private ScreenResolution nativeResolution;
    private GameStateRunner runner = null;
    private ScreenResolution[] resolutions;
    private int bitDepth;
    private int refreshRate;
    private GraphicsDevice device;
    private boolean requestToClose = false;

    /**
     * Creates a visible GameWindow with the supplied parameters.
     *
     * @param title the title of the window.
     * @param screenWidth the screen resolution width.
     * @param screenHeight the screen resolution height.
     * @param fullscreen the fullscreen state of the GameWindow.
     * @param device the GraphicsDevice to use for this GameWindow.
     */
    public GameWindow(String title, int screenWidth, int screenHeight, 
            boolean fullscreen, GraphicsDevice device) {
        this.title = title;
        this.fullscreen = fullscreen;
        setScreenResolution(new ScreenResolution(screenWidth, screenHeight));
        this.device = device;
        initNativeScreenResolution(device);
        this.resolutions = getSupportedScreenResolutionList(device);
        applyGraphicsConfiguration();
    }
    
    /**
     * Creates a visible GameWindow with the supplied parameters. Uses the
     * default screen device.
     *
     * @param title the title of the window.
     * @param screenWidth the screen resolution width.
     * @param screenHeight the screen resolution height.
     * @param fullscreen the fullscreen state of the GameWindow.
     */
    public GameWindow(String title, int screenWidth, int screenHeight, 
            boolean fullscreen) {
        this(title, screenWidth, screenHeight, fullscreen, 
                getLocalGraphicsEnvironment().getDefaultScreenDevice());
    }

    /**
     * Creates a visible GameWindow with the supplied parameters. Uses the
     * default screen device; starts in windowed mode.
     *
     * @param title the title of the window.
     * @param screenWidth the screen resolution width.
     * @param screenHeight the screen resolution height.
     */
    public GameWindow(String title, int screenWidth, int screenHeight) {
        this(title, screenWidth, screenHeight, false, 
                getLocalGraphicsEnvironment().getDefaultScreenDevice());
    }
    
    /**
     * Checks to see if the full screen resolution is supported. If the size
     * does not work for fullscreen, it will still work for a window.
     *
     * @param width the screen width to check
     * @param height the screen height to check
     * @return if the resolution is supported
     */
    public boolean supportsResolution(int width, int height) {
        return supportsResolution(new ScreenResolution(width, height));
    }

    /**
     * Checks to see if the full screen resolution is supported. If the size
     * does not work for fullscreen, it will still work for a window.
     *
     * @param resolution the screen resolution to check
     * @return if the resolution is supported
     */
    public boolean supportsResolution(ScreenResolution resolution) {
        for (ScreenResolution s : resolutions) {
            if (s.equals(resolution)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Makes a list of ScreenResolutions that the given graphics device
     * supports.
     *
     * @param device the graphics device
     * @return the list of supported screen resolutions
     */
    public final ScreenResolution[] getSupportedScreenResolutionList(GraphicsDevice device) {
        this.device = device;
        DisplayMode current = this.device.getDisplayMode();
        this.bitDepth = current.getBitDepth();
        this.refreshRate = current.getRefreshRate();
        DisplayMode[] list = this.device.getDisplayModes();
        ArrayList<ScreenResolution> collected = new ArrayList<>();
        ScreenResolution res;
        for (DisplayMode dm : list) {
            res = new ScreenResolution(dm.getWidth(), dm.getHeight());
            if (!collected.contains(res)) {
                collected.add(res);
            }
        }
        return (ScreenResolution[]) collected.toArray(new ScreenResolution[0]);
    }

    /**
     * Sets this GameWindow's native screen resolution with the native
     * resolution of the given device.
     *
     * @param device the graphics device that will have its screen resolution be
     * called the native resolution.
     */
    private void initNativeScreenResolution(GraphicsDevice device) {
        nativeResolution = new ScreenResolution(
                device.getDisplayMode().getWidth(),
                device.getDisplayMode().getHeight()
        );
    }

    /**
     * Checks to see if the GameWindow is requesting to close.
     *
     * @return the request to close state.
     */
    public boolean isRequestingToClose() {
        return requestToClose;
    }

    /**
     * Gets the left insets of the window.
     *
     * @return the left insets of the window.
     */
    private int getInsetsLeft() {
        if (!fullscreen) {
            return frame.getInsets().left;
        } else {
            return 0;
        }
    }

    /**
     * Gets the top insets of the window.
     *
     * @return the top insets of the window.
     */
    private int getInsetsTop() {
        if (!fullscreen) {
            return frame.getInsets().top;
        } else {
            return 0;
        }
    }

    /**
     * Gets the bottom insets of the window.
     *
     * @return the bottom insets of the window.
     */
    private int getInsetsBottom() {
        if (!fullscreen) {
            return frame.getInsets().bottom;
        } else {
            return 0;
        }
    }

    /**
     * Gets the right insets of the window.
     *
     * @return the right insets of the window.
     */
    private int getInsetsRight() {
        if (!fullscreen) {
            return frame.getInsets().right;
        } else {
            return 0;
        }
    }

    /**
     * Checks to see if the GameWindow is in fullscreen.
     *
     * @return the fullscreen state of the window.
     */
    public boolean isFullscreen() {
        return fullscreen;
    }
    
    /**
     * Checks to see if the GameWindow is in windowed mode.
     *
     * @return the windowed state of the window.
     */
    public boolean isWindowed() {
        return !fullscreen;
    }

    /**
     * Sets the fullscreen state of the GameWindow. Call
     * <code>applyGraphicsConfiguration()</code> to update the fullscreen state.
     *
     * @param fullscreen the new fullscreen state.
     */
    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
    }
    
    /**
     * Sets the windowed state of the GameWindow. Call
     * <code>applyGraphicsConfiguration()</code> to update the windowed state.
     *
     * @param windowed the new windowed state.
     */
    public void setWindowed(boolean windowed) {
        this.fullscreen = !windowed;
    }

    /**
     * Sets the screen resolution of the GameWindow. Call
     * <code>applyGraphicsConfiguration()</code> to update the fullscreen state.
     * If the screen resolution is not supported, this method returns
     * <code>false</code>.
     * 
     * @param screenResolution the new screen resolution.
     * @return if the screen change was successful.
     */
    public boolean setScreenResolution(ScreenResolution screenResolution) {
        if (isFullscreen()) {
            if (!supportsResolution(screenResolution)) {
                return false;
            }
        }
        this.screenResolution = screenResolution;
        return true;
    }

    /**
     * Has the GameWindow update its window to match the configuration it was
     * given. The Graphics object obtained from <code>getDrawGraphics()</code>
     * is now invalid, and need to have <code>dispose()</code> called it, 
     * replacing it with a new Graphics object.
     */
    public final void applyGraphicsConfiguration() {
        if (frame == null) {
            frame = new Frame(title);
            frame.setIgnoreRepaint(true);
            frame.setResizable(false);
            frame.setVisible(true);
            setupInput();
        }
        applyScreenResolution();
        createBuffers();
    }

    /**
     * Converts a screen resolution into a display mode.
     * @param screenResolution the screen resolution to convert.
     * @return the display mode that is the converted screen resolution.
     */
    private DisplayMode convertScreenResolution(ScreenResolution screenResolution) {
        return new DisplayMode(
                screenResolution.getScreenWidth(),
                screenResolution.getScreenHeight(),
                bitDepth, refreshRate
        );
    }
    
    /**
     * Sets the size of the window when in windowed mode, and sets the
     * display mode of the current graphics device when in fullscreen mode.
     */
    private void applyScreenResolution() {
        if (isWindowed()) {
            if (device.getFullScreenWindow()!=null && 
                    !screenResolution.equals(nativeResolution)) {
                device.setDisplayMode(convertScreenResolution(nativeResolution));
            }
            device.setFullScreenWindow(null);
            int w = getScreenWidth() + getInsetsLeft() + getInsetsRight();
            int h = getScreenHeight() + getInsetsTop() + getInsetsBottom();
            frame.setBounds(
                    (nativeResolution.getScreenWidth() / 2) - (w / 2),
                    (nativeResolution.getScreenHeight() / 2) - (h / 2),
                    w, h
            );
        } else {
            device.setFullScreenWindow(frame);
            device.setDisplayMode(convertScreenResolution(screenResolution));
        }
    }
    
    /**
     * Sets up the input handling for the window.
     */
    private void setupInput() {
        frame.addKeyListener(new KeyHandler());
        frame.addMouseListener(new MouseHandler());
    }
    
    /**
     * Creates the buffers for double buffered drawing.
     */
    private void createBuffers() {
        if (frame.getBufferStrategy()!=null) {
            frame.getBufferStrategy().dispose();
        }
        frame.createBufferStrategy(2);
    }

    /**
     * Gets the Graphics object to draw to the screen.
     *
     * @return the graphics object to draw on the screen
     */
    public Graphics getDrawGraphics() {
        if (frame.getBufferStrategy() == null) {
            frame.createBufferStrategy(2);
        }
        return frame.getBufferStrategy().getDrawGraphics();
    }

    /**
     * Swaps the buffers
     *
     * @param g the graphics that was used to draw to the GameWindow
     */
    public void swapBuffers(Graphics g) {
        g.dispose();
        frame.getBufferStrategy().show();
    }

    /**
     * Destroys the window and all of its used resources.
     */
    public void destroy() {
        if (frame != null) {
            frame.dispose();
        }
    }

    /**
     * Gets the screen width in pixels.
     *
     * @return the screen width in pixels.
     */
    public int getScreenWidth() {
        return screenResolution.getScreenWidth();
    }

    /**
     * Gets the screen height in pixels.
     *
     * @return the screen height in pixels.
     */
    public int getScreenHeight() {
        return screenResolution.getScreenHeight();
    }

    public void requestToClose() {
        this.requestToClose = false;
    }

    /**
     * Gets the screen resolution.
     *
     * @return the screen resolution.
     */
    public ScreenResolution getScreenResolution() {
        return screenResolution;
    }

    /**
     * Gets the mouse position in the screen's coordinate space.
     * Returns (-1, -1) if the mouse
     * position is not on the screen.
     * @return the mouse position.
     */
    public Point getMousePosition() {
        try {
            Point p = frame.getMousePosition();
            p.x += getInsetsLeft();
            p.y += getInsetsTop();
            return p;
        } catch(NullPointerException e) {
            return new Point(-1, -1);
        }
    }
    
    /**
     * For internal use only
     * @param runner 
     */
    void giveGameStateRunner(GameStateRunner runner) {
        this.runner = runner;
    }
    
    /**
     * The internal key handler for GameWindow.
     */
    class KeyHandler implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            if (runner!=null) {
                runner.keyTyped(e);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (runner!=null) {
                runner.keyPressed(e);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (runner!=null) {
                runner.keyReleased(e);
            }
        }
        
    }
    
    /**
     * The internal mouse handler for GameWindow.
     */
    class MouseHandler implements MouseListener {

        /**
         * Translates the coordinates to the correct space.
         * @param e the mouse event to transform
         */
        private void translateMouseEvent(MouseEvent e) {
            e.translatePoint(getInsetsLeft(), getInsetsTop());
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            if (runner!=null) {
                translateMouseEvent(e);
                runner.mouseClicked(e);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (runner!=null) {
                translateMouseEvent(e);
                runner.mousePressed(e);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (runner!=null) {
                translateMouseEvent(e);
                runner.mouseReleased(e);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (runner!=null) {
                translateMouseEvent(e);
                runner.mouseEntered(e);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (runner!=null) {
                translateMouseEvent(e);
                runner.mouseExited(e);
            }
        }
    }
}
