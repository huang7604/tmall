package com.tmall.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tmall.bean.Category;
import com.tmall.bean.OrderItem;
import com.tmall.bean.Product;
import com.tmall.bean.ProductImage;
import com.tmall.bean.PropertyValue;
import com.tmall.bean.Review;
import com.tmall.bean.User;
import com.tmall.dao.CategoryDao;
import com.tmall.dao.OrderItemDao;
import com.tmall.dao.ProductDao;
import com.tmall.dao.ProductImageDao;
import com.tmall.dao.PropertyValueDao;
import com.tmall.dao.ReviewDao;
import com.tmall.dao.UserDao;
import com.tmall.util.Page;

public class HomeServlet extends BaseHomeServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String index(HttpServletRequest request,HttpServletResponse response,Page page){
		List<Category> categorys=new CategoryDao().list();
		new ProductDao().fill(categorys);
		new ProductDao().fillByRow(categorys);
		request.setAttribute("categorys", categorys);
		return "WEB-INF/jsp/home/index.jsp";
	}
	
	public String registerForm(HttpServletRequest request,HttpServletResponse response,Page page){
		return "WEB-INF/jsp/home/register.jsp";
	}
	
	public String register(HttpServletRequest request,HttpServletResponse response,Page page){
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		boolean isExist=new UserDao().isExist(name);
		if(isExist){
			request.setAttribute("msg", "用户已存在！");
			return "WEB-INF/jsp/home/register.jsp";
		}
		if(name!=null&&password!=null){
			User user=new User();
			user.setName(name);
			user.setPassword(password);
			new UserDao().add(user);
			return "WEB-INF/jsp/home/registerSuccess.jsp";
		}else{
			request.setAttribute("msg", "用户或密码不能为空！");
			return "WEB-INF/jsp/home/register.jsp";
		}
		
		
	}
	
	public String loginForm(HttpServletRequest request,HttpServletResponse response,Page page){
		return "WEB-INF/jsp/home/login.jsp";
	}
	
	public String login(HttpServletRequest request,HttpServletResponse response,Page page){
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		if(!(name!=null&&password!=null)){
			request.setAttribute("msg", "用户或密码不能为空！");
			return "WEB-INF/jsp/home/login.jsp";
		}
		
		User user=new UserDao().get(name, password);
		if(user!=null){
			request.getSession().setAttribute("user", user);
			return "@/tmall/home/index";
		}else{
			request.setAttribute("msg", "用户或密码错误！");
			return "WEB-INF/jsp/home/login.jsp";
		}
		
	}
	
	public String loginAjax(HttpServletRequest request,HttpServletResponse response,Page page){
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		if(!(name!=null&&password!=null)){
			return "%fail";
		}
		
		User user=new UserDao().get(name, password);
		if(user!=null){
			request.getSession().setAttribute("user", user);
			return "%success";
		}else{
			return "%fail";
		}
		
	}
	
	public String logout(HttpServletRequest request,HttpServletResponse response,Page page){
		request.getSession().removeAttribute("user");
		return "@/tmall/home/index";
		
	}
	
	public String product(HttpServletRequest request,HttpServletResponse response,Page page){
		int pid=Integer.parseInt(request.getParameter("pid"));
		ProductDao productDao=new ProductDao();
		Product product=productDao.get(pid);
		ProductImageDao productImageDao=new ProductImageDao();
		List<ProductImage> singleImages=productImageDao.list(product, ProductImageDao.type_single);
		List<ProductImage> detailImages=productImageDao.list(product, ProductImageDao.type_detail);
		product.setProductSingleImages(singleImages);
		product.setProductDetailImages(detailImages);
		productDao.setSaleAndReviewNumber(product);
		List<Review> reviews=new ReviewDao().list(pid);
		List<PropertyValue> propertyValues=new PropertyValueDao().list(pid);
		request.setAttribute("product", product);
		request.setAttribute("reviews", reviews);
		request.setAttribute("propertyValues", propertyValues);
		return "WEB-INF/jsp/home/product.jsp";
	}
	
	public String test(HttpServletRequest request,HttpServletResponse response,Page page){
		request.getSession().removeAttribute("user");
		return "WEB-INF/jsp/home/test.html";
		
	}
	
	public String checkLogin(HttpServletRequest request,HttpServletResponse response,Page page){
		HttpSession session=request.getSession(false);
		if(session==null){
			return "%fail";
		}else{
			User user=(User)session.getAttribute("user");
			if(user!=null){
				return "%success";
			}else{
				return "%fail";
			}
		}
		
	}
	
	public String category(HttpServletRequest request,HttpServletResponse response,Page page){
		int cid=Integer.parseInt(request.getParameter("cid"));
		Category category=new CategoryDao().get(cid);
		new ProductDao().fill(category);
		new ProductDao().setSaleAndReviewNumber(category.getProducts());
		request.setAttribute("category", category);
		return "WEB-INF/jsp/home/category.jsp";
		
	}
	
	public String search(HttpServletRequest request,HttpServletResponse response,Page page){
		
		String keyword=request.getParameter("keyword");
		List<Product> products=new ProductDao().search(keyword, 0, 20);
		new ProductDao().setSaleAndReviewNumber(products);
		request.setAttribute("ps", products);
		
		return "WEB-INF/jsp/home/searchResult.jsp";
		//return null;
		
	}
	
    public String buyone(HttpServletRequest request,HttpServletResponse response,Page page){
		int pid=Integer.parseInt(request.getParameter("pid"));
		int num=Integer.parseInt(request.getParameter("num"));
		Product product=new ProductDao().get(pid);
		OrderItemDao orderItemDao=new OrderItemDao();
		User user=(User) request.getSession().getAttribute("user");
		boolean found=false;
		int id=0;
		List<OrderItem> orderItems=orderItemDao.listByUser(user.getId());
		if(orderItems!=null){
			for(OrderItem orderItem:orderItems){
				if(product.getId()==orderItem.getProduct().getId()){
					orderItem.setNumber(orderItem.getNumber()+num);
					orderItemDao.update(orderItem);
					found=true;
					id=orderItem.getId();
					break;
				}
			}
		}
		
		if(!found){
			OrderItem orderItem=new OrderItem();
			orderItem.setNumber(num);
			orderItem.setProduct(product);
			orderItem.setUser(user);
			orderItemDao.add(orderItem);
			id=orderItem.getId();
		}
	    return "@buy?id="+id;
		
	}
    
    public String buy(HttpServletRequest request,HttpServletResponse response,Page page){
		String[] ids=request.getParameterValues("id");
		float total=0;
		ArrayList<OrderItem> ods=new ArrayList<OrderItem>();
		OrderItemDao orderItemDao=new OrderItemDao();
		for(String id:ids){
			OrderItem orderItem=orderItemDao.get(Integer.parseInt(id));
			if(orderItem!=null){
			total+=orderItem.getNumber()*orderItem.getProduct().getPromotePrice();
			ods.add(orderItem);
			}
		}
		HttpSession session=request.getSession(false);
		if(session==null){
			return "/WEB-INF/jsp/home/login.jsp";
		}
		session.setAttribute("ods", ods);
		request.setAttribute("total", total);
	    return "/WEB-INF/jsp/home/buy.jsp";
		
	}
    
    public String addCart(HttpServletRequest request,HttpServletResponse response,Page page){
		int pid=Integer.parseInt(request.getParameter("pid"));
		int num=Integer.parseInt(request.getParameter("num"));
		Product product=new ProductDao().get(pid);
		OrderItemDao orderItemDao=new OrderItemDao();
		User user=(User) request.getSession().getAttribute("user");
		boolean found=false;
		
		List<OrderItem> orderItems=orderItemDao.listByUser(user.getId());
		if(orderItems!=null){
			for(OrderItem orderItem:orderItems){
				if(product.getId()==orderItem.getProduct().getId()){
					orderItem.setNumber(orderItem.getNumber()+num);
					orderItemDao.update(orderItem);
					found=true;
					break;
				}
			}
		}
		
		if(!found){
			OrderItem orderItem=new OrderItem();
			orderItem.setNumber(num);
			orderItem.setProduct(product);
			orderItem.setUser(user);
			orderItemDao.add(orderItem);
		}
	    return "%success";
		
	}
	
    public String cart(HttpServletRequest request,HttpServletResponse response,Page page){
		User user=(User) request.getSession().getAttribute("user");
		List<OrderItem> ods=new OrderItemDao().listByUser(user.getId());
		request.setAttribute("ods", ods);
	    return "/WEB-INF/jsp/home/cart.jsp";
	}
    
    public String deleteOrderItem(HttpServletRequest request,HttpServletResponse response,Page page){
    	int oiid=Integer.parseInt(request.getParameter("oiid"));
    	new OrderItemDao().delete(oiid);
		return "%success";
	}
    
    public String changeOrderItem(HttpServletRequest request,HttpServletResponse response,Page page){
    	int oiid=Integer.parseInt(request.getParameter("oiid"));
    	int num=Integer.parseInt(request.getParameter("num"));
    	OrderItemDao orderItemDao=new OrderItemDao();
    	OrderItem orderItem=orderItemDao.get(oiid);
    	orderItem.setNumber(num);
    	orderItemDao.update(orderItem);
		return "%success";
	}
	
	

}
