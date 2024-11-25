package org.example.dao;

import org.example.dao.custom.impl.AdminDAOImpl;
import org.example.dao.custom.impl.CoordinatorDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {
    }
    public static DAOFactory getDAOFactory() {
        return (daoFactory == null)? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DAOType {
        ADMIN,COORDINATOR
    }
    public SuperDAO getDAO(DAOType types) {
        switch (types){
            case COORDINATOR:
                return new CoordinatorDAOImpl();
            case ADMIN:
                return new AdminDAOImpl();
            default:
                return null;
        }
    }
}
