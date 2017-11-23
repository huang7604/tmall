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
  <div class="listData">
     <table class="table table-striped table-bordered table-hover  table-condensed">
       <thead>
         <tr class="success">
           <th>ID</th>
           <th>图片</th>
           <th>产品名称</th>
           <th>产品小标题</th>
           <th>原价格</th>
           <th>优惠价格</th>
           <th>库存数量</th>
           <th>图片管理</th>
           <th>设置属性</th>
           <th>编辑</th>
           <th>删除</th>
         </tr>
       </thead>
       <tbody>
          <c:forEach items="${products }" var="p">
            <tr>
              <td>${p.id }</td>
              <td><img></td>
              <td>${p.name }</td>
              <td>${p.subTitle }</td>
              <td>${p.orignalPrice}</td>
              <td>${p.promotePrice }</td>
              <td>${p.stock }</td>
              <td><a href="/tmall/admin/productImage/list?pid=${p.id }"><span class="glyphicon glyphicon-picture"></span></a></td>
              <td><a href="/tmall/admin/product/editPropertyValue?id=${p.id }"><span class="glyphicon glyphicon-th-list"></span></a></td>
              <td><a href="/tmall/admin/product/edit?pid=${p.id }"><span class="glyphicon glyphicon-edit"></span></a></td>
              <td><a href="/tmall/admin/product/delete?pid=${p.id }&cid=${category.id}" deleteLink="true"><span class="glyphicon glyphicon-trash"></span></a></td>
            </tr>
          </c:forEach>
       </tbody>
     </table>
  </div>
  
  <%@include file="include/adminPager.jsp" %>
  
  <div class="addDiv panel panel-warning">
    <div class="panel-heading">新增产品</div>
    <div class="panel-body">
      <form action="add" method="post"  id="addForm">
        <input type="hidden" name="cid" value="${category.id }">
        <table class="addTable">
          <tr>
          <td>产品名称</td><td><input type="text" name="name" id="name" class="form-control"/></td>
          </tr>
          <tr>
          <td>产品小标题</td><td><input type="text" name="subTitle" id="subTitle" class="form-control"/></td>
          </tr>
          <tr>
          <td>原价格</td><td><input type="text" name="orignalPrice" id="orignalPrice" class="form-control"/></td>
          </tr>
          <tr>
          <td>优惠价格</td><td><input type="text" name="promotePrice" id="promotePrice" class="form-control"/></td>
          </tr>
          <tr>
          <td>库存</td><td><input type="text" name="stock" id="stock" class="form-control"/></td>
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
