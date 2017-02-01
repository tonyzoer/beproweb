package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.ICommand;
import com.zoer.bepro.contreller.command.CommandFactory;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.services.impl.DefaultServiceFactory;

/**
 * Created by zoer on 31.01.17.
 */
public class RemoveCourseFromStudent implements ICommand {
    @Override
    public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
        DefaultServiceFactory.getInstance().getDefaultCoursesService().deleteCourseFromStudent(Integer.parseInt(req.getParameter("item")),
                req.getSessionWrapper().getUser().getProfile().getStudentProfile().get().getId());
        req.addParameter("item",DefaultServiceFactory.getInstance().getDefaultCoursesService().getById(Integer.parseInt(req.getParameter("item"))).getSpecId().toString());
        return CommandFactory.SPECINFO.getCommand().execute(req);
    }
}
