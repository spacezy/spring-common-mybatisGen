/*
* Copyright  © shaoling
*/package cn.shaolingweb.demo.curd.dao.impl;

import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Repository;
import  cn.shaolingweb.framework.dao.MyBatisBaseDaoImpl;
import cn.shaolingweb.demo.curd.model.User;
import cn.shaolingweb.demo.curd.dao.UserDao;

@Repository
public class UserDaoImpl extends MyBatisBaseDaoImpl<User,java.lang.Integer> implements  UserDao{
	
}
