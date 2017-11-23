<%@page contentType="text/html; charset=utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<link href="/tmall/css/home/products.css" type="text/css" rel="stylesheet">
<c:if test="${empty param.categorycount }">
  <c:set var="categorycount" scope="page" value="100"></c:set>
</c:if>

<c:if test="${!empty param.categorycount }">
  <c:set var="categorycount" scope="page" value="${param.categorycount }"></c:set>
</c:if>

<div class="products">
  <c:forEach items="${categorys }" var="category" varStatus="c">
     
        <div class="productsOfCategory">
        <span class="categoryTitle">${category.name }</span>
           <div class="left-mark"></div>
           <br>
           <c:forEach items="${category.products }" var="product" varStatus="p">
             <c:if test="${p.count<=5 }">
              <div class="productItem">
                <a href="product?pid=${product.id }">
                <img alt="" src="/tmall/image/productSingle_middle/${product.firstProductImage.id }.jpg">
                </a>
                <a href="product?pid=${product.id }" class="productItemDescLink">
                  <span class="productItemDesc">
                   [热销] ${fn:substring(product.name,0,20) }
                  </span>
                </a>
                <span class="productPrice">
                   ${product.promotePrice }
                </span>
              </div>
             </c:if>
           </c:forEach>
        </div>
    
  </c:forEach>
</div>