package sockets;

import rabbitmq.Producer;
import rabbitmq.Receiver;

public class Handler extends Thread {

    private final RingNode node;
    private final RingNode leftNode;
    private final RingNode rightNode;
    private int timer;

    public Handler(RingNode node, RingNode leftNode, RingNode rightNode) {
        this.node = node;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.timer = 0;
    }

    public void openAndCheckLeftConnection() {
        if (this.leftNode.getLeftConnection() == null || !this.leftNode.getLeftConnection().isConnectionOpen()) {
            SocketClient leftSocketClient = new SocketClient(this.leftNode);
            leftSocketClient.connect();
            System.out.println("Open connection to left node!");
            this.leftNode.setLeftConnection(leftSocketClient);
        }
    }

    public void openAndCheckRightConnection() {
        if (this.rightNode.getRightConnection() == null || !this.rightNode.getRightConnection().isConnectionOpen()) {
            SocketClient rightSocketClient = new SocketClient(this.leftNode);
            rightSocketClient.connect();
            System.out.println("Open connection to right node!");
            this.rightNode.setRightConnection(rightSocketClient);
        }
    }

    public void sendMessages() {

    }

    public void sendTokenToRight() {
        this.node.setTokenAvailable(false);
        this.rightNode.setTokenAvailable(true);
        this.timer = 0;
    }

    public void readMessages() {
        if (this.rightNode.getRightConnection() != null) {
            String messageFromRight = this.rightNode.getRightConnection().readMessages();
            if (messageFromRight != null) {
                System.out.println("Client: " + this.node.getId() + " Received message: " + messageFromRight + " from " + this.rightNode.getId());
            }
        }

        if (this.leftNode.getLeftConnection() != null) {
            String messageFromLeft = this.leftNode.getLeftConnection().readMessages();
            if (messageFromLeft != null) {
                System.out.println("Client: " + this.node.getId() + " Received message: " + messageFromLeft + " from " + this.leftNode.getId());
            }
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (node.isTokenAvailable()) {
                    //open connection to left and right nodes
                    openAndCheckLeftConnection();
                    openAndCheckRightConnection();

                    //send messages
                    sendMessages();

                    Thread.sleep(1000);
                    this.timer++;

                    if (this.timer == 10) {
                        //send token to right
                        sendTokenToRight();
                    }
                } else {
                    //read messages - not allowed to publish, only subscribe
                    readMessages();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Error!");
        }
    }
}
