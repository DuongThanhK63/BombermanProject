package exceptions;

public class LevelExceptions extends Exception{

    public LevelExceptions() {
    }

    public LevelExceptions(String message) {
        super(message);
    }

    public LevelExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public LevelExceptions(Throwable cause) {
        super(cause);
    }
}
