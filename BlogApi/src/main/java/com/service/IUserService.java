package com.service;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.dto.BlogDto;
import com.dto.UserDto;
import com.exception.BlogException;
import com.exception.ControllerException;
import com.model.Blog;
import com.model.User;
import com.repository.UserRepository;

public interface IUserService {

	public boolean Register(UserDto uu);

	/*
	 * {ur.save(uu);
	 * 
	 * }
	 */

	public User findByEmailAndPassword(String email, String password);

	public User findByEmail(String email);

	int findIdByName(@Param("email") String email);

	public List<User> findBlogByUserId(int userid);

	List<User> findAllById(int userid);

	public User findByUserid(int userid);

	public ModelAndView funcCreate(@ModelAttribute UserDto userDto1, ModelMap modelMap) throws ControllerException;
	
	public boolean emptyBlogList(HttpSession session);

	public User createUser(UserDto userDto);

	public User updateUser(UserDto userDto) throws BlogException;

	public boolean deletedUser(UserDto userDto);

	public boolean deletedUserUsingId(int userid);

	public String loginFunction(UserDto userDto) throws BlogException;

	public List<UserDto> fetchAllUser();

	public String logoutFunction(UserDto userDto);


}