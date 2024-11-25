package org.example.bo.custom.impl;

import org.example.bo.custom.AdminBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.AdminDAO;
import org.example.dao.custom.impl.AdminDAOImpl;
import org.example.entity.Admin;
import org.example.model.AdminDTO;
import org.mindrot.jbcrypt.BCrypt;

public class AdminBOImpl implements AdminBO {
    AdminDAO adminDAO = (AdminDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ADMIN);

    @Override
    public boolean saveAdmin(AdminDTO admin) {
        /*AdminDAO adminDAO = new AdminDAOImpl();
        return adminDAO.save(new Admin(admin.getUserId(), admin.getUserName(),admin.getPassword(),admin.getForgetPassword()));*/

        // Hash the password using BCrypt
        String hashedPassword = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt());

        // Replace the plain password with the hashed one
        return adminDAO.save(new Admin(admin.getUserId(), admin.getUserName(),hashedPassword,admin.getForgetPassword(),admin.getRole()));

    }

    @Override
    public String getIdByUserName(String username) {
        AdminDAO adminDAO = new AdminDAOImpl();
        return adminDAO.getId(username);
    }

}
