package com.cafe24.jblog.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public int deleteAll() {
		return sqlSession.delete("user.deleteAll");
	}

	public int getCount() {
		return sqlSession.selectOne("user.getCount");
	}

	public int insert(UserVo vo) {
		return sqlSession.insert("user.insert", vo);
	}

	public UserVo getUser(UserVo vo) {
		return sqlSession.selectOne("user.get", vo);
	}

}
