package com.service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class IRedisServiceImpl implements IRedisService {

	@Autowired
	private RedisTemplate<String, Object> template;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public Object getValue(String key) {
		return template.opsForValue().get(key);
	}

	@Override
	public void setValue(String key, Object value) {
		template.opsForValue().set(key, value);
		template.expire(key, 15, TimeUnit.MINUTES);

	}

	@Override
	public String deleteValue(String key) {
		Set<String> keys = new HashSet<>();
		keys.add(key);
		template.opsForValue().getOperations().delete(keys);
		return "logged out done";

	}

	/*@Override
	public String getCountValue(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	@Override
	public void setCountValue(String key, String value) {
		stringRedisTemplate.opsForValue().setIfAbsent(key, value);

	}

	@Override
	public void updateCountValue(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);

	}

	@Override
	public String getBlockedValue(String key) {
		return stringRedisTemplate.opsForValue().get(key);

	}

	@Override
	public void setBlockedValue(String key, String value) {
		stringRedisTemplate.opsForValue().setIfAbsent(key, value);
	}

	@Override
	public void updateBlockedValue(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);

	}*/

}
