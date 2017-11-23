<%@page contentType="text/html; charset=utf8" %>
<%@include file="include/adminHeader.jsp" %>
<%@include file="include/adminNavigator.jsp" %>
<title>产品图片管理</title>
<div class="workArea">
  <ol class="breadcrumb">
    <li><a href="/tmall/admin/category/list">所有分类</a></li>
    <li><a href="list?cid=${category.id }">${category.name }</a></li>
    <li>${product.name }</li>
    <li>产品图片管理</li>
  </ol>
   <script type="text/javascript">
    $(function(){
    	 $("#addFormSingle").submit(function(){
    	    	if(!checkEmpty("filepathsingle","单个图片")){
    	    		return false;
    	    	}
    	    	return true;
    	    });
    	    
    	    $("#addFormDetail").submit(function(){
    	    	if(!checkEmpty("filepathDetail","详情图片")){
    	    		return false;
    	    	}
    	    	
    	    	return true;
    	    });
    });
   
  </script>
  <table class="addPictureTable">
    <tr>
      <td class="addPictureTableTd">
           <div class="panel panel-warning addPicturnDiv">
              <div class="panel-heading">新增产品<b class="text-primary">单个</b>图片</div>
              <div class="panel-body">
                 <form action="add" method="post" id="addFormSingle" enctype="multipart/form-data">
                    <table class="addTable">
                       <tr>
                         <td>请选择本地图片 尺寸400X400 为佳</td>
                       </tr>
                        <tr>
                         <td><input type="file" name="filepath" id="filepathsingle"></td>
                       </tr>
                       <tr class="submitTr">
                         <td align="center">
                           <input type="hidden" name="type" value="type_single">
                           <input type="hidden" name="pid" value="${product.id }">
                           <button class="btn btn-success" type="submit">提交</button>
                         </td>
                       </tr>
                    </table>
                 </form>
              </div>
           </div>
          </td>
          <td class="addPictureTableTd">
         
           <div class="panel panel-warning addPicturnDiv">
              <div class="panel-heading">新增产品<b class="text-primary">详情</b>图片</div>
              <div class="panel-body">
                 <form action="add" method="post" id="addFormDetail" enctype="multipart/form-data">
                    <table class="addTable">
                       <tr>
                         <td>请选择本地图片 宽度790为佳</td>
                       </tr>
                        <tr>
                         <td><input type="file" name="filepath" id="filepathDetail"></td>
                       </tr>
                       <tr class="submitTr">
                         <td align="center">
                           <input type="hidden" name="type" value="type_detail">
                           <input type="hidden" name="pid" value="${product.id }">
                           <button class="btn btn-success" type="submit">提交</button>
                         </td>
                       </tr>
                    </table>
                 </form>
              </div>
           </div>
          </td>
          </tr>
           
           <tr>
           <td>
            <table class="table table-bordered table-striped table-hover table-condensed">
              <thead>
               <tr class="success">
                <th>ID</th>
                <th>单个产品缩略图</th>
                <th>删除</th>
                </tr>
             </thead>
              <tbody>
               <c:forEach items="${imageSingles}" var="is">
                 <tr>
                   <td>${is.id }</td>
                   <td><a title="点击查看图片" href="/tmall/image/productSingle/${is.id }.jpg"><img height="50px" src="/tmall/image/productSingle/${is.id }.jpg"></a></td>
                   <td><a href="delete?id=${is.id }"><span class="glyphicon glyphicon-trash"></span></a></td>
                 </tr>
               </c:forEach>
             </tbody>
             
            </table>
         </td>
         
         <td>
           
           <table class="table table-bordered table-striped table-hover table-condensed">
             <thead>
              <tr class="success">
               <th>ID</th>
               <th>产品详情图片缩略图</th>
               <th>删除</th>
              </tr>
             </thead>
            
             <tbody>
               <c:forEach items="${imageDetails}" var="id">
                 <tr>
                   <td>${id.id }</td>
                   <td><a title="点击查看图片" href="/tmall/image/productDetail/${id.id }.jpg"><img height="50px" src="/tmall/image/productDetail/${id.id }.jpg"></a></td>
                   <td><a href="delete?id=${id.id }" ><span class="glyphicon glyphicon-trash"></span></a></td>
                 </tr>
               </c:forEach>
             </tbody>
           </table>
      </td>  
      </tr>
     </table>
        
   
 
  </div>
</body>
</html>
