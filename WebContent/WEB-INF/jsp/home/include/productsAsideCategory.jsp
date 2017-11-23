<%@page contentType="text/html; charset=utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<c:forEach items="${categorys }" var="category">
   <div cid="${category.id }" class="productsAsideCategorys">
     <c:forEach items="${category.productsByRow }" var="products">
        <div class="row show1">
          <c:forEach items="${products }" var="product">
            <c:if test="${!empty product.subTitle }">
               <a href="product?pid=${product.id }">
                 <c:forEach items="${fn:split(product.subTitle,' ') }" var="title" varStatus="p">
                   <c:if test="${p.index==0 }">
                     ${title }
                   </c:if>
                 </c:forEach>
               </a>
            </c:if>
          </c:forEach>
        </div>
     </c:forEach>
   </div>
</c:forEach>