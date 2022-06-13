package ru.smart.dao;

import org.springframework.stereotype.Repository;
import ru.smart.domain.Subject;

@Repository
public class SubjectDAO extends BaseDAO<Subject> {

    public SubjectDAO() {
        setClazz(Subject.class);
    }
}
