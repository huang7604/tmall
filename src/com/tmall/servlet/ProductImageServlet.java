package com.tmall.servlet;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.bean.Product;
import com.tmall.bean.ProductImage;
import com.tmall.dao.ProductImageDao;
import com.tmall.util.ImageUtil;
import com.tmall.util.Page;

public class ProductImageServlet extends BackServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		int pid=Integer.valueOf(request.getParameter("pid"));
		Product product=productDao.get(pid);
		List<ProductImage> imageSingles=null;
		List<ProductImage> imageDetails=null;
		imageSingles = productImageDao.list(product, ProductImageDao.type_single);
		imageDetails = productImageDao.list(product, ProductImageDao.type_detail);
		request.setAttribute("category", product.getCategory());
		request.setAttribute("product", product);
		request.setAttribute("imageSingles", imageSingles);
		request.setAttribute("imageDetails", imageDetails);
		return "WEB-INF/jsp/admin/listProductImage.jsp";
	}

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		HashMap<String,String> map=new HashMap<String,String>();
		InputStream in=fileUpload(request,map);
		int pid=Integer.valueOf(map.get("pid"));
		String type=map.get("type");
		Product product=productDao.get(pid);
		ProductImage productImage=new ProductImage();
		productImage.setProduct(product);
		productImage.setType(type);
		productImageDao.add(productImage);
		
		File imageFolder=null;
		File image_small=null;
		File image_middle=null;
		if(ProductImageDao.type_single.equals(type)){
			imageFolder=new File(request.getServletContext().getRealPath("/image/productSingle"));
			image_small=new File(request.getServletContext().getRealPath("/image/productSingle_small"));
			image_middle=new File(request.getServletContext().getRealPath("/image/productSingle_middle"));
		}else{
			imageFolder=new File(request.getServletContext().getRealPath("/image/productDetail"));
		}
		
		File image=new File(imageFolder,productImage.getId()+".jpg");
		FileOutputStream fop=null;
		try {
			if(in!=null){
			   fop=new FileOutputStream(image);
			   byte[] buffer=new byte[1024*1024];
			   int length=in.read(buffer);
			   while(length!=-1){
				   fop.write(buffer, 0, length);
				   length=in.read(buffer);
			   }
			   fop.flush();
			   BufferedImage bufferedImage=ImageUtil.change2jpg(image);
			   ImageIO.write(bufferedImage, "jpg", image);
			   
			   if(ProductImageDao.type_single.equals(type)){
				   File f_small=new File(image_small,productImage.getId()+".jpg");
				   File f_middle=new File(image_middle,productImage.getId()+".jpg");
				   ImageUtil.resizeImage(image, 56, 56, f_small);
                   ImageUtil.resizeImage(image, 217, 190, f_middle);

			   }
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		return "@/tmall/admin/productImage/list?pid="+pid;
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		int id=Integer.valueOf(request.getParameter("id"));
		ProductImage productImage=productImageDao.get(id);
		String type=productImage.getType();
		int pid=productImage.getProduct().getId();
		productImageDao.delete(id);
		File imageFolder=null;
		File image_small=null;
		File image_middle=null;
		if(ProductImageDao.type_single.equals(type)){
			imageFolder=new File(request.getServletContext().getRealPath("/image/productSingle"));
			image_small=new File(request.getServletContext().getRealPath("/image/productSingle_small"));
			image_middle=new File(request.getServletContext().getRealPath("/image/productSingle_middle"));
			File image=new File(imageFolder,id+".jpg");
			File small=new File(image_small,id+".jpg");
			File middle=new File(image_middle,id+".jpg");
			if(image.exists()){
				image.delete();
			}
			if(small.exists()){
				small.delete();
			}
			if(middle.exists()){
				middle.delete();
			}
		}else{
			imageFolder=new File(request.getServletContext().getRealPath("/image/productDetail"));
			File image=new File(imageFolder,id+".jpg");
			if(image.exists()){
				image.delete();
			}
		}
		return "@/tmall/admin/productImage/list?pid="+pid;
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
