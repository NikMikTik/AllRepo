package com.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;


import com.dto.BlogDto;
import com.dto.UserDto;
import com.exception.BlogException;
import com.exception.ControllerException;
import com.model.Blog;
import com.model.User;
import com.repository.BlogRepository;
import com.repository.UserRepository;
import com.sun.java_cup.internal.runtime.Symbol;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRedisService redis;

	protected ModelMapper modelMapper = new ModelMapper();
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	ModelAndView modelAndView = new ModelAndView();

	protected User usr;
	protected UserDto udo;

	@Autowired
	private final UserRepository ur;

	public UserServiceImpl(UserRepository ur) {
		this.ur = ur;
	}

	@Override
	public boolean Register(UserDto udo) {
		boolean value = false;
		String emailcheck = udo.getEmail();
		if (ur.findByEmail(emailcheck) == null) {
			User uu = modelMapper.map(udo, User.class);
			ur.save(uu);
			value = true;
		}
		return value;

	}

	@Override
	public User findByEmailAndPassword(String email, String password) {
		return ur.findByEmailAndPassword(email, password);

	}

	public List<UserDto> fetchAllUser() {
		List<UserDto> udlist = new ArrayList<>();
		List<User> users = ur.findAll();
		for (User user : users) {
			udo = modelMapper.map(user, UserDto.class);
			udlist.add(udo);
		}
		return udlist;

	}

	/*
	 * private List<UserDto> UserAssembler(List<User> users) { List<UserDto>
	 * userList=new ArrayList<>(); users.forEach(user -> { UserDto userDto =new
	 * UserDto(); userDto.setUserid(user.getUserid());
	 * userDto.setUsername(user.getUsername());
	 * userDto.setPassword(user.getPassword()); userDto.setEmail(user.getEmail());
	 * userList.add(userDto); }); return userList;
	 * 
	 * }
	 */

	@Override
	public User findByEmail(String email) {
		return ur.findByEmail(email);

	}

	@Override
	public int findIdByName(String email) {
		return ur.findIdByName(email);
	}

	@Override
	public User findByUserid(int userid) {
		return ur.findByUserid(userid);
	}

	@Override
	public List<User> findAllById(int userid) {

		return ur.findAllById(userid);
	}

	@Override
	public ModelAndView funcCreate(UserDto userDto1, ModelMap modelMap) throws ControllerException {
		String username = userDto1.getUsername();
		String password = userDto1.getPassword();
		String email = userDto1.getEmail();
		UserDto userDto = new UserDto();
		userDto.setUsername(username);
		userDto.setPassword(password);
		userDto.setEmail(email);
		if (Register(userDto)) {
			logger.info("Registration of User Done");
			modelMap.put("check", "true");
			modelAndView.setViewName("Login");
		} else if (findByEmail(email) != null) {
			logger.info("Already Existing Email");
			modelMap.put("check", "Existing");
			modelAndView.setViewName("SignUp");
		} else {
			modelMap.put("check", "false");
			logger.info("Not able to Register the User");
			modelAndView.setViewName("SignUp");
			throw new ControllerException("Not Able to Register the User");
		}
		return modelAndView;
	}

	@Override
	public List<User> findBlogByUserId(int userid) {
		return userRepository.findBlogByUserId(userid);
	}

	@Override
	public boolean emptyBlogList(HttpSession session) {
		boolean value = true;
		List<User> userList = new ArrayList<>();
		int ii = (Integer) session.getAttribute("uid");
		userList = findBlogByUserId(ii);
		for (User user : userList) {
			if (user.getBlog().isEmpty()) {
				value = false;
			}
		}
		return value;
	}

	@Override
	public User createUser(UserDto userDto) {
		String emailcheck = userDto.getEmail();
		
		if (userRepository.findByEmail(emailcheck) == null) {
			User user = modelMapper.map(userDto, User.class);
			return userRepository.save(user);
		} else {
			return null;
		}

	}

	@Override
	public User updateUser(UserDto userDto) throws BlogException {
		int idcheck = userDto.getUserid();
		if (userRepository.findByUserid(idcheck) != null) {

			User user = userRepository.findByUserid(idcheck);
			if (!user.getEmail().equals(userDto.getEmail()))
				if (userRepository.findByEmail(userDto.getEmail()) == null) {

					user.setEmail(userDto.getEmail());

				} else {
					throw new BlogException("Email Id Already exists");
				}

			user.setUsername(userDto.getUsername());
			user.setPassword(userDto.getPassword());

			return userRepository.save(user);
		} else {
			return null;
		}

	}

	@Override
	public boolean deletedUser(UserDto userDto) {
		int idcheck = userDto.getUserid();
		if (userRepository.findByUserid(idcheck) != null) {
			User user = userRepository.findByUserid(idcheck);
			userRepository.delete(user);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deletedUserUsingId(int userid) {
		if (userRepository.findByUserid(userid) != null) {
			User user = userRepository.findByUserid(userid);
			userRepository.delete(user);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String loginFunction(UserDto userDto) throws BlogException {
		User user1 = new User();
		String key = user1.getEmail();
		User user = modelMapper.map(userDto, User.class);
		user1 = userService.findByEmail(user.getEmail());
		if (userService.findByEmail(user.getEmail()) == null) {
			return "You are not a registered Author...Kindly Register yourself..";
			
		}	
			if (user.getPassword().equals(user1.getPassword())) {
				try {
					Object token = Jwts.builder().setSubject("users/TzMUocMF4p").setExpiration(new Date(1300819380))
							.claim("name", "Robert Token Man").claim("scope", "self groups/admins")
							.signWith(SignatureAlgorithm.HS256, "secret".getBytes("UTF-8")).compact();
					redis.setValue(user.getEmail(), token);
					return "You have successfully logged In";
				} catch (Exception e) {

					e.printStackTrace();
				}
			}else {
				return "Username/Password Invalid";
				
			}
			return "login method called";
	}

	@Override
	public String logoutFunction(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		if(redis.deleteValue(user.getEmail())==null)
		return "cannot logout";
		return "logged out successfully";

	}
}
