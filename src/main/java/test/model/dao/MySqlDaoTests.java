package test.model.dao;

import com.zoer.bepro.model.dao.DaoFactory;
import com.zoer.bepro.model.dao.GenericDao;
import com.zoer.bepro.model.dao.Identified;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.dao.mysqldao.MySqlDaoFactory;
import com.zoer.bepro.model.domain.*;
import org.junit.Before;
import org.junit.runners.Parameterized;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;


public class MySqlDaoTests extends GenericDaoTest<Connection> {

    private Connection connection;

    private GenericDao dao;

    private static final DaoFactory factory = MySqlDaoFactory.getInstance();

    @Parameterized.Parameters
    public static Collection getParameters() {
        return Arrays.asList(new Object[][]{
                {User.class, new User()},
                {StudentProfile.class,new StudentProfile()},
                {Specifications.class,new Specifications()},
                {Profile.class,new Profile()},
                {JobOffers.class, new JobOffers()},
                {Courses.class,new Courses()},
                {CompanyProfile.class,new CompanyProfile()}
        });
    }

    @Before
    public void setUp() throws PersistException, SQLException {
//        connection = factory.getContext();
//        connection.setAutoCommit(false);
        dao = factory.getDao(daoClass);
//        dao.setRollback(true);
    }

//    @After
//    public void tearDown() throws SQLException {
//        context().rollback();
//        context().close();
//    }

    @Override
    public GenericDao dao() {
        return dao;
    }

    @Override
    public Connection context() {
        return connection;
    }

    public MySqlDaoTests(Class clazz, Identified<Integer> notPersistedDto) {
        super(clazz, notPersistedDto);
    }
}
