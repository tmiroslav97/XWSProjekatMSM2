package services.app.messageservice.exception;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

}
