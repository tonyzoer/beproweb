package com.zoer.bepro.model.dao;

/**
 * Created by zoer on 26.12.16.
 */


import com.mysql.cj.jdbc.MysqlDataSourceFactory;
import com.zoer.bepro.model.dao.mysqldao.MyDataSourceFactory;
import com.zoer.bepro.model.domain.Specifications;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @param <T>  domain
 * @param <PK> pk key type (for future can be not only int)
 */
public abstract class AbstractJDBCDao<T extends Identified<PK>, PK extends Integer> implements GenericDao<T, PK> {
    private final static Logger logger = Logger.getLogger(AbstractJDBCDao.class);
    private boolean rollback = false;

    public AbstractJDBCDao() {
    }

    /*
    * For Tests
    * */
    @Override
    public void setRollback(boolean b) {
        rollback = b;
    }

    /**
     * SELECT * FROM [Table]
     */
    public abstract String getSelectAllQuery();

    /**
     * SELECT * FROM [Table] WHERE id=?
     */
    public abstract String getSelectQuery();

    /**
     * INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
    public abstract String getCreateQuery();

    /**
     * UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
     */
    public abstract String getUpdateQuery();

    /**
     * DELETE FROM [Table] WHERE id= ?;
     */
    public abstract String getDeleteQuery();

    /*
    * SELECT * FROM [Table] WHERE id=last_insert_id();
    * */
    public abstract String getSelectLastInsertQuery();


    protected abstract List<T> parseResultSet(ResultSet rs) throws PersistException;


    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;


    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws PersistException;


    @Override
    public T persist(T object) throws PersistException {
        T persistInstance;
        try (Connection connection = MyDataSourceFactory.getMySQLDataSource().getConnection()) {
            if (rollback) connection.setAutoCommit(false);
            String sql = getCreateQuery();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                prepareStatementForInsert(statement, object);
                int count = statement.executeUpdate();
                if (count != 1) {
                    logger.debug("On persist modify more then 1 record: " + count + " class:" + object.getClass().toString());
                    throw new PersistException("On persist modify more then 1 record: " + count);
                }
            } catch (Exception e) {
                logger.debug(e);
                throw new PersistException(e);
            }

            sql = getSelectLastInsertQuery();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet rs = statement.executeQuery();
                List<T> list = parseResultSet(rs);
                if ((list == null) || (list.size() != 1)) {
                    throw new PersistException("Exception on findByPK new persist data.");
                }
                persistInstance = list.iterator().next();
            } catch (Exception e) {
                logger.debug(e);
                throw new PersistException(e);
            } finally {
                if (rollback) {
                    connection.rollback();
                }
            }
        } catch (SQLException e) {
            logger.debug(e);
            throw new PersistException(e);
        }
        return persistInstance;
    }

    @Override
    public T getByPK(Integer key) throws PersistException {
        return getBy(getSelectQuery(), key.toString());
    }

    protected T getBy(String sql, String key) throws PersistException {
        List<T> list = null;
        try (Connection connection = MyDataSourceFactory.getMySQLDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setObject(1, key);
                ResultSet rs = statement.executeQuery();
                list = parseResultSet(rs);
            } catch (Exception e) {
                logger.debug(e);
                throw new PersistException(e);
            }
            if (list == null || list.size() == 0) {
                logger.debug("Record with PK = " + key + " not found.");
                throw new PersistException("Record with PK = " + key + " not found.");
            }
            if (list.size() > 1) {
                logger.debug("Received more than one record.");
                throw new PersistException("Received more than one record.");
            }
        } catch (SQLException e) {
            logger.debug(e);
        }
        return list.iterator().next();
    }

    @Override
    public void update(T object) throws PersistException {
        String sql = getUpdateQuery();
        try (Connection connection = MyDataSourceFactory.getMySQLDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                prepareStatementForUpdate(statement, object);
                int count = statement.executeUpdate();
                if (count != 1) {
                    logger.debug("On update modify more then 1 record: " + count);
                    throw new PersistException("On update modify more then 1 record: " + count);
                }
            } catch (Exception e) {
                logger.debug(e);
                throw new PersistException(e);
            }
        } catch (SQLException e) {
            logger.debug(e);
            e.printStackTrace();
        }
    }

    @Override
    public void delete(T object) throws PersistException {
        String sql = getDeleteQuery();
        try (Connection connection = MyDataSourceFactory.getMySQLDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try {
                    statement.setObject(1, object.getId());
                } catch (Exception e) {
                    throw new PersistException(e);
                }
                int count = statement.executeUpdate();
                if (count != 1) {
                    // throw new PersistException("On delete modify more then 1 record: " + count);
                    logger.debug("On delete modify more then 1 record: " + count);
                }
            } catch (Exception e) {
                throw new PersistException(e);
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    @Override
    public List<T> getAll() throws PersistException {
        return getAll(getSelectAllQuery());
    }

    @Override
    public List<T> getAll(String sql) throws PersistException {
        List<T> list;
        try (Connection connection = MyDataSourceFactory.getMySQLDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet rs = statement.executeQuery();
                list = parseResultSet(rs);
            } catch (Exception e) {
                throw new PersistException(e);
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return list;
    }


    //this code uses only in MySqlJobOffersDao and MySqlStudentProfileDao, its here to pretend copypaste
    protected void prepareStatementForInsertSpecification(PreparedStatement statement, T object, Identified<Integer> object2) throws PersistException {
        try {
            statement.setInt(1, object.getId());
            statement.setInt(2, object2.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    protected void addManyToManyRowToObj(String sql, T obj, Identified<Integer> object2) throws PersistException {
        try (Connection connection = MyDataSourceFactory.getMySQLDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                prepareStatementForInsertSpecification(statement, obj, object2);
                int count = statement.executeUpdate();
                if (count != 1) {
                    throw new PersistException("On persist modify more or less then 1 record: " + count);
                }
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }
}
