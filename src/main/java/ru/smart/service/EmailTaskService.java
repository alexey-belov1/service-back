package ru.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.smart.dao.EmailTaskDAO;
import ru.smart.domain.EmailTask;

import java.util.List;

@Service
public class EmailTaskService {

    private EmailTaskDAO emailTaskDAO;

    public EmailTaskService(EmailTaskDAO emailTaskDAO) {
        this.emailTaskDAO = emailTaskDAO;
    }

    @Transactional(readOnly = true)
    public List<EmailTask> findAllWithLimit(int limit) {
        return this.emailTaskDAO.findAllWithLimit(limit);
    }
}
