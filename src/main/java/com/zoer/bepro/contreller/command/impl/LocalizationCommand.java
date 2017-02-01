package com.zoer.bepro.contreller.command.impl;


import com.zoer.bepro.contreller.command.ICommand;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.SessionWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;

public class LocalizationCommand implements ICommand {

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
