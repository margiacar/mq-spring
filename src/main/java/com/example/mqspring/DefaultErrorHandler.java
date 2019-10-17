package com.example.mqspring;

import org.springframework.util.ErrorHandler;

public class DefaultErrorHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
        System.err.println("spring jms custom error handling example");
        System.err.println(t.getCause().getMessage());
    }
}