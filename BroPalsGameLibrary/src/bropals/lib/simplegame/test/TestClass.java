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
package bropals.lib.simplegame.test;

import bropals.lib.simplegame.logger.InfoLogger;
import bropals.lib.simplegame.state.GameState;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Jonathon
 */
public class TestClass extends GameState {

    private int offset = 100;
    private int vel = 1;
    
    public static void main(String[] ars) {
        
    }
    
    @Override
    public void update() {
        offset += vel;
        if (offset > 300) {
            vel = -1;
        }
        if (offset < 100) {
            vel = 1;
        }
    }

    @Override
    public void render(Object graphicsObj) {
        Graphics g = (Graphics)graphicsObj;
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, getWindow().getScreenWidth(), getWindow().getScreenHeight());
        g.setColor(Color.RED);
        g.drawImage(getImage("test"), 100, 0, offset, getWindow().getScreenHeight(), null);
    }

    @Override
    public void onEnter() {
    }

    @Override
    public void onExit() {
    }

    @Override
    public void key(int keycode, boolean pressed) {
        
    }

    @Override
    public void mouse(int mousebutton, int x, int y, boolean pressed) {
        if (pressed) {
            InfoLogger.println("" + x + ", " + y);
        }
    }
}
