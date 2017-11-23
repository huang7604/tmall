<%@page contentType="text/html; charset=utf8" %>
<%@include file="include/adminHeader.jsp" %>
<%@include file="include/adminNavigator.jsp" %>
<title>订单管理</title>
<div class="workArea">
  <h1 class="label label-info">订单管理</h1>
  <div class="listData">
     <table class="table table-striped table-bordered table-hover  table-condensed">
       <thead>
         <tr class="success">
            <th>ID</th>
            <th>状态</th>
            <th>金额</th>
            <th width="100px">商品数量</th>
            <th width="100px">买家名称</th>
            <th>创建时间</th>
            <th>支付时间</th>
            <th>发货时间</th>
            <th>确认收货时间</th>
            <th width="120px">操作</th>   
         </tr>
       </thead>
        <tbody>
          <c:forEach items="${orders }" var="user">
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
