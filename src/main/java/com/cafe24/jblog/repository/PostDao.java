package com.cafe24.jblog.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int deleteAll() {
		return sqlSession.delete("post.deleteAll");
	}

	public int getCount() {
		return sqlSession.selectOne("post.getCount");
	}

	public int insert(PostVo vo) {
		return sqlSession.insert("post.insert", vo);
	}

}
