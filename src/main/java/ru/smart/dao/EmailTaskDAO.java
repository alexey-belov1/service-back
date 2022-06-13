package ru.smart.dao;

import org.springframework.stereotype.Repository;
import ru.smart.domain.EmailTask;

import java.util.List;

@Repository
public class EmailTaskDAO extends BaseDAO<EmailTask> {

    public EmailTaskDAO() {
        setClazz(EmailTask.class);
    }

    public List<EmailTask> findAllWithLimit(int limit) {
        return getCurrentSession().createQuery("from EmailTask")
                .setMaxResults(limit)
                .list();
    }
}
