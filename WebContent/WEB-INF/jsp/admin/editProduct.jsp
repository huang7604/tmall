<%@page contentType="text/html; charset=utf8" %>
<%@include file="include/adminHeader.jsp" %>
<%@include file="include/adminNavigator.jsp" %>
<title>产品管理</title>
<div class="workArea">
  <ol class="breadcrumb">
    <li><a href="/tmall/admin/category/list">所有分类</a></li>
    <li><a href="list?cid=${category.id }">${category.name }</a></li>
    <li>产品管理</li>
  </ol>
  
  <div class="addDiv panel panel-warning">
    <div class="panel-heading">编辑产品</div>
    <div class="panel-body">
      <form action="update" method="post"  id="addForm">
        <input type="hidden" name="cid" value="${category.id }">
        <input type="hidden" name="pid" value="${product.id }">
        <table class="addTable">
          <tr>
          <td>产品名称</td><td><input type="text" name="name" id="name" class="form-control" value="${product.name }"/></td>
          </tr>
          <tr>
          <td>产品小标题</td><td><input type="text" name="subTitle" id="subTitle" class="form-control" value="${product.subTitle }"/></td>
          </tr>
          <tr>
          <td>原价格</td><td><input type="text" name="orignalPrice" id="orignalPrice" class="form-control" value="${product.orignalPrice }"/></td>
          </tr>
          <tr>
          <td>优惠价格</td><td><input type="text" name="promotePrice" id="promotePrice" class="form-control" value="${product.promotePrice }"/></td>
          </tr>
          <tr>
          <td>库存</td><td><input type="text" name="stock" id="stock" class="form-control" value="${product.stock }"/></td>
          </tr>
          <tr>
          <td colspan="2" align="center"><button type="submit" class="btn btn-success">提 交</button></td>
          </tr>
        </table>
      </form>
    </div>
    <script type="text/javascript">
      $("#addForm").submit(function(){
    	  if(!checkEmpty("name","产品名称")){
    		  return false;
    	  }
    	  if(!checkEmpty("subTitle","产品小标题")){
    		  return false;
    	  }
    	  if(!checkNumber("orignalPrice","原价格")){
    		  return false;
    	  }
    	  if(!checkNumber("promotePrice","优惠价格")){
    		  return false;
    	  }
    	  if(!checkInt("stock","库存")){
    		  return false;
    	  }
    	 
    	  
    	
    	  return true;
      });
      
    </script>
  </div>
</div>



</body>
</html>
