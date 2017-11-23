<%@page contentType="text/html; charset=utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="/tmall/css/home/product.css" type="text/css" rel="stylesheet">
<script type="text/javascript">
   $(function(){
	   var stock=${product.stock}
	   $("img.smallImage").mouseenter(function(){
		   var url=$(this).attr("bigUrl");
		   $("img.bigImage").attr("src",url);
	   });
	   
	   $("a.increaseNumber").click(function(){
		  
	        var num= $("input.productNumberSetting").val();
	        num++;
	        if(num>stock)
	            num = stock;
	       
	        $("input.productNumberSetting").val(num);
	    });
	    $("a.decreaseNumber").click(function(){
	        var num= $("input.productNumberSetting").val();
	        --num;
	        if(num<=0)
	            num=1;
	        $("input.productNumberSetting").val(num);
	    });
	    
	    $("a.buyLink").click(function(){
	    	var page="/tmall/home/checkLogin";
	    	$.get(
	    		page,
	    		function(result){
	    			if("success"==result){
	    				var num=$("input.productNumberSetting").val();
	    				location.href=$("a.buyLink").attr("href")+"&num="+num;
	    			}else{
	    				$("#loginModal").modal('show'); 
	    			}
	    		}
	    	)
	    	return false;
	    });
	    
	    $("a.addCartLink").click(function(){
	    	var page="/tmall/home/checkLogin";
	    	$.get(
	    		page,
	    		function(result){
	    			if("success"==result){
	    				var num=$("input.productNumberSetting").val();
	    				var href=$("a.addCartLink").attr("href")+"&num="+num;
	    				$.get(href,
	    						function(result){
	    					if("success"==result){
	    						 $(".addCartButton").html("已加入购物车");
                                 $(".addCartButton").attr("disabled","disabled");
                                 $(".addCartButton").css("background-color","lightgray")
                                 $(".addCartButton").css("border-color","lightgray")
                                 $(".addCartButton").css("color","black")
	    					}
	    				});
	    			}else{
	    				$("#loginModal").modal('show'); 
	    			}
	    		}
	    	)
	    	return false;
	    });
	    
	    
	     


   });

</script>
<div class="imageAndInfo">
   <img src="/tmall/image/category/${product.category.id }.jpg">
   <div class="image">
      <img class="bigImage" src="/tmall/image/productSingle/${product.firstProductImage.id }.jpg">
      <div class="smallImage">
      <c:forEach items="${product.productSingleImages }" var="image">
         <img class="smallImage" bigUrl="/tmall/image/productSingle/${image.id }.jpg"
         src="/tmall/image/productSingle_small/${image.id }.jpg">
      </c:forEach>
      </div>
   </div>
   
   <div class="info">
      <div class="name">${product.name }</div>
      <div class="subTitle">${product.subTitle }</div>
      <div class="productPrice">
         <div class="juhuasuan">
             <span class="big">聚划算</span>
             <span>此商品将参加聚划算，</span><span class="time">1天19小时</span><span>后开始</span>
         </div>
         <div class="price">
             <div class="gouwujuanDiv">
                <img alt="" src="/tmall/image/site/gouwujuan.png" height="16px">
                <span>全天猫实物商品通用</span>
             </div>
             <div class="orignalPrice">
                <span class="originalPriceDesc">价格</span>
                <span class="originalPriceYuan">¥</span>
                <span class="originalPrice">${product.orignalPrice }</span>
             </div>
             <div class="promotePrice">
                 <span class="promotionPriceDesc">促销价 </span>
                 <span class="promotionPriceYuan">¥</span>
                 <span class="promotionPrice">${product.promotePrice }</span>
             </div>
         </div>
      </div>
      <div class="productSaleAndReviewNumber">
         <div>销量<span class="redColor">${product.saleCount }</span></div>
         <div>累计评价<span class="redColor">${product.reviewCount }</span></div>
      </div>
      <div class="productNumber">
        <span>数量</span>
        <span>
        <input type="text" value="1" class="productNumberSetting">
        </span>
        <span class="inAndde">
        <a href="#nowhere" class="increaseNumber"><img src="/tmall/image/site/increase.png"></a>
        <a href="#nowhere" class="decreaseNumber"><img src="/tmall/image/site/decrease.png"></a>
        </span>
        <span>库存${product.stock }件</span>
      </div>
      <div class="serviceCommitment">
            <span class="serviceCommitmentDesc">服务承诺</span>
            <span class="serviceCommitmentLink">
                <a href="#nowhere">正品保证</a>
                <a href="#nowhere">极速退款</a>
                <a href="#nowhere">赠运费险</a>
                <a href="#nowhere">七天无理由退换</a>
            </span>
        </div>   
        <div class="buyDiv">
            <a class="buyLink" href="buyone?pid=${product.id }"><button class="buyButton">立即购买</button></a>
            <a href="addCart?pid=${product.id }" class="addCartLink"><button class="addCartButton"><span class="glyphicon glyphicon-shopping-cart"></span>加入购物车</button></a>
        </div>
        
   </div>
</div>