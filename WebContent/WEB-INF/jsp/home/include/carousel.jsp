<%@page contentType="text/html; charset=utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
$(function(){
	//$("#carousel-of-product").carousel();
});
</script>
<div id="carousel-of-product"  class="carousel-of-product carousel slide1" data-ride="carousel" >
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-of-product" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-of-product" data-slide-to="1"></li>
    <li data-target="#carousel-of-product" data-slide-to="2"></li>
    <li data-target="#carousel-of-product" data-slide-to="3"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img class="carouselImage" src="/tmall/image/lunbo/1.jpg" >
    </div>
    <div class="item">
      <img  class="carouselImage" src="/tmall/image/lunbo/2.jpg" >
    </div>
    <div class="item">
		<img  class="carouselImage" src="/tmall/image/lunbo/3.jpg" >
    </div>

    <div class="item">
        <img  class="carouselImage" src="/tmall/image/lunbo/4.jpg" >
    </div>

  </div>

  <!-- Controls -->
<!--   <a class="left carousel-control" href="#carousel-of-product" role="button" data-slide="prev"> -->
<!--     <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> -->

<!--   </a> -->
<!--   <a class="right carousel-control" href="#carousel-of-product" role="button" data-slide="next"> -->
<!--     <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> -->

<!--   </a> -->

</div>	
