package com.tmall.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.tmall.bean.Product;
import com.tmall.bean.ProductImage;
import com.tmall.util.DBUtil;

public class ProductImageDao {
	 public static final String type_single = "type_single";
	 public static final String type_detail = "type_detail";

	   //Í¼Æ¬±ðÊýÁ¿
		public int getTotal(){
			int total=0;
			Connection con=null;
			Statement st=null;
			ResultSet rs=null;
			try{
				con=DBUtil.getConnection();
				st=con.createStatement();
				String sql="select count(*) from productimage";
				rs=st.executeQuery(sql);
				while(rs.next()){
					total=rs.getInt(1);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try {
					rs.close();
					st.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return total;
		}
		
		public void add(ProductImage productimage){
			Connection con=null;
			PreparedStatement pst=null;
			String sql="insert into productimage values(null,?,?)";
			try{
				con=DBUtil.getConnection();
				pst=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				pst.setInt(1, productimage.getProduct().getId());
				pst.setString(2, productimage.getType());
				pst.execute();
				ResultSet rs=pst.getGeneratedKeys();
				if(rs.next()){
					productimage.setId(rs.getInt(1));
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try {
					pst.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		public void updata(ProductImage productimage){
			Connection con=null;
			PreparedStatement pst=null;
			String sql="update productimage set pid=?,name=? where id=?";
			try{
				con=DBUtil.getConnection();
				pst=con.prepareStatement(sql);
				pst.setInt(1, productimage.getProduct().getId());
				pst.setString(2, productimage.getType());
				pst.setInt(3, productimage.getId());
				pst.execute();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try {
					pst.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		public void delete(int id){
			Connection con=null;
			PreparedStatement pst=null;
			String sql="delete from productimage where id=?";
			try{
				con=DBUtil.getConnection();
				pst=con.prepareStatement(sql);
				pst.setInt(1,id);
				pst.execute();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try {
					pst.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		public ProductImage get(int id){
			Connection con=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
			ProductImage productimage=null;
			String sql="select * from productimage where id=?";
			try{
				con=DBUtil.getConnection();
				pst=con.prepareStatement(sql);
				pst.setInt(1, id);
				rs=pst.executeQuery();
				if(rs.next()){
					productimage=new ProductImage();
					productimage.setId(rs.getInt(1));
					productimage.setProduct(new ProductDao().get(rs.getInt(2)));
					productimage.setType(rs.getString(3));
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try {
					rs.close();
					pst.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return productimage;
		}
		
		public List<ProductImage> list(Product product,String type){
			return list(product,type,0,Short.MAX_VALUE);
		}
		
		
		public List<ProductImage> list(Product product,String type,int start,int end){
			List<ProductImage> productimages=null;
			Connection con=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
			String sql="select * from productimage where pid=? and type=? order by id desc limit ?,?";
			try{
				con=DBUtil.getConnection();
				pst=con.prepareStatement(sql);
				pst.setInt(1, product.getId());
				pst.setString(2, type);
				pst.setInt(3, start);
				pst.setInt(4, end);
				rs=pst.executeQuery();
				productimages=new ArrayList<ProductImage>();
				while(rs.next()){
					ProductImage productimage=new ProductImage();
					productimage.setId(rs.getInt(1));
					productimage.setProduct(product);
					productimage.setType(rs.getString(3));
					productimages.add(productimage);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try {
					rs.close();
					pst.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return productimages;
		}

}
