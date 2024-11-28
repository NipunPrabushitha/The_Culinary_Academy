package org.example.bo.custom.impl;

import org.example.bo.custom.AdminBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.AdminDAO;
import org.example.dao.custom.impl.AdminDAOImpl;
import org.example.entity.Admin;
import org.example.model.AdminDTO;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public ArrayList<AdminDTO> getAllAdmins() {
        AdminDAO adminDAO = new AdminDAOImpl();
        List<Admin> adminList = adminDAO.getAll();
        ArrayList<AdminDTO> adminDTOList = new ArrayList<>();

        for (Admin admin : adminList) {
            adminDTOList.add(new AdminDTO(admin.getUserId(), admin.getUserName(), admin.getPassword(), admin.getForgetPassword(), admin.getRole()));
        }

        return adminDTOList;
    }

    @Override
    public boolean updatePassword(String newPassword, String userName) {
        AdminDAO adminDAO = new AdminDAOImpl();
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        return adminDAO.update(hashedPassword, userName);
    }

}
