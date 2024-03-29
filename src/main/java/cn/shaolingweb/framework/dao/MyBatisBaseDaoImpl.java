package cn.shaolingweb.framework.dao;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.shaolingweb.demo.curd.model.User;
import cn.shaolingweb.framework.exception.BusinessException;
import cn.shaolingweb.framework.model.Pager;

/**
 * SqlSessionDaoSupport  :mybatis-spring-1.2.0取消自动注入sqlSessionTemplate/sqlSessionFactory;
 * @description: mybatis DAO 通用工具类
 */
@Repository("myBatisBaseDao")
public class MyBatisBaseDaoImpl<T, PK extends Serializable> implements
		MyBatisBaseDao<T, PK> {
	private static Logger logger = Logger.getLogger(MyBatisBaseDaoImpl.class);
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 根据条件 分页查询
	 */
	public String COUNT = ".findPage_count";
	
	/**
	 * 根据ID 删除
	 */
	public String DELETE = ".delete";
	
	/**
	 * 根据ID 查询
	 */
	public String GETBYID = ".getById";
	
	/**
	 * 插入
	 */
	public String INSERT = ".insert";
	
	/**
	 * 批量插入
	 */
	public String INSERT_BATCH = ".insertBatch";
	
	private Method invokingMethod;
	
	/**
	 * 根据条件 分页查询
	 */
	public String PAGESELECT = ".findPage";
	
	private Object target;
	
	/**
	 * 更新
	 */
	public String UPDATE = ".update";
	
	@Override
	public void delete(PK pk, Class<T> cls) {
		if (pk == null) {
			throw new BusinessException(" pk can't null!");
		}
		sqlSessionTemplate.delete(cls.getName() + DELETE, pk);
	}
	
	@Override
	public void deleteByIds(String ids, Class<User> cls) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		this.getCurSqlSessionTemplate().delete(cls.getName() + ".delete_batch_string", map);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByCondition(T obj) {
		if (obj == null) {
			throw new BusinessException(" condition can't null!");
		}
		return (List<T>) sqlSessionTemplate.selectList(obj.getClass().getName() + PAGESELECT, obj);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByCondition(T obj, int offset, int limit) {
		if (obj == null) {
			throw new BusinessException(" condition can't null!");
		}
		RowBounds rb = new RowBounds(offset, limit);
		return (List<T>) sqlSessionTemplate.selectList(obj.getClass().getName() + PAGESELECT, obj, rb);
	}
	
	@Override
	public List<T> findByCondition(T obj, Pager pager) {
		if (obj == null) {
			throw new BusinessException(" condition can't null!");
		}
		if (pager != null) {
			return this.findByCondition(obj, pager.getStartRow(), pager.getPageSize());
		} else {
			return this.findByCondition(obj);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T findByPK(PK pk, Class<T> cls) {
		if (pk == null) {
			throw new BusinessException(" pk can't null!");
		}
		return (T) sqlSessionTemplate.selectOne(cls.getName() + GETBYID, pk);
		
	}
	
	@Override
	public SqlSessionTemplate getCurSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	
	public Method getInvokingMethod() {
		return invokingMethod;
	}
	
	/**
	 * 获得调用的方法映射的sql语句的statement id。
	 * 规约是映射文件的namespace就是对应的dao的类名（类的全名，如String，
	 * 则类名是java.lang.String），映射的statement id是方法名。
	 * 
	 * @return
	 */
	public String getStatement() {
		if (target == null || invokingMethod == null)
			return null;
		Class<?> c = target.getClass();
		Method[] methods = c.getMethods();
		String namespace = target.getClass().getName();
		StringBuffer bufId = new StringBuffer(invokingMethod.getName());
		for (Method m : methods) {
			if (m.getName().equals(invokingMethod.getName()) && !m.equals(invokingMethod)) {
				Class<?>[] s = m.getParameterTypes();
				if (s != null && s.length > 0) {
					for (Class<?> z : s) {
						bufId.append("_").append(z.getSimpleName());
					}
				}
			}
		}
		return namespace + "." + bufId.toString();
	}
	
	/**
	 * 获得当前方法对应的语句映射Id,如果提供的id不为空， 则直接返回，否则则按照默认规则生成的id.
	 * 
	 * @param statement
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getStatment(String statement) {
		if (StringUtils.isBlank(statement)) {
			return this.getStatement();
		}
		return statement;
	}
	
	public Object getTarget() {
		return target;
	}
	
	@Override
	public Integer getTotalCount(T object) {
		if (object == null) {
			throw new BusinessException(" condition can't null!");
		}
		Object obj = sqlSessionTemplate.selectOne(object.getClass().getName() + COUNT, object);
		if (obj != null) {
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	@Override
	public void insertBatch(Class<T> cls, List<T> domainList) {
		sqlSessionTemplate.insert(cls.getName() + INSERT_BATCH, domainList);
	}
	
	@Override
	public void insertBatch(Class<T> cls, List<T> domainList, Integer count) {
		SqlSession sqlSession = null;
		try {
			if (domainList == null) {
				return;
			}
			sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
			int num = 0;
			for (T t : domainList) {
				
				sqlSession.insert(cls.getName() + INSERT, t);
				num++;
				if (count == num) {
					sqlSession.commit();
					num = 0;
				}
			}
			
			sqlSession.commit();
		} catch (Exception e) {
			if (sqlSession != null) {
				sqlSession.rollback(true);
			}
			logger.error(e.getMessage(), e);
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	
	@Override
	public int save(T obj) {
		if (obj == null) {
			throw new BusinessException(" object can't null!");
		}
		return sqlSessionTemplate.insert(obj.getClass().getName() + INSERT, obj);
	}
	
	public void setInvokingMethod(Method invokingMethod) {
		this.invokingMethod = invokingMethod;
	}
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public void setTarget(Object target) {
		this.target = target;
	}
	
	@Override
	public void update(T obj) {
		if (obj == null) {
			throw new BusinessException(" object can't null!");
		}
		sqlSessionTemplate.update(obj.getClass().getName() + UPDATE, obj);
	}
	
	@Override
	public void updateBatch(Class<T> cls, List<T> domainList, Integer count) {
		SqlSession sqlSession = null;
		try {
			if (domainList == null) {
				return;
			}
			sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
			int num = 0;
			for (T t : domainList) {
				sqlSession.update(cls.getName() + UPDATE, t);
				num++;
				if (count == num) {
					sqlSession.commit();
					num = 0;
				}
			}
			sqlSession.commit();
		} catch (Exception e) {
			if (sqlSession != null) {
				sqlSession.rollback(true);
			}
			logger.error(e.getMessage(), e);
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	
}
