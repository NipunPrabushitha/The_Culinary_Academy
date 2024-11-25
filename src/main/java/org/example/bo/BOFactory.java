package org.example.bo;

import org.example.bo.custom.impl.AdminBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    public BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return (boFactory == null)? boFactory = new BOFactory() : boFactory;
    }
    public enum BOTypes {
        ADMIN
    }
    public SuperBO getBO(BOTypes types) {
        switch (types){
            case ADMIN:
                return new AdminBOImpl();
                default:
                return null;
        }
    }
}
