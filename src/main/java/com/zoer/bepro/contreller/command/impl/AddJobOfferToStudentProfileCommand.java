package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.JobOffers;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.impl.DefaultStudentProfileService;

/**
 * Created by zoer on 25.01.17.
 */
public class AddJobOfferToStudentProfileCommand implements Command {
    private static AddJobOfferToStudentProfileCommand ourInstance = new AddJobOfferToStudentProfileCommand();

    public static AddJobOfferToStudentProfileCommand getInstance() {
        return ourInstance;
    }

    private AddJobOfferToStudentProfileCommand() {
    }

    @Override
    public String execute(RequestWrapper req, User user) throws InsufficientPermissionsException, PersistException {
        JobOffers jo=new JobOffers();
        jo.setId(Integer.parseInt(req.getParameter("jobofferid")));
        DefaultStudentProfileService.getInstance().addJobOffer(user.getProfile().getStudentProfile().get(),jo);
        return null;
    }
}
