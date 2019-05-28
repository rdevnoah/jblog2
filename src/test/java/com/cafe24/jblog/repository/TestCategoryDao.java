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

import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/applicationContext.xml" }) // root wac에 저장된 bean들 사용
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCategoryDao {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Test // categoryDao insert
	public void test01() {
		categoryDao.deleteAll();
		assertThat(categoryDao.getCount(), is(0));
		blogDao.deleteAll();
		assertThat(blogDao.getCount(), is(0));
		userDao.deleteAll();
		assertThat(userDao.getCount(), is(0));
		// UserVo vo = new User("아이디","이름","비밀번호");
		UserVo user1 = new UserVo("zzagam1", "김영호", "zzagam1");
		// BoardVo board = new BoardVo("블로그아이디", "로고", "타이틀");
		BlogVo blog1 = new BlogVo("zzagam1", "로고파일위치", "블로그 제목입니다.");
		
		//CategoryVo category = new CategoryVo("블로그아이디");
		UserVo user2 = new UserVo("zzagam2", "류상희", "zzagam2");
		BlogVo blog2 = new BlogVo("zzagam2", "로고파일위치", "블로그 제목입니다.");
		
		UserVo user3 = new UserVo("zzagam3", "김정호", "zzagam3");
		BlogVo blog3 = new BlogVo("zzagam3", "로고파일위치", "블로그 제목입니다.");
		
		CategoryVo category = new CategoryVo("zzagam1");
		assertThat(userDao.insert(user1), is(1));
		assertThat(blogDao.insert(blog1), is(1));
		assertThat(categoryDao.insert(category), is(1));
		
		category = new CategoryVo("zzagam2");
		assertThat(userDao.insert(user2), is(1));
		assertThat(blogDao.insert(blog2), is(1));
		assertThat(categoryDao.insert(category), is(1));
		
		category = new CategoryVo("zzagam3");
		assertThat(userDao.insert(user3), is(1));
		assertThat(blogDao.insert(blog3), is(1));
		assertThat(categoryDao.insert(category), is(1));
		
		assertThat(userDao.getCount(), is(3));
		assertThat(blogDao.getCount(), is(3));
		assertThat(categoryDao.getCount(), is(3));

	}

}
