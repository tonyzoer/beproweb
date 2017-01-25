package com.zoer.bepro.model.dao;


/** Фабрика объектов для работы с базой данных */
public interface DaoFactory {

    public interface DaoCreator {
        public GenericDao create();
    }


    /** Возвращает объект для управления персистентным состоянием объекта */
    public GenericDao getDao(Class dtoClass) throws PersistException;
}
