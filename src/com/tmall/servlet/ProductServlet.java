package com.tmall.servlet;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.bean.Category;
import com.tmall.bean.Product;
import com.tmall.bean.PropertyValue;
import com.tmall.util.Page;

public class ProductServlet extends BackServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		int cid=Integer.valueOf(request.getParameter("cid"));
		Category category=categoryDao.get(cid);
		List<Product> products = null;
		products = productDao.list(cid, page.getStart(), page.getCount());
		int total=productDao.getTotal(cid);
		if(total==0){
			total++;
		}
		page.setTotal(total);
		page.setParam("&cid="+cid);
		request.setAttribute("page", page);
		request.setAttribute("category", category);
		request.setAttribute("products", products);
		return "/WEB-INF/jsp/admin/listProduct.jsp";
		
	}

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		int cid=Integer.valueOf(request.getParameter("cid"));
		Category category=categoryDao.get(cid);
		String name=request.getParameter("name");
		String subTitle=request.getParameter("subTitle");
		String orignalPrice=request.getParameter("orignalPrice");
		String promotePrice=request.getParameter("promotePrice");
		String stock=request.getParameter("stock");
		Product product=new Product();
		product.setCategory(category);
		product.setName(name);
		product.setSubTitle(subTitle);
		product.setOrignalPrice(Float.valueOf(orignalPrice));
		product.setPromotePrice(Float.valueOf(promotePrice));
		product.setStock(Integer.valueOf(stock));
		product.setCreateDate(new Date());
		productDao.add(product);
		return "@/tmall/admin/product/list?cid="+cid;
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		int cid=Integer.valueOf(request.getParameter("cid"));
		int id=Integer.valueOf(request.getParameter("pid"));
		productDao.delete(id);
		return "@/tmall/admin/product/list?cid="+cid;
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		int id=Integer.valueOf(request.getParameter("pid"));
		Product product=productDao.get(id);
		request.setAttribute("product", product);
		request.setAttribute("category", product.getCategory());
		return "/WEB-INF/jsp/admin/editProduct.jsp";
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		int cid=Integer.valueOf(request.getParameter("cid"));
		int id=Integer.valueOf(request.getParameter("pid"));
		Category category=categoryDao.get(cid);
		String name=request.getParameter("name");
		String subTitle=request.getParameter("subTitle");
		String orignalPrice=request.getParameter("orignalPrice");
		String promotePrice=request.getParameter("promotePrice");
		String stock=request.getParameter("stock");
		Product product=new Product();
		product.setCategory(category);
		product.setId(id);
		product.setName(name);
		product.setSubTitle(subTitle);
		product.setOrignalPrice(Float.valueOf(orignalPrice));
		product.setPromotePrice(Float.valueOf(promotePrice));
		product.setStock(Integer.valueOf(stock));
		product.setCreateDate(new Date());
		productDao.update(product);
		return "@/tmall/admin/product/list?cid="+cid;
	}
	
	public String editPropertyValue(HttpServletRequest request, HttpServletResponse response, Page page) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product=productDao.get(id);
        propertyValueDao.init(product);
        List<PropertyValue> propertyValues=propertyValueDao.list(id);
        request.setAttribute("product", product);
        request.setAttribute("category", product.getCategory());
        request.setAttribute("propertyValues", propertyValues);
        return "/WEB-INF/jsp/admin/editProductValue.jsp";        
    }
	
	 public String updatePropertyValue(HttpServletRequest request, HttpServletResponse response, Page page) {
	        int pvid = Integer.parseInt(request.getParameter("pvid"));
	        String value = request.getParameter("value");
	        PropertyValue propertyValue=propertyValueDao.get(pvid);
	        propertyValue.setValue(value);
	        propertyValueDao.update(propertyValue);
	        return "%success";
	    }



}
