package org.example.bo;

import org.example.bo.custom.impl.AdminBOImpl;
import org.example.bo.custom.impl.CourseBOImpl;
import org.example.bo.custom.impl.RegistrationBOImpl;
import org.example.bo.custom.impl.StudentBoImpl;

public class BOFactory {
    private static BOFactory boFactory;

    public BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return (boFactory == null)? boFactory = new BOFactory() : boFactory;
    }
    public enum BOTypes {
        ADMIN,STUDENT,COURSE,REGISTRATION
    }
    public SuperBO getBO(BOTypes types) {
        switch (types){
            case ADMIN:
                return new AdminBOImpl();
            case STUDENT:
                return new StudentBoImpl();
            case COURSE:
                return new CourseBOImpl();
            case REGISTRATION:
                return new RegistrationBOImpl();
            default:
                return null;
        }
    }
}

