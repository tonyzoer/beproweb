package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.CompanyProfile;
import com.zoer.bepro.model.domain.StudentProfile;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.ProfileType;
import com.zoer.bepro.model.services.impl.DefaultProfileService;

import javax.swing.text.View;

/**
 * Created by zoer on 20.01.17.
 */
public class SetProfileCommand implements Command {


    @Override
    public String execute(RequestWrapper req, User user) throws InsufficientPermissionsException, PersistException {
        String type=req.getParameter("type");
        switch (type){
            case "student":
                StudentProfile stp=new StudentProfile();
                stp.setName(req.getParameter("name"));
                stp.setTel(req.getParameter("tel"));
                stp.setCountry(req.getParameter("country"));
                stp.setCvurl(req.getParameter("cv"));
                DefaultProfileService.getInstance().createStudentProfile(user.getProfile(),stp);
                req.getSessionWrapper().setProfileType(ProfileType.STUDENT);
                return CommandMapping.STUDENTPROFILE.getCommand().execute(req,user);
            case "company":
                CompanyProfile cmp=new CompanyProfile();
                cmp.setImgurl(req.getParameter("picture"));
                cmp.setInfotxt(req.getParameter("text"));
                DefaultProfileService.getInstance().createCompanyProfile(user.getProfile(),cmp);
                req.getSessionWrapper().setProfileType(ProfileType.COMPANY);
                return CommandMapping.COMPANYPROFILE.getCommand().execute(req,user);
        }

        return ViewJsp.UserSpace.CHOOSE_PROFILE_JSP;
            }
}
