package test.command;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.command.CommandMapping;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.SessionWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.contreller.util.impl.RequestWrapperImpl;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.Profile;
import com.zoer.bepro.model.domain.StudentProfile;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.ProfileType;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by zoer on 01.02.17.
 */
public class AccountSwitcherCommandTest {
    private RequestWrapper req = mock(RequestWrapper.class);
    private SessionWrapper ses = mock(SessionWrapper.class);

    public void execute() throws Exception {
        Command c = CommandMapping.DETECTPROFILE.getCommand();
        User u = null;
        when(req.getSessionWrapper().getUser()).thenReturn(u);
        assertEquals(ViewJsp.General.MAIN, c.execute(req));
    }

    @Test
    public void execute_STUDENT() throws InsufficientPermissionsException, PersistException {
        Command c = CommandMapping.DETECTPROFILE.getCommand();
        User user = util.getRandomStudentUser();
        when(req.getSessionWrapper()).thenReturn(ses);
        when(ses.getUser()).thenReturn(user);
        when(ses.getProfileType()).thenReturn(ProfileType.STUDENT);

        assertEquals(ViewJsp.StudentSpace.STUDENT_JSP, c.execute(req));
    }

    @Test
    public void execute_COMPANY() throws InsufficientPermissionsException, PersistException {
        Command c = CommandMapping.DETECTPROFILE.getCommand();
        User user = util.getRandomCompanyUser();
        when(req.getSessionWrapper()).thenReturn(ses);
        when(ses.getUser()).thenReturn(user);
        when(ses.getProfileType()).thenReturn(ProfileType.COMPANY);

        assertEquals(ViewJsp.CompanySpace.COMPANY_JSP, c.execute(req));
    }

    @Test
    public void execute_NOONE() throws InsufficientPermissionsException, PersistException {
        Command c = CommandMapping.DETECTPROFILE.getCommand();
        User user = util.getRandomNooneUser();
        when(req.getSessionWrapper()).thenReturn(ses);
        when(ses.getUser()).thenReturn(user);
        when(ses.getProfileType()).thenReturn(ProfileType.NOONE);

        assertEquals(ViewJsp.UserSpace.CHOOSE_PROFILE_JSP, c.execute(req));
    }

    @Test
    public void execute_user_null() throws InsufficientPermissionsException, PersistException {
        Command c = CommandMapping.DETECTPROFILE.getCommand();
        User user = null;
        when(req.getSessionWrapper()).thenReturn(ses);
        when(ses.getUser()).thenReturn(user);
        assertEquals(ViewJsp.General.MAIN, c.execute(req));
    }
}