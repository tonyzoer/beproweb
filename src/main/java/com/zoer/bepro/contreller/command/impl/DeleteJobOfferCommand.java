package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.JobOffers;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.impl.DefaultJobOffersService;

/**
 * Created by zoer on 25.01.17.
 */
public class DeleteJobOfferCommand implements Command {


    @Override
    public String execute(RequestWrapper req, User user) throws InsufficientPermissionsException, PersistException {
        JobOffers jo=DefaultJobOffersService.getInstance().getById(Integer.parseInt(req.getParameter("item")));
        if (user.getProfile().getCompanyProfile().get().getId()==jo.getCompanyId()){
        DefaultJobOffersService.getInstance().delete(jo);
        }
        return CommandMapping.COMPANYPROFILE.getCommand().execute(req,user);
    }
}
