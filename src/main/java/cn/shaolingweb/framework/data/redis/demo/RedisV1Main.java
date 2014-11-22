package cn.shaolingweb.framework.data.redis.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author  shaoling@shaolingweb.cn
 */
public class RedisV1Main {
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("spring-data-redis.xml");
		RedisV1UserDao userDao = (RedisV1UserDao) app.getBean("userDao");
	}
}
