package com.tmall.servlet;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.tmall.dao.*;
import com.tmall.util.Page;

public abstract class BackServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract String list(HttpServletRequest request,HttpServletResponse response,Page page);
	public abstract String add(HttpServletRequest request,HttpServletResponse response,Page page);
	public abstract String delete(HttpServletRequest request,HttpServletResponse response,Page page);
	public abstract String edit(HttpServletRequest request,HttpServletResponse response,Page page);
	public abstract String update(HttpServletRequest request,HttpServletResponse response,Page page);
	
	protected CategoryDao categoryDao=new CategoryDao();
	protected OrderDao orderDao=new OrderDao();
	protected OrderItemDao orderItemDao=new OrderItemDao();
	protected ProductDao productDao=new ProductDao();
	protected ProductImageDao productImageDao=new ProductImageDao();
	protected PropertyDao propertyDao=new PropertyDao();
	protected PropertyValueDao propertyValueDao=new PropertyValueDao();
	protected ReviewDao reviewDao=new ReviewDao();
	protected UserDao userDao=new UserDao();
	
	
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
	
	public InputStream fileUpload(HttpServletRequest request,Map<String,String> param){
		InputStream in=null;
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload fileUpload=new ServletFileUpload(factory);
		try {
			List<FileItem> fileItems=fileUpload.parseRequest(request);
			Iterator<FileItem> it=fileItems.iterator();
			while(it.hasNext()){
				FileItem fileitem=it.next();
				if(!fileitem.isFormField()){
					in=fileitem.getInputStream();
				}else{
					String name=fileitem.getFieldName();
					String value=fileitem.getString("utf8");
					param.put(name, value);
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return in;
	}
	
	

}
