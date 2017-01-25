package com.zoer.bepro.model.dao.mysqldao;

import com.zoer.bepro.model.dao.*;
import com.zoer.bepro.model.domain.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class MySqlDaoFactory implements DaoFactory{
    private static MySqlDaoFactory instance = new MySqlDaoFactory();
    private Map<Class, DaoCreator> creators;
    private DataSource ds = MyDataSourceFactory.getMySQLDataSource();
    private MySqlDaoFactory() {

        TreeMap<String, Integer> tre;
        creators = new HashMap<Class, DaoCreator>();
        creators.put(User.class, new DaoCreator() {
            @Override
            public GenericDao create() {
                return new MySqlUserDao();
            }
        });
        creators.put(Profile.class, new DaoCreator() {
            @Override
            public GenericDao create() {
                return new MySqlProfileDao();
            }
        });
        creators.put(StudentProfile.class, new DaoCreator() {
            @Override
            public GenericDao create() {
                return new MySqlStudentProfileDao();
            }
        });
        creators.put(CompanyProfile.class, new DaoCreator() {
            @Override
            public GenericDao create() {
                return new MySqlCompanyProfileDao();
            }
        });
        creators.put(Specifications.class, new DaoCreator() {
            @Override
            public GenericDao create() {
                return new MySqlSpecificationsDao();
            }
        });
        creators.put(JobOffers.class, new DaoCreator() {
            @Override
            public GenericDao create() {
                return new MySqlJobOffersDao();
            }
        });
        creators.put(Courses.class, new DaoCreator() {
            @Override
            public GenericDao create() {
                return new MySqlCoursesDao();
            }
        });
        creators.put(JobOfferText.class, new DaoCreator() {
            @Override
            public GenericDao create() {
                return new MySqlJobOfferTextDao();
            }
        });
    }

    public static MySqlDaoFactory getInstance() {
        return instance;
    }

    public Connection getContext() throws PersistException {
        Connection connection = null;
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return connection;
    }

    @Override
    public GenericDao getDao( Class dtoClass) throws PersistException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create();
    }

}

