package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.ICommand;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.services.ProfileType;
import com.zoer.bepro.model.services.impl.DefaultServiceFactory;

/**
 * Created by zoer on 27.01.17.
 */
public class ShowSpecCoursesCommand implements ICommand {


    @Override
    public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
        Integer specID = Integer.parseInt(req.getParameter("item"));
        req.setAttribute("spec", DefaultServiceFactory.getInstance().getDefaultSpecificationService().getById(specID));
        req.setAttribute("courses", DefaultServiceFactory.getInstance().getDefaultCoursesService().getAllSpecCourses(specID));
        if (req.getSessionWrapper().getProfileType()== ProfileType.STUDENT){
            req.setAttribute("student",true);
            req.setAttribute("studentsCourses",DefaultServiceFactory.getInstance().getDefaultCoursesService().getAllStudentsCourses(specID,
                    req.getSessionWrapper().getUser().getProfile().getStudentProfile().get().getId()));
        }
        return ViewJsp.General.SPECIFICATION;
    }
}
