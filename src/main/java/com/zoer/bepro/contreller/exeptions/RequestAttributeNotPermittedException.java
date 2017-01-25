package com.zoer.bepro.contreller.exeptions;

public class RequestAttributeNotPermittedException extends RuntimeException {
    public RequestAttributeNotPermittedException(String message) {
        super(message);
    }
}
