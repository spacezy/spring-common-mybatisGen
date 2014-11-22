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
 * 
 * jedis客户端不足：
 * 1 connection管理缺乏自动化，connection-pool的设计缺少必要的容器支持
 * 2 时间操作需要关注序列化、反序列化，因为jedis API接受的数据类型为String 和byte,对结构化数据(json/xml)操作需要额外的支持
 * 3 事务操作纯粹为硬编码
 * 4 pub/sub功能，缺乏必要设计模式的支持，对开发者来说，需关注的太多
 * 
 * spring-data-redis针对jedis提供了如下功能：
 * 1 连接池自动管理，提供一个高度封装的RedisTemplate类
 * 2 针对jedis API中大量API进行归类封装，将同一类型操作封装为operation接口
 *	ValueOperations 简单k-v操作
 *  	SetOperations		set类型操作
 *  	ZSetOperations 	zset类型操作
 *  	HashOperations	map类型操作
 *  	ListOperations		list类型操作
 * 3 提供针对key的bound便捷化操作，可以通过bound封装指定的key,然后进行一系列的操作而无需再次指定
 * 	key即BoundKeyOperations:
 * 	BoundValueOperations/BoundSetOperations/BoundListOperations/BoundSetOperations/BoundHashOperations
 * 4 将事务操作封装，有容器控制
 * 5 针对数据的序列化/反序列化，提供多种可选择策略(RedisSerializer)
 * 	JdkSerializationRedisSerializer.class	JDK自身序列化
 * 	StringRedisSerializer.class	k/v为字符串的场景，字符和字节间转换
 * 	JacksonJsonRedisSerializer.class 	javaBean和JSON间，需jackson-mapper-asl支持
 * 	OxmSerializer.class	javaBean和XML间
 * 6工具类
 * 	org.springframework.data.redis.serializer.SerializationUtils
 * 7 基于设计模式，和jms开发思路，将pub/sub的API设计进行了封装
 * 8 没有对sharding提供良好的封装，若需要自己实现
 * 
 * 
 * @author  shaoling@shaolingweb.cn
 */
package cn.shaolingweb.framework.data.redis;