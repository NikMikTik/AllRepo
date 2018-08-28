package com.caltex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caltex.dto.CustomerDto;
import com.caltex.dto.ResponseDto;
import com.caltex.service.CustomerService;

@RestController
@RequestMapping("/caltex")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ResponseDto firstUserTransaction(@RequestBody CustomerDto customerDto) {
		return customerService.firstUserTransaction(customerDto);
	}
	
	@RequestMapping(value = "/transaction", method = RequestMethod.PUT)
	public ResponseDto UserTransaction(@RequestBody CustomerDto customerDto) {
		return customerService.UserTransaction(customerDto);
	}
	

}
