package com.cafe24.jblog.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int insert(CategoryVo categoryVo) {
		return sqlSession.insert("category.insert", categoryVo);
	}

	public int getCount() {
		return sqlSession.selectOne("category.getCount");
	}

	public int deleteAll() {
		return sqlSession.delete("category.deleteAll");
		
	}

	public CategoryVo getCategory(String blogId) {
		return sqlSession.selectOne("category.getTestCategory", blogId);
	}

	public List<CategoryVo> getCategoryListById(String id) {
		return sqlSession.selectList("category.getCategoryListById", id);
	}

}
