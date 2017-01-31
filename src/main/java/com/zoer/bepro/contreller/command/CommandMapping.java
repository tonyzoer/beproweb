package com.zoer.bepro.contreller.command;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.command.impl.*;

/**
 * Created by zoer on 23.01.17.
 */
public enum CommandMapping {
    LOGIN(new LoginCommand()),
    REGISTER(new RegistrationCommand()),
    DETECTPROFILE(new AccountSwitcherCommand()),
    SETPROFILE(new SetProfileCommand()),
    ADDSPECIFICATION(new AddSpecificationCommand()),
    LOGOUT(new LogOutCommand()),
    JOBOFFERS(new AllJobOffersCommand()),
    JOBOFFERINFO(new JobOfferFullInfoCommand()),
    LOCALE(new LocalizationCommand()),
    CREATEJOBOFFERVIEW(new CreateJobOfferViewCommand()),
    CREATEJOBOFFER(new CreateJobOfferCommand()),
    REGISTERVIEW(new RegistrationViewCommand()),
    COMPANYPROFILE(new CompanyProfileCommand()),
    STUDENTPROFILE(new StudentProffileCommand()),
    DELETEJOBOFFER(new DeleteJobOfferCommand()),
    ADDJOBOFFERTOSTUDENT(new AddJobOfferToStudentProfileCommand()),
    USERINFO(new UserInfoCommand()),
    SPECINFO(new ShowSpecCoursesCommand()),
    ADDCOURSETOSTODENT(new AddCourseToStudent()),
    REMOVECOURSEFROMSTUDENT(new RemoveCourseFromStudent())
    ;



    private Command command;
    CommandMapping(Command instance) {
        this.command = instance;
    }
    public Command getCommand() {
        return command;
    }
}
