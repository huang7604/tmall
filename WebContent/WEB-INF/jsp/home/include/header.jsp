<%@page contentType="text/html; charset=utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<html>
  <head>
     <script type="text/javascript" src="/tmall/js/jquery/2.1.1/jquery.min.js"></script>
     <script type="text/javascript" src="/tmall/js/bootstrap/3.3.7/bootstrap.min.js"></script>
     <link type="text/css" rel="stylesheet" href="/tmall/css/bootstrap/3.3.7/bootstrap.min.css">
     <link rel="stylesheet" href="/tmall/css/home/style.css">
  </head>
  <script type="text/javascript">
     function checkEmpty(id,name){
    	 var value=$("#"+id).val();
    	 if(value.length==0){
    		alert(name+"不能为空");
     		$("#"+id)[0].focus();
     		return true;
    	 }
    	 
    	 return false;
    	 
     }
     
     function formatMoney(num){
    	    num = num.toString().replace(/\$|\,/g,'');  
    	    if(isNaN(num))  
    	        num = "0";  
    	    sign = (num == (num = Math.abs(num)));  
    	    num = Math.floor(num*100+0.50000000001);  
    	    cents = num%100;  
    	    num = Math.floor(num/100).toString();  
    	    if(cents<10)  
    	    cents = "0" + cents;  
    	    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
    	    num = num.substring(0,num.length-(4*i+3))+','+  
    	    num.substring(num.length-(4*i+3));  
    	    return (((sign)?'':'-') + num + '.' + cents);  
    	}

     
  </script>
  <body>
  
  

