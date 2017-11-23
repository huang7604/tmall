package com.tmall.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.bean.User;

public class HomeLoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		String contextPath=request.getServletContext().getContextPath();
		String uri=request.getRequestURI();
		uri=uri.substring(contextPath.length()+1);
		String[] NoNeed=new String[]{"index","registerForm","register",
				"login","loginForm","loginAjax",
				"logout","product","checkLogin","category","search"};
		if(uri.startsWith("home/")){
			String method=uri.substring(uri.lastIndexOf("/")+1);
			if(!Arrays.asList(NoNeed).contains(method)){
				User user=(User)request.getSession().getAttribute("user");
				if(user==null){
					response.sendRedirect("/tmall/home/loginForm");
					return ;
				}
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
