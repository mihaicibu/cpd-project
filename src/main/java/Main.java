import sockets.Handler;
import sockets.RingNode;

public class Main {

    public static void main(String[] args) {

        RingNode firstNode = new RingNode(1, 5050, true, 7070, 6060);
        RingNode secondNode = new RingNode(2, 6060, false, 5050, 7070);
        RingNode thirdNode = new RingNode(3, 7070, false, 6060, 5050);

        Handler firstHandler = new Handler(firstNode, thirdNode, secondNode);
        Handler secondHandler = new Handler(secondNode, firstNode, thirdNode);
        Handler thirdHandler = new Handler(thirdNode, secondNode, firstNode);

        firstHandler.start();
        secondHandler.start();
        thirdHandler.start();
    }
}
