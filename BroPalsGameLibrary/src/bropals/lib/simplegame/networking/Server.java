/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.networking;

import bropals.lib.simplegame.logger.ErrorLogger;
import bropals.lib.simplegame.logger.InfoLogger;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implements the server-side of a basic client-server system.
 * @author Jonathon
 */
public class Server {
    
    private final List<ClientHandler> clientHandlers =
            Collections.synchronizedList(new ArrayList<ClientHandler>());
    private boolean runningServer = false;
    private final ServerSocket serverSocket;
    private final ServerMessageHandler messageHandler;
    
    /**
     * Creates a new server at the specified port.
     * @param port the port to create the server
     * @param messageHandler the message handler that the server should use.
     * @throws IOException thrown if something goes wrong while making the
     * server.
     */
    public Server(int port, ServerMessageHandler messageHandler) throws IOException {
        serverSocket = new ServerSocket(port);
        this.messageHandler = messageHandler;
    }
    
    /**
     * Has the server start to listen for clients. This thread may be
     * blocked while the server listens for a new client.
     */
    public void startServer() {
        runningServer = true;
        while (runningServer) {
            try {
                Socket socket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(this, socket, 
                        messageHandler);
                clientHandlers.add(handler);
                handler.start();
                InfoLogger.println("A new client has connected to the server");
            } catch(IOException ioe) {
                ErrorLogger.println("An error occured in the server: " + ioe);
                runningServer = false;
            }
        }
        InfoLogger.println("The server has stopped listening for new clients.");
    }
    
    /**
     * Broadcast a message to all clients except for the one who sent it.
     * @param sentMessage the one who sent the message.
     * @param message the message to broadcast.
     */
    public void broadcastMessage(ClientHandler sentMessage, String message) {
        synchronized(clientHandlers) {
            for (ClientHandler handler : clientHandlers) {
                if (!handler.equals(sentMessage)) {
                    handler.sendMessageToClient(message);
                }
            }
        }
    }
    
    /**
     * Broadcast a message to all clients including the one who sent it.
     * @param message the message to broadcast.
     */
    public void broadcastMessage(String message) {
        synchronized(clientHandlers) {
            for (ClientHandler handler : clientHandlers) {
                handler.sendMessageToClient(message);
            }
        }
    }

    /**
     * Remove a ClientHandler from the server's list of handlers.
     * @param handler The handler being removed from the server's list of handlers.
     */
    public void removeHandler(ClientHandler handler) {
        clientHandlers.remove(handler);
        InfoLogger.println("Server now has " + clientHandlers.size() + " clients");
    }
}
