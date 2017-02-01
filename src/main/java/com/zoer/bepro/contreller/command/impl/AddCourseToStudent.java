package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.ICommand;
import com.zoer.bepro.contreller.command.CommandFactory;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.services.impl.DefaultServiceFactory;

/**
 * Created by zoer on 30.01.17.
 */
public class AddCourseToStudent implements ICommand {
    @Override
    public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
        int course=Integer.parseInt(req.getParameter("course"));
        int stud=req.getSessionWrapper().getUser().getProfile().getStudentProfile().get().getId();
        String url=req.getParameter("url");
        DefaultServiceFactory.getInstance().getDefaultCoursesService().addCourseToStudent(course,stud
                ,url);
        req.addParameter("item",req.getParameter("item"));
        return CommandFactory.SPECINFO.getCommand().execute(req);
    }
}
