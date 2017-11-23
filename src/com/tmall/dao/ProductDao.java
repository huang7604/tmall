package com.tmall.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tmall.bean.Category;
import com.tmall.bean.Product;
import com.tmall.bean.ProductImage;
import com.tmall.util.DBUtil;
import com.tmall.util.DateUtil;

public class ProductDao {
	
	//获取分类下的产品总数，分页业务使用
		public int getTotal(int cid){
			int total=0;
			Connection con=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
			String sql="select count(*) from product where cid=?";
			try{
				con=DBUtil.getConnection();
			    pst=con.prepareStatement(sql);
			    pst.setInt(1, cid);
			    rs=pst.executeQuery();
			    if(rs.next()){
			    	total=rs.getInt(1);
			    }
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					rs.close();
					pst.close();
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			return total;
		}
		
		public void add(Product product){
			Connection con=null;
			PreparedStatement pst=null;
			String sql="insert into product values(null,?,?,?,?,?,?,?)";
			try{
				con=DBUtil.getConnection();
			    pst=con.prepareStatement(sql);
			    pst.setString(1, product.getName());
			    pst.setString(2, product.getSubTitle());
			    pst.setFloat(3, product.getOrignalPrice());
			    pst.setFloat(4, product.getPromotePrice());
			    pst.setInt(5, product.getStock());
			    pst.setInt(6, product.getCategory().getId());
			    pst.setTimestamp(7, DateUtil.d2t(product.getCreateDate()));
			    pst.execute();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					pst.close();
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		
		public void delete(int id){
			Connection con=null;
			PreparedStatement pst=null;
			String sql="delete from product where id=?";
			try{
				con=DBUtil.getConnection();
			    pst=con.prepareStatement(sql);
			    pst.setInt(1, id);
			    pst.execute();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					pst.close();
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		
		public void update(Product product){
			Connection con=null;
			PreparedStatement pst=null;
			String sql="update product set name=?,subTitle=?,orignalPrice=?,promotePrice=?,stock=?,createDate=?,cid=? where id=?";
			try{
				con=DBUtil.getConnection();
			    pst=con.prepareStatement(sql);
			    pst.setString(1,product.getName());
			    pst.setString(2, product.getSubTitle());
			    pst.setFloat(3, product.getOrignalPrice());
			    pst.setFloat(4, product.getPromotePrice());
			    pst.setInt(5, product.getStock());
			    pst.setTimestamp(6, DateUtil.d2t(product.getCreateDate()));
			    pst.setInt(7, product.getCategory().getId());
			    pst.setInt(8, product.getId());
			    pst.execute();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					pst.close();
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		
		public Product get(int id){
			Connection con=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
			Product product=null;
			String sql="select * from product where id=?";
			try{
				con=DBUtil.getConnection();
			    pst=con.prepareStatement(sql);
			    pst.setInt(1, id);
			    rs=pst.executeQuery();
			    if(rs.next()){
			    	product=new Product();
			    	product.setId(id);
			    	product.setName(rs.getString("name"));
			    	product.setSubTitle(rs.getString("subTitle"));
			    	product.setOrignalPrice(rs.getFloat("orignalPrice"));
			    	product.setPromotePrice(rs.getFloat("promotePrice"));
			    	product.setStock(rs.getInt("stock"));
			    	product.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
			    	product.setCategory(new CategoryDao().get(rs.getInt("cid")));
			    	setFirstProductImage(product);
			    }
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					pst.close();
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			
			return product;
		}
		
		//获取类下的属性列表
		
		public List<Product> list(int cid,int start,int end){
			Connection con=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
			List<Product> products=null;
			String sql="select * from product where cid=? order by id desc limit ?,?";
			try{
				con=DBUtil.getConnection();
			    pst=con.prepareStatement(sql);
			    pst.setInt(1, cid);
			    pst.setInt(2, start);
			    pst.setInt(3, end);
			    rs=pst.executeQuery();
			    products=new ArrayList<Product>();
			    Category category=new CategoryDao().get(cid);
			    while(rs.next()){
			    	Product product=new Product();
			    	product.setId(rs.getInt("id"));
			    	product.setName(rs.getString("name"));
			    	product.setSubTitle(rs.getString("subTitle"));
			    	product.setOrignalPrice(rs.getFloat("orignalPrice"));
			    	product.setPromotePrice(rs.getFloat("promotePrice"));
			    	product.setStock(rs.getInt("stock"));
			    	product.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
			    	product.setCategory(category);
			    	setFirstProductImage(product);
			    	products.add(product);
			    }
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					pst.close();
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			return products;
		}
		
		public List<Product> list(int cid){
			return list(cid,0,Short.MAX_VALUE);
		}
		
		//填充类实例的product,用于前端展示
		public void fill(Category c){
			List<Product> products=this.list(c.getId());
			c.setProducts(products);
		}
		
		public void fill(List<Category> list){
			for(Category c:list){
				fill(c);
			}
		}
		
		 public void fillByRow(List<Category> cs) {
		        int productNumberEachRow = 8;
		        for (Category c : cs) {
		            List<Product> products =  c.getProducts();
		            List<List<Product>> productsByRow =  new ArrayList<>();
		            for (int i = 0; i < products.size(); i+=productNumberEachRow) {
		                int size = i+productNumberEachRow;
		                size= size>products.size()?products.size():size;
		                List<Product> productsOfEachRow =products.subList(i, size);
		                productsByRow.add(productsOfEachRow);
		            }
		            c.setProductsByRow(productsByRow);
		        }
		    }
		     
		    public void setFirstProductImage(Product p) {
		        List<ProductImage> pis= new ProductImageDao().list(p, ProductImageDao.type_single);
		        if(!pis.isEmpty())
		            p.setFirstProductImage(pis.get(0));     
		    }
		     
		    public void setSaleAndReviewNumber(Product p) {
		        int saleCount = new OrderItemDao().getSaleCount(p.getId());
		        p.setSaleCount(saleCount);          
		 
		        int reviewCount = new ReviewDao().getCount(p.getId());
		        p.setReviewCount(reviewCount);
		         
		    }
		 
		    public void setSaleAndReviewNumber(List<Product> products) {
		        for (Product p : products) {
		            setSaleAndReviewNumber(p);
		        }
		    }
		 
		    public List<Product> search(String keyword, int start, int count) {
		         List<Product> beans = new ArrayList<Product>();
		          
		         if(null==keyword||0==keyword.trim().length())
		             return beans;
		            String sql = "select * from Product where name like ? limit ?,?";
		      
		            try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
		                ps.setString(1, "%"+keyword.trim()+"%");
		                ps.setInt(2, start);
		                ps.setInt(3, count);
		      
		                ResultSet rs = ps.executeQuery();
		      
		                while (rs.next()) {
		                    Product bean = new Product();
		                    int id = rs.getInt(1);
		                    int cid = rs.getInt("cid");
		                    String name = rs.getString("name");
		                    String subTitle = rs.getString("subTitle");
		                    float orignalPrice = rs.getFloat("orignalPrice");
		                    float promotePrice = rs.getFloat("promotePrice");
		                    int stock = rs.getInt("stock");
		                    Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
		 
		                    bean.setName(name);
		                    bean.setSubTitle(subTitle);
		                    bean.setOrignalPrice(orignalPrice);
		                    bean.setPromotePrice(promotePrice);
		                    bean.setStock(stock);
		                    bean.setCreateDate(createDate);
		                    bean.setId(id);
		 
		                    Category category = new CategoryDao().get(cid);
		                    bean.setCategory(category);
		                    setFirstProductImage(bean);                
		                    beans.add(bean);
		                }
		            } catch (SQLException e) {
		      
		                e.printStackTrace();
		            }
		            return beans;
		    }



}
