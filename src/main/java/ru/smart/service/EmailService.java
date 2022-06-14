package ru.smart.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.smart.dao.EmailTaskDAO;
import ru.smart.domain.EmailTask;

@Service
public class EmailService {

    private JavaMailSender emailSender;

    private EmailTaskDAO emailTaskDAO;

    public EmailService(JavaMailSender emailSender, EmailTaskDAO emailTaskDAO) {
        this.emailSender = emailSender;
        this.emailTaskDAO = emailTaskDAO;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Async("asyncExecutor")
    public void send(EmailTask emailTask) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(emailTask.getRecipient());
        message.setSubject(emailTask.getTheme());
        message.setText(emailTask.getText());
        this.emailSender.send(message);
        this.emailTaskDAO.delete(emailTask);
    }
}
