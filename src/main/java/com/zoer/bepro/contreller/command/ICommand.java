package com.zoer.bepro.contreller.command;


import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.User;

public interface ICommand {
    String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException;
}
