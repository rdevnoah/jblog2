package com.cafe24.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog.vo.UserVo;

public class AdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		String pathId = pathVariables.get("id").toString();

		HttpSession session = request.getSession(false);
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (session == null || authUser == null) { // 인증이 안되어 있음
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		if (authUser.getId().equals(pathId)==false) {
			response.sendRedirect(request.getContextPath() + "/"+authUser.getId());
			return false;
		}

		return true;
	}

}
