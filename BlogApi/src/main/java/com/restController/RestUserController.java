package com.restController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dto.BlogDto;
import com.dto.UserDto;
import com.dto.BlogDto;
import com.exception.BlogException;
import com.model.User;
import com.repository.UserRepository;
import com.service.IRedisService;
import com.service.IUserService;
import com.service.UserServiceImpl;

@RestController
@RequestMapping("/api")
public class RestUserController {
	Logger logger = LoggerFactory.getLogger(RestUserController.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	IUserService userService;
	
	@Autowired
	IRedisService iredis;

	

	@RequestMapping(value = "/allUser", method = RequestMethod.GET)
	public List<UserDto> retrieveUsers() {
		return userService.fetchAllUser();
	}

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public User createUser(@Valid @RequestBody UserDto userDto, HttpServletRequest request,
			HttpServletResponse response) throws BlogException {
		User createdUser = userService.createUser(userDto);
		if (createdUser == null) {
			throw new BlogException("Cannot Create New User");
		} else {
			logger.info("New User Created");
			return createdUser;
		}

	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	public User updateUser(@RequestBody UserDto userDto, HttpServletRequest request, HttpServletResponse response)
			throws BlogException {
		Object token = iredis.getValue(request.getHeader("Authorization"));
		if (token == null) {
			throw new BlogException("You are not allowed to update the profile");
		} else {
			User updatedUser = this.userService.updateUser(userDto);
			if (updatedUser == null) {
				throw new BlogException("Cannot update New User");
			} else {
				logger.info("User Updated");
				return updatedUser;
			}
		}

	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	public String deleteUser(@RequestBody UserDto userDto, HttpServletRequest request, HttpServletResponse response)
			throws BlogException {
		Object token = iredis.getValue(request.getHeader("Authorization"));
		if (token == null) {
			throw new BlogException("You are not allowed to update the profile");
		} else {
			boolean deletedUser = this.userService.deletedUser(userDto);
			if (deletedUser == false) {
				throw new BlogException("Cannot Delete User No:" + userDto.getUserid()
						+ " Either It does Not Exist or It is Already Deleted ");
			} else {
				logger.info("User No: " + userDto.getUserid() + " Deleted");
				return "User No: " + userDto.getUserid() + " Deleted";
			}
		}
	}

	@RequestMapping(value = "/deleteUser/{userid}", method = RequestMethod.DELETE, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	public String deleteUserUsingId(@PathVariable("userid") int userid, HttpServletRequest request, HttpServletResponse response) throws BlogException {
		boolean deletedUserUsingId = this.userService.deletedUserUsingId(userid);
		Object token = iredis.getValue(request.getHeader("Authorization"));
		if (token == null) {
			throw new BlogException("You are not allowed to update the profile");
		} else {
			if (deletedUserUsingId == false) {
				throw new BlogException(
						"Cannot Delete User No:" + userid + " Either It does Not Exist or It is Already Deleted ");
			} else {
				logger.info("User No: " + userid + " Deleted");
				return "User No: " + userid + " Deleted";
			}
		}
	}

}
