package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.Specifications;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.ProfileType;
import com.zoer.bepro.model.services.impl.DefaultSpecificationService;

import java.util.List;

/**
 * Created by zoer on 20.01.17.
 */
public class AccountSwitcherCommand implements Command {
    private static AccountSwitcherCommand instance = new AccountSwitcherCommand();

    public static AccountSwitcherCommand getInstance() {
        return instance;
    }

    @Override
    public String execute(RequestWrapper req, User user) throws InsufficientPermissionsException, PersistException {
//        User user = req.getSessionWrapper().getUser();
        if (user == null) {
            //TODO log
            return ViewJsp.General.MAIN;
        } else {
            ProfileType prftype = req.getSessionWrapper().getProfileType();
            switch (prftype) {
                case STUDENT:
                    List<Specifications> specificationsStudentList=DefaultSpecificationService.getInstance().
                            getStudentsSpecifications(user.getProfile().getStudentProfile().get().getId());
                    req.getSessionWrapper().setSdudentsSpecifications(specificationsStudentList);
                    List<Specifications> specificationsList=DefaultSpecificationService.getInstance().findAll();
                    specificationsList.removeAll(specificationsStudentList);
                    req.getSessionWrapper().setSpecifications(specificationsList);
                    return CommandMapping.valueOf("STUDENTPROFILE").getCommand().execute(req, user);
                case NOONE:
                    return ViewJsp.UserSpace.CHOOSE_PROFILE_JSP;

                case COMPANY:
                    return CommandMapping.valueOf("COMPANYPROFILE").getCommand().execute(req, user);

                case ADMIN:
                    return ViewJsp.AdminSpace.ADMIN_JSP;

            }
        }
        return ViewJsp.General.MAIN;
    }
}
