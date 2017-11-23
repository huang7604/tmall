<%@page contentType="text/html; charset=utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link type="text/css" rel="stylesheet" href="/tmall/css/home/category.css">
<script type="text/javascript">

function showProductsAsideCategorys(cid){
    $("div.eachCategory[cid="+cid+"]").css("background-color","white");
    //$("div.eachCategory[cid="+cid+"] a").css("color","#87CEFA");
    $("div.productsAsideCategorys[cid="+cid+"]").show();
}
 
function hideProductsAsideCategorys(cid){
    $("div.eachCategory[cid="+cid+"]").css("background-color","#e2e2e3");
    //$("div.eachCategory[cid="+cid+"] a").css("color","#000");
    $("div.productsAsideCategorys[cid="+cid+"]").hide();
}

$(function(){
	$("div.rightMenu span").mouseenter(function(){
	    var left = $(this).position().left;
	    var top = $(this).position().top;
	    var width = $(this).css("width");
	   
	    var destLeft = parseInt(left) + parseInt(width)/2;
	    $("img#catear").css("left",destLeft);
	    $("img#catear").css("top",top+150);
	    $("img#catear").fadeIn(500);       
	});
	
	$("div.rightMenu span").mouseleave(function(){
		$("img#catear").hide();
	});
	
	 $("div.eachCategory").mouseenter(function(){
	        var cid = $(this).attr("cid");
	        showProductsAsideCategorys(cid);
	 });
	 $("div.eachCategory").mouseleave(function(){
	        var cid = $(this).attr("cid");
	        hideProductsAsideCategorys(cid);
	 });
	 $("div.productsAsideCategorys").mouseenter(function(){
	        var cid = $(this).attr("cid");
	        showProductsAsideCategorys(cid);
	    });
	 $("div.productsAsideCategorys").mouseleave(function(){
	        var cid = $(this).attr("cid");
	        hideProductsAsideCategorys(cid);
	  });
	     
	  
	


})


</script>

<img src="/tmall/image/site/catear.png" id="catear" class="catear">
<div class="categoryWithcarousel">

<div class="headBar show1">
  <div class="head">
    <span style="margin-left:10px;color:#ffffff;" class="glyphicon glyphicon-th-list"></span>
    <span style="margin-left:10px;color:#ffffff;" >商品分类</span>
  </div>
  <div class="rightMenu">
     <span><a href=""><img  src="/tmall/image/site/chaoshi.png"></a></span>
     <span><a href=""><img  src="/tmall/image/site/guoji.png"></a></span>
     <c:forEach items="${categorys }" var="category" varStatus="c">
        <c:if test="${c.count<=4 }">
          <span><a href="">${category.name }</a></span>
        </c:if>
     </c:forEach>
  </div>
</div>

<%@include file="categoryMenu.jsp" %>
<div>
    <%@include file="productsAsideCategory.jsp" %>
</div>
<div class="myCarousel">
   <%@include file="carousel.jsp" %>
</div>


</div>