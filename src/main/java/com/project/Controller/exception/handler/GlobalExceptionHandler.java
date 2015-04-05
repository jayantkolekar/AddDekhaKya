package com.project.Controller.exception.handler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
 
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
     
    @ExceptionHandler(SQLException.class)
    public UserErrorMessage handleSQLException(HttpServletRequest request, Exception exception){
        logger.info("SQLException Occured :: Error : {}",exception.getMessage());
        UserErrorMessage response = new UserErrorMessage();
	    response.setUrl(request.getRequestURL().toString());
	    response.setMessage(exception.getMessage());
	     
	    return response;
    }
     
    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="IOException occured")
    @ExceptionHandler(IOException.class)
    public void handleIOException(){
        logger.error("IOException  Occured");       
    }
        
    @ExceptionHandler(Exception.class)
    public UserErrorMessage handleException(HttpServletRequest request, Exception exception){
        logger.error("Exception Occured :: Error : {}",exception.getMessage());   
        
        UserErrorMessage response = new UserErrorMessage();
	    response.setUrl(request.getRequestURL().toString());
	    response.setMessage(exception.getMessage());
	     
	    return response;
    }
}