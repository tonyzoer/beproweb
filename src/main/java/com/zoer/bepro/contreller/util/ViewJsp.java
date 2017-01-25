package com.zoer.bepro.contreller.util;

public interface ViewJsp {
    interface General {
//        String LOGIN_PAGE = "/";
//        String LOGOFF_PAGE = "jsp/logoff.jsp";
        String REGISTER_JSP = "WEB-INF/jsp/reg.jsp";
        String ERROR_404 = "404.jsp";
        String MAIN="/";
        String JOBOFFERS="WEB-INF/jsp/alljoboffers.jsp";
    }

    interface AdminSpace {
        String ADMIN_JSP = "WEB-INF/jsp/adminprofile.jsp";
    }

    interface UserSpace {
        String USER_JSP = "jsp/.jsp";
        String REG_THANK="WEB-INF/jsp/regthanks.jsp";
        String CHOOSE_PROFILE_JSP="WEB-INF/jsp/chooseprofiletype.jsp";
    }
    interface StudentSpace{
        String STUDENT_JSP="WEB-INF/jsp/studentprofile.jsp";
    }
    interface CompanySpace{
        String COMPANY_JSP="WEB-INF/jsp/companyprofile.jsp";
    }

    interface JobOffer{
        String JOBOFFERINFO="WEB-INF/jsp/jobofferinfo.jsp";
        String CREATWJOBOFFER="WEB-INF/jsp/createjoboffer.jsp";
    }


}
