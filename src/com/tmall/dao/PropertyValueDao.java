package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tmall.bean.Product;
import com.tmall.bean.Property;
import com.tmall.bean.PropertyValue;
import com.tmall.util.DBUtil;

public class PropertyValueDao {

	public int getTotal(){
		int total=0;
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select count(*) from propertyvalue";
		try{
			con=DBUtil.getConnection();
		    pst=con.prepareStatement(sql);
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
	
	public void add(PropertyValue pv){
		Connection con=null;
		PreparedStatement pst=null;
		String sql="insert into propertyvalue values(null,?,?,?)";
		try{
			con=DBUtil.getConnection();
		    pst=con.prepareStatement(sql);
		    pst.setInt(1, pv.getProduct().getId());
		    pst.setInt(2, pv.getProperty().getId());
		    pst.setString(3, pv.getValue());
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
		String sql="delete from propertyvalue where id=?";
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
	
	public void update(PropertyValue pv){
		Connection con=null;
		PreparedStatement pst=null;
		String sql="update propertyvalue set ptid=?,pid=?,value=? where id=?";
		try{
			con=DBUtil.getConnection();
		    pst=con.prepareStatement(sql);
		    pst.setInt(1, pv.getProperty().getId());
		    pst.setInt(2, pv.getProduct().getId());
		    pst.setString(3,pv.getValue() );
		    pst.setInt(4, pv.getId());
		   
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
	
	public PropertyValue get(int id) {
        PropertyValue bean = new PropertyValue();
        Connection c = null;
        Statement s =null;
        ResultSet rs =null;
        try{
        	c = DBUtil.getConnection(); 
        	s = c.createStatement();
            String sql = "select * from propertyvalue where id = " + id;
  
            rs = s.executeQuery(sql);
 
            if (rs.next()) {
                int pid = rs.getInt("pid");
                int ptid = rs.getInt("ptid");
                String value = rs.getString("value");
                 
                Product product = new ProductDao().get(pid);
                Property property = new PropertyDao().get(ptid);
                bean.setProduct(product);
                bean.setProperty(property);
                bean.setValue(value);
                bean.setId(id);
            }
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }finally{
        	try {
				rs.close();
				s.close();
	        	c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return bean;
    }
    public PropertyValue get(int ptid, int pid ) {
        PropertyValue bean = null;
        Connection c = null;
        Statement s =null;
        ResultSet rs =null;
        try {
            
            String sql = "select * from PropertyValue where ptid = " + ptid + " and pid = " + pid;
            c = DBUtil.getConnection(); 
        	s = c.createStatement(); 
            rs = s.executeQuery(sql);
             
            if (rs.next()) {
                bean= new PropertyValue();
                int id = rs.getInt("id");
 
                String value = rs.getString("value");
                 
                Product product = new ProductDao().get(pid);
                Property property = new PropertyDao().get(ptid);
                bean.setProduct(product);
                bean.setProperty(property);
                bean.setValue(value);
                bean.setId(id);
            }
             
        } catch (SQLException e) {
             
            e.printStackTrace();
        }finally{
        	try {
				rs.close();
				s.close();
	        	c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return bean;
    }
  
    public List<PropertyValue> list() {
        return list(0, Short.MAX_VALUE);
    }
  
    public List<PropertyValue> list(int start, int count) {
        List<PropertyValue> beans = new ArrayList<PropertyValue>();
  
        String sql = "select * from PropertyValue order by id desc limit ?,? ";
        Connection c = null;
        PreparedStatement ps =null;
        ResultSet rs =null;
        try{
        	c = DBUtil.getConnection(); 
            ps = c.prepareStatement(sql);
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            rs = ps.executeQuery();
  
            while (rs.next()) {
                PropertyValue bean = new PropertyValue();
                int id = rs.getInt(1);
 
                int pid = rs.getInt("pid");
                int ptid = rs.getInt("ptid");
                String value = rs.getString("value");
                 
                Product product = new ProductDao().get(pid);
                Property property = new PropertyDao().get(ptid);
                bean.setProduct(product);
                bean.setProperty(property);
                bean.setValue(value);
                bean.setId(id);           
                beans.add(bean);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }finally{
        	try {
				rs.close();
				ps.close();
	        	c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return beans;
    }
 
    public void init(Product p) {
        List<Property> pts= new PropertyDao().list(p.getCategory().getId());
         
        for (Property pt: pts) {
            PropertyValue pv = get(pt.getId(),p.getId());
            if(null==pv){
                pv = new PropertyValue();
                pv.setProduct(p);
                pv.setProperty(pt);
                this.add(pv);
            }
        }
    }
 
    public List<PropertyValue> list(int pid) {
        List<PropertyValue> beans = new ArrayList<PropertyValue>();
         
        String sql = "select * from PropertyValue where pid = ? order by ptid desc";
        Connection c = null;
        ResultSet rs =null;
        PreparedStatement ps =null;
        try  {
        	c = DBUtil.getConnection(); 
            ps = c.prepareStatement(sql);
            ps.setInt(1, pid);
  
            rs = ps.executeQuery();
  
            while (rs.next()) {
                PropertyValue bean = new PropertyValue();
                int id = rs.getInt(1);
 
                int ptid = rs.getInt("ptid");
                String value = rs.getString("value");
                 
                Product product = new ProductDao().get(pid);
                Property property = new PropertyDao().get(ptid);
                bean.setProduct(product);
                bean.setProperty(property);
                bean.setValue(value);
                bean.setId(id);           
                beans.add(bean);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }finally{
        	try {
				rs.close();
				ps.close();
	        	c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return beans;
    }


}
