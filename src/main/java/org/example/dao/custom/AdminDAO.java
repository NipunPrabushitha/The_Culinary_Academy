package org.example.dao.custom;


import org.example.dao.SuperDAO;
import org.example.entity.Admin;

import java.util.ArrayList;

public interface AdminDAO extends SuperDAO {
    boolean save(Admin admin);

    String getId(String username);

    ArrayList<Admin> getAll();

    boolean update(String hashedPassword, String userName);
}
