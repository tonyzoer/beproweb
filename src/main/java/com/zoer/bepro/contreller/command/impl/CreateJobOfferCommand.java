package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.command.CommandMapping;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.JobOfferText;
import com.zoer.bepro.model.domain.JobOffers;
import com.zoer.bepro.model.domain.Specifications;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.ProfileType;
import com.zoer.bepro.model.services.impl.DefaultJobOfferTextService;
import com.zoer.bepro.model.services.impl.DefaultJobOffersService;
import com.zoer.bepro.model.services.impl.DefaultSpecificationService;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zoer on 22.01.17.
 */
public class CreateJobOfferCommand implements Command {


    @Override
    public String execute(RequestWrapper req, User user) throws InsufficientPermissionsException, PersistException {
        if (req.getSessionWrapper().getProfileType() != ProfileType.COMPANY) {
            return ViewJsp.General.MAIN;
        }
        JobOffers jo = new JobOffers();
        jo.setDescription(req.getParameter("name"));
        jo.setCompanyId(user.getProfile().getCompanyProfile().get().getId());
        jo = DefaultJobOffersService.getInstance().insert(jo);
        JobOfferText jot = new JobOfferText();
        jot.setId(jo.getId());
        jot.setText(req.getParameter("textinfo"));
        DefaultJobOfferTextService.getInstance().update(jot);
        String specifications = req.getParameter("spec");
        if (specifications != "") {
            List<String> specArr = Arrays.asList(specifications.split(","));
            for (String specId : specArr) {
                Specifications spec = new Specifications();
                spec.setId(Integer.parseInt(specId.split("--")[0]));
                DefaultJobOffersService.getInstance().addSpecificationToJobOffer(jo, spec);
            }
        }
        String newSpecifications = req.getParameter("newspec");
        if (newSpecifications != "") {
            List<String> newSpecArr = Arrays.asList(newSpecifications.split(","));
            for (String newSpec : newSpecArr) {
                Specifications spec = new Specifications();
                spec.setValue(newSpec);
                spec = DefaultSpecificationService.getInstance().insert(spec);
                DefaultJobOffersService.getInstance().addSpecificationToJobOffer(jo, spec);
            }
        }
        req.addParameter("item", jo.getId().toString());
        return CommandMapping.JOBOFFERINFO.getCommand().execute(req, user);
    }
}
