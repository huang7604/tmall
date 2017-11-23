<%@page contentType="text/html; charset=utf8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="top">
  <a href="/tmall/home/index"><span class="glyphicon glyphicon-home redColor"></span>
     天猫首页
  </a>
  <span class="a">瞄，欢迎来到天猫</span>
  <c:if test="${!empty user }">
    <a href="loginForm">${user.name }</a>
    <a href="logout">退出</a>
  </c:if>
  
  <c:if test="${empty user }">
    <a href="loginForm">请登录</a>
    <a href="registerForm">欢迎注册</a>
  </c:if>
  
  <span class="pull-right b">
    <a href="">我的订单</a>
    <a href="cart">
    <span style="color:#C40000;margin:0px" class=" glyphicon glyphicon-shopping-cart redColor">
    </span>
           购物车</a>
  </span>
</nav>