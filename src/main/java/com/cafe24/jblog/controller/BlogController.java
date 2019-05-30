package com.cafe24.jblog.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.PostVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	
	
	@RequestMapping({"/{id:^(?!assets).*$}/{categoryNo}/{postNo}", "/{id:^(?!assets).*$}/{categoryNo}", "/{id:^(?!assets).*$}"})
	public String main(@PathVariable String id
			, @PathVariable Optional<String> categoryNo, @PathVariable Optional<String> postNo, Model model) {
		Map<String, Object> map = null;
		if (!categoryNo.isPresent() && !postNo.isPresent()) {
			map = blogService.getPostById(id);
		}
		if (categoryNo.isPresent() && !postNo.isPresent()) {
			map = blogService.getPostByIdAndCategoryNo(id, categoryNo.get());
		}
		if (categoryNo.isPresent() && postNo.isPresent()) {
			map = blogService.getPostByIdAndPostNo(id, categoryNo.get(), postNo.get());
		}
		model.addAttribute("map",map);
		return "/blog/blog-main";
	}
	
	@RequestMapping({"/{id}/admin/basic"})
	public String adminMain(@PathVariable String id, Model model) {
		BlogVo vo = blogService.getBlogById(id);
		model.addAttribute("blog", vo);
		System.out.println(vo.getLogo()+" $$$$$$$$$$$$");
		return "/blog/blog-admin-basic";
	}
	
	//${authUser.id}/admin/basic/update
	@RequestMapping(value= {"/{id}/admin/basic/update"}, method = RequestMethod.POST)
	public String updateBasic(@PathVariable String id 
			, String title 
			, @RequestParam(value="logo")MultipartFile multipartFile
			, Model model) {
		System.out.println(title);
		System.out.println(multipartFile.getOriginalFilename());
		
		blogService.updateBlog(id, title, multipartFile);
		model.addAttribute("status", "success");
		return "redirect:/"+id+"/admin/basic";
	}
	///${authUser.id }/admin/category
	@RequestMapping({"/{id}/admin/category"})
	public String adminCategoryMain(@PathVariable String id, Model model) {
		Map<String, Object> map = blogService.getAdminCategoryMain(id);
		model.addAttribute("map", map);
		return "/blog/blog-admin-category";
	}
	
	
	//${authUser.id }/admin/write
	@RequestMapping({"{id}/admin/write"})
	public String writePost(@PathVariable String id, Model model) {
		Map<String, Object> map = blogService.getAdminCategoryMain(id);
		model.addAttribute("map", map);
		return "/blog/blog-admin-write";
	}
	
	@RequestMapping(value= {"/{id}/admin/write"}, method = RequestMethod.POST)
	public String writePost(@PathVariable String id, PostVo vo) {
		System.out.println(vo);
		blogService.writePost(id, vo);
		return "redirect:/"+id+"/"+vo.getCategoryNo();
	}
	
	
}
