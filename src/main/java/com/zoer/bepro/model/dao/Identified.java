package com.zoer.bepro.model.dao;

import java.io.Serializable;

/**
 * Интерфейс идентифицируемых объектов.
 */
public interface Identified<PK extends Serializable> extends Comparable<Identified<Integer>> {

    /** Возвращает идентификатор объекта */
    public PK getId();

}
