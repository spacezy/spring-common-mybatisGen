/*
* Copyright  © shaoling
*/package cn.shaolingweb.demo.dao.impl;

import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Repository;
import  cn.shaolingweb.framework.dao.MyBatisBaseDaoImpl;
import cn.shaolingweb.demo.model.User;
import cn.shaolingweb.demo.dao.UserDao;

@Repository
public class UserDaoImpl extends MyBatisBaseDaoImpl<User,java.lang.Integer> implements  UserDao{
	
}
