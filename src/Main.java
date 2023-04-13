public class Main {

    public static EventListener eventListener;
    public static FrameHandler frameHandler;

    public static void main(String[] args){
        eventListener = new EventListener();
        frameHandler = new FrameHandler();
        frameHandler.displayLogin();
    }
}
