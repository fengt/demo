package com.xjw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xjw.dao.MyBatisGeneralDao;

@Service
@Transactional
public class BaseService<T> {

	@Autowired
	protected MyBatisGeneralDao myBatisDao;

	public MyBatisGeneralDao getMyBatisDao() {
		return myBatisDao;
	}

	public void setMyBatisDao(MyBatisGeneralDao myBatisDao) {
		this.myBatisDao = myBatisDao;
	}
	
}
