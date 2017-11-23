package com.tmall.servlet;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.bean.Category;
import com.tmall.bean.Property;
import com.tmall.util.Page;

public class PropertyServlet extends BackServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf8");
		int cid=Integer.valueOf(request.getParameter("cid"));
		Category category=categoryDao.get(cid);
		int total;
	    total = propertyDao.getTotal(cid);
	    if(total==0){
			total++;
		}
		page.setTotal(total);
		page.setParam("&cid="+cid);
		List<Property> properties=propertyDao.list(cid, page.getStart(), page.getCount());
		request.setAttribute("category", category);
		request.setAttribute("properties", properties);
		request.setAttribute("page", page);
		return "/WEB-INF/jsp/admin/listProperty.jsp";
	}

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		int cid=Integer.valueOf(request.getParameter("cid"));
		String name=request.getParameter("name");
		Category category=categoryDao.get(cid);
		Property property=new Property();
		property.setName(name);
		property.setCategory(category);
		propertyDao.add(property);
		return "@/tmall/admin/property/list?cid="+cid;
		
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		String id=request.getParameter("pid");
		String cid=request.getParameter("cid");
		propertyDao.delete(Integer.valueOf(id));
		return "@/tmall/admin/property/list?cid="+cid;
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		String id=request.getParameter("pid");
		propertyDao.get(Integer.valueOf(id));
		request.setAttribute("property",propertyDao.get(Integer.valueOf(id)));
		return "/WEB-INF/jsp/admin/editProperty.jsp";
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		
		String id=request.getParameter("id");
		String cid=request.getParameter("cid");
		String name=request.getParameter("name");
		Category category=categoryDao.get(Integer.valueOf(cid));
		Property property=new Property();
		property.setCategory(category);
		property.setId(Integer.valueOf(id));
		property.setName(name);
		propertyDao.update(property);
		return "@/tmall/admin/property/list?cid="+cid;
	}

}
