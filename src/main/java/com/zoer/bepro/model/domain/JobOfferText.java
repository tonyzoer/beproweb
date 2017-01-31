package com.zoer.bepro.model.domain;

import com.zoer.bepro.model.dao.Identified;

/**
 * Created by zoer on 24.01.17.
 */
public class JobOfferText implements Identified<Integer> {
Integer id;
String text;

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Integer getId() {
        return id;
    }
    @Override
    public int compareTo(Identified<Integer> integerIdentified) {
        return this.getId()-integerIdentified.getId();
    }
}
