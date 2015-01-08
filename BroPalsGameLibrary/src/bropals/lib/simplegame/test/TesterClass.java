/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
