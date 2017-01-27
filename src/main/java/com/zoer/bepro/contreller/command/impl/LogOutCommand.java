package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.User;


public class LogOutCommand implements Command {

    @Override
    public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
        req.getSessionWrapper().setUser(null);
        req.getSessionWrapper().setProfileType(null);
        return ViewJsp.General.MAIN;
    }
}
