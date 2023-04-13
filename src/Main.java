public class Main {

    public static EventListener eventListener;
    public static FrameHandler frameHandler;

    public static void main(String[] args) throws Exception{
        eventListener = new EventListener();
        frameHandler = new FrameHandler();
        frameHandler.displayWelcome();
    }
}
