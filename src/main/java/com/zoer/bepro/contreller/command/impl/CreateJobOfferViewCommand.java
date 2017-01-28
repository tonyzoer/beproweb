package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.Specifications;
import com.zoer.bepro.model.services.ProfileType;
import com.zoer.bepro.model.services.impl.DefaultServiceFactory;
import com.zoer.bepro.model.services.impl.DefaultSpecificationService;

import java.util.List;

/**
 * Created by zoer on 22.01.17.
 */
public class CreateJobOfferViewCommand implements Command {


    @Override
    public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
        if (req.getSessionWrapper().getProfileType()!= ProfileType.COMPANY){
            throw new InsufficientPermissionsException();
        }
        List<Specifications> spec= DefaultServiceFactory.getInstance().getDefaultSpecificationService().findAll();
        req.setAttribute("specificationList", spec);
        return ViewJsp.JobOffer.CREATWJOBOFFER;
    }
}
