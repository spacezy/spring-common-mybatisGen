package cn.shaolingweb.framework.data.redis.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;

import cn.shaolingweb.demo.model.User;


/**
 * 
 * @author  shaoling@shaolingweb.cn
 */
@Repository
public class RedisV1UserDao extends RedisBase{
	//存的数据都是可序列化的内容
	
	 void save(final User user) {//#1：传入的参数需要final修饰
		//通过模板类，实现方法回调
		 getTemplate().execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection conn) throws DataAccessException {
				//#2 redis的set方法 ，#3无论k、v都需要序列化
				 conn.set(getTemplate().getStringSerializer().serialize("user.id"+user.getId()), 
						 getTemplate().getStringSerializer().serialize(user.getName()));
				return null;
			}
		});
	 };  
	 void saveHash(User user) {//操作hash
		 final String hkey="user:"+user.getId();
		 BoundHashOperations<String, String, String> hashOps=getTemplate().boundHashOps(hkey);
		 Map<String, String> data=new HashMap<String, String>();
		 data.put("id", user.getId()+"");
		 data.put("name", user.getName());
		 hashOps.putAll(data);
		 //取出hash
		 getTemplate().execute(new RedisCallback<User>() {
			@Override
			public User doInRedis(RedisConnection conn) throws DataAccessException {
				byte [] bhkey=getTemplate().getStringSerializer().serialize(hkey);
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
	 User read(String uid) {
		 return null;
	 };  
	 void delete(String uid) {
		 
	 };  
}
