package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.ICommand;
import com.zoer.bepro.contreller.command.CommandFactory;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.JobOfferText;
import com.zoer.bepro.model.domain.JobOffers;
import com.zoer.bepro.model.domain.Specifications;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.ProfileType;
import com.zoer.bepro.model.services.impl.DefaultServiceFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zoer on 22.01.17.
 */
public class CreateJobOfferCommand implements ICommand {


    @Override
    public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
        User user=req.getSessionWrapper().getUser();
         if (req.getSessionWrapper().getProfileType() != ProfileType.COMPANY) {
            return ViewJsp.General.MAIN;
        }
        JobOffers jo = new JobOffers();
        jo.setDescription(req.getParameter("name"));
        jo.setCompanyId(user.getProfile().getCompanyProfile().get().getId());
        jo = DefaultServiceFactory.getInstance().getDefaultJobOffersService().insert(jo);
        JobOfferText jot = new JobOfferText();
        jot.setId(jo.getId());
        jot.setText(req.getParameter("textinfo"));
        DefaultServiceFactory.getInstance().getDefaultJobOfferTextService().update(jot);
        String specifications = req.getParameter("spec");
        if (specifications != "") {
            List<String> specArr = Arrays.asList(specifications.split(","));
            for (String specId : specArr) {
                Specifications spec = new Specifications();
                spec.setId(Integer.parseInt(specId.split("--")[0]));
                DefaultServiceFactory.getInstance().getDefaultJobOffersService().addSpecificationToJobOffer(jo, spec);
            }
        }
        String newSpecifications = req.getParameter("newspec");
        if (newSpecifications != "") {
            List<String> newSpecArr = Arrays.asList(newSpecifications.split(","));
            for (String newSpec : newSpecArr) {
                Specifications spec = new Specifications();
                spec.setValue(newSpec);
                spec = DefaultServiceFactory.getInstance().getDefaultSpecificationService().insert(spec);
                DefaultServiceFactory.getInstance().getDefaultJobOffersService().addSpecificationToJobOffer(jo, spec);
            }
        }
        req.addParameter("item", jo.getId().toString());
        return CommandFactory.JOBOFFERINFO.getCommand().execute(req);
    }
}
