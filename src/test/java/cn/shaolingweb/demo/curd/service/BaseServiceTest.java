package cn.shaolingweb.demo.curd.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.shaolingweb.demo.curd.model.User;

import com.alibaba.fastjson.JSON;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:springExt/spring-BaseServiceTest.xml")
public class BaseServiceTest {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Autowired
	private BaseService<User, Serializable> baseService;
	
	@Test
	public void testQuery() {
		try {
			System.out.println("---"+sqlSessionTemplate.getConnection().getCatalog());
			System.out.println(baseService);
			User obj=new User();
			obj.setUsername("admin");
			List<User> users= baseService.findByCondition(obj);
			System.out.println(users);
			System.err.println("---------"+JSON.toJSONString(users));
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		fail("Not yet implemented");
	}
	@Test
	public void testAdd() {
		User user=new User();
		user.setUserId(22);
		user.setUsername("shaoling");
		user.setEmail("shaoling@jd.com");
		baseService.save(user);
	}
	
	
}
