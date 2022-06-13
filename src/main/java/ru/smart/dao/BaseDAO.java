package ru.smart.dao;

import org.hibernate.*;
import org.postgresql.shaded.com.ongres.scram.common.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class BaseDAO<T> {

    private Class<T> clazz;

    @Autowired
    protected SessionFactory sessionFactory;

    public final void setClazz(final Class<T> clazz) {
        this.clazz = Preconditions.checkNotNull(clazz, "clazz");
    }

    public Optional<T> findOne(final long id) {
        T entity = getCurrentSession().get(clazz, id);
        return Optional.ofNullable(entity);
    }

    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    public T create(final T entity) {
        Preconditions.checkNotNull(entity, "entity");
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(final T entity) {
        Preconditions.checkNotNull(entity, "entity");
        getCurrentSession().update(entity);
        return entity;
    }

    public void delete(final T entity) {
        Preconditions.checkNotNull(entity, "entity");
        getCurrentSession().delete(entity);
    }

    public void deleteById(final long entityId) {
        T entity = findOneNotSafe(entityId);
        Preconditions.checkArgument(entity != null, "entity");
        delete(entity);
    }

    private T findOneNotSafe(final long id) {
        return getCurrentSession().get(clazz, id);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
