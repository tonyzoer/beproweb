package com.zoer.bepro.model.services.impl;

import com.zoer.bepro.model.dao.AbstractJDBCDao;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.dao.mysqldao.MySqlCoursesDao;
import com.zoer.bepro.model.dao.mysqldao.MySqlDaoFactory;
import com.zoer.bepro.model.domain.Courses;
import com.zoer.bepro.model.services.CoursesService;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * Created by Zoer on 13.01.2017.
 */
public class DefaultCoursesService extends GenericEntityService<Courses> implements CoursesService {

    DefaultCoursesService() {
    }

    private final static Logger logger = Logger.getLogger(DefaultCoursesService.class);
    @Override
    AbstractJDBCDao<Courses, Integer> getDao() throws PersistException {
        return (AbstractJDBCDao<Courses, Integer>) MySqlDaoFactory.getInstance().getDao(Courses.class);
    }



    @Override
    public List<Courses> getAllSpecCourses(Integer specId) {
        try {
            MySqlCoursesDao dao = (MySqlCoursesDao) getDao();
            return dao.getAllBySpec(specId);
        } catch (PersistException e) {
            logger.debug(e);
        }
        return null;
    }
    @Override
    public Map<Courses,String> getAllStudentsCourses(Integer specId,Integer studentId){
        try {
            MySqlCoursesDao dao = (MySqlCoursesDao) getDao();
            return dao.getAllStudentsSpecCourses(specId,studentId);
        } catch (PersistException e) {
            logger.debug(e);
        }
        return null;
    }
    @Override
    public Map<Courses,String> getAllStudentsCourses(Integer studentId){
        try {
            MySqlCoursesDao dao = (MySqlCoursesDao) getDao();
            return dao.getAllStudentsSpecCourses(studentId);
        } catch (PersistException e) {
            logger.debug(e);
        }
        return null;
    }
    @Override
    public boolean addCourseToStudent(Integer courseId, Integer studId, String url){
        try {
            MySqlCoursesDao dao = (MySqlCoursesDao) getDao();
           return dao.insertCourseToStudent(courseId,studId,url);
        } catch (PersistException e) {
            logger.debug(e);
        }
        return false;
    }
    @Override
    public boolean deleteCourseFromStudent(Integer courseId, Integer studId){
        try {
            MySqlCoursesDao dao = (MySqlCoursesDao) getDao();
            return dao.deleteCourseToStudent(courseId,studId);
        } catch (PersistException e) {
            logger.debug(e);
        }
        return false;
    }
}
