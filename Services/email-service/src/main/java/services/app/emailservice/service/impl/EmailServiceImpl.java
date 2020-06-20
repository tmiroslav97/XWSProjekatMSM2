package services.app.emailservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import services.app.emailservice.service.intf.EmailService;

public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment environment;

    @Override
    @Async
    public void sendMailTo(String email, String subject, String message) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom(environment.getProperty("spring.mail.username"));
        mail.setSubject(subject);
        mail.setText(message);
        javaMailSender.send(mail);

    }
}
