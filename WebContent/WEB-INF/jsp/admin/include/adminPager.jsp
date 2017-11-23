<%@page contentType="text/html; charset=utf8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
$(function(){
	$("ul.pagination li.disabled a").click(function(){
		return false;
	});
});

</script>
<div class="page">
<ul class="pagination">
  <li <c:if test="${page.start==0 }">class="disabled"</c:if>><a href="?page.start=0${page.param }">«</a></li>
  <li <c:if test="${page.start==0 }">class="disabled"</c:if>><a href="?page.start=${page.start-page.count}${page.param }">‹</a></li>
  <c:forEach begin="0" end="${page.page-1 }" varStatus="status">
     <li><a href="?page.start=${status.index*page.count }${page.param }">${status.count }</a></li>
  </c:forEach>
  <li <c:if test="${page.start==page.last }">class="disabled"</c:if> ><a href="?page.start=${page.start+page.count}${page.param }">›</a></li>
  <li <c:if test="${page.start==page.last }">class="disabled"</c:if>><a href="?page.start=${page.last }${page.param }">»</a></li>
</ul>
</div>