package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient extends Thread {

    private static final String LOCALHOST = "localhost";

    private Socket socket;
    private BufferedReader read;
    private PrintWriter write;
    private final RingNode node;

    public SocketClient(RingNode node) {
        this.node = node;
    }

    public void connect(){
        try {
            System.out.println(node.getId() + " Start connecting to port: " + node.getPort());

            socket = new Socket(LOCALHOST, node.getPort());
            read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            write = new PrintWriter(socket.getOutputStream());

            start();
        } catch (IOException e) {
            System.out.println("Error during connection!");
        }
    }

    public void disconnect() {
        try {
            read.close();
            write.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error occurred when trying to disconnect!");
        }
    }

    public void sendMessage(String message) {
        write.println(message);
    }

    public String readMessages() {
        String message = null;
        try {
            message =  read.readLine();
        } catch (IOException e) {
            System.out.println("Error reading message!");
        }
        return message;
    }

    public boolean isConnectionOpen() {
        return socket.isConnected() && super.isAlive();
    }

    @Override
    public void run() {
        String input = null;

        while (socket.isConnected()) {
            try {
                input = read.readLine();
            } catch (IOException e) {
                System.out.println("Error!");
            }

            System.out.println("Connection closed!");
        }
    }
}
