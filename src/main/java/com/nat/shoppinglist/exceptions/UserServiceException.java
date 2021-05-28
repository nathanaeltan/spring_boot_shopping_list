package com.nat.shoppinglist.exceptions;

public class UserServiceException extends RuntimeException {

	private static final long serialVersionUID = 6964065009068977104L;

	public UserServiceException(String message) {
		super(message);
	}

}
