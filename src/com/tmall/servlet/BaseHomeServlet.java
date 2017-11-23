package com.tmall.servlet;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.util.Page;

public class BaseHomeServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void service(HttpServletRequest request,HttpServletResponse response){
		int start=0;
		int count=5;
		try{
			start=Integer.parseInt(request.getParameter("page.start"));
			count=Integer.parseInt(request.getParameter("page.count"));
		}catch(Exception e){
			e.printStackTrace();
		}
		Page page=new Page(start,count);
		try{
			   String method=(String)request.getAttribute("method");
			   
			   Method m = this.getClass().getMethod(method, javax.servlet.http.HttpServletRequest.class,
	                javax.servlet.http.HttpServletResponse.class,Page.class);
	           String redirect = m.invoke(this,request,response,page).toString();
	           if(redirect.startsWith("@")){
	        	   response.sendRedirect(redirect.substring(1));
	           }else if(redirect.startsWith("%")){
	        	   response.getWriter().print(redirect.substring(1));
	           }else{
	        	   request.getRequestDispatcher(redirect).forward(request, response);
	        	   //response.getWriter().print(redirect);
	           }
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	

}
