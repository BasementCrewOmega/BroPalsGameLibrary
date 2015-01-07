/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.networking;

/**
 * Lets the application decide how the server interprets messages from the
 * client.
 * @author Jonathon
 */
public interface ServerMessageHandler {
    
    /**
     * Handle a message that has came from the client corresponding 
     * ClientHandler.
     * @param server the server that this client is associated with.
     * @param handler the client handler that is using this ServerMessageHandler.
     * @param message the message sent from the client to the server.
     */
    void handleMessage(Server server, ClientHandler handler, String message);
}
