package com.exception;

public class ControllerException extends Exception {
	public ControllerException(String message) {
		super(message);

		System.out.println(message);

	}
}
