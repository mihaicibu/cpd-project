package sockets;

public class RingNode {

    private int id;
    private int port;
    private boolean tokenAvailable;
    private int leftSocketPort;
    private int rightSocketPort;
    private int counter;
    private SocketClient leftConnection;
    private SocketClient rightConnection;

    public RingNode(int id, int port, boolean tokenAvailable, int leftSocketPort, int rightSocketPort) {
        this.id = id;
        this.port = port;
        this.tokenAvailable = tokenAvailable;
        this.leftSocketPort = leftSocketPort;
        this.rightSocketPort = rightSocketPort;
        this.counter = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isTokenAvailable() {
        return tokenAvailable;
    }

    public void setTokenAvailable(boolean tokenAvailable) {
        this.tokenAvailable = tokenAvailable;
    }

    public int getLeftSocketPort() {
        return leftSocketPort;
    }

    public void setLeftSocketPort(int leftSocketPort) {
        this.leftSocketPort = leftSocketPort;
    }

    public int getRightSocketPort() {
        return rightSocketPort;
    }

    public void setRightSocketPort(int rightSocketPort) {
        this.rightSocketPort = rightSocketPort;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public SocketClient getLeftConnection() {
        return leftConnection;
    }

    public void setLeftConnection(SocketClient leftConnection) {
        this.leftConnection = leftConnection;
    }

    public SocketClient getRightConnection() {
        return rightConnection;
    }

    public void setRightConnection(SocketClient rightConnection) {
        this.rightConnection = rightConnection;
    }
}
