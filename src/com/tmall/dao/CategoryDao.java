package com.tmall.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tmall.bean.Category;
import com.tmall.util.DBUtil;

public class CategoryDao {
	
	//类别数量
	public int getTotal(){
		int total=0;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try{
			con=DBUtil.getConnection();
			st=con.createStatement();
			String sql="select count(*) from category";
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
	
	public void add(Category category){
		Connection con=null;
		PreparedStatement pst=null;
		String sql="insert into category values(null,?)";
		try{
			con=DBUtil.getConnection();
			pst=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, category.getName());
			pst.execute();
			ResultSet rs=pst.getGeneratedKeys();
			if(rs.next()){
				int id=rs.getInt(1);
				category.setId(id);
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
	
	public void update(Category category){
		Connection con=null;
		PreparedStatement pst=null;
		String sql="update category set name=? where id=?";
		try{
			con=DBUtil.getConnection();
			pst=con.prepareStatement(sql);
			pst.setString(1, category.getName());
			pst.setInt(2, category.getId());
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
	
	public void delete(Category category){
		Connection con=null;
		PreparedStatement pst=null;
		String sql="delete from category where id=?";
		try{
			con=DBUtil.getConnection();
			pst=con.prepareStatement(sql);
			pst.setInt(1, category.getId());
			pst.execute();
			System.out.println(sql);
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
	
	public Category get(int id){
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		Category category=null;
		String sql="select * from category where id=?";
		try{
			con=DBUtil.getConnection();
			pst=con.prepareStatement(sql);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			if(rs.next()){
				category=new Category();
				category.setId(rs.getInt(1));
				category.setName(rs.getString(2));
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
		return category;
	}
	
	public List<Category> list(){
		return list(0,Short.MAX_VALUE);
	}
	
	//获取类别列表，首页业务使用
	public List<Category> list(int start,int end){
		List<Category> categorys=null;
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select * from category order by id desc limit ?,?";
		try{
			con=DBUtil.getConnection();
			pst=con.prepareStatement(sql);
			pst.setInt(1, start);
			pst.setInt(2, end);
			rs=pst.executeQuery();
			categorys=new ArrayList<Category>();
			while(rs.next()){
				Category category=new Category();
				category.setId(rs.getInt(1));
				category.setName(rs.getString(2));
				categorys.add(category);
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
		return categorys;
	}

}
