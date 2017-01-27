package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.CompanyProfile;
import com.zoer.bepro.model.domain.JobOffers;
import com.zoer.bepro.model.domain.Specifications;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.impl.DefaultCompanyProfileService;
import com.zoer.bepro.model.services.impl.DefaultJobOffersService;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zoer on 22.01.17.
 */
public class AllJobOffersCommand implements Command {


    @Override
    public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
        List<Pair<JobOffers,String>> jobOffersWithCompanyNames=DefaultJobOffersService.getInstance().getJobOffersWithCompanyNames();
        req.setAttribute("alljoboffers",jobOffersWithCompanyNames);
        return ViewJsp.General.JOBOFFERS;
    }
}
