package com.zoer.bepro.contreller.command.impl;

import com.zoer.bepro.contreller.command.Command;

/**
 * Created by zoer on 23.01.17.
 */
public enum CommandMapping {
    LOGIN(LoginCommand.getInstance()),
    REGISTER(RegistrationCommand.getInstance()),
    DETECTPROFILE(AccountSwitcherCommand.getInstance()),
    SETPROFILE(SetProfileCommand.getInstance()),
    ADDSPECIFICATION(AddSpecificationCommand.getInstance()),
    LOGOUT(LogOutCommand.getInstance()),
    JOBOFFERS(AllJobOffersCommand.getInstance()),
    JOBOFFERINFO(JobOfferFullInfoCommand.getInstance()),
    LOCALE(LocalizationCommand.getInstance()),
    CREATEJOBOFFERVIEW(CreateJobOfferViewCommand.getInstance()),
    CREATEJOBOFFER(CreateJobOfferCommand.getInstance()),
    REGISTERVIEW(RegistrationViewCommand.getInstance()),
    COMPANYPROFILE(CompanyProfileCommand.getInstance()),
    STUDENTPROFILE(StudentProffileCommand.getInstance()),
    DELETEJOBOFFER(DeleteJobOfferCommand.getInstance()),
    ADDJOBOFFERTOSTUDENT(AddJobOfferToStudentProfileCommand.getInstance()),
    USERINFO(UserInfoCommand.getInstance()),
    SPECINFO(ShowSpecCoursesCommand.getInstance())
    ;



    private Command command;
    CommandMapping(Command instance) {
        this.command = instance;
    }
    public Command getCommand() {
        return command;
    }
}
