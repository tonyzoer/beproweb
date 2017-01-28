package com.zoer.bepro.model.services.impl;

import com.zoer.bepro.model.services.*;

/**
 * Created by zoer on 28.01.17.
 */
public class DefaultServiceFactory {
    private static DefaultServiceFactory ourInstance = new DefaultServiceFactory();
    private DefaultCompanyProfileService defaultCompanyProfileService = new DefaultCompanyProfileService();
    private DefaultCoursesService defaultCoursesService = new DefaultCoursesService();
    private DefaultJobOffersService defaultJobOffersService = new DefaultJobOffersService();
    private DefaultJobOfferTextService defaultJobOfferTextService = new DefaultJobOfferTextService();
    private DefaultProfileService defaultProfileService = new DefaultProfileService();
    private DefaultSpecificationService defaultSpecificationService = new DefaultSpecificationService();
    private DefaultStudentProfileService defaultStudentProfileService = new DefaultStudentProfileService();
    private DefaultUserSevice defaultUserSevice = new DefaultUserSevice();

    public DefaultCompanyProfileService getDefaultCompanyProfileService() {
        return defaultCompanyProfileService;
    }

    public DefaultCoursesService getDefaultCoursesService() {
        return defaultCoursesService;
    }

    public DefaultJobOffersService getDefaultJobOffersService() {
        return defaultJobOffersService;
    }

    public DefaultJobOfferTextService getDefaultJobOfferTextService() {
        return defaultJobOfferTextService;
    }

    public DefaultProfileService getDefaultProfileService() {
        return defaultProfileService;
    }

    public DefaultSpecificationService getDefaultSpecificationService() {
        return defaultSpecificationService;
    }

    public DefaultStudentProfileService getDefaultStudentProfileService() {
        return defaultStudentProfileService;
    }

    public DefaultUserSevice getDefaultUserSevice() {
        return defaultUserSevice;
    }


    public static DefaultServiceFactory getInstance() {
        return ourInstance;
    }

    private DefaultServiceFactory() {
        if(ourInstance!=null){
            throw new IllegalStateException("Already exsists");
        }
    }
    @Override
    public Object clone() throws CloneNotSupportedException{
        throw new CloneNotSupportedException("Cannot clone instance of this class");
    }

}
