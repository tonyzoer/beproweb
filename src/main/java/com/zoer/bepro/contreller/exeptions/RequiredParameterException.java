package com.zoer.bepro.contreller.exeptions;

public class RequiredParameterException extends InvalidParameterException {
    public RequiredParameterException(String message) {
        super(message);
    }
}
