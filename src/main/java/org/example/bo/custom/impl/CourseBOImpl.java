package org.example.bo.custom.impl;


import org.example.bo.custom.CourseBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.CourseDao;
import org.example.entity.Course;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CourseBOImpl implements CourseBO {

    CourseDao courseDao = (CourseDao) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.COURSE);

    @Override
    public boolean saveCourse(Course entity) throws IOException {
        return courseDao.save(
                new Course(entity.getId(),entity.getProgramId(),entity.getProgramName(),entity.getDuration(),entity.getFee(),entity.getRegistrations()));
    }

    @Override
    public boolean updateCourse(Course entity) throws IOException {
        return courseDao.update(new Course(entity.getId(),entity.getProgramId(),entity.getProgramName(),entity.getDuration(),entity.getFee(),entity.getRegistrations()));
    }

    @Override
    public boolean deleteCourse(String id) throws IOException {
        return courseDao.delete(id);
    }

    @Override
    public List<Course> getAllCourse() throws IOException {

        List<Course> allCourse = courseDao.getaAll();

        return allCourse;

    }

    @Override
    public List<Course> SearchCID(String cid) throws IOException {

        return courseDao.SearchCID(cid);

    }

    @Override
    public Course serachbyCIDs(String cid) throws SQLException, ClassNotFoundException {
        return courseDao.searchByCId(cid);
    }

}
