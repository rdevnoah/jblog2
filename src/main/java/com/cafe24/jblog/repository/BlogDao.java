package com.cafe24.jblog.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

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

	public List<PostVo> getAllBlogById(String id) {
		return sqlSession.selectList("post.getAllPostById",id);
	}

	public PostVo getMainPostById(String id) {
		return sqlSession.selectOne("post.getMainPost", id);
	}

	public List<CategoryVo> getCategoryListById(String id) {
		return sqlSession.selectList("category.getCategoryListById", id);
	}

}
