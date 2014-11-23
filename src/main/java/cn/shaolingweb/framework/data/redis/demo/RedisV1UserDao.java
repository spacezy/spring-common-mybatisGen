package cn.shaolingweb.framework.data.redis.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.SortParameters.Order;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.query.SortQueryBuilder;
import org.springframework.stereotype.Repository;

import cn.shaolingweb.demo.model.User;


/**
 * 
 * @author  shaoling@shaolingweb.cn
 */
@Repository
public class RedisV1UserDao extends RedisBase<String,User>{
	//存的数据都是可序列化的内容
	
	 void save(final User user) {//#1：传入的参数需要final修饰
		//通过模板类，实现方法回调
		 getTemplate().execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection conn) throws DataAccessException {
				//#2 redis的set方法 ，#3无论k、v都需要序列化
				 conn.set(getTemplate().getStringSerializer().serialize("user.id"+user.getUserId()), 
						 getTemplate().getStringSerializer().serialize(user.getUsername()));
				return null;
			}
		});
	 };  
	 void saveHash(User user) {//操作hash
		 final String hkey="user:"+user.getUserId();
		 BoundHashOperations<String, String, String> hashOps=getTemplate().boundHashOps(hkey);
		 Map<String, String> data=new HashMap<String, String>();
		 data.put("id", user.getUserId()+"");
		 data.put("name", user.getName());
		 hashOps.putAll(data);
		 //取出hash
		 getTemplate().execute(new RedisCallback<User>() {
			@Override
			public User doInRedis(RedisConnection conn) throws DataAccessException {
				final byte [] bhkey=getTemplate().getStringSerializer().serialize(hkey);
				if (conn.exists(bhkey)) {
					List<byte[]> value=conn.hMGet(bhkey, 
							getTemplate().getStringSerializer().serialize("id"),
							getTemplate().getStringSerializer().serialize("name")
							);
					User user=new User();
					user.setId(Integer.valueOf(getTemplate().getStringSerializer().deserialize(value.get(0))));
					user.setName(getTemplate().getStringSerializer().deserialize(value.get(1)));
					return user;
				}
				return null;
			}
		});
		 //删除
		 String pattern=hkey;//支持匹配符删除
		 Set<String> keys=getTemplate().keys(pattern);
		 if (!keys.isEmpty()) {
			getTemplate().delete(keys);
		}
	 }
	 void valueOperationsSample() {
		ValueOperations<String, User> valueOps=getTemplate().opsForValue();
		//opsForList、opsForSet、opsForZSet、opsForHash
		User user=new User();
		user.setId(11);
		user.setName("shaoling");
		String key="user_"+user.getUserId();
		valueOps.set(key, user);
		user=valueOps.get(key);
	 }
	 public void boundValueOperationsSample() {
		User user=new User();
		user.setId(22);
		user.setName("shaoling");
		String key="user_"+user.getUserId();
		BoundValueOperations<String, User> bvOps=getTemplate().boundValueOps(key);
		//boundListOps、boundSetOps、boundZSetOps、boundHashOps
		bvOps.set(user);
		bvOps.expire(60,TimeUnit.MINUTES);
	}
	 void txUnusedPoolSample() {//非连接池环境下，事务操作
		 User user=new User(22, "shaoling");
		 String key="user_"+user.getUserId();
		 getTemplate().watch(key);
		 getTemplate().multi();
		 ValueOperations<String, User> ops=getTemplate().opsForValue();
		 ops.set(key, user);
		 getTemplate().exec();
	 }
	 void txUsedPoolSample() {//在连接池环境中 借助sessionCallback来绑定connection 
		 SessionCallback<User> sc=new SessionCallback<User>() {
			@Override
			public User execute(RedisOperations  ops) throws DataAccessException {
				 ops.multi();
				 User user=new User(22, "shaoling");
				 String key="user_"+user.getUserId();
				 BoundValueOperations<String, User> bvops=ops.boundValueOps(key);
				 bvops.set(user);
				 bvops.expire(60, TimeUnit.MINUTES);
				 ops.exec();
				return user;
			}
		};
		getTemplate().execute(sc);
	 }
	 void pipelineSample() {// pipeline : 1，正确使用方式 
		 final byte[] rawKey = getTemplate().getStringSerializer().serialize("userTotal");
	        RedisCallback<List<Object>> pipelineCallback = new RedisCallback<List<Object>>() {
	            @Override  
	            public List<Object> doInRedis(RedisConnection connection) throws DataAccessException {
	                connection.openPipeline();  
	                connection.incr(rawKey);  
	                connection.incr(rawKey);  
	                return connection.closePipeline();  
	            } 
	        };
	        List<Object> results = (List<Object>)getTemplate().execute(pipelineCallback);  
	        for(Object item : results){  
	            System.out.println(item.toString());  
	        } 
	 }
	 void pipelineSample2() {// pipeline : 2，备用方式  
		 byte[] rawKey=getTemplate().getStringSerializer().serialize("userTotal");
		 RedisConnectionFactory factory=getTemplate().getConnectionFactory();
		 RedisConnection redisConnection=RedisConnectionUtils.getConnection(factory);
		 List<Object> result;
		 redisConnection.openPipeline();
		 redisConnection.incr(rawKey);
		 result=redisConnection.closePipeline();
		 if (result!=null) {
			for (Object obj : result) {
				System.out.println(obj);
			}
		}
	 }
	 void sort() {//list、set排序
		 ListOperations<String, String> listOps=getStringRedisTemplate().opsForList();
		 String key="user:list";
		 listOps.leftPush(key, "zhangsan");
		 listOps.leftPush(key, "lisi");
		 SortQueryBuilder<String> builder=SortQueryBuilder.sort(key);
		 builder.alphabetical(true);//对字符串使用字典排序
		 builder.order(Order.DESC);//倒序
		 builder.limit(0,2);
		 List<String> results=getStringRedisTemplate().sort(builder.build());
		 for (String str : results) {
			System.out.println(str);
		}
	 }
	 //分布式队列：JMS的“queue”功能
	 //参考：http://shift-alt-ctrl.iteye.com/blog/1887644
	 //redis中list具有"双端队列"特性，同时redis具有持久数据的能力，因此redis实现分布式队列
	 //是安全可靠的。
	 //redis中的队列阻塞时，整个connection都无法继续进行其它操作
	 //通过spring-data-redis来实现"同步队列"
	 //1)Redis中的"队列"为双端队列,基于list数据结构实现,并提供了"队列阻塞"功能.
	 //2)如果期望使用redis做"分布式队列"server,且数据存取较为密集时,务必配置(redis.conf)中关于list数据结构的限制:
	
	 //当list中数据个数达到阀值时,将会被重构为linkedlist
	//如果队列的存/取速度较为接近,此值可以稍大
	 	//list-max-ziplist-entries 5120
	 	//list-max-ziplist-value 1024
//	 3) Redis已经提供了"队列"的持久化能力,无需额外的技术支持
//	 4) Redis并没有提供JMS语义中"queue"消息的消费确认的功能,
//	 5) Redis并不能像JMS那样提供高度中心化的"队列"服务集群,更适合"快速/小巧/及时消费"的情景.
	 
	 
	//Spring-data-redis: pub/sub消息订阅：JMS的“topic”功能
	 //参考：http://shift-alt-ctrl.iteye.com/blog/1887700
	 
	 
}
