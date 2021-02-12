package com.glints.satuamal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestIdException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5424469867023235499L;
	
	String message;
	
	@JsonIgnore
	String trace;
    
    public BadRequestIdException () {
    }
    
    public BadRequestIdException (String message) {
        super(message);
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}
