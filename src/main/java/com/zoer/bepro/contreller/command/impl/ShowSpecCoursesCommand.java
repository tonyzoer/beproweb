package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.services.impl.DefaultCoursesService;
import com.zoer.bepro.model.services.impl.DefaultSpecificationService;

/**
 * Created by zoer on 27.01.17.
 */
public class ShowSpecCoursesCommand implements Command {


    @Override
    public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
        Integer specID = Integer.parseInt(req.getParameter("item"));
        req.setAttribute("spec", DefaultSpecificationService.getInstance().getById(specID));
        req.setAttribute("courses",DefaultCoursesService.getInstance().getAllSpecCourses(specID));
        return ViewJsp.General.SPECIFICATION;
    }
}
