package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog.vo.UserVo;

public class AuthLogoutInterceptor extends HandlerInterceptorAdapter{

	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(false);
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if (authUser == null || session == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		session.removeAttribute("authUser");
		session.invalidate();
		response.sendRedirect(request.getContextPath());
		
		
		return false;
	}
	
	
}
