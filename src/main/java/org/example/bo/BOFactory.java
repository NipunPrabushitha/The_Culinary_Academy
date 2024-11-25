package org.example.bo;

import org.example.bo.custom.impl.AdminBOImpl;
import org.example.bo.custom.impl.CoordinatorBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    public BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return (boFactory == null)? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        ADMIN,COORDINATOR
    }
    public SuperBO getBO(BOTypes types) {
        switch (types){
            case ADMIN:
                return new AdminBOImpl();
            case COORDINATOR:
                return new CoordinatorBOImpl();
            default:
                return null;
        }
    }
}
