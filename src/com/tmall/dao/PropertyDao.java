package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tmall.bean.Category;
import com.tmall.bean.Property;
import com.tmall.util.DBUtil;

public class PropertyDao {
	
	//获取分类下的属性总数，分页业务使用
	public int getTotal(int cid){
		int total=0;
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select count(*) from property where cid=?";
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
	
	public void add(Property property){
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="insert into property values(null,?,?)";
		try{
			con=DBUtil.getConnection();
		    pst=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		    pst.setInt(1, property.getCategory().getId());
		    pst.setString(2, property.getName());
		    pst.execute();
		    
		    rs=pst.getGeneratedKeys();
		    if(rs.next()){
		    	property.setId(rs.getInt(1));
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
	}
	
	public void delete(int id){
		Connection con=null;
		PreparedStatement pst=null;
		String sql="delete from property where id=?";
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
	
	public void update(Property property){
		Connection con=null;
		PreparedStatement pst=null;
		String sql="update property set cid=?,name=? where id=?";
		try{
			con=DBUtil.getConnection();
		    pst=con.prepareStatement(sql);
		    pst.setInt(1, property.getCategory().getId());
		    pst.setString(2, property.getName());
		    pst.setInt(3, property.getId());
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
	
	public Property get(int id){
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		Property property=null;
		String sql="select * from property where id=?";
		try{
			con=DBUtil.getConnection();
		    pst=con.prepareStatement(sql);
		    pst.setInt(1, id);
		    rs=pst.executeQuery();
		    if(rs.next()){
		    	property=new Property();
		    	property.setId(id);
		    	property.setName(rs.getString(3));
		    	property.setCategory(new CategoryDao().get(rs.getInt(2)));
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
		
		return property;
	}
	
	//获取类下的属性列表
	
	public List<Property> list(int cid,int start,int end){
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		List<Property> properties=null;
		String sql="select * from property where cid=? order by id desc limit ?,?";
		try{
			con=DBUtil.getConnection();
		    pst=con.prepareStatement(sql);
		    pst.setInt(1, cid);
		    pst.setInt(2, start);
		    pst.setInt(3, end);
		    rs=pst.executeQuery();
		    properties=new ArrayList<Property>();
		    Category category=new CategoryDao().get(cid);
		    while(rs.next()){
		    	Property property=new Property();
		    	property.setId(rs.getInt(1));
		    	property.setName(rs.getString(3));
		    	property.setCategory(category);
		    	properties.add(property);
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
		return properties;
	}
	
	public List<Property> list(int cid){
		return list(cid,0,Short.MAX_VALUE);
	}

}
