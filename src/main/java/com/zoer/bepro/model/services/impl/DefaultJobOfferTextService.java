package com.zoer.bepro.model.services.impl;

import com.zoer.bepro.model.dao.AbstractJDBCDao;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.dao.mysqldao.MySqlDaoFactory;
import com.zoer.bepro.model.domain.JobOfferText;

/**
 * Created by zoer on 24.01.17.
 */
public class DefaultJobOfferTextService extends GenericEntityService<JobOfferText> {
    private static DefaultJobOfferTextService ourInstance = new DefaultJobOfferTextService();

    public static DefaultJobOfferTextService getInstance() {
        return ourInstance;
    }

    private DefaultJobOfferTextService() {
    }

    @Override
    AbstractJDBCDao<JobOfferText, Integer> getDao() throws PersistException {
        return (AbstractJDBCDao<JobOfferText, Integer>) MySqlDaoFactory.getInstance().getDao(JobOfferText.class);
    }
}
