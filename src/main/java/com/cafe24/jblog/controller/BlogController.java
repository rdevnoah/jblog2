package com.cafe24.jblog.controller;

import java.util.Map;

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
	
	@RequestMapping("/{id}")
	public String main(@PathVariable String id, Model model) {
		Map<String, Object> map = blogService.getBlogById(id);
		model.addAttribute("map",map);
		return "/blog/blog-main";
	}
}
