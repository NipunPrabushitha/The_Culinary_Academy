package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.controller.AdminRegistration;
import org.example.dao.custom.AdminDAO;
import org.example.entity.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    @Override
    public boolean save(Admin admin) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(admin);
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
            String sql = "SELECT CAST(password AS CHAR) FROM Admin WHERE userName = :userName";
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

    @Override
    public ArrayList<Admin> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        ArrayList<Admin> admins = new ArrayList<>();

        String sql = "SELECT * FROM Admin";

        List<Object[]> adminList = session.createNativeQuery(sql).getResultList();
        for (Object[] admin : adminList) {
            String UserId = (String) admin[0];
            String userName = (String) admin[4];
            String password = (String) admin[2];
            String forgetPassword = (String) admin[1];
            String role = (String) admin[3];

            Admin admin1 = new Admin(UserId, userName, password, forgetPassword, role);
            admins.add(admin1);
        }
        transaction.commit();
        session.close();

        return admins;
    }

}
