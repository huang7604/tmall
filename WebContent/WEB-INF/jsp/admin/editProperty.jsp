<%@page contentType="text/html; charset=utf8" %>
<%@include file="include/adminHeader.jsp" %>
<%@include file="include/adminNavigator.jsp" %>
<title>属性管理</title>
<div class="workArea">
  <ol class="breadcrumb">
    <li><a href="/tmall/admin/category/list">所有分类</a></li>
    <li><a href="list?cid=${category.id }">${propert.category.name }</a></li>
    <li>属性管理</li>
  </ol>

  <div class="addDiv panel panel-warning">
    <div class="panel-heading">编辑属性</div>
    <div class="panel-body">
      <form action="update" method="post"  id="addForm">
        <input type="hidden" name="cid" value="${property.category.id }">
        <input type="hidden" name="id" value="${property.id }">
        <table class="addTable">
          <tr>
          <td>属性名称</td><td><input type="text" name="name" id="name" class="form-control" value="${property.name }"/></td>
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
