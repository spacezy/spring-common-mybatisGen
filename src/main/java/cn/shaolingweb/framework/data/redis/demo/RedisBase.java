package cn.shaolingweb.framework.data.redis.demo;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 基础类，业务类直接集成，可直接使用redis模板类
 * @author  shaoling@shaolingweb.cn
 */
public abstract class RedisBase<K extends Serializable, V extends Serializable> {
	@Autowired
	private RedisTemplate<K, V> redisTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	public RedisTemplate<K, V> getTemplate() {
		return redisTemplate;
	}
	public StringRedisTemplate getStringRedisTemplate() {
		return stringRedisTemplate;
	}
}