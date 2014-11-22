package cn.shaolingweb.framework.data.redis.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 基础类，业务类直接集成，可直接使用redis模板类
 * @author  shaoling@shaolingweb.cn
 */
public abstract class RedisBase<K, V> {
	@Autowired
	private RedisTemplate<K, V> redisTemplate;
	public RedisTemplate<K, V> getTemplate() {
		return redisTemplate;
	}
}