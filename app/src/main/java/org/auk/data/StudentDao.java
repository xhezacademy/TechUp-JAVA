package org.auk.data;

import org.auk.models.Student;
import org.auk.utils.DbUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class StudentDao implements BaseDao<Student> {

    public void save(Student student) {
        Transaction transaction = null;

        try (var sessionFactory = DbUtil.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.getStackTrace();
        }
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void delete(Student student) {

    }

    public Student findById(int id) {
        Student student = null;
        Transaction transaction = null;

        try (var session = DbUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Student where id = (:id)");
            query.setParameter("id", id);
            student = (Student) query.getSingleResult();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return student;
    }

    public List<Student> getAll() {
        List<Student> studentList = new ArrayList<>();
        Transaction transaction = null;

        try (var session = DbUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            studentList = session.createQuery("from Student", Student.class).getResultList();
            transaction.commit();
        } catch (HibernateException e) {

            e.printStackTrace();
        }

        return studentList;
    }

}
