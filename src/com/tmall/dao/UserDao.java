package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.tmall.bean.User;
import com.tmall.util.DBUtil;

public class UserDao {

	public int getTotal(){
		int total=0;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try{
			con=DBUtil.getConnection();
			st=con.createStatement();
			String sql="select count(*) from user";
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
	
	public void add(User user){
		Connection con=null;
		PreparedStatement pst=null;
		String sql="insert into user values(null,?,?)";
		try{
			con=DBUtil.getConnection();
			pst=con.prepareStatement(sql);
			pst.setString(1, user.getName());
			pst.setString(2, user.getPassword());
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
	
	public void updata(User user){
		Connection con=null;
		PreparedStatement pst=null;
		String sql="update user set name=?,passqord=? where id=?";
		try{
			con=DBUtil.getConnection();
			pst=con.prepareStatement(sql);
			pst.setString(1, user.getName());
			pst.setString(2, user.getPassword());
			pst.setInt(3, user.getId());
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
	
	public void delete(User user){
		Connection con=null;
		PreparedStatement pst=null;
		String sql="delete from user where id=?";
		try{
			con=DBUtil.getConnection();
			pst=con.prepareStatement(sql);
			pst.setInt(1, user.getId());
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
	
	public User get(int id){
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		User user=null;
		String sql="select * from user where id=?";
		try{
			con=DBUtil.getConnection();
			pst=con.prepareStatement(sql);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			if(rs.next()){
				user=new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
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
		return user;
	}
	
	public List<User> list(){
		return list(0,Short.MAX_VALUE);
	}
	
	//分页
	public List<User> list(int start,int end){
		List<User> users=null;
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select * from user order by id desc limit ?,?";
		try{
			con=DBUtil.getConnection();
			pst=con.prepareStatement(sql);
			pst.setInt(1, start);
			pst.setInt(2, end);
			rs=pst.executeQuery();
			users=new ArrayList<User>();
			while(rs.next()){
				User user=new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				users.add(user);
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
		return users;
	}
	
	//判断用户是否存在
	public boolean isExist(String name) {
        User user = get(name);
        return user!=null;
 
    }
 
    public User get(String name) {
        User user = null;
        Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
        String sql = "select * from User where name = ?";
        try{
        	con=DBUtil.getConnection();
        	pst=con.prepareStatement(sql);
            pst.setString(1, name);
            rs =pst.executeQuery();
  
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(name);
                user.setPassword(rs.getString("password"));
                
            }
  
        } catch (SQLException e) {
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
        return user;
    }
 
    //判断用户和密码是否存在
    public User get(String name, String password) {
    	User user = null;
        Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
        String sql = "select * from user where name = ? and password=?";
        try {
        	con=DBUtil.getConnection();
        	pst=con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, password);
            rs =pst.executeQuery();
            if (rs.next()) {
            	 user = new User();
                 int id = rs.getInt("id");
                 user.setName(name);
                 user.setPassword(rs.getString("password"));
                 user.setId(id);
            }
  
        } catch (SQLException e) {
  
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
        return user;
    }

}
