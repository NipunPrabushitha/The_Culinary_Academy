package org.example.dao.custom.impl;


import org.example.config.FactoryConfiguration;
import org.example.dao.custom.StudentDao;
import org.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public boolean save(Student dto) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(dto);

            transaction.commit();
            return true;  // Indicating success
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;  // Indicating failure
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Student dto) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(dto);

        transaction.commit();
        session.close();

        return false;
    }

    @Override
    public boolean delete(int entityId) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();

        try {
            session.beginTransaction();
            Student student = session.get(Student.class,entityId);
            session.delete(student);
            session.getTransaction().commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public List<Student> getaAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Student> list = session.createQuery("from Student ", Student.class).list();

        transaction.commit();
        session.close();


        return list;
    }

    @Override
    public List<Student> SearchSID(int sid) throws IOException {
        List<Student> studentModels = new ArrayList<>();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            studentModels = session.createQuery("FROM Student WHERE id = :sid", Student.class)
                    .setParameter("sid", sid)
                    .getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return studentModels;
    }

    @Override
    public Student searchById(int id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        Student student = null;

        try {
            transaction = session.beginTransaction();
            // find student
            String hql = "FROM Student WHERE id = :id";
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("id", id);
            student = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return student;
    }
}
