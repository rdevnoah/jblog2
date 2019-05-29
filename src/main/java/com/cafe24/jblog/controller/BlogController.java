package com.cafe24.jblog.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.jblog.service.BlogService;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping({"/{id}/{categoryNo}/{postNo}", "/{id}/{categoryNo}", "/{id}"})
	public String main(@PathVariable String id
			, @PathVariable Optional<String> categoryNo, @PathVariable Optional<String> postNo, Model model) {
		Map<String, Object> map = null;
		if (!categoryNo.isPresent() && !postNo.isPresent()) {
			map = blogService.getBlogById(id);
		}
		if (categoryNo.isPresent() && postNo.isPresent()) {
			map = blogService.getPostByIdAndPostNo(id, postNo.get());
		}
		if (categoryNo.isPresent() && !postNo.isPresent()) {
			map = blogService.getPostByIdAndCategoryNo(id, categoryNo.get());
		}
		model.addAttribute("map",map);
		return "/blog/blog-main";
	}
	
	
	
}
