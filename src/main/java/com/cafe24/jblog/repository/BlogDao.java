package com.cafe24.jblog.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int deleteAll() {
		return sqlSession.delete("blog.deleteAll");
	}

	public int getCount() {
		return sqlSession.selectOne("blog.getCount");
	}

	public int insert(BlogVo blog) {
		return sqlSession.insert("blog.insert", blog);
	}

}
