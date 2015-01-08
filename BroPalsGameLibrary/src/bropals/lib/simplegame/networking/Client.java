/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.networking;

import bropals.lib.simplegame.logger.ErrorLogger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Implements the client-side of a basic client-server program.
 * @author Jonathon
 */
public class Client extends Thread {
    
    private Socket socket;
    private BufferedReader fromServer;
    private PrintStream toServer;
    private final ClientMessageHandler messageHandler;
    private boolean running = false;
    
    /**
     * Creates a new client using the given message handler and attempts to connect it at the specified
     * address.
     * @param address the address of the server to connect to
     * @param port the port number. Should be the same as the server's port.
     * @param messageHandler the message handler that this Client should use.
     * @throws IOException thrown is something goes wrong in connecting to the
     *  server.
     */
    public Client(InetAddress address, int port, ClientMessageHandler messageHandler) throws IOException {
        socket = new Socket(address, port);
        this.messageHandler = messageHandler;
        fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toServer = new PrintStream(socket.getOutputStream(), true);
    }
    
    public void closeClient() {
        running = false;
        try {
            fromServer.close();
            toServer.close();
            socket.close();
        } catch(IOException e) {
            ErrorLogger.println("Unable to close client: " + e);
        }
    }

    /**
     * Has this client begin to listen to the server for messages.
     */
    public void listenToServer() {
        start();
    }
    
    public void sendMessageToServer(String message) {
        toServer.println(message);
    }
    
    @Override
    public void run() {
        try {
            String str;
            while ( (str = fromServer.readLine()) != null ) {
                messageHandler.handleMessage(this, str);
            }
        } catch(IOException e) {
            ErrorLogger.println("Error in client while communicating with "
             + " the server: " + e);
        }
    }    
}
