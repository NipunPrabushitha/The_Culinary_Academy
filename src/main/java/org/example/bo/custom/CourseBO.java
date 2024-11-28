package org.example.bo.custom;


import org.example.bo.SuperBO;
import org.example.entity.Course;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CourseBO extends SuperBO {
    boolean saveCourse(Course entity) throws IOException;

    boolean updateCourse(Course entity) throws IOException;

    boolean deleteCourse(String id) throws IOException;

    List<Course> getAllCourse() throws IOException;

    List<Course> SearchCID(String cid) throws IOException;


    Course serachbyCIDs(String cid) throws SQLException, ClassNotFoundException;
}
