package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.ICommand;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.CompanyProfile;
import com.zoer.bepro.model.domain.StudentProfile;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.ProfileType;
import com.zoer.bepro.model.services.impl.DefaultServiceFactory;

/**
 * Created by zoer on 26.01.17.
 */
public class UserInfoCommand implements ICommand {

    @Override
    public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
        String nick = req.getParameter("item");
        User pageUser = DefaultServiceFactory.getInstance().getDefaultUserSevice().getUser(nick);
        req.setAttribute("pageuser", pageUser);
        ProfileType prfltp = DefaultServiceFactory.getInstance().getDefaultProfileService().getProfileType(pageUser.getProfile());
//        req.setAttribute("profiletype", prfltp);

        switch (prfltp) {
            case STUDENT:
                req.setAttribute("type", 1);
                StudentProfile sp = pageUser.getProfile().getStudentProfile().get();
                req.setAttribute("name", sp.getName());
                req.setAttribute("spec", DefaultServiceFactory.getInstance().getDefaultSpecificationService().getStudentsSpecifications(sp.getId()));
                req.setAttribute("specpassedcourses",DefaultServiceFactory.getInstance().getDefaultCoursesService().getAllStudentsCourses(sp.getId()));
                req.setAttribute("cv", sp.getCvurl());
                break;
            case NOONE:
            case COMPANY:
                req.setAttribute("type", 2);
            CompanyProfile cp=pageUser.getProfile().getCompanyProfile().get();
            req.setAttribute("imgurl",cp.getImgurl());
            req.setAttribute("name",cp.getInfotxt());
            req.setAttribute("joboffers", DefaultServiceFactory.getInstance().getDefaultJobOffersService().getCompanyJobOffers(cp));
            break;
            case ADMIN:
                req.setAttribute("type", 3);break;

        }
        return ViewJsp.General.USER;
    }
}
