package logging;

public class Logger {

    public <T> void log(T message) {
        System.out.println("The message is: " + message);
    }
}
