package com.cafe24.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.repository.PostDao;
import com.cafe24.jblog.repository.UserDao;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

@Service
public class BlogService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private PostDao postDao;
	
	
	public Map<String, Object> getBlogById(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		//1. 아이디에 해당하는 블로그 메인 (최신글) 전체 내용 가져오기
		PostVo mainPost = blogDao.getMainPostById(id);
		map.put("main", mainPost);
		
		//2. 아이디에 해당하는 블로그 모든 글 가져오기
		List<PostVo> vlogList = blogDao.getAllBlogById(id);	
		map.put("post", vlogList);
		
		//3. 아이디에 해당하는 블로그의 카테고리들 가져오기
		List<CategoryVo> categoryList = blogDao.getCategoryListById(id);
		map.put("category", categoryList);
		
		return map;
	}

}
