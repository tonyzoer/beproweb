package test.model.services;

import com.zoer.bepro.model.services.impl.DefaultCoursesService;
import com.zoer.bepro.model.services.impl.DefaultServiceFactory;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zoer on 01.02.17.
 */
public class DefaultCoursesServiceTest {
    @Test
    public void getAllSpecCourses() throws Exception {

        DefaultServiceFactory.getInstance().getDefaultCoursesService().getAllSpecCourses(2);
    }

    @Test
    public void getAllStudentsCourses() throws Exception {

    }

    @Test
    public void getAllStudentsCourses1() throws Exception {

    }

    @Test
    public void addCourseToStudent() throws Exception {

    }

    @Test
    public void deleteCourseFromStudent() throws Exception {

    }

}