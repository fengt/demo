package com.xjw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xjw.entity.User;

@Service
@Transactional
public class UserService extends BaseService<User>{

	private static final String NAMESPACE = "com.xjw.UserMapper";
	
	public List<User> query(){
		return myBatisDao.getSqlSession().selectList(NAMESPACE + ".select");
	}
	
	public int insert(User user){
    	return myBatisDao.getSqlSession().insert(NAMESPACE + ".insert", user);
	}
	
	
	public int update(User user){
		return myBatisDao.getSqlSession().update(NAMESPACE + ".update", user);
	}
	
	public int delete(int id){
		return myBatisDao.getSqlSession().delete(NAMESPACE + ".delete", id);
	}
	
	public User findById(int id){
		return myBatisDao.getSqlSession().selectOne(NAMESPACE + ".findById", id);
	}
	
}
