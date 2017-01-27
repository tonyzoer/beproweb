package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.command.CommandDispatcher;
import com.zoer.bepro.contreller.command.CommandMapping;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.exeptions.NotFoundException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.model.domain.User;


public class DefaultCommandDispatcher implements CommandDispatcher{
    private static final DefaultCommandDispatcher instance = new DefaultCommandDispatcher();
    private DefaultCommandDispatcher() {
    }

    public static DefaultCommandDispatcher getInstance() {
        return instance;
    }


    @Override
    public String executeRequest(RequestWrapper requestWrapper) throws InsufficientPermissionsException, NotFoundException {
        User user = requestWrapper.getSessionWrapper(false).getUser();
        String commandName = requestWrapper.getParameter("command");
        try {
            Command command = CommandMapping.valueOf(commandName).getCommand();
            return command.execute(requestWrapper, user);
        } catch (InsufficientPermissionsException ipe) {
            throw ipe;
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new NotFoundException();
        } catch (Exception exception) {
//            logger.error(exception);
            throw new NotFoundException();
        }
    }

}
