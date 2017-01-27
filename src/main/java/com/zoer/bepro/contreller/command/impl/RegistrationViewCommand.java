package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.User;

/**
 * Created by zoer on 23.01.17.
 */
public class RegistrationViewCommand implements Command {

    @Override
    public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
        if(req.getSessionWrapper().getUser()==null)
        return ViewJsp.General.REGISTER_JSP;
        else return ViewJsp.General.MAIN;
    }
}
