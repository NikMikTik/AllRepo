package com.exception;

public class BlogException extends Exception {
	public BlogException(String message) {
		super(message);
		System.out.println(message);

	}
}
