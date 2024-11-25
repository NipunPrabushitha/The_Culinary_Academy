package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.CoordinatorDAO;
import org.example.entity.Coordinator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

public class CoordinatorDAOImpl implements CoordinatorDAO {

    @Override
    public boolean save(Coordinator coordinator) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(coordinator);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public String getId(String username) {
        String password = null;

        // Obtain Hibernate session
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Native SQL query to fetch userId based on userName
            String sql = "SELECT CAST(password AS CHAR) FROM Coordinator WHERE userName = :userName";
            NativeQuery query = session.createNativeQuery(sql);
            query.setParameter("userName", username);

            // Retrieve the result as a String
            password = (String) query.getSingleResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return password;
    }
}
