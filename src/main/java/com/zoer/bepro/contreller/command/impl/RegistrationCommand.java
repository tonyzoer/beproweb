package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.command.CommandMapping;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.JspMessagesSetter;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.impl.DefaultServiceFactory;
import com.zoer.bepro.utils.RegisterVerifier;

/**
 * Created by zoer on 20.01.17.
 */
public class RegistrationCommand implements Command {

    @Override
    public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
        User user1=new User();
        user1.setNickname(req.getParameter("uname"));
        user1.setPassword(req.getParameter("pass"));
        user1.setEmail( req.getParameter("email"));
        if (!RegisterVerifier.isValidEmailAddress(user1.getEmail())){
            JspMessagesSetter.setOutputError(req, JspMessagesSetter.JspError.WRONG_EMAIL,"email "+user1.getEmail()+"is invalid");
            return CommandMapping.REGISTERVIEW.getCommand().execute(req);
        }
        if (!RegisterVerifier.isValifStringLength(user1.getNickname(),4)){
            JspMessagesSetter.setOutputError(req, JspMessagesSetter.JspError.WRONG_LOGIN_LENGTH,"login"+user1.getNickname()+"is too short");
            return CommandMapping.REGISTERVIEW.getCommand().execute(req);
        }
        if (!RegisterVerifier.isPasswordIsValid(user1.getPassword())){
            JspMessagesSetter.setOutputError(req, JspMessagesSetter.JspError.WRONG_PASSWORD,"password is invalid");
            return CommandMapping.REGISTERVIEW.getCommand().execute(req);
        }
        user1= DefaultServiceFactory.getInstance().getDefaultUserSevice().insert(user1);
        if (user1!=null){
            return ViewJsp.UserSpace.REG_THANK;
        } else{
            JspMessagesSetter.setOutputError(req,JspMessagesSetter.JspError.LOGIN_ALREADY_EXIST,"User already exsits");
            return CommandMapping.REGISTERVIEW.getCommand().execute(req);
        }

    }
}
