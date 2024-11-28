package org.example.dao.custom;


import org.example.dao.CrudDao;
import org.example.entity.Course;
import org.example.dao.CrudDao;

import java.io.IOException;
import java.util.List;

public interface CourseDao extends CrudDao<Course> {

    boolean delete(String entity) throws IOException;

    List<Course> getaAll() throws IOException;

    List<Course> SearchCID(String cid) throws IOException;

    Course searchByCId(String id);

    boolean save(Course course) throws IOException;
}
