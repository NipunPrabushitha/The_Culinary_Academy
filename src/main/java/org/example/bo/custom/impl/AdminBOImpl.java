package org.example.bo.custom.impl;

import org.example.bo.custom.AdminBO;
import org.example.dao.custom.AdminDAO;
import org.example.dao.custom.impl.AdminDAOImpl;
import org.example.entity.Admin;
import org.example.model.AdminDTO;

public class AdminBOImpl implements AdminBO {

    @Override
    public boolean saveAdmin(AdminDTO admin) {
        AdminDAO adminDAO = new AdminDAOImpl();
        return adminDAO.save(new Admin(admin.getUserId(), admin.getUserName(),admin.getPassword(),admin.getForgetPassword()));
    }

}
