package org.example.bo.custom.impl;

import org.example.bo.custom.CoordinatorBO;
import org.example.dao.custom.AdminDAO;
import org.example.dao.custom.CoordinatorDAO;
import org.example.dao.custom.impl.AdminDAOImpl;
import org.example.dao.custom.impl.CoordinatorDAOImpl;
import org.example.entity.Coordinator;
import org.example.model.CoordinatorDTO;

public class CoordinatorBOImpl implements CoordinatorBO {

    @Override
    public boolean saveCoordinator(CoordinatorDTO coordinator) {
        CoordinatorDAO coordinatorDAO = new CoordinatorDAOImpl();
        return coordinatorDAO.save(new Coordinator(coordinator.getUserId(),coordinator.getUserName(),coordinator.getPassword(),coordinator.getForgetPassword()));
    }

    @Override
    public String getIdByUserName(String username) {
        CoordinatorDAO coordinatorDAO = new CoordinatorDAOImpl();
        return coordinatorDAO.getId(username);
    }
}
