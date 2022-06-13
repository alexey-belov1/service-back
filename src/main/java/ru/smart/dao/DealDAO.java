package ru.smart.dao;

import org.springframework.stereotype.Repository;
import ru.smart.domain.Deal;

import java.util.List;

@Repository
public class DealDAO extends BaseDAO<Deal> {

    public DealDAO() {
        setClazz(Deal.class);
    }

    public List<Deal> findAllWithSubject() {
        return getCurrentSession().createQuery("select d from Deal d join fetch d.subject").list();
    }
}
