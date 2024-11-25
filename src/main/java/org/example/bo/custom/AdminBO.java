package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.model.AdminDTO;

public interface AdminBO extends SuperBO {
    boolean saveAdmin(AdminDTO admin);

    String getIdByUserName(String username);
}
