/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.networking;

import bropals.lib.simplegame.logger.ErrorLogger;
import bropals.lib.simplegame.logger.InfoLogger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Contains the skeleton to send messages to the server by the client, and to
 * send messages to the client by the server.
 *
 * @author Jonathon
 */
public class ClientHandler extends Thread {

    private static int currentId = 0;

    private static int nextId() {
        currentId++;
        return currentId;
    }

    private Server server;
    private Socket socket;
    private BufferedReader fromClient;
    private PrintStream toClient;
    private ServerMessageHandler serverMessageHandler;

    /**
     * Creates a client handler. Should only be called by the Server object.
     *
     * @param server the server that is keeping track of this client handler.
     * @param socket the socket that this ClientHandler is wrapping.
     * @param serverMessageHandler the message handler to use for this
     * ClientHandler.
     */
    ClientHandler(Server server, Socket socket, ServerMessageHandler serverMessageHandler) throws IOException {
        super("Client " + nextId());
        this.server = server;
        this.socket = socket;
        this.serverMessageHandler = serverMessageHandler;
        fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toClient = new PrintStream(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            String str;
            while ((str = fromClient.readLine()) != null) {
                serverMessageHandler.handleMessage(server, this, str);
            }
            fromClient.close();
            toClient.flush();
            toClient.close();
            socket.close();
        } catch (IOException e) {
            ErrorLogger.println("Error in server while communicating with "
                    + "client: " + e);
        }
        InfoLogger.println("ClientHandler lost connection to its client: "
                + "removing handler");
        this.server.removeHandler(this);
        server = null;
        socket = null;
    }
    
    public void sendMessageToClient(String message) {
        toClient.println(message);
    }
}
