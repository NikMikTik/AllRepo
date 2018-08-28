package com.UnitTestCases;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.BlogApplication;
import com.dto.BlogDto;
import com.dto.UserDto;
import com.exception.BlogException;
import com.model.User;
import com.service.IBlogService;
import com.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(BlogApplication.class)
public class UserTestCases {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	Integer port;

	@MockBean
	IBlogService blogService;

	@MockBean
	IUserService userService;

	Logger logger = LoggerFactory.getLogger(UserTestCases.class);

	@Test
	public void findByBlogidTest() throws BlogException {

		String title = "blog 1";
		Integer blogId = 1;
		BlogDto blogDto = new BlogDto();
		blogDto.setBlogid(blogId);
		blogDto.setBlogTitle(title);
		Mockito.when(blogService.findByBlogid(Mockito.anyInt())).thenReturn(Arrays.asList(blogDto));
		String url = "http://localhost:" + port + "/api/blog/1";
		List<BlogDto> blogDtos = restTemplate
				.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<BlogDto>>() {
				}).getBody();
		Assertions.assertThat(blogDtos.size()).isEqualTo(1);
		Assertions.assertThat(blogDtos.get(0).getBlogid()).isEqualTo(blogId);
		Assertions.assertThat(blogDtos.get(0).getBlogTitle()).isEqualTo(title);
		logger.info("Test Run Successful");

	}

	@Test
	public void fetchAllUserTest1() {

		String email = "nik@gmail.com";
		String password = "qwe";
		int uid = 1;
		String usern = "Nikku";

		// 1
		UserDto userDto = new UserDto();
		userDto.setEmail(email);
		userDto.setPassword(password);
		userDto.setUserid(uid);
		userDto.setUsername(usern);

		List<UserDto> userDtoList = new ArrayList<>();
		userDtoList.add(userDto);

		Mockito.when(userService.fetchAllUser()).thenReturn(userDtoList);
		String url = "http://localhost:" + port + "/api/allUser";
		List<UserDto> userDto1 = restTemplate
				.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<UserDto>>() {
				}).getBody();
		Assertions.assertThat(userDto1.size()).isEqualTo(1);
		Assertions.assertThat(userDto1.get(0).getEmail()).isEqualTo(email);
		Assertions.assertThat(userDto1.get(0).getUsername()).isEqualTo(usern);
		logger.info("Test Run Successful");

	}

	@Test
	public void fetchAllUserTest2() {

		String email = "nik@gmail.com";
		String password = "qwe";
		int uid = 1;
		String usern = "Nikku";

		// 1
		UserDto userDto = new UserDto();
		userDto.setEmail(email);
		userDto.setPassword(password);
		userDto.setUserid(uid);
		userDto.setUsername(usern);

		// 2
		UserDto userDto2 = new UserDto();
		userDto2.setEmail("mik@gamil.com");
		userDto2.setUserid(2);
		userDto2.setUsername("Mikku");
		userDto2.setPassword("qwe");

		List<UserDto> userDtoList = new ArrayList<>();
		userDtoList.add(userDto);
		userDtoList.add(userDto2);

		Mockito.when(userService.fetchAllUser()).thenReturn(userDtoList);
		String url = "http://localhost:" + port + "/api/allUser";
		List<UserDto> userDto1 = restTemplate
				.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<UserDto>>() {
				}).getBody();
		Assertions.assertThat(userDto1.size()).isEqualTo(2);
		Assertions.assertThat(userDto1.get(0).getEmail()).isEqualTo(email);
		Assertions.assertThat(userDto1.get(0).getUsername()).isEqualTo(usern);
		Assertions.assertThat(userDto1.get(1).getEmail()).isEqualTo("mik@gamil.com");
		logger.info("Test Run Successful");

	}

	@Test
	public void createUserTest3() {
		ModelMapper modelMapper = new ModelMapper();
		String email = "pik@gmail.com";
		String password = "qwe";
		int uid = 5;
		String usern = "Pikku";

		UserDto userDto = new UserDto();
		userDto.setEmail(email);
		userDto.setPassword(password);
		userDto.setUserid(uid);
		userDto.setUsername(usern);

		User user = new User();
		user = modelMapper.map(userDto, User.class);

		Mockito.when(userService.createUser(Mockito.any())).thenReturn((user));
		String url = "http://localhost:" + port + "/api/createUser";
		HttpEntity<UserDto> entity = new HttpEntity<>(userDto);
		ResponseEntity<User> response = restTemplate.postForEntity(url, entity, User.class);

		User user121 = response.getBody();
		assertEquals(200, response.getStatusCodeValue());
		assertNotNull(user121);
		assertThat(user121.getEmail(), is("pik@gmail.com"));
		logger.info("Test Run Successful");

	}

	@Test
	public void updateUserTest3() throws BlogException, Exception {
		ModelMapper modelMapper = new ModelMapper();
		String email = "pik@gmail.com";
		String password = "qwe";
		int uid = 5;
		String usern = "Pikku";

		UserDto userDto = new UserDto();
		userDto.setEmail(email);
		userDto.setPassword(password);
		userDto.setUserid(uid);
		userDto.setUsername(usern);

		User user = new User();
		user = modelMapper.map(userDto, User.class);
		Mockito.when(userService.createUser(Mockito.any())).thenReturn((user));

		userDto.setUsername("Nikku");
		user = modelMapper.map(userDto, User.class);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "pik@gmail.com");
		Mockito.when(userService.updateUser(Mockito.any())).thenReturn((user));
		String url = "http://localhost:" + port + "/api/updateUser";
		HttpEntity<UserDto> entity = new HttpEntity<>(userDto, headers);
		ResponseEntity<User> response = restTemplate.exchange(url, HttpMethod.PUT, entity, User.class);

		User user121 = response.getBody();
		assertEquals(201, response.getStatusCodeValue());
		assertNotNull(user121);
		assertThat(user121.getUsername(), is("Nikku"));
		logger.info("Test Run Successful");

	}

	// ERROR

	@Test
	public void loginUserTest4() throws BlogException, Exception {
		ModelMapper modelMapper = new ModelMapper();
		String email = "pik@gmail.com";
		String password = "qwe";
		int uid = 5;
		String usern = "Pikku";

		UserDto userDto = new UserDto();
		userDto.setEmail(email);
		userDto.setPassword(password);
		userDto.setUserid(uid);
		userDto.setUsername(usern);

		User user = new User();
		user = modelMapper.map(userDto, User.class);
		Mockito.when(userService.createUser(userDto)).thenReturn((user));


		Mockito.when(userService.loginFunction(userDto)).thenReturn("");
		String url = "http://localhost:" + port + "/api/login";
		HttpEntity<UserDto> entity = new HttpEntity<>(userDto);
		ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
		assertEquals(200, response.getStatusCodeValue());
		/*assertEquals("You have successfully logged In", response.getBody());*/

	}

}