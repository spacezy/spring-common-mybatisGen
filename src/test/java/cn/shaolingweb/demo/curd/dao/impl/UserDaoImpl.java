/*
* Copyright  © shaoling
*/package cn.shaolingweb.demo.curd.dao.impl;

import org.springframework.stereotype.Repository;

import cn.shaolingweb.demo.curd.dao.UserDao;
import cn.shaolingweb.demo.curd.model.User;
import cn.shaolingweb.framework.dao.MyBatisBaseDaoImpl;

@Repository
public class UserDaoImpl extends MyBatisBaseDaoImpl<User,java.lang.Integer> implements  UserDao{
	
}
