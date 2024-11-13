package com.api.tracking.generator;

public class InvalidParameterException extends Throwable {
	
	private static final long serialVersionUID = 1L;

	public InvalidParameterException() {}

    public InvalidParameterException(String message)
    {
       super(message);
    }

}
