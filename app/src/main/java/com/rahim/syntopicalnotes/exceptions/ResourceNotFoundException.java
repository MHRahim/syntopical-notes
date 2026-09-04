package com.rahim.syntopicalnotes.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String message) {
        super(message);
	}
}
