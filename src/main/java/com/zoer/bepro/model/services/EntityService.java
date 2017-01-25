package com.zoer.bepro.model.services;

import com.zoer.bepro.model.dao.Identified;
import com.zoer.bepro.model.dao.PersistException;

import java.util.List;

public interface EntityService<T extends Identified<Integer>> {

    T insert(T t) throws PersistException;

    void update(T t) throws PersistException;

    void delete(T obj) throws PersistException;

    T getById(Integer id) throws PersistException;

    List<T> findAll() throws PersistException;
}

