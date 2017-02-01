package test.command;

import com.zoer.bepro.contreller.command.Command;
import com.zoer.bepro.contreller.command.CommandMapping;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.SessionWrapper;
import com.zoer.bepro.contreller.util.ViewJsp;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.services.ProfileType;
import com.zoer.bepro.model.services.impl.DefaultServiceFactory;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by zoer on 01.02.17.
 */
public class AddCourseToStudentTest {
    private RequestWrapper req = mock(RequestWrapper.class);
    private SessionWrapper ses = mock(SessionWrapper.class);
    @Test
    public void execute() throws InsufficientPermissionsException, PersistException {
        Command c = CommandMapping.ADDCOURSETOSTODENT.getCommand();
        when(req.getParameter("course")).thenReturn(String.valueOf(util.getRandomInt()));
        when(req.getSessionWrapper()).thenReturn(ses);
        when(ses.getUser()).thenReturn(util.getRandomStudentUser());
        when(req.getParameter("url")).thenReturn(String.valueOf(util.getRandomInt()));
        when(req.getParameter("item")).thenReturn(String.valueOf(util.getRandomInt()));
        Command cp=new Command() {
            @Override
            public String execute(RequestWrapper req) throws InsufficientPermissionsException, PersistException {
                return ViewJsp.General.SPECIFICATION;
            }
        };
        assertEquals(ViewJsp.General.SPECIFICATION,cp.execute(req));
    }

}