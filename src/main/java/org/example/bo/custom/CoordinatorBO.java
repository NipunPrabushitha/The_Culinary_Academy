package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.model.CoordinatorDTO;

public interface CoordinatorBO extends SuperBO {

    boolean saveCoordinator(CoordinatorDTO coordinator);

    String getIdByUserName(String username);

}