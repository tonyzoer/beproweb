package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.Specifications;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.impl.DefaultCoursesService;

/**
 * Created by zoer on 27.01.17.
 */
public class ShowSpecCoursesCommand implements Command {
    private static ShowSpecCoursesCommand ourInstance = new ShowSpecCoursesCommand();

    public static ShowSpecCoursesCommand getInstance() {
        return ourInstance;
    }

    private ShowSpecCoursesCommand() {
    }

    @Override
    public String execute(RequestWrapper req, User user) throws InsufficientPermissionsException, PersistException {
        Integer specID = Integer.parseInt(req.getParameter("item"));
        req.setAttribute("courses",DefaultCoursesService.getInstance().getAllSpecCourses(specID));
        return ViewJsp.General.SPECIFICATION;
    }
}
