package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.ICommand;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.JobOffers;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.ProfileType;
import com.zoer.bepro.model.services.impl.DefaultServiceFactory;

import java.util.List;

/**
 * Created by zoer on 24.01.17.
 */
public class CompanyProfileCommand implements ICommand {


    @Override
    public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
        User user=req.getSessionWrapper().getUser();
        ProfileType prftype = req.getSessionWrapper().getProfileType();
        if (prftype != ProfileType.COMPANY) return ViewJsp.General.MAIN;
        List<JobOffers> jobOffers= DefaultServiceFactory.getInstance().getDefaultJobOffersService().getCompanyJobOffers(user.getProfile().getCompanyProfile().get());
        req.setAttribute("joboffers",jobOffers);
        return ViewJsp.CompanySpace.COMPANY_JSP;
    }
}
