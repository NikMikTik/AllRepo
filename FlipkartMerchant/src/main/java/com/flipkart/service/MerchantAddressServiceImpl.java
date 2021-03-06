package com.flipkart.service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flipkart.dto.MerchantAddressDto;
import com.flipkart.dto.MerchantDto;
import com.flipkart.dto.ResponseDto;
import com.flipkart.model.Merchant;
import com.flipkart.model.MerchantAddress;
import com.flipkart.repository.MerchantAddressRepository;
import com.flipkart.repository.MerchantRepository;

@Service
@Transactional
public class MerchantAddressServiceImpl implements MerchantAddressService {

	@Autowired
	MerchantRepository merchantRepository;

	@Autowired
	MerchantAddressRepository merchantAddressRepository;

	@Autowired
	private IRedisService iredis;

	String noToken="******";
	ResponseDto response = new ResponseDto();
	ModelMapper modelMapper=new ModelMapper();
	Logger logger = LoggerFactory.getLogger(MerchantAddressServiceImpl.class);

	@Override
	public ResponseDto addAddress(MerchantDto merchantDto, HttpServletRequest request) {
		if (merchantRepository.findByMerchantId(merchantDto.getMerchantId()) == null) {
			response.setCode(HttpStatus.NOT_FOUND.value());
			response.setToken(noToken);
			response.setMessage("Merchant Not found");
			response.setResponse("Access Denied to Add Products");
			return response;
		}
		Merchant merchant1 = merchantRepository.findByMerchantId(merchantDto.getMerchantId());
		if (merchant1.getMerchantEmail().equals(iredis.getValue(request.getHeader("Authorization")))) {
			MerchantAddressDto merchantAddressDto = merchantDto.getMerchantAddressDto();
			MerchantAddress merchantAddress = modelMapper.map(merchantAddressDto, MerchantAddress.class);
			merchant1.setMerchantAddress(merchantAddress);
			merchantAddressRepository.save(merchantAddress);
			merchant1.setMerchantConfirmPassword(merchant1.getMerchantPassword());
			merchantRepository.save(merchant1);
			logger.info("Merchant's Address Inserted Successfully");
			response.setCode(HttpStatus.OK.value());
			response.setToken(noToken);
			response.setMessage("Merchant's Address Inserted");
			response.setResponse("Access given to Add Products");
			return response;
		} else {
			response.setCode(HttpStatus.UNAUTHORIZED.value());
			response.setToken(noToken);
			response.setMessage("You are not authorized to enter someone eles's Address");
			response.setResponse("Access Denied...");
			return response;
		}
	}
}
