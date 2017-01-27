package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.JobOffers;
import com.zoer.bepro.model.domain.Specifications;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.ProfileType;
import com.zoer.bepro.model.services.impl.DefaultCompanyProfileService;
import com.zoer.bepro.model.services.impl.DefaultJobOfferTextService;
import com.zoer.bepro.model.services.impl.DefaultJobOffersService;
import com.zoer.bepro.model.services.impl.DefaultSpecificationService;

import java.util.List;

/**
 * Created by zoer on 22.01.17.
 */
public class JobOfferFullInfoCommand implements Command {

    @Override
    public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
        String jobOfferID = req.getParameter("item");
        JobOffers jobOffer = DefaultJobOffersService.getInstance().getById(Integer.parseInt(jobOfferID));
        List<Specifications> jobOfferSpecification = DefaultSpecificationService.getInstance().getJobOfferSpecifications(jobOffer.getId());
        req.setAttribute("jobOfferSpecification", jobOfferSpecification);
        req.setAttribute("jobOffer", jobOffer);
        req.setAttribute("jobOfferText", DefaultJobOfferTextService.getInstance().getById(jobOffer.getId()));
        req.setAttribute("company", DefaultCompanyProfileService.getInstance().getById(jobOffer.getCompanyId()));
        if (req.getSessionWrapper().getProfileType() == ProfileType.STUDENT) {
            req.setAttribute("student", true);
            req.setAttribute("alreadyaplied", DefaultJobOffersService.getInstance().existsSTudentJobOffer(req.getSessionWrapper().getUser().getProfile().getStudentProfile().get(), jobOffer));
        } else {
            req.setAttribute("student", false);

        }
        return ViewJsp.JobOffer.JOBOFFERINFO;
    }
}
