<%@page contentType="text/html; charset=utf8" %>
<%@include file="include/adminHeader.jsp" %>
<%@include file="include/adminNavigator.jsp" %>
<title>分类管理</title>
<div class="workArea">
  <h1 class="label label-info">分类管理</h1>
  <div class="listData">
     <table class="table table-striped table-bordered table-hover  table-condensed">
       <thead>
         <tr class="success">
           <th>ID</th>
           <th>图片</th>
           <th>分类名称</th>
           <th>属性管理</th>
           <th>产品管理</th>
           <th>编辑</th>
           <th>删除</th>
         </tr>
       </thead>
       <tbody>
          <c:forEach items="${categorys }" var="c">
            <tr>
              <td>${c.id }</td>
              <td><img src="/tmall/image/category/${c.id }.jpg" style="height: 15px;"></td>
              <td>${c.name }</td>
              <td><a href="/tmall/admin/property/list?cid=${c.id }"><span class="glyphicon glyphicon-th-list"></span></a></td>
              <td><a href="/tmall/admin/product/list?cid=${c.id }"><span class="glyphicon glyphicon-shopping-cart"></span></a></td>
              <td><a href="/tmall/admin/category/edit?cid=${c.id }"><span class="glyphicon glyphicon-edit"></span></a></td>
              <td><a href="/tmall/admin/category/delete?cid=${c.id }" deleteLink="true"><span class="glyphicon glyphicon-trash"></span></a></td>
            </tr>
          </c:forEach>
       </tbody>
     </table>
  </div>
  
  <%@include file="include/adminPager.jsp" %>
  
  <div class="addDiv panel panel-warning">
    <div class="panel-heading">新增分类</div>
    <div class="panel-body">
      <form action="add" method="post" enctype="multipart/form-data" id="addForm">
        <table class="addTable">
          <tr>
          <td>分类名称</td><td><input type="text" name="name" id="name" class="form-control"/></td>
          </tr>
          <tr>
          <td>分类图片</td><td><input type="file" name="image" id="image"/></td>
          </tr>
          <tr>
          <td colspan="2" align="center"><button type="submit" class="btn btn-success">提 交</button></td>
          </tr>
        </table>
      </form>
    </div>
    <script type="text/javascript">
      $("#addForm").submit(function(){
    	  if(!checkEmpty("name","分类名称")){
    		  return false;
    	  }
    	  
    	  if(!checkEmpty("image","分类图片")){
    		  return false;
    	  }
    	  return true;
      });
      
    </script>
  </div>
</div>



</body>
</html>
