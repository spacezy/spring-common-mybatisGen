package cn.shaolingweb.demo.curd.service;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:springExt/spring-BaseServiceTest.xml")
public class BaseServiceTest {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Test
	public void test() {
		try {
			System.out.println(sqlSessionTemplate.getConnection().getCatalog());
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		fail("Not yet implemented");
	}
	
}
