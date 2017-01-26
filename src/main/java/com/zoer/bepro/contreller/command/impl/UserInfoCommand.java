package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.User;

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
        return null;
    }
}
