package services.app.emailservice.service.intf;

public interface EmailService {
    public void sendMailTo(String email, String subject, String message);
}