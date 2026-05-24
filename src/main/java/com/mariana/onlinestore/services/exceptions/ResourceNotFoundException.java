package com.mariana.onlinestore.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object id) {
		super("Resource was not found. Id: " + id);
	}
	
}
