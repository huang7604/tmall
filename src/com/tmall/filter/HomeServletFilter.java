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
import javax.servlet.http.HttpSession;

import com.tmall.bean.User;

public class HomeServletFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		request.setCharacterEncoding("utf8");
		String contentPath=request.getServletContext().getContextPath();
		HttpSession session=request.getSession(false);
		if(session!=null){
			User user=(User) session.getAttribute("user");
			request.setAttribute("user", user);
		}
		String uri=request.getRequestURI();
		uri=uri.substring(contentPath.length()+1);
		if(uri.startsWith("home/")){
			String method=uri.substring(uri.lastIndexOf("/")+1);
			request.setAttribute("method", method);
			request.getRequestDispatcher("/homeServlet").forward(request, response);
			//response.getWriter().print(method);
			return ;
		}
		//response.getWriter().print(uri);
		arg2.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
