package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.ICommand;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.JobOffers;
import com.zoer.bepro.model.domain.Specifications;
import com.zoer.bepro.model.domain.StudentProfile;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.ProfileType;
import com.zoer.bepro.model.services.impl.DefaultServiceFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zoer on 22.01.17.
 */
public class JobOfferFullInfoCommand implements ICommand {

    @Override
    public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
        String jobOfferID = req.getParameter("item");
        JobOffers jobOffer = DefaultServiceFactory.getInstance().getDefaultJobOffersService().getById(Integer.parseInt(jobOfferID));
        List<Specifications> jobOfferSpecification = DefaultServiceFactory.getInstance().getDefaultSpecificationService().getJobOfferSpecifications(jobOffer.getId());
        req.setAttribute("jobOfferSpecification", jobOfferSpecification);
        req.setAttribute("jobOffer", jobOffer);
        req.setAttribute("jobOfferText", DefaultServiceFactory.getInstance().getDefaultJobOfferTextService().getById(jobOffer.getId()));
        req.setAttribute("company", DefaultServiceFactory.getInstance().getDefaultCompanyProfileService().getById(jobOffer.getCompanyId()));
        if (req.getSessionWrapper().getProfileType() == ProfileType.STUDENT) {
            req.setAttribute("student", true);
            req.setAttribute("alreadyaplied", DefaultServiceFactory.getInstance().getDefaultJobOffersService().existsSTudentJobOffer(req.getSessionWrapper().getUser().getProfile().getStudentProfile().get(), jobOffer));
        } else {
            req.setAttribute("student", false);

        }
        if (req.getSessionWrapper().getProfileType() == ProfileType.COMPANY &&
                jobOffer.getCompanyId() == req.getSessionWrapper().getUser().getProfile().getCompanyProfile().get().getId()) {
            List<StudentProfile> studentProfiles = DefaultServiceFactory.getInstance().getDefaultStudentProfileService().getStudentToComfirmJobOffer(jobOffer);
            List<User> users = new ArrayList<>();
            for (StudentProfile studentProfile : studentProfiles
                    ) {
                users.add(DefaultServiceFactory.getInstance().getDefaultUserSevice().getUser(studentProfile));
            }
            req.setAttribute("students", users);
        }
        return ViewJsp.JobOffer.JOBOFFERINFO;
    }
}
