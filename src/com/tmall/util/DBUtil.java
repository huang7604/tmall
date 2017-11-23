package com.tmall.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DBUtil {
   
	private static String user="root";
	private static String password="20141369";
	private static String url="jdbc:mysql://localhost:3306/tmall";
	private static String Driver="com.mysql.jdbc.Driver";
	
	static{
		try{
			Class.forName(Driver);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
