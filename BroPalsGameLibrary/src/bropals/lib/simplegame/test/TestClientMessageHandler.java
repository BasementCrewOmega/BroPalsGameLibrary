/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.test;

import bropals.lib.simplegame.logger.InfoLogger;
import bropals.lib.simplegame.networking.Client;
import bropals.lib.simplegame.networking.ClientMessageHandler;

/**
 * Testing client stuff.
 * @author Jonathon
 */
public class TestClientMessageHandler implements ClientMessageHandler {

    @Override
    public void handleMessage(Client client, String message) {
        InfoLogger.println(message);
    }
}
