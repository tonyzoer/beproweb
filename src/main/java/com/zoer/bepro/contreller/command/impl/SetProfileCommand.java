package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.ICommand;
import com.zoer.bepro.contreller.command.CommandFactory;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.CompanyProfile;
import com.zoer.bepro.model.domain.StudentProfile;
import com.zoer.bepro.model.services.ProfileType;
import com.zoer.bepro.model.services.impl.DefaultServiceFactory;

/**
 * Created by zoer on 20.01.17.
 */
public class SetProfileCommand implements ICommand {


    @Override
    public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
        String type=req.getParameter("type");
        switch (type){
            case "student":
                StudentProfile stp=new StudentProfile();
                stp.setName(req.getParameter("name"));
                stp.setTel(req.getParameter("tel"));
                stp.setCountry(req.getParameter("country"));
                stp.setCvurl(req.getParameter("cv"));
                DefaultServiceFactory.getInstance().getDefaultProfileService().createStudentProfile(req.getSessionWrapper().getUser().getProfile(),stp);
                req.getSessionWrapper().setProfileType(ProfileType.STUDENT);
                return CommandFactory.STUDENTPROFILE.getCommand().execute(req);
            case "company":
                CompanyProfile cmp=new CompanyProfile();
                cmp.setImgurl(req.getParameter("picture"));
                cmp.setInfotxt(req.getParameter("text"));
                DefaultServiceFactory.getInstance().getDefaultProfileService().createCompanyProfile(req.getSessionWrapper().getUser().getProfile(),cmp);
                req.getSessionWrapper().setProfileType(ProfileType.COMPANY);
                return CommandFactory.COMPANYPROFILE.getCommand().execute(req);
        }

        return ViewJsp.UserSpace.CHOOSE_PROFILE_JSP;
            }
}
