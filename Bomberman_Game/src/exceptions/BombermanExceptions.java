package exceptions;

public class BombermanExceptions extends Exception{

    public BombermanExceptions() {
    }

    public BombermanExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public BombermanExceptions(String message) {
        super(message);
    }

    public BombermanExceptions(Throwable cause) {
        super(cause);
    }
}
