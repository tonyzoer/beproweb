package com.zoer.bepro.contreller.command;


import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.exeptions.NotFoundException;
import com.zoer.bepro.contreller.util.RequestWrapper;

public interface CommandDispatcher {
    String executeRequest(RequestWrapper requestWrapper) throws InsufficientPermissionsException, NotFoundException;
}
