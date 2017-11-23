<%@page contentType="text/html; charset=utf8" %>

<%@include file="include/header.jsp" %>
<%@include file="include/top.jsp" %>
<%@include file="include/search.jsp" %>
<title>${product.name }</title>
<script>
  $(function(){
	  
	  
	  $("a.productDetailTopReviewLink").click(function(){
		  $("div.productReviewDiv").show();
		  $("div.productDetailDiv").hide();
	  });
	  
	  $("a.productReviewTopPartLink").click(function(){
		  $("div.productDetailDiv").show();
		  $("div.productReviewDiv").hide();
	  });
	  
	  
  });
</script>
<div class="productPageDiv">
  <%@include file="include/imageAndInfo.jsp" %>
  <%@include file="include/productDetail.jsp" %>
  <%@include file="include/productReview.jsp" %>
</div>
<%@include file="include/footer.jsp" %>