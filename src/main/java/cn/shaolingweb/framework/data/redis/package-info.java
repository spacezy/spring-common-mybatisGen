/**
 * 放弃了Jedis原生实现，有事务机制的框架，spring-data-redis都解决了。
 * 
 * <pre>
	<dependency>
		<groupId>org.springframework.data</groupId>
		<artifactId>spring-data-redis</artifactId>
		<version>1.4.1.RELEASE</version>
	</dependency>
	<dependency>
		<groupId>redis.clients</groupId>
		<artifactId>jedis</artifactId>
		<version>2.6.0</version>
	</dependency>
 * <pre>
 * @author  shaoling@shaolingweb.cn
 */
package cn.shaolingweb.framework.data.redis;