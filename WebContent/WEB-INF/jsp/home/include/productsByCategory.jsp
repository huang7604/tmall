<%@page contentType="text/html; charset=utf8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<style>
div.categoryProducts {
   padding: 0px 20px 40px 20px;
}

div.productUnit {
	width: 225px;
	height: 338px;
	border: 3px solid #fff;
	background-color: white;
	margin: 12px 5px;
	float: left;
	padding: 0px;
}

div.productUnit:hover {
	width: 225px;
	border: 3px solid #C40000;
	padding: 0px;
}


div.productUnit span.productUnitDesc {
	font-size: 12px;
	color: #666666;
	display: block;
	padding: 16px;
}

div.productUnitFrame{
	border:1px solid #eee;
}
div.productUnitFrame:hover{
	border:1px solid #C40000;
}

div.productUnit span.productPrice {
	font-size: 20px;
	color: #CC0000;
	display: block;
	padding-left: 4px;
}

div.productUnit span.productReview {
	border-left-width: 1px;
	border-left-style: solid;
	border-left-color: #EEEEEE;
	border-right-width: 1px;
	border-right-style: solid;
	border-right-color: #EEEEEE;
}

div.productUnit a.productLink {
	margin: 10px 0px;
	color: #333333;
	font: 0.8em;
	display: block;
}

div.productUnit a.productLink:hover {
	text-decoration: underline;
	color: #C40000;
}

div.productUnit a.tmallLink {
	margin: 10px 0px;
	color: #999999;
	font: 0.8em;
	display: block;
	text-decoration: underline;
}

div.productUnit a.tmallLink:hover {
	text-decoration: underline;
	color: #C40000;
}

div.productUnit div.productInfo {
	color: #999999;
}

div.productUnit span.monthDeal, div.productUnit span.productReview {
	display: inline-block;
	width: 90px;
	height: 30px;
	padding-top: 5px;
	padding-left: 5px;
}

div.productUnit span.wangwang {
	padding-left: 3px;
}

div.productUnit span.productDealNumber {
	font-weight: bold;
	color: #B57C5B;
}

div.productUnit span.productReviewNumber {
	font-weight: bold;
	color: #3388BB;
}

div.productUnit img.productImage {
	width: 100%;
	height: 190px;
}

div.productUnit div.productInfo {
	border-top-width: 1px;
	border-top-style: solid;
	border-top-color: #EEEEEE;
}


</style>
<c:if test="${empty param.categorycount}">
    <c:set var="categorycount" scope="page" value="100"/>
</c:if>
 
<c:if test="${!empty param.categorycount}">
    <c:set var="categorycount" scope="page" value="${param.categorycount}"/>
</c:if>
     
<div class="categoryProducts">
    <c:forEach items="${category.products}" var="p" varStatus="stc">
        <c:if test="${stc.count<=categorycount}">
        <div class="productUnit" price="${p.promotePrice}">
            <div class="productUnitFrame">
                <a href="product?pid=${p.id}">
                    <img class="productImage" src="/tmall/image/productSingle_middle/${p.firstProductImage.id}.jpg">
                </a>
                <span class="productPrice">¥<fmt:formatNumber type="number" value="${p.promotePrice}" minFractionDigits="2"/></span>
                <a class="productLink" href="foreproduct?pid=${p.id}">
                 ${fn:substring(p.name, 0, 50)}
                </a>
                 
                <a  class="tmallLink" href="product?pid=${p.id}">天猫专卖</a>
     
                <div class="show1 productInfo">
                    <span class="monthDeal ">月成交 <span class="productDealNumber">${p.saleCount}笔</span></span>
                    <span class="productReview">评价<span class="productReviewNumber">${p.reviewCount}</span></span>
                  
                </div>
            </div>
        </div>
        </c:if>
    </c:forEach>
        
</div>
