package ru.smart.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.smart.domain.EmailTask;
import ru.smart.service.EmailService;
import ru.smart.service.EmailTaskService;

import java.util.List;

@Component
public class EmailTaskScheduler {

    private EmailService emailService;
    private EmailTaskService emailTaskService;

    public EmailTaskScheduler(EmailService emailService, EmailTaskService emailTaskService) {
        this.emailService = emailService;
        this.emailTaskService = emailTaskService;
    }

    @Scheduled(fixedDelay = 60000)
    public void scheduleFixedDelaySendEmail() {
        List<EmailTask> list = this.emailTaskService.findAllWithLimit(10);
        list.forEach(emailService::send);
    }
}
