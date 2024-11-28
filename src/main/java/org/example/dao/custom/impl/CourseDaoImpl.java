package org.example.dao.custom.impl;


import org.example.config.FactoryConfiguration;
import org.example.dao.custom.CourseDao;
import org.example.entity.Course;
import org.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {


    @Override
    public boolean save(Student enitiy) throws IOException {
        return false;
    }

    @Override
    public boolean update(Course entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);


        transaction.commit();
        session.close();


        return false;
    }

    @Override
    public boolean delete(int id) throws IOException {
        return false;
    }

    @Override
    public boolean delete(String entityId) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        try {
            session.beginTransaction();
            Course course = session.get(Course.class,entityId);
            session.delete(course);
            session.getTransaction().commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public List<Course> getaAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Course> list = session.createQuery("from Course ", Course.class).list();


        transaction.commit();
        session.close();


        return list;
    }


    @Override
    public List<Course> SearchCID(String cid) throws IOException {
        List<Course> courseList = new ArrayList<>();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            courseList = session.createQuery("FROM Course WHERE programId = :cid", Course.class)
                    .setParameter("cid", cid)
                    .getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return courseList;
    }

    @Override
    public Course searchByCId(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        Course course = null;

        try {
            transaction = session.beginTransaction();

            // find Customer by ID
            String hql = "FROM Course WHERE programId = :id";
            Query<Course> query = session.createQuery(hql, Course.class);
            query.setParameter("id", id);
            course = query.uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return course;
    }

    @Override
    public boolean save(Course course) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(course);


        transaction.commit();
        session.close();


        return false;
    }


}
