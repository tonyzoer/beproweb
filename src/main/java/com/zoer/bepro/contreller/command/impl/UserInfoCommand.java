package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.CompanyProfile;
import com.zoer.bepro.model.domain.Profile;
import com.zoer.bepro.model.domain.StudentProfile;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.ProfileType;
import com.zoer.bepro.model.services.impl.DefaultJobOffersService;
import com.zoer.bepro.model.services.impl.DefaultProfileService;
import com.zoer.bepro.model.services.impl.DefaultSpecificationService;
import com.zoer.bepro.model.services.impl.DefaultUserSevice;

/**
 * Created by zoer on 26.01.17.
 */
public class UserInfoCommand implements Command {

    @Override
    public String execute(RequestWrapper req, User user) throws InsufficientPermissionsException, PersistException {
        String nick = req.getParameter("nickname");
        User pageUser = DefaultUserSevice.getInstance().getUser(nick);
        req.setAttribute("pageuser", pageUser);
        ProfileType prfltp = DefaultProfileService.getInstance().getProfileType(pageUser.getProfile());
//        req.setAttribute("profiletype", prfltp);

        switch (prfltp) {
            case STUDENT:
                req.setAttribute("type", 1);
                StudentProfile sp = pageUser.getProfile().getStudentProfile().get();
                req.setAttribute("name", sp.getName());
                req.setAttribute("spec", DefaultSpecificationService.getInstance().getStudentsSpecifications(sp.getId()));
                req.setAttribute("cv", sp.getCvurl());
                break;
            case NOONE:
            case COMPANY:
                req.setAttribute("type", 2);
            CompanyProfile cp=pageUser.getProfile().getCompanyProfile().get();
            req.setAttribute("imgurl",cp.getImgurl());
            req.setAttribute("name",cp.getInfotxt());
            req.setAttribute("joboffers", DefaultJobOffersService.getInstance().getCompanyJobOffers(cp));
            break;
            case ADMIN:
                req.setAttribute("type", 3);break;

        }
        return ViewJsp.General.USER;
    }
}
