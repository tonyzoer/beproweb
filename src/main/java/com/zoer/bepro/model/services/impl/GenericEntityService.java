package com.zoer.bepro.model.services.impl;

import com.zoer.bepro.model.dao.AbstractJDBCDao;
import com.zoer.bepro.model.dao.Identified;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.services.EntityService;
import org.apache.log4j.Logger;

import java.util.List;


/**
 * Created by Zoer on 13.01.2017.
 */
public abstract class GenericEntityService <T extends Identified<Integer>> implements EntityService<T>{

    abstract AbstractJDBCDao<T,Integer> getDao() throws PersistException;
    private final static Logger logger = Logger.getLogger(GenericEntityService.class);


    @Override
    public T insert(T t)  {
        try {
            return getDao().persist(t);
        } catch (PersistException e) {
        logger.debug(e);
        }
        return null;

    }

    @Override
    public void update(T t) throws PersistException {
         getDao().update(t);
    }

    @Override
    public void delete(T obj) throws PersistException {
        getDao().delete(obj);
    }

    @Override
    public T getById(Integer id) throws PersistException {
        return getDao().getByPK(id);
    }

    @Override
    public List<T> findAll() throws PersistException {
        return getDao().getAll();
    }

}
