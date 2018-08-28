package com.restController;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dto.UserDto;
import com.exception.BlogException;
import com.model.User;
import com.service.IRedisService;
import com.service.IUserService;

import redis.clients.jedis.Jedis;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

	@Autowired
	private IUserService userService;

	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private IRedisService redis;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestBody UserDto userDto) throws BlogException {
		
		String msg=userService.loginFunction(userDto);
		if(msg==null)
			return "Cannot login";
		return msg; 
		

		}

	@PostMapping("/logout")
	public String logout(@RequestBody UserDto userDto) {
		String msg=userService.logoutFunction(userDto);
		return msg; 
		

		
	}

}
