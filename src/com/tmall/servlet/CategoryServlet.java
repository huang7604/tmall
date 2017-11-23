package com.tmall.servlet;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.bean.Category;
import com.tmall.util.ImageUtil;
import com.tmall.util.Page;

public class CategoryServlet extends BackServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		
		
		List<Category> categorys;
	    categorys=categoryDao.list(page.getStart(),page.getCount());
		int total=categoryDao.getTotal();
		if(total==0){
			total++;
		}
		page.setTotal(total);
		request.setAttribute("page", page);
		request.setAttribute("categorys", categorys);
	    
		return "/WEB-INF/jsp/admin/listCategory.jsp";
	    //return null;
	}

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		HashMap<String,String> param=new HashMap<String,String>();
		InputStream in=fileUpload(request,param);
		FileOutputStream fop=null;
		Category category=new Category();
		category.setName(param.get("name"));
		categoryDao.add(category);
		File imageFolder=new File(request.getServletContext().getRealPath("/image/category"));
		File image=new File(imageFolder,category.getId()+".jpg");
		try{
			fop=new FileOutputStream(image);
			byte[] buffer=new byte[1024*1024];
			int length=in.read(buffer);
			while(length!=-1){
				fop.write(buffer);
				length=in.read(buffer);
			}
			fop.flush();
			BufferedImage img=ImageUtil.change2jpg(image);
			ImageIO.write(img, "jpg", image);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				in.close();
				fop.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "@/tmall/admin/category/list";
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		String cid=request.getParameter("cid");
		Category category=new Category();
		category.setId(Integer.valueOf(cid));
		categoryDao.delete(category);
		return "@/tmall/admin/category/list";
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		String cid=request.getParameter("cid");
		Category c=categoryDao.get(Integer.valueOf(cid));
		request.setAttribute("category", c);
		return "/WEB-INF/jsp/admin/editCategory.jsp";
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		HashMap<String,String> param=new HashMap<String,String>();
		InputStream in=fileUpload(request,param);
		FileOutputStream fop=null;
		Category category=new Category();
		category.setName(param.get("name"));
		category.setId(Integer.valueOf(param.get("id")));
		
		categoryDao.update(category);
		File imageFolder=new File(request.getServletContext().getRealPath("/image/category"));
		File image=new File(imageFolder,category.getId()+".jpg");
		try{
			if(in!=null){
			fop=new FileOutputStream(image);
			byte[] buffer=new byte[1024*1024];
			int length=in.read(buffer);
			while(length!=-1){
				fop.write(buffer);
				length=in.read(buffer);
			}
			fop.flush();
			BufferedImage img=ImageUtil.change2jpg(image);
			ImageIO.write(img, "jpg", image);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				in.close();
				fop.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "@/tmall/admin/category/list";
		
	}

}
