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
import com.zoer.bepro.model.services.impl.DefaultJobOffersService;
import com.zoer.bepro.model.services.impl.DefaultSpecificationService;

import java.util.List;

/**
 * Created by zoer on 24.01.17.
 */
public class StudentProffileCommand implements Command {
    private static StudentProffileCommand ourInstance = new StudentProffileCommand();

    public static StudentProffileCommand getInstance() {
        return ourInstance;
    }

    private StudentProffileCommand() {
    }

    @Override
    public String execute(RequestWrapper req, User user) throws InsufficientPermissionsException, PersistException {
        ProfileType prftype = req.getSessionWrapper().getProfileType();
        if (prftype != ProfileType.STUDENT) return ViewJsp.General.MAIN;
        List<Specifications> specificationsStudentList;
        specificationsStudentList = DefaultSpecificationService.getInstance().
                getStudentsSpecifications(user.getProfile().getStudentProfile().get().getId());
        req.getSessionWrapper().setSdudentsSpecifications(specificationsStudentList);
        List<Specifications> specificationsList = DefaultSpecificationService.getInstance().findAll();
        specificationsList.removeAll(specificationsStudentList);
        req.getSessionWrapper().setSpecifications(specificationsList);
        List<JobOffers> jobOfferss=DefaultJobOffersService.getInstance().getStudentsJobOffers(user.getProfile().getStudentProfile().get());
        req.setAttribute("studentsOffers", jobOfferss);
            return ViewJsp.StudentSpace.STUDENT_JSP;
    }
}
