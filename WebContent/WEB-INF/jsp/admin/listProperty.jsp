<%@page contentType="text/html; charset=utf8" %>
<%@include file="include/adminHeader.jsp" %>
<%@include file="include/adminNavigator.jsp" %>
<title>属性管理</title>
<div class="workArea">
  <ol class="breadcrumb">
    <li><a href="/tmall/admin/category/list">所有分类</a></li>
    <li><a href="list?cid=${category.id }">${category.name }</a></li>
    <li>属性管理</li>
  </ol>
  <div class="listData">
     <table class="table table-striped table-bordered table-hover  table-condensed">
       <thead>
         <tr class="success">
           <th>ID</th>
           <th>属性名称</th>
           <th>编辑</th>
           <th>删除</th>
         </tr>
       </thead>
       <tbody>
          <c:forEach items="${properties}" var="p">
            <tr>
              <td>${p.id }</td>
              <td>${p.name }</td>
              <td><a href="/tmall/admin/property/edit?pid=${p.id }"><span class="glyphicon glyphicon-edit"></span></a></td>
              <td><a href="/tmall/admin/property/delete?pid=${p.id }&cid=${category.id}" deleteLink="true"><span class="glyphicon glyphicon-trash"></span></a></td>
            </tr>
          </c:forEach>
       </tbody>
     </table>
  </div>
  
  <%@include file="include/adminPager.jsp" %>
  
  <div class="addDiv panel panel-warning">
    <div class="panel-heading">新增属性</div>
    <div class="panel-body">
      <form action="add" method="post"  id="addForm">
        <input type="hidden" name="cid" value="${category.id }">
        <table class="addTable">
          <tr>
          <td>属性名称</td><td><input type="text" name="name" id="name" class="form-control"/></td>
          </tr>
          <tr>
          <td colspan="2" align="center"><button type="submit" class="btn btn-success">提 交</button></td>
          </tr>
        </table>
      </form>
    </div>
    <script type="text/javascript">
      $("#addForm").submit(function(){
    	  if(!checkEmpty("name","属性名称")){
    		  return false;
    	  }
    	  
    	
    	  return true;
      });
      
    </script>
  </div>
</div>



</body>
</html>
