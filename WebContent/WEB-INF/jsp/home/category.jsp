<%@page contentType="text/html; charset=utf8" %>

<%@include file="include/header.jsp" %>
<%@include file="include/top.jsp" %>
<%@include file="include/search.jsp" %>
<title>天猫商城-${category.name}</title>
<style>
  div.categoryPageDiv{
    width: 70%;
    margin-left: auto;
    margin-right: auto;
    overflow: hidden;
    margin-top: 20px;
  }
  
  div.categoryPageDiv img{
   
    margin-left: auto;
    margin-right: auto;
    padding: 0px 20px 40px 20px;
  }
</style>
<div class="categoryPageDiv">
   <img src="/tmall/image/category/${category.id}.jpg">
   <%@include file="include/productsByCategory.jsp"%>
</div>
 

<%@include file="include/footer.jsp" %>