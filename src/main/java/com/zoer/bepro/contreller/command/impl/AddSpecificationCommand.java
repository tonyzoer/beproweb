package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.Specifications;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.impl.DefaultProfileService;
import com.zoer.bepro.model.services.impl.DefaultSpecificationService;
import com.zoer.bepro.model.services.impl.DefaultStudentProfileService;

import java.util.List;

/**
 * Created by zoer on 21.01.17.
 */
public class AddSpecificationCommand implements Command {

    private static AddSpecificationCommand instance = new AddSpecificationCommand();

    public static AddSpecificationCommand getInstance() {
        return instance;
    }

    @Override
    public String execute(RequestWrapper req, User user) throws InsufficientPermissionsException, PersistException {
        String parameter = req.getParameter("item");
        Specifications specification = DefaultSpecificationService.getInstance().getById(Integer.parseInt(parameter.split("--")[0]));
        if(DefaultStudentProfileService.getInstance().addSpecification(specification, user.getProfile().getStudentProfile().get())){
        List<Specifications> studentSpecifications = req.getSessionWrapper().getStudentSpecifications();
        studentSpecifications.add(specification);
        req.getSessionWrapper().setSdudentsSpecifications(studentSpecifications);
        }
        return ViewJsp.StudentSpace.STUDENT_JSP;
    }
}
