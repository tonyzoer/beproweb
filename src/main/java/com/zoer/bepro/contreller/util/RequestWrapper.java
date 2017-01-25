package com.zoer.bepro.contreller.util;

public interface RequestWrapper {
    void setAttribute(String attributeName, Object value);
    String getParameter(String attributeName);
    SessionWrapper getSessionWrapper(boolean toCreate);
    public  void addParameter(String name,String value);
    default SessionWrapper getSessionWrapper() {
        return getSessionWrapper(false);
    }
}
