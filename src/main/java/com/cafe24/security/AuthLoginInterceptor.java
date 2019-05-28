
package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		UserVo vo = new UserVo(id, password);
		UserVo authUser = userService.getUserByIdAndPassword(vo);
		
		if (authUser == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		//세션 처리
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		System.out.println(request.getContextPath());
		response.sendRedirect(request.getContextPath()+"/"+authUser.getId());
		
		return false;
	}
}
