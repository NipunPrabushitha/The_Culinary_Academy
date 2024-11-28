package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.model.AdminDTO;

import java.util.ArrayList;

public interface AdminBO extends SuperBO {
    boolean saveAdmin(AdminDTO admin);

    String getIdByUserName(String username);

    ArrayList<AdminDTO> getAllAdmins();

    boolean updatePassword(String newPassword, String userName);
}
