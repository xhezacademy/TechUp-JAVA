package org.auk.entity;

import org.auk.util.DbUtil;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArticleDao {

    public List<Article> getAll() {
        List<Article> articleList = new ArrayList<>();
        Transaction transaction = null;

        try (var session = DbUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            articleList = session.createQuery("from Article", Article.class).getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return articleList;
    }

    public Optional<Article> findBySlug(String slug) {
        Optional<Article> article = Optional.empty();
        Transaction transaction;

        try (var session = DbUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            article = session.createQuery("from Article a where a.slug = :slug", Article.class)
                    .setParameter("slug", slug)
                    .setMaxResults(1)
                    .getResultList()
                    .stream()
                    .findFirst();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return article;
    }

    public static <T> Optional<T> findOrEmpty(final DaoRetriever<T> retriever) {
        try {
            return Optional.of(retriever.retrieve());
        } catch (NoResultException ex) {
            //log
        }
        return Optional.empty();
    }
}
