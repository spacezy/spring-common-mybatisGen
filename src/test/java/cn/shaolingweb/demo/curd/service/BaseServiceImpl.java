package cn.shaolingweb.demo.curd.service;

import java.io.Serializable;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.shaolingweb.demo.curd.model.User;
import cn.shaolingweb.framework.dao.MyBatisBaseDao;
import cn.shaolingweb.framework.model.Pager;

@Service
public class BaseServiceImpl<T, PK extends Serializable>implements BaseService<T, PK>{
	
	@Autowired
	private MyBatisBaseDao<T, Serializable> myBatisBaseDao;

	/**
	 * @param obj
	 * @return
	 */
	@Override
	public int save(T obj) {
		return myBatisBaseDao.save(obj);
	}

	/**
	 * @param obj
	 * @return
	 */
	@Override
	public List<T> findByCondition(T obj) {
		return myBatisBaseDao.findByCondition(obj);
	}

	/**
	 * @param obj
	 * @param start
	 * @param limit
	 * @return
	 */
	@Override
	public List<T> findByCondition(T obj, int start, int limit) {
		return myBatisBaseDao.findByCondition(obj, start, limit);
	}

	/**
	 * @param obj
	 * @param pager
	 * @return
	 */
	@Override
	public List<T> findByCondition(T obj, Pager pager) {
		return myBatisBaseDao.findByCondition(obj, pager);
	}

	/**
	 * @param pk
	 * @param cls
	 * @return
	 */
	@Override
	public T findByPK(PK pk, Class<T> cls) {
		return myBatisBaseDao.findByPK(pk, cls);
	}

	/**
	 * @param object
	 */
	@Override
	public void update(T obj) {
		myBatisBaseDao.update(obj);
	}

	/**
	 * @param pk
	 * @param cls
	 */
	@Override
	public void delete(PK pk, Class<T> cls) {
		myBatisBaseDao.delete(pk, cls);
	}

	/**
	 * @param ids
	 * @param cls
	 */
	@Override
	public void deleteByIds(String ids, Class<User> cls) {
		myBatisBaseDao.deleteByIds(ids, cls);
	}

	/**
	 * @param object
	 * @return
	 */
	@Override
	public Integer getTotalCount(T object) {
		return myBatisBaseDao.getTotalCount(object);
	}

	/**
	 * @param cls
	 * @param domainList
	 * @param count
	 */
	@Override
	public void insertBatch(Class<T> cls, List<T> domainList, Integer count) {
		myBatisBaseDao.insertBatch(cls, domainList, count);
	}

	/**
	 * @param cls
	 * @param domainList
	 */
	@Override
	public void insertBatch(Class<T> cls, List<T> domainList) {
		myBatisBaseDao.insertBatch(cls, domainList);
	}

	/**
	 * @param cls
	 * @param domainList
	 * @param count
	 */
	@Override
	public void updateBatch(Class<T> cls, List<T> domainList, Integer count) {
		myBatisBaseDao.updateBatch(cls, domainList, count);
	}

	/**
	 * @return
	 */
	@Override
	public SqlSessionTemplate getCurSqlSessionTemplate() {
		return myBatisBaseDao.getCurSqlSessionTemplate();
	}
}
