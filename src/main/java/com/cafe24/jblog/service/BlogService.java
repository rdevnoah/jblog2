package com.cafe24.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.repository.PostDao;
import com.cafe24.jblog.repository.UserDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

@Service
public class BlogService {

	private static final String SAVE_PATH = "/Users/noah/jblog-uploads/blog";
	private static final String URL = "images";

	@Autowired
	private UserDao userDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private BlogDao blogDao;

	@Autowired
	private PostDao postDao;

	public Map<String, Object> getPostById(String id) {
		Map<String, Object> map = new HashMap<String, Object>();

		// 1. 아이디에 해당하는 블로그 메인 (최신글) 전체 내용 가져오기
		PostVo mainPost = blogDao.getMainPost(id);
		map.put("main", mainPost);

		// 2. 아이디에 해당하는 블로그 모든 글 가져오기
		List<PostVo> blogList = blogDao.getAllPost(id);
		map.put("post", blogList);
		// 3. 아이디에 해당하는 블로그의 카테고리들 가져오기
		List<CategoryVo> categoryList = categoryDao.getCategoryListById(id);
		// 4. 블로그 정보 받아오기
		BlogVo blog = blogDao.getBlogById(id);
		String url = URL + "/" + blog.getLogo();
		blog.setLogo(url);
		map.put("category", categoryList);
		map.put("title", "none");
		map.put("blog", blog);
		System.out.println(blog.getLogo()+" aaaaaaaaaaaaa");

		return map;
	}

	public Map<String, Object> getPostByIdAndPostNo(String id, String postNo) {
		Map<String, Object> map = new HashMap<String, Object>();

		// 1. 포스트 번호에 해당하는 해당 포스트 가져오기
		PostVo mainPost = blogDao.getMainPost(Long.parseLong(postNo));
		map.put("main", mainPost);
		// 2. 아이디에 해당하는 블로그 모든 글 가져오기
		List<PostVo> blogList = blogDao.getAllPost(id);
		map.put("post", blogList);
		// 3. 아이디에 해당하는 블로그의 카테고리들 가져오기
		List<CategoryVo> categoryList = categoryDao.getCategoryListById(id);
		// 4. 블로그 정보 받아오기
		BlogVo blog = blogDao.getBlogById(id);
		String url = URL + "/" + blog.getLogo();
		blog.setLogo(url);
		map.put("category", categoryList);
		map.put("title", "exist");
		map.put("blog", blog);
		return map;
	}

	public Map<String, Object> getPostByIdAndCategoryNo(String id, String categoryNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		CategoryVo vo = new CategoryVo(id, Long.parseLong(categoryNo));

		// 1. 해당 아이디와 카테고리번호에 해당하는 최신 글 가져오기
		PostVo mainPost = blogDao.getMainPost(vo);
		map.put("main", mainPost);
		// 2. 해당 아이디와 카테고리번호에 해당하는 포스트 가져오기
		List<PostVo> blogList = blogDao.getAllPost(vo);
		map.put("post", blogList);
		// 3. 아이디에 해당하는 블로그의 카테고리들 가져오기
		List<CategoryVo> categoryList = categoryDao.getCategoryListById(id);
		// 4. 블로그 정보 받아오기
		BlogVo blog = blogDao.getBlogById(id);
		String url = URL + "/" + blog.getLogo();
		blog.setLogo(url);
		map.put("category", categoryList);
		map.put("title", "exist");
		map.put("blog", blog);
		return map;
	}

	public BlogVo getBlogById(String id) {
		BlogVo vo = blogDao.getBlogById(id);
		String url = URL + "/" + vo.getLogo();
		vo.setLogo(url);
		return vo;
	}

	public void updateBlog(String id, String title, MultipartFile multipartFile) {

		try {
			String originalFilename = multipartFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf('.') + 1); // .빼고 확장자
			String saveFileName = generateSaveFileName(extName);

			byte[] fileDataBuffer;
			fileDataBuffer = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
			os.write(fileDataBuffer);
			os.close();
			BlogVo vo = new BlogVo(id, title, saveFileName);

			blogDao.update(vo);
		} catch (IOException e) {
			throw new RuntimeException("FileUpload Error" + e);
		}
	}

	private String generateSaveFileName(String extName) {
		String fileName = "";
		Calendar calendar = Calendar.getInstance();

		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("." + extName);
		return fileName;

	}

	public Map<String, Object> getAdminCategoryMain(String id) {
		//1. 아이디에 해당하는 블로그 정보 얻어오기
		//2. 아이디에 해당하는 카테고리 정보들 얻어오기
		Map<String, Object> map = new HashMap<String, Object>();
		BlogVo blogVo = blogDao.getBlogById(id);
		List<CategoryVo> categoryList = categoryDao.getCategoryListById(id);
		
		map.put("blog", blogVo);
		map.put("categoryList", categoryList);
		return map;
	}

	public List<CategoryVo> addAndGetCategoryList(String id, String name, String description) {
		CategoryVo vo = new CategoryVo(id, name, description);
		categoryDao.insertCategory(vo);
		List<CategoryVo> list = categoryDao.getCategoryListById(id);
		return list;
	}

	public List<CategoryVo> removeAndGetCategoryList(String id, String no) {
		CategoryVo vo = new CategoryVo(id, Long.parseLong(no));
		categoryDao.removeCategory(vo);
		List<CategoryVo> list = categoryDao.getCategoryListById(id);
		return list;
	}

}
