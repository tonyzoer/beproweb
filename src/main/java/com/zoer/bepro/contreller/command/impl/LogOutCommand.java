package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.ICommand;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;


public class LogOutCommand implements ICommand {

    @Override
    public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
        req.getSessionWrapper().invalidate();
        return ViewJsp.General.MAIN;
    }
}
