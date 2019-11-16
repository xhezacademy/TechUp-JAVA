package org.auk.entity;

import org.auk.util.DbUtil;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.util.Optional;

public class UserDao {

    public Optional<User> findByUsername(String username) {
        Optional<User> user = Optional.empty();
        Transaction transaction;

        try (var session = DbUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            user = session.createQuery("from User a where a.username = :username", User.class)
                    .setParameter("username", username)
                    .setMaxResults(1)
                    .getResultList()
                    .stream()
                    .findFirst();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return user;
    }
}
