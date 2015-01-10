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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 **/
package bropals.lib.simplegame.test;

import bropals.lib.simplegame.*;
import bropals.lib.simplegame.io.PropertiesReader;
import bropals.lib.simplegame.logger.ErrorLogger;
import bropals.lib.simplegame.logger.InfoLogger;
import bropals.lib.simplegame.networking.Server;
import bropals.lib.simplegame.util.AssetBank;
import bropals.lib.simplegame.util.AssetLoader;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Owner
 */
public class TesterClass {

    public static void main(String[] args) {
        //Test the server
        try {
            Server server = new Server(17373, new TestServerMessageHandler());
            server.startServer();
        } catch(IOException e) {
            ErrorLogger.println("Error while making server: " + e);
        }
        /* 
        // make a window
        GameWindow window = new GameWindow("Super cool", 500, 350);

        // make a GameStateRunner that is using the newly made window with
        // an inital GameState.
        GameStateRunner runner = new GameStateRunner(window, new TestState());

        // begin looping the game!
        runner.loop();
        */
    }
}
