package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.ICommand;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.impl.DefaultServiceFactory;

/**
 * Created by zoer on 19.01.17.
 */
public class LoginCommand implements ICommand {


    @Override
    public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
        RequestWrapper request;
        User AutentificatedUser = DefaultServiceFactory.getInstance().getDefaultUserSevice().
                authentication(req.getParameter("uname"),
                        req.getParameter("pass"));
        if (AutentificatedUser != null) {
            req.getSessionWrapper().setUser(AutentificatedUser);
            req.getSessionWrapper().setProfileType(DefaultServiceFactory.getInstance().getDefaultProfileService().getProfileType(AutentificatedUser.getProfile()));
        }
        return ViewJsp.General.MAIN;

    }
}
