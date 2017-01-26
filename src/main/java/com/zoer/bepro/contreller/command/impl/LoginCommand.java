package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.StudentProfile;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.impl.*;

/**
 * Created by zoer on 19.01.17.
 */
public class LoginCommand implements Command {

    private static LoginCommand instance = new LoginCommand();

    private LoginCommand() {
    }

    static LoginCommand getInstance() {

        return instance;
    }

    @Override
    public String execute(RequestWrapper req, User user) throws InsufficientPermissionsException, PersistException {
        RequestWrapper request;
        User AutentificatedUser = DefaultUserSevice.getInstance().
                authentication(req.getParameter("uname"),
                        req.getParameter("pass"));
        if (AutentificatedUser != null) {

            req.getSessionWrapper().setUser(AutentificatedUser);
            req.getSessionWrapper().setProfileType(DefaultProfileService.getInstance().getProfileType(AutentificatedUser.getProfile()));

        }


        return ViewJsp.General.MAIN;

    }
}
