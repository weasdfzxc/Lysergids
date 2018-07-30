package com.jiamin.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jiamin.pojo.User;

public class AdminInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		User user = (User)session.getAttribute("user");
		if (user != null) {
			if (user.getRole().equals("admin")) {
				return true;
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/adminerror.htm");
		return false;
		
		
		
		
	}
	
}
