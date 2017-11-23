package com.tmall.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.bean.User;
import com.tmall.util.Page;

public class UserServlet extends BackServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		int total=userDao.getTotal();
		page.setTotal(total);
		List<User> users=userDao.list(page.getStart(),page.getCount());
		request.setAttribute("page", page);
		request.setAttribute("users", users);
		return "/WEB-INF/jsp/admin/listUser.jsp";
	}

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

}
