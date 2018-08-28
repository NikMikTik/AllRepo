package com.service;

import org.springframework.stereotype.Service;

@Service
public interface IRedisService {
	public Object getValue(String key);

	public void setValue(String key, Object value);

	public String deleteValue(String key);

/*	public String getCountValue(String key);

	public void setCountValue(String key, String value);
	
	public void updateCountValue(String key, String value);
	
	public String getBlockedValue(String key);

	public void setBlockedValue(String key, String value);
	
	public void updateBlockedValue(String key, String value);*/

}
