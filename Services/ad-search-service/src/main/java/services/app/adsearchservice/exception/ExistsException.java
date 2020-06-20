package services.app.adsearchservice.exception;

@SuppressWarnings("serial")
public class ExistsException extends RuntimeException {

    public ExistsException() {
    }

    public ExistsException(String message) {
        super(message);
    }
}
