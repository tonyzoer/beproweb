package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.ICommand;
import com.zoer.bepro.contreller.command.CommandFactory;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.Specifications;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.impl.DefaultServiceFactory;

import java.util.List;

/**
 * Created by zoer on 21.01.17.
 */
public class AddSpecificationCommand implements ICommand {

    @Override
    public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
        User user=req.getSessionWrapper().getUser();
        String parameter = req.getParameter("item");
        if (parameter==null){
            return CommandFactory.STUDENTPROFILE.getCommand().execute(req);
        }
        Specifications specification = DefaultServiceFactory.getInstance().getDefaultSpecificationService().getById(Integer.parseInt(parameter.split("--")[0]));
        if(DefaultServiceFactory.getInstance().getDefaultStudentProfileService().addSpecification(specification, user.getProfile().getStudentProfile().get())){
        List<Specifications> studentSpecifications = req.getSessionWrapper().getStudentSpecifications();
        studentSpecifications.add(specification);
        req.getSessionWrapper().setSdudentsSpecifications(studentSpecifications);
        }
        return CommandFactory.STUDENTPROFILE.getCommand().execute(req);
    }
}
