package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.ICommand;
import com.zoer.bepro.contreller.command.CommandFactory;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.JobOffers;
import com.zoer.bepro.model.services.impl.DefaultServiceFactory;

/**
 * Created by zoer on 25.01.17.
 */
public class DeleteJobOfferCommand implements ICommand {


    @Override
    public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
        JobOffers jo= DefaultServiceFactory.getInstance().getDefaultJobOffersService().getById(Integer.parseInt(req.getParameter("item")));
        if (req.getSessionWrapper().getUser().getProfile().getCompanyProfile().get().getId()==jo.getCompanyId()){
            DefaultServiceFactory.getInstance().getDefaultJobOffersService().delete(jo);
        }
        return CommandFactory.COMPANYPROFILE.getCommand().execute(req);
    }
}
