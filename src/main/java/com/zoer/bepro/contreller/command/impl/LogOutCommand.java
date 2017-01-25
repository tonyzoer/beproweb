package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.User;

/**
 * Created by zoer on 22.01.17.
 */
public class LogOutCommand implements Command {
    public static final LogOutCommand instance=new LogOutCommand();

    public static LogOutCommand getInstance() {
        return instance;
    }

    @Override
    public String execute(RequestWrapper req, User user) throws InsufficientPermissionsException, PersistException {
        req.getSessionWrapper().setUser(null);
        return ViewJsp.General.MAIN;
    }
}
