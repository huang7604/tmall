<%@page contentType="text/html; charset=utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="categoryMenu">
  <c:forEach items="${categorys }" var="category">
     <div cid="${category.id }" class="eachCategory">
        <span class="glyphicon glyphicon-link"></span>
        <a href="category?cid=${category.id }">${category.name }</a>
     </div>
  </c:forEach>
</div>