<%@page contentType="text/html; charset=utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="include/header.jsp" %>
<%@include file="include/top.jsp" %>
<%@include file="include/search.jsp" %>
<title>商城注册</title>

<style>
  form.registerForm{
    width: 50%;
    margin-left: auto;
    margin-right: auto;
    margin-top: 100px;
    
  }
  table.registerTable{
    width: 70%;
     margin-left: auto;
    margin-right: auto;
  }
  
  table.registerTable td{
    padding: 10px;
  }
  
  button.bt{
    background-color: #C40000;
    width: 80%;
    border: 1px solid #C40000;
  }
  div.registerErrorMessageDiv{
    visibility: hidden;
  }
  
</style>
<script type="text/javascript">
  $(function(){
	  <c:if test="${!empty msg}">
	    $("span.errorMessage").html("${msg}");
	    $("div.registerErrorMessageDiv").css("visibility","visible");       
	  </c:if>

	  $(".registerForm").submit(function(){
		  if(0==$("#name").val().length){
	            $("span.errorMessage").html("请输入用户名");
	            $("div.registerErrorMessageDiv").css("visibility","visible");           
	            return false;
	        }       
	        if(0==$("#password").val().length){
	            $("span.errorMessage").html("请输入密码");
	            $("div.registerErrorMessageDiv").css("visibility","visible");           
	            return false;
	        }       
	        if(0==$("#repassword").val().length){
	            $("span.errorMessage").html("请输入重复密码");
	            $("div.registerErrorMessageDiv").css("visibility","visible");           
	            return false;
	        }       
	        if($("#password").val() !=$("#repassword").val()){
	            $("span.errorMessage").html("重复密码不一致");
	            $("div.registerErrorMessageDiv").css("visibility","visible");           
	            return false;
	        }       
	 
	        return true;

	  });
  });

</script>
<form method="post" action="register" class="registerForm">
   <div class="registerErrorMessageDiv">
     <div class="alert alert-danger" role="alert">
       <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
       <span class="errorMessage"></span>
     </div>
   </div>
   <table class="registerTable">
      <tr>
         <td style="font-weight:bold;" colspan="2" align="center">设置会员名</td>
         
      </tr>
       <tr>
         <td>登录名</td>
         <td>
         <input id="name" type="text" name="name" placeholder="会员名一旦设置成功，无法更改" class="form-control">
         </td>
      </tr>
      <tr>
         <td style="font-weight:bold;" colspan="2" align="center">设置登录密码</td>
      </tr>
      <tr>
         <td>登录密码</td>
         <td>
         <input id="password" name="password" type="password" class="form-control">
         </td>
      </tr>
      <tr>
         <td>确认密码</td>
         <td>
         <input id="repassword" name="repassword" type="password" class="form-control">
         </td>
      </tr>
      <tr>
        <td colspan="2" align="center"><button type="submit" class="btn btn-primary bt">注       册</button></td>
      </tr>
      
   </table>
</form>
<%@include file="include/footer.jsp" %>