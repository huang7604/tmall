package com.tmall.util;

public class Page {
	
	private int start=0;
	private int count=0;
	private int total=0;
	private String param;
	public Page(int start,int count){
		this.start=start;
		this.count=count;
	}
	public void setStart(int start){
		this.start=start;
	}
	
	public int getStart(){
		return start;
	}
	
	public void setCount(int count){
		this.count=count;
	}
	
	public int getCount(){
		return count;
	}
	
	public void setTotal(int total){
		this.total=total;
	}
	
	public int getTotal(){
		return total;
	}
	
	public int getPage(){
		if(total%count==0){
			return total/count;
		}else{
			return total/count+1;
		}
	}
	
	public int getLast(){
		 int last;
	        // ����������50�����ܹ���5�����ģ���ô���һҳ�Ŀ�ʼ����45
	        if (0 == total % count)
	            last = total - count;
	        // ����������51�����ܹ���5�����ģ���ô���һҳ�Ŀ�ʼ����50
	        else
	            last = total - total % count;
	         
	        last = last<0?0:last;
	        return last;

	}
	
	public String getParam(){
		return param;
	}
	
	public void setParam(String param){
		this.param=param;
	}
	
	public boolean isHasPreviouse(){
        if(start==0)
            return false;
        return true;
         
    }
    public boolean isHasNext(){
        if(start==getLast())
            return false;
        return true;
    }


}
