<%@page contentType="text/html; charset=utf8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
     <script type="text/javascript" src="/tmall/js/jquery/jquery.min.js"></script>
     <script type="text/javascript" src="/tmall/js/bootstrap/bootstrap.min.js"></script>
     <link type="text/css" rel="stylesheet" href="/tmall/css/bootstrap/3.3.7/bootstrap.min.css">
     <link type="text/css" rel="stylesheet" href="/tmall/css/back/back.css">
     <script type="text/javascript">
        function checkEmpty(id,name){
        	var value=$("#"+id).val();
        	if(value.length==0){
        		alert(name+"不能为空");
        		$("#"+id)[0].focus();
        		return false;
        	}
        	
        	return true;
        	
        }
        
        function checkNumber(id,name){
        	var value=$("#"+id).val();
        	if(value.length==0){
        		alert(name+"不能为空");
        		$("#"+id)[0].focus();
        		return false;
        	}
        	if(isNaN(value)){
        		alert(name+"必须是数字");
        		$("#"+id)[0].focus();
        		return false;
        	}
        	
        	return true;
        }
        
        function checkInt(id,name){
        	var value=$("#"+id).val();
        	if(value.length==0){
        		alert(name+"不能为空");
        		$("#"+id)[0].focus();
        		return false;
        	}
        	if(!parseInt(value)==value){
        		alert(name+"必须是整数");
        		$("#"+id)[0].focus();
        		return false;
        	}
        	
        	return true;
        }
        
        $(function(){
        	$("a").click(function(){
        		var deleteLink=$(this).attr("deleteLink");
        		if("true"==deleteLink){
        			var confirmDelete=confirm("确认删除");
        			if(confirmDelete){
        				return true;
        			}
        			return false;
        		}
        	});
        });
     </script>
  </head>
<body>