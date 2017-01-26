package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.Profile;
import com.zoer.bepro.model.domain.StudentProfile;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.ProfileType;
import com.zoer.bepro.model.services.impl.DefaultProfileService;
import com.zoer.bepro.model.services.impl.DefaultSpecificationService;
import com.zoer.bepro.model.services.impl.DefaultUserSevice;

/**
 * Created by zoer on 26.01.17.
 */
public class UserInfoCommand implements Command {
    private static UserInfoCommand ourInstance = new UserInfoCommand();

    public static UserInfoCommand getInstance() {
        return ourInstance;
    }

    private UserInfoCommand() {
    }

    @Override
    public String execute(RequestWrapper req, User user) throws InsufficientPermissionsException, PersistException {
        String nick=req.getParameter("nickname");
        User pageuser=DefaultUserSevice.getInstance().getUser(nick);
        req.setAttribute("pageuser",pageuser);
        ProfileType prfltp=DefaultProfileService.getInstance().getProfileType(pageuser.getProfile());
//        req.setAttribute("profiletype", prfltp);

        switch (prfltp) {
            case STUDENT:
                StudentProfile sp=user.getProfile().getStudentProfile().get();
                req.setAttribute("name",sp.getName());
                req.setAttribute("spec", DefaultSpecificationService.getInstance().getStudentsSpecifications(sp.getId()));
                req.setAttribute("cv",sp.getCvurl());

            case NOONE:
            case COMPANY:
            case ADMIN:
        }
        return ViewJsp.General.USER;
    }
}
