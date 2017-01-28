package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.command.CommandMapping;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.Specifications;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.impl.DefaultServiceFactory;
import com.zoer.bepro.model.services.impl.DefaultSpecificationService;
import com.zoer.bepro.model.services.impl.DefaultStudentProfileService;

import java.util.List;

/**
 * Created by zoer on 21.01.17.
 */
public class AddSpecificationCommand implements Command {

    @Override
    public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
        User user=req.getSessionWrapper().getUser();
        String parameter = req.getParameter("item");
        Specifications specification = DefaultServiceFactory.getInstance().getDefaultSpecificationService().getById(Integer.parseInt(parameter.split("--")[0]));
        if(DefaultServiceFactory.getInstance().getDefaultStudentProfileService().addSpecification(specification, user.getProfile().getStudentProfile().get())){
        List<Specifications> studentSpecifications = req.getSessionWrapper().getStudentSpecifications();
        studentSpecifications.add(specification);
        req.getSessionWrapper().setSdudentsSpecifications(studentSpecifications);
        }
        return CommandMapping.STUDENTPROFILE.getCommand().execute(req);
    }
}
