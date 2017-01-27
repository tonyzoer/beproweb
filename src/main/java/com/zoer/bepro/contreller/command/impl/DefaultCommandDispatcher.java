package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.command.CommandDispatcher;
import com.zoer.bepro.contreller.command.CommandMapping;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.exeptions.NotFoundException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.model.domain.User;
import jdk.internal.util.xml.PropertiesDefaultHandler;
import org.apache.log4j.Logger;


public class DefaultCommandDispatcher implements CommandDispatcher{
    private final static Logger logger = Logger.getLogger(DefaultCommandDispatcher.class);
    private static final DefaultCommandDispatcher instance = new DefaultCommandDispatcher();
    private DefaultCommandDispatcher() {
    }

    public static DefaultCommandDispatcher getInstance() {
        return instance;
    }


    @Override
    public String executeRequest(RequestWrapper requestWrapper) throws InsufficientPermissionsException, NotFoundException {
        String commandName = requestWrapper.getParameter("command");
        try {
            Command command = CommandMapping.valueOf(commandName).getCommand();
            return command.execute(requestWrapper);
        } catch (InsufficientPermissionsException ipe) {
            throw ipe;
        } catch (IllegalArgumentException | NullPointerException e) {
            logger.error(e);
            throw new NotFoundException();
        } catch (Exception exception) {
            logger.error(exception);
            throw new NotFoundException();
        }
    }

}
