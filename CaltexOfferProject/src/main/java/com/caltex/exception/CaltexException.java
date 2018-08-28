package com.caltex.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaltexException extends RuntimeException{

	Logger logger = LoggerFactory.getLogger(CaltexException.class);

	public CaltexException(String message) {
		super(message);
		logger.info(message);
	}

	

}
