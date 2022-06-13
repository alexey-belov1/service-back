package ru.smart.dao;

import org.springframework.stereotype.Repository;
import ru.smart.domain.User;

import java.util.Optional;

@Repository
public class UserDAO extends BaseDAO<User> {

    public UserDAO() {
        setClazz(User.class);
    }

    public Optional<User> findByLogin(String login) {
        User user = (User) getCurrentSession().createQuery("from User where login = :login")
                .setParameter("login", login)
                .uniqueResult();
        return Optional.ofNullable(user);
    }
}
