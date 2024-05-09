package com.example.demo.exception;

public class CustomerNotFoundException extends Exception{
	private String message;
	
	public CustomerNotFoundException (String message) {
        super(message);
        this.message = message;
    }

}
