<%@page contentType="text/html; charset=utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="search">
<a href="/tmall/home/index">
   <img src="/tmall/image/site/logo.gif" class="logo" id="logo">
</a>

<form action="search" method="post">
   <div class="searchDiv">
     <input type="text" name="keyword" placeholder="时尚男鞋     太阳镜">
     <button type="submit" class="searchButton">搜索</button>
     <div class="">
        <c:forEach items="${categorys }" var="category" varStatus="c">
           <c:if test="${c.count>=5 and c.count<=8 }">
              <span>
              <a href="category?cid=${category.id }">${category.name }</a>
              <c:if test="${c.count!=8 }">
                <span>|</span>
              </c:if>
              </span>
           </c:if>
        </c:forEach>
     </div>
     </div>
</form>
</div>