package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.CoordinatorDAO;
import org.example.entity.Coordinator;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
}
