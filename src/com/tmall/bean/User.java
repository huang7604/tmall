package com.tmall.bean;

public class User {
	private int id;
	private String name;
	private String password;
	
	public void setId(int id){
		this.id=id;
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public void setPassword(String password){
		this.password=password;
	}
	
	public String getPassword(){
		return password;
	}
	
	//获取匿名名称
	public String getAnonymousName(){
		if(null==name){
			return null;
		}
		
		if(name.length()<1){
			return "*";
		}
		
		if(name.length()==2){
			return name.substring(0, 1)+"*";
		}
		
		char[] cn=name.toCharArray();
		for(int i=0;i<cn.length-1;i++){
			cn[i]='*';
		}
		
		return new String(cn);
		
	}
	

}
