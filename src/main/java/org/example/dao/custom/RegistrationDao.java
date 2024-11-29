package org.example.dao.custom;


import org.example.dao.CrudDao;
import org.example.entity.Registration;
import org.example.dao.CrudDao;

import java.io.IOException;
import java.util.List;

public interface RegistrationDao extends CrudDao<Registration> {

    boolean save(Registration Dto) throws IOException;

    boolean delete(Long id) throws IOException;

    List<Registration> getaAll() throws IOException;

    Registration searchByRID(Long id);


    void getRAll();
}
