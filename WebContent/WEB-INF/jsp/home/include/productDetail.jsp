<%@page contentType="text/html; charset=utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="productDetailDiv">
   <div class="productDetailTopPart">
      <a href="#nowhere" class="productDetailTopPartSelectedLink selected">商品详情</a>
      <a href="#nowhere" class="productDetailTopReviewLink">累计评价</a>
   </div>
   
   <div class="productParamterPart">
     <div class="productParamter">产品参数：</div>
     <div class="productParamterList">
        <c:forEach items="${propertyValues }" var="pv">
           <span>${pv.property.name }:${pv.value }</span>
        </c:forEach> 
     </div>
   </div>
   <div class="productDetialImagePart">
      <c:forEach items="${product.productDetailImages }" var="pi">
         <img src="/tmall/image/productDetail/${pi.id }.jpg">
      </c:forEach>
   </div>
</div>