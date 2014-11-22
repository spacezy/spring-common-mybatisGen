package cn.shaolingweb.framework.data.redis.demo;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import cn.shaolingweb.demo.model.User;


/**
 * 
 * @author  shaoling@shaolingweb.cn
 */
@Repository
public class RedisV1UserDao {
	//存的数据都是可序列化的内容
	@Autowired
	private RedisTemplate<Serializable, Serializable> redisTemplate;
	
	 void save(final User user) {//#1：传入的参数需要final修饰
		//通过模板类，实现方法回调
		 redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection conn) throws DataAccessException {
				//#2 redis的set方法 ，#3无论k、v都需要序列化
				 conn.set(redisTemplate.getStringSerializer().serialize("user.id"+user.getId()), 
						 redisTemplate.getStringSerializer().serialize(user.getName()));
				return null;
			}
		});
	 };  
	 User read(String uid) {
		 return null;
	 };  
	 void delete(String uid) {
		 
	 };  
}
