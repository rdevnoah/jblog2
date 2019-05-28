package com.cafe24.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.repository.UserDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	public int registerUser(UserVo vo) {
		userDao.insert(vo);
		blogDao.insert(new BlogVo(vo.getId(), "블로그입니다.", "default"));
		categoryDao.insert(new CategoryVo(vo.getId()));
		return 1;
	}

	public UserVo getUserByIdAndPassword(UserVo vo) {
		return userDao.getUser(vo);
	}	

}
