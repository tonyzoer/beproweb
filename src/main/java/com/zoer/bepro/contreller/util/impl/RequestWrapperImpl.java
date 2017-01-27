package com.zoer.bepro.contreller.util.impl;

import com.zoer.bepro.contreller.exeptions.RequestAttributeNotPermittedException;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.SessionWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RequestWrapperImpl implements RequestWrapper {
    private HttpServletRequest req;
    private Map<String,String> params = new HashMap();
    private final Set<String> deniedToReadParameters = new HashSet<>();
    private final Set<String> deniedToWriteAttributes = new HashSet<>();


    public RequestWrapperImpl (HttpServletRequest req) {
        this.req = req;
    }

    public RequestWrapperImpl() {
        deniedToReadParameters.add("test");
        deniedToWriteAttributes.add("test");
    }

    @Override
    public void setAttribute(String attributeName, Object value) {
        if (deniedToWriteAttributes.contains(attributeName))
            throw new RequestAttributeNotPermittedException("tried setAttr: "+attributeName);
        req.setAttribute(attributeName, value);
    }

    @Override
    public String getParameter(String parameter) {
        if ( params.get( parameter ) != null ) {
            return params.get(parameter);
        }
        if (deniedToReadParameters.contains(parameter))
            throw new RequestAttributeNotPermittedException("tried getParam: "+parameter);
        return req.getParameter(parameter);
    }

    @Override
    public SessionWrapper getSessionWrapper(boolean toCreate) {
        return new SessionWrapperImpl(req.getSession(toCreate));
    }
    public  void addParameter(String name,String value){
        params.put(name,value);
    }
}
