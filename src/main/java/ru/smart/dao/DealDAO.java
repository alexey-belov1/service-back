package ru.smart.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.smart.domain.Deal;
import ru.smart.service.filter.FilterDB;

import java.util.List;

@Repository
public class DealDAO extends BaseDAO<Deal> {

    public DealDAO() {
        setClazz(Deal.class);
    }

    public List<Deal> findAllWithSubjectAndFilters(List<FilterDB> filters) {
        Session session = getCurrentSession();
        for (FilterDB filter : filters) {
            session.enableFilter(filter.getName());
            if (filter.withParameter()) {
                session.getEnabledFilter(filter.getName()).setParameter(filter.getParamName(), filter.getParamValue());
            }
        }
        return session.createQuery("select d from Deal d join fetch d.subject order by d.created desc").list();
    }
}
