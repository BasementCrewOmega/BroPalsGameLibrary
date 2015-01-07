/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.networking;

/**
 * Lets the application decide how the client interprets messages from the
 * server.
 * @author Jonathon
 */
public interface ClientMessageHandler {
    
    /**
     * Handle a message that has come from the server.
     * @param message the message that was received from the server.
     */
    void handleMessage(String message);
}
