<%@page contentType="text/html; charset=utf8" %>
<%@page contentType="text/html; charset=utf8" %>

<%@include file="include/header.jsp" %>
<%@include file="include/top.jsp" %>
<%@include file="include/search.jsp" %>
<title>天猫商城-搜索</title>
<style>
  div.categoryPageDiv{
    width: 70%;
    margin-left: auto;
    margin-right: auto;
    overflow: hidden;
    margin-top: 20px;
  }
  
  
</style>
<div class="categoryPageDiv">
   <%@include file="include/productsBySearch.jsp"%>
</div>
 

<%@include file="include/footer.jsp" %>