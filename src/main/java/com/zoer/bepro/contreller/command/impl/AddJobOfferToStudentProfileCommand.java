package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.command.CommandMapping;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.JobOffers;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.impl.DefaultServiceFactory;


public class AddJobOfferToStudentProfileCommand implements Command {
    @Override
    public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
        User user=req.getSessionWrapper().getUser();
        JobOffers jo=new JobOffers();
        jo.setId(Integer.parseInt(req.getParameter("item")));
        DefaultServiceFactory.getInstance().getDefaultStudentProfileService().addJobOffer(user.getProfile().getStudentProfile().get(),jo);
        req.addParameter("item", String.valueOf(jo.getId()));
        return CommandMapping.JOBOFFERINFO.getCommand().execute(req);
    }
}
