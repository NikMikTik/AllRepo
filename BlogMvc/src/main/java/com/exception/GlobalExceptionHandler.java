package com.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	
	//FOR MVC
	@ExceptionHandler(BlogException.class)
	public ModelAndView blogError(BlogException exception) {
		logger.info("Errorrrr" + exception.getLocalizedMessage());
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception.getMessage());
		mav.setViewName("BlogError");
		return mav;
	}
	
	
	/*@ExceptionHandler(BlogException.class)
	public ModelAndView dashoboarError(Exception exception) {
		logger.info("Errorrrr" + exception);
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception.getMessage());
		mav.setViewName("DashboardError");
		return mav;
	}*/
	
	@ExceptionHandler(ControllerException.class)
	public ModelAndView controllerError(Exception exception) {
		logger.info("Errorrrr" + exception);
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception.getMessage());
		mav.setViewName("ControllerError");
		return mav;
	}
	
	
	//FOR RSET
	
	
	/*@ExceptionHandler(Exception.class)
	public Exception blogError(Exception exception) {
		logger.error("Error Ocurred :: " + exception.getMessage());
		return exception;
	}
	
		@ExceptionHandler(BlogException.class)
		public Exception blogError(BlogException exception) {
			logger.info("Error Ocurred :: " + exception.getMessage());
			return exception;
		}
		*/
	
}
