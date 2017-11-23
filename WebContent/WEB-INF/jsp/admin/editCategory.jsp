<%@page contentType="text/html; charset=utf8" %>

<%@include file="include/adminHeader.jsp" %>
<%@include file="include/adminNavigator.jsp" %>
<title>分类管理</title>
<div class="workArea">
  <h1 class="label label-info">分类编辑</h1>
  
  <div class="addDiv panel panel-warning">
    <div class="panel-heading">编辑分类</div>
    <div class="panel-body">
      <form action="update" method="post" enctype="multipart/form-data" id="addForm">
        <input type="hidden" name="id"  value="${category.id }"/>
        <table class="addTable">
          <tr>
          <td>分类名称</td><td><input type="text" name="name" id="name" class="form-control" value="${category.name }"/></td>
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
    	  
    	  return true;
      });
      
    </script>
  </div>
</div>



</body>
</html>
