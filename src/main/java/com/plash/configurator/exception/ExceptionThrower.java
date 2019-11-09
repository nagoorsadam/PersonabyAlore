package com.plash.configurator.exception;

import com.plash.configurator.component.CustomException;


public class ExceptionThrower {
//500
    public void throwGeneralException() throws Exception{
        Exception e = new Exception("Error from General Exception");
        throw e;
    }
    public void throwCustomException(final int errorcode,final String message) throws CustomException {

        CustomException e = new CustomException();
        e.setCode(errorcode);
        e.setMessage(message);
        throw e;
    }

}
