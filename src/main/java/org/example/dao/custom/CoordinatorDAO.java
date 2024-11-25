package org.example.dao.custom;

import org.example.dao.SuperDAO;
import org.example.entity.Coordinator;

public interface CoordinatorDAO extends SuperDAO {

    boolean save(Coordinator coordinator);

    String getId(String username);

}
