package com.caltex.service;

import com.caltex.dto.CustomerDto;
import com.caltex.dto.ResponseDto;

public interface CustomerService {

	ResponseDto firstUserTransaction(CustomerDto customerDto);

	ResponseDto UserTransaction(CustomerDto customerDto);

}
