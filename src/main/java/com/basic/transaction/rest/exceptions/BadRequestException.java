package com.basic.transaction.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
	private static final long serialVersionUID = 6515859333106154905L;

	public BadRequestException() {
        super();
    }
	
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public BadRequestException(String message) {
        super(message);
    }
    
    public BadRequestException(Throwable cause) {
        super(cause);
    }
}