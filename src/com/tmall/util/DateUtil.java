package com.tmall.util;

import java.sql.Timestamp;
import java.util.Date;

public class DateUtil {
	public static Timestamp d2t(Date date){
		if(date==null){
			return null;
		}
		
		return new Timestamp(date.getTime());
	}
	
	public static Date t2d(Timestamp t){
		if(t==null){
			return null;
		}
		
		return new Date(t.getTime());
	}

}
