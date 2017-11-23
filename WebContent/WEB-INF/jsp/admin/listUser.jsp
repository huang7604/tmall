<%@page contentType="text/html; charset=utf8" %>
<%@include file="include/adminHeader.jsp" %>
<%@include file="include/adminNavigator.jsp" %>
<title>用户管理</title>
<div class="workArea">
  <h1 class="label label-info">用户管理</h1>
  <div class="listData">
     <table class="table table-striped table-bordered table-hover  table-condensed">
       <thead>
         <tr class="success">
           <th>ID</th>
           <th>用户名称</th>
         </tr>
       </thead>
        <tbody>
          <c:forEach items="${users }" var="user">
            <tr>
              <td>${user.id }</td>
              <td>${user.name }</td>
            </tr>
          </c:forEach>
       </tbody>
     </table>
  </div>
  
  <%@include file="include/adminPager.jsp" %>
  
  
</div>
</body>
</html>
