package org.example.bo.custom.impl;


import org.example.bo.custom.StudentBo;
import org.example.dao.DAOFactory;
import org.example.dao.custom.StudentDao;
import org.example.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StudentBoImpl implements StudentBo {
    /*StudentDao studentDao = (StudentDao) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.STUDENT);*/
    StudentDao studentDao = (StudentDao) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.STUDENT);

    @Override
    public boolean saveStudent(Student entity) throws IOException {
        return studentDao.save(new Student(entity.getId(),entity.getFirstName(),entity.getLastName(),entity.getAddress(),entity.getEmail(),entity.getPhoneNumber(),entity.getEnrollmentDate(),entity.getRegistrations(),entity.getRole()));
    }

    @Override
    public boolean updateStudent(Student entity) throws IOException {
        return studentDao.update(new Student(entity.getId(),entity.getFirstName(),entity.getLastName(),entity.getAddress(),entity.getEmail(),entity.getPhoneNumber(),entity.getEnrollmentDate(),entity.getRegistrations(),entity.getRole()));
    }

    @Override
    public boolean deleteStudent(int id) throws IOException {
        return studentDao.delete(id);
    }

    @Override
    public List<Student> getAllStudent() throws IOException {
        List<Student> allStudent = studentDao.getaAll();

        return allStudent;
    }

    @Override
    public List<Student> SearchSID(int sid) throws IOException {
        return studentDao.SearchSID(sid);
    }

    @Override
    public Student serachbyIDS(int sid) throws SQLException, ClassNotFoundException {
        return studentDao.searchById(sid);
    }
}
