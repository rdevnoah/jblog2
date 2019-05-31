package com.cafe24.jblog.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.jblog.dto.JSONResult;
import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.CategoryVo;

@Controller
public class APIController {

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private UserService userService;
	//${authUser.id }/admin/addCategory
	@ResponseBody
	@RequestMapping({"/{id}/admin/addCategory"})
	public List<CategoryVo> addCategory(@PathVariable String id, String name, String description) {
		
		List<CategoryVo> list = blogService.addAndGetCategoryList(id, name, description);
		return list;
	}
	
	@ResponseBody
	@RequestMapping({"/{id}/admin/deleteCategory"})
	public List<CategoryVo> deleteCategory(@PathVariable String id, String no){
		
		List<CategoryVo> list = blogService.removeAndGetCategoryList(id, no);
		return list;
	}
	
	@ResponseBody
	@RequestMapping({"/user/checkId"})
	public JSONResult checkId(@RequestParam(value="id", required = true, defaultValue="")String id) {
		Boolean exist = userService.existId(id);
		return JSONResult.success(exist);
	}
}
