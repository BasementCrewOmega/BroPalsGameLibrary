/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.test;

import bropals.lib.simplegame.logger.InfoLogger;
import bropals.lib.simplegame.networking.ClientHandler;
import bropals.lib.simplegame.networking.Server;
import bropals.lib.simplegame.networking.ServerMessageHandler;

/**
 * Testing server stuff
 * @author Jonathon
 */
public class TestServerMessageHandler implements ServerMessageHandler {

    @Override
    public void handleMessage(Server server, ClientHandler handler, String message) {
        InfoLogger.println(message);
    }
    
}
