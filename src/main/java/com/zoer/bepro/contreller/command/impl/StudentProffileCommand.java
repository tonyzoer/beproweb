package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.JobOffers;
import com.zoer.bepro.model.domain.Specifications;
import com.zoer.bepro.model.services.ProfileType;
import com.zoer.bepro.model.services.impl.DefaultServiceFactory;

import java.util.List;

/**
 * Created by zoer on 24.01.17.
 */
public class StudentProffileCommand implements Command {


    @Override
    public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
        ProfileType prftype = req.getSessionWrapper().getProfileType();
        if (prftype != ProfileType.STUDENT)
            return ViewJsp.General.MAIN;
        List<Specifications> specificationsStudentList;
        specificationsStudentList = DefaultServiceFactory.getInstance().getDefaultSpecificationService().
        getStudentsSpecifications(req.getSessionWrapper().getUser().getProfile().getStudentProfile().get().getId());
        req.getSessionWrapper().setSdudentsSpecifications(specificationsStudentList);
        List<Specifications> specificationsList = DefaultServiceFactory.getInstance().getDefaultSpecificationService().findAll();
        specificationsList.removeAll(specificationsStudentList);
        req.getSessionWrapper().setSpecifications(specificationsList);
        List<JobOffers> jobOfferss = DefaultServiceFactory.getInstance().getDefaultJobOffersService().getStudentsJobOffers(req.getSessionWrapper().getUser().getProfile().getStudentProfile().get());
        req.setAttribute("studentsOffers", jobOfferss);
        return ViewJsp.StudentSpace.STUDENT_JSP;
    }
}
