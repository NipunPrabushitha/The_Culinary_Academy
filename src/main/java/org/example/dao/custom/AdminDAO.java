package org.example.dao.custom;

import org.example.dao.SuperDAO;
import org.example.entity.Admin;

public interface AdminDAO extends SuperDAO {
    boolean save(Admin admin);

    String getId(String username);
}
