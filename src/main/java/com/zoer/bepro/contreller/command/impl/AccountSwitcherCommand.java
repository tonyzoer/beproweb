package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.ICommand;
import com.zoer.bepro.contreller.command.CommandFactory;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.JspMessagesSetter;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.ProfileType;
import org.apache.log4j.Logger;

/**
 * Created by zoer on 20.01.17.
 */
public class AccountSwitcherCommand implements ICommand {
    private  static final Logger logger = Logger.getLogger(AccountSwitcherCommand.class);


    @Override
    public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
        User user = req.getSessionWrapper().getUser();
        if (user == null) {
            logger.debug("Session is overed");
            JspMessagesSetter.setOutputMessage(req, JspMessagesSetter.JspResult.SESSION_IS_OVVERED);
            return ViewJsp.General.MAIN;
        } else {
            ProfileType prftype = req.getSessionWrapper().getProfileType();
            switch (prftype) {
                case STUDENT:
                    return CommandFactory.STUDENTPROFILE.getCommand().execute(req);
                case NOONE:
                    return ViewJsp.UserSpace.CHOOSE_PROFILE_JSP;

                case COMPANY:
                    return CommandFactory.COMPANYPROFILE.getCommand().execute(req);

                case ADMIN:
                    return ViewJsp.AdminSpace.ADMIN_JSP;

            }
        }
        return ViewJsp.General.MAIN;
    }
}
