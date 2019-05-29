package com.cafe24.jblog.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cafe24.jblog.vo.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/applicationContext.xml" }) // root wac에 저장된 bean들 사용
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserDao {

	@Autowired
	private UserDao userDao;

	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private PostDao postDao;

	@Test // userDAO insert test
	public void test01() {
		postDao.deleteAll();
		assertThat(postDao.getCount(), is(0));
		categoryDao.deleteAll();
		assertThat(categoryDao.getCount(), is(0));
		blogDao.deleteAll();
		assertThat(blogDao.getCount(), is(0));
		userDao.deleteAll();
		assertThat(userDao.getCount(), is(0));
		
		UserVo vo1 = new UserVo("zzagam1", "김영호", "zzagam1");
		UserVo vo2 = new UserVo("zzagam2", "류상희", "zzagam2");
		UserVo vo3 = new UserVo("zzagam3", "김정호", "zzagam3");
		assertThat(userDao.insert(vo1), is(1));
		assertThat(userDao.insert(vo2), is(1));
		assertThat(userDao.insert(vo3), is(1));
		assertThat(userDao.getCount(), is(3));
		UserVo insertUser = new UserVo("zzagam2", "zzagam2");
		UserVo vo = userDao.getUser(insertUser);
		assertThat(insertUser.getId().equals(vo.getId()),is(true));
	}
	
	

	
	
	
}
