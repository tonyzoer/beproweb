package com.zoer.bepro.model.services;

import com.zoer.bepro.model.domain.Courses;

import java.util.List;
import java.util.Map;

/**
 * Created by Zoer on 13.01.2017.
 */
public interface CoursesService extends EntityService<Courses> {
    List<Courses> getAllSpecCourses(Integer specId);

    Map<Courses,String> getAllStudentsCourses(Integer specId, Integer studentId);

    Map<Courses,String> getAllStudentsCourses(Integer studentId);

    boolean addCourseToStudent(Integer courseId, Integer studId, String url);
}
