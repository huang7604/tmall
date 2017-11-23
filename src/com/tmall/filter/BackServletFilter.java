package com.tmall.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class BackServletFilter implements Filter{
	
	@Override
	public void init(FilterConfig config) throws ServletException{
		
	}
	
	@Override
	public void doFilter(ServletRequest rq,ServletResponse rp,FilterChain chain) throws IOException,ServletException{
		HttpServletRequest request=(HttpServletRequest)rq;
		HttpServletResponse response=(HttpServletResponse)rp;
		request.setCharacterEncoding("utf8");
		String contextPath=request.getServletContext().getContextPath();
		String uri=request.getRequestURI();
		uri=uri.substring(contextPath.length()+1);
		
		
		if(uri.startsWith("admin/")){
			String servletPath=uri.substring(uri.indexOf("/"),uri.lastIndexOf("/"))+"Servlet";
			//response.getWriter().println(servletPath);
			String method=uri.substring(uri.lastIndexOf("/")+1);
			//response.getWriter().println(method);
			request.setAttribute("method", method);
			request.getRequestDispatcher(servletPath).forward(request, response);
			return ;
		}
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy(){
		
	}

}
