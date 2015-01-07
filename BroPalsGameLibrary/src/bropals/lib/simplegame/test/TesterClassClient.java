/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.test;

import bropals.lib.simplegame.logger.ErrorLogger;
import bropals.lib.simplegame.networking.Client;
import java.io.IOException;
import java.net.InetAddress;

/**
 *
 * @author Jonathon
 */
public class TesterClassClient {

    public static void main(String[] args) {
        //Test the client
        try {
            Client client = new Client(InetAddress.getByName("192.168.1.74"), 17373, new TestClientMessageHandler());
            client.listenToServer();
        } catch (IOException e) {
            ErrorLogger.println("Error while making server: " + e);
        }
    }
}
