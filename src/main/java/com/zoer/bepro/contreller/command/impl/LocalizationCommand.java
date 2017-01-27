package com.zoer.bepro.contreller.command.impl;


import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.SessionWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.domain.User;

public class LocalizationCommand implements Command {

    @Override
    public String execute(RequestWrapper req) {
        SessionWrapper sessionWrapper = req.getSessionWrapper(true);
        String language = req.getParameter("language");
        if ("ENG".equals(language)) {
            sessionWrapper.setLanguage("en_US");
        } else {
            sessionWrapper.setLanguage("ua_UA");
        }

        return ViewJsp.General.MAIN;
    }
}
