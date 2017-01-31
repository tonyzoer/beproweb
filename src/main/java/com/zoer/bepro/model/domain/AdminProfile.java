package com.zoer.bepro.model.domain;

import com.zoer.bepro.model.dao.Identified;

/**
 * Created by zoer on 27.12.16.
 */
public class AdminProfile  implements Identified<Integer>{
    @Override
    public Integer getId() {
        return null;
    }
    @Override
    public int compareTo(Identified<Integer> integerIdentified) {
        return this.getId()-integerIdentified.getId();
    }
}
