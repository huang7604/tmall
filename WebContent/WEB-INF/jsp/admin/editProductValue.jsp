<%@page contentType="text/html; charset=utf8" %>
<%@include file="include/adminHeader.jsp" %>
<%@include file="include/adminNavigator.jsp" %>
<title>产品属性管理</title>
<div class="workArea">
  <ol class="breadcrumb">
    <li><a href="/tmall/admin/category/list">所有分类</a></li>
    <li><a href="list?cid=${category.id }">${category.name }</a></li>
    <li>${product.name }</li>
    <li>产品属性编辑</li>
  </ol>
  
  <div class="editProductValue">
     <table>
     <c:forEach items="${propertyValues }" var="pv">
       <tr><td><span class="pvValue">${pv.property.name }</span></td>
       <td><span><input type="text" pvid="${pv.id }" class="form-control pvValue" name="value" value="${pv.value }"></span></td></tr>
     </c:forEach>
     </table>
  </div>
  <script type="text/javascript">
    $(function(){
    	$("input.pvValue").keyup(function(){
    		var value=$(this).val();
    		var pvid=$(this).attr("pvid");
    		var parentSpan=$(this).parent("span");
    		$.post(
    				"/tmall/admin/product/updatePropertyValue",
    				{"value":value,"pvid":pvid},
    				function(result){
    					if("success"==result){
    						
    					}else{
    						$(this).css("border","1px solid red");
    						
    					}
    					
    				});
    	});
    });
  </script>
</div>

