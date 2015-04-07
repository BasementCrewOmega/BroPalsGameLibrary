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

import bropals.lib.simplegame.GameStateRunner;
import bropals.lib.simplegame.LWJGLGameWindow;
import bropals.lib.simplegame.io.AssetManager;
import bropals.lib.simplegame.logger.InfoLogger;
import bropals.lib.simplegame.state.GameState;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

/**
 *
 * @author Jonathon
 */
public class TestClass extends GameState {

    public static void main(String[] ars) {
        
        GameStateRunner runner = new GameStateRunner(new LWJGLGameWindow("Game", 640, 480), 
            new AssetManager(new File("test_files"), true));
        runner.setState(new TestClass());
        runner.loop();
    }
    
    @Override
    public void update() {
        
    }

    @Override
    public void render(Object graphicsObj) {        
//        glDrawArrays(GL_TRIANGLES, 0, 3);
        Graphics g = (Graphics)graphicsObj;
        g.setColor(Color.BLUE);
        g.fillRect(100, 100, 200, 200);
    }

    @Override
    public void onEnter() {
//        //Makes a rectangle with texture coordinates.
//        // ( x, y, textureX, textureY )
//        LWJGLContext context = ((LWJGLGameWindow)getWindow()).getContext();
//        
//        //Load shaders
//        context.loadVertexShader("vertexShader", getAssetManager().loadSource("vertexShader.txt"));
//        context.loadFragmentShader("fragmentShader", getAssetManager().loadSource("fragmentShader.txt"));
//        context.createProgram("program", "vertexShader", "fragmentShader");
//        context.useProgram("program");
//        
//        /* (x_pos, y_pos, red, green, blue, alpha) */
//        final float[] vertexData = new float[] {
//            0.1f, 0.1f, 1.0f, 0.0f, 0.0f, 1.0f,
//            0.9f, 0.1f, 0.8f, 0.2f, 0.0f, 1.0f,
//            0.1f, 0.9f, 1.0f, 0.0f, 0.2f, 1.0f,
//            0.9f, 0.9f, 0.6f, 0.4f, 0.0f, 1.0f
//        };
//        context.bufferVertexDataFloat("data", vertexData, GL_STATIC_DRAW);
//        context.vertexAttribPointer("vertexPosition", "program", 2, GL_FLOAT, false, (Float.SIZE/8)*6, 0);
//        context.vertexAttribPointer("fragColorIn", "program", 4, GL_FLOAT, false, (Float.SIZE/8)*6, (Float.SIZE/8)*2);
//        context.enableVertexAttribArray("vertexPosition", "program");
//        context.enableVertexAttribArray("fragColorIn", "program");
//        InfoLogger.println("Init arbeit");
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
