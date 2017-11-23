package com.tmall.bean;

import java.util.List;

public class Category {

	private int id;
	private String name;
	private List<Product> products;
	private List<List<Product>> productsByRow;
	
	public void setId(int id){
		this.id=id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setProducts(List<Product> products){
		this.products=products;
	}
	
	public List<Product> getProducts(){
		return products;
	}
	
	public void setProductsByRow(List<List<Product>> productsByRow){
		this.productsByRow=productsByRow;
	}
	
	//每个类的产品，用于前台首页展示类别的产品
	public List<List<Product>> getProductsByRow(){
		return productsByRow;
	}
}
