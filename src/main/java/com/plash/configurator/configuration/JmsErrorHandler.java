package com.plash.configurator.configuration;

import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

/**
 * Created by Utsav on 11-Mar-17.
 */
@Service
public class JmsErrorHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
        //handle exception here
        System.out.println("JMS Exception caught\n\n");
        t.printStackTrace();
    }

}