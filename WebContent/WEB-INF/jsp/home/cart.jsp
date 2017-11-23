<%@page contentType="text/html; charset=utf8" %>

<%@include file="include/header.jsp" %>
<%@include file="include/top.jsp" %>
<%@include file="include/search.jsp" %>
<title>购物车</title>
<style>
div.cartDiv {
	max-width: 1020px;
	margin: 10px auto;
	color: black;
}

span.cartTitlePrice {
	color: #C40000;
	font-size: 14px;
	font-weight: bold;
	margin-left: 5px;
	margin-right: 3px;
}

div.cartTitle button {
	background-color: #AAAAAA;
	border: 1px solid #AAAAAA;
	color: white;
	width: 53px;
	height: 25px;
	border-radius: 2px;
}

table.cartProductTable {
	width: 100%;
	font-size:12px;
}

table.cartProductTable th {
	font-weight: normal;
	color: #3C3C3C;
	padding: 20px 20px;
}

img.cartProductImg {
	padding: 1px;
	border: 1px solid #EEEEEE;
	width: 80px;
	height: 80px;
}

a.cartProductLink {
	color: #3C3C3C;
}

a.cartProductLink:hover {
	color: #C40000;
	text-decoration: underline;
}

div.cartProductLinkOutDiv {
	position: relative;
	height: 80px;
}

div.cartProductLinkInnerDiv {
	position: absolute;
	bottom: 0;
	height: 20px;
}

tr.cartProductItemTR td {
	padding: 20px 20px;
}

tr.cartProductItemTR {
	border: 1px solid #CCCCCC;
}

span.cartProductItemOringalPrice {
	text-decoration: line-through;
	color: #9C9C9C;
	display: block;
	font-weight: bold;
	font-size: 14px;
}

span.cartProductItemPromotionPrice {
	font-family: Arial;
	font-size: 14px;
	font-weight: bold;
	color: #C40000;
}

span.cartProductItemSmallSumPrice {
	font-family: Arial;
	font-size: 14px;
	font-weight: bold;
	color: #C40000;
}

div.cartProductChangeNumberDiv {
	border: solid 1px #E5E5E5;
	width: 80px;
}

div.cartProductChangeNumberDiv input {
	border: solid 1px #AAAAAA;
	width: 42px;
	display: inline-block;
}

div.cartProductChangeNumberDiv a {
	text-decoration: none;
}

div.cartProductChangeNumberDiv a {
	width: 14px;
	display: inline-block;
	text-align: center;
	color: black;
	text-decoration: none;
}

img.cartProductItemIfSelected, img.selectAllItem {
	cursor: pointer;
}

div.cartFoot {
	background-color: #E5E5E5;
	line-height: 50px;
	margin: 20px 0px;
	color: black;
	padding-left: 20px;
}

span.cartSumNumber {
	color: #C40000;
	font-weight: bold;
	font-size: 16px;
}

span.cartSumPrice {
	color: #C40000;
	font-weight: bold;
	font-size: 20px;
}

div.cartFoot button {
	background-color: #AAAAAA;
	border: 0px solid #AAAAAA;
	color: white;
	height: height%;
	width: 120px;
	height: 50px;
	font-size: 20px;
	text-align: center;
	/* 	border-radius: 2px; */
}

div.boughtDiv {
	max-width: 1013px;
	margin: 10px auto;
}
</style>
<script type="text/javascript">
var OrderItemId=0;
function changePrice(oiid,num){
  var price=$("span.cartProductItemSmallSumPrice[oiid="+oiid+"]").attr("promotePrice");
  $("span.cartProductItemSmallSumPrice[oiid="+oiid+"]").html("￥"+formatMoney(num*price));
  $("input.orderItemNumberSetting[oiid="+oiid+"]").val(num);
}

function changeOrderItem(oiid,num){
	$.post("/tmall/home/changeOrderItem",
			{"oiid":oiid,"num":num},
			function(result){
				if("success"==result){
					changePrice(oiid,num)
				}
			});
}

function syncCreateOrderButton(){
    var selectAny = false;
    $(".cartProductItemIfSelected").each(function(){
        if("selectit"==$(this).attr("selectit")){
            selectAny = true;
        }
    });
     
    if(selectAny){
        $("button.createOrderButton").css("background-color","#C40000");
        $("button.createOrderButton").removeAttr("disabled");
    }
    else{
        $("button.createOrderButton").css("background-color","#AAAAAA");
        $("button.createOrderButton").attr("disabled","disabled");      
    }
         
}

function calcCartSumPriceAndNumber(){
    var sum = 0;
    var totalNumber = 0;
    $("img.cartProductItemIfSelected[selectit='selectit']").each(function(){
        var oiid = $(this).attr("oiid");
        var price =$(".cartProductItemSmallSumPrice[oiid="+oiid+"]").text();
        price = price.replace(/,/g, "");
        price = price.replace(/￥/g, "");
        sum += new Number(price);   
         
        var num =$(".orderItemNumberSetting[oiid="+oiid+"]").val();
        totalNumber += new Number(num); 
         
    });
     
    $("span.cartSumPrice").html("￥"+formatMoney(sum));
    $("span.cartTitlePrice").html("￥"+formatMoney(sum));
    $("span.cartSumNumber").html(totalNumber);
}

function syncSelect(){
    var selectAll = true;
    $(".cartProductItemIfSelected").each(function(){
        if("false"==$(this).attr("selectit")){
            selectAll = false;
        }
    });
     
    if(selectAll)
        $("img.selectAllItem").attr("src","/tmall/image/site/cartSelected.png");
    else
        $("img.selectAllItem").attr("src","/tmall/image/site/cartNotSelected.png");
     
}




  $(function(){
	  
	  $("a.deleteOrderItem").click(function(){
		  var page="/tmall/home/checkLogin";
		  oiid=$(this).attr("oiid");
		  $.get(page,
		   function(result){
			  if("success"==result){
				  $("#deleteConfirmModal").modal('show');
  			   }else{
  				 $("#loginModal").modal('show'); 
  			   }
		    });
		  
	  });
	  
	  $("button.deleteConfirmButton").click(function(){
	    	$.post("/tmall/home/deleteOrderItem",
	    			{"oiid":OrderItemId},
	    			function(result){
	    				if(result=="success"){
	    					location.reload();
	    				}
	    			});
	   });
	  
	  $("a.numberMinus").click(function(){
		  var page="/tmall/home/checkLogin";
		  var oiid=$(this).attr("oiid");
		  var stock=$(this).attr("stock");
		  $.get(page,
		   function(result){
			  if("success"==result){
				  var num=$("input.orderItemNumberSetting[oiid="+oiid+"]").val();
				  num--;
				  if(num<1){
					  num=1;
				  }
				  changeOrderItem(oiid,num)
  			   }else{
  				 $("#loginModal").modal('show'); 
  			   }
		    });
	  });
	  
	  $("a.numberPlus").click(function(){
		  var page="/tmall/home/checkLogin";
		  var oiid=$(this).attr("oiid");
		  var stock=$(this).attr("stock");
		  $.get(page,
		   function(result){
			  if("success"==result){
				  var num=$("input.orderItemNumberSetting[oiid="+oiid+"]").val();
				  num++;
				  if(num>stock){
					  num=stock;
				  }
				  changeOrderItem(oiid,num)
  			   }else{
  				 $("#loginModal").modal('show'); 
  			   }
		    });
	  });
	  
	  $("input.orderItemNumberSetting").keyup(function(){
		  var page="/tmall/home/checkLogin";
		  var oiid=$(this).attr("oiid");
		  var stock=$(this).attr("stock");
		  $.get(page,
		   function(result){
			  if("success"==result){
				  var num=$("input.orderItemNumberSetting[oiid="+oiid+"]").val();
				  num = parseInt(num);
			        if(isNaN(num))
			            num= 1;
			        if(num<=0)
			            num = 1;
			        if(num>stock)
			            num = stock;
				  changeOrderItem(oiid,num)
  			   }else{
  				 $("#loginModal").modal('show'); 
  			   }
		    });
	  });
	  
	  $("img.cartProductItemIfSelected").click(function(){
	        var selectit = $(this).attr("selectit")
	        if("selectit"==selectit){
	            $(this).attr("src","/tmall/image/site/cartNotSelected.png");
	            $(this).attr("selectit","false")
	            $(this).parents("tr.cartProductItemTR").css("background-color","#fff");
	        }
	        else{
	            $(this).attr("src","/tmall/image/site/cartSelected.png");
	            $(this).attr("selectit","selectit")
	            $(this).parents("tr.cartProductItemTR").css("background-color","#FFF8E1");
	        }
	        syncCreateOrderButton();
	        calcCartSumPriceAndNumber();
	        syncSelect();
	    });
	  
	  $("img.selectAllItem").click(function(){
	        var selectit = $(this).attr("selectit")
	        if("selectit"==selectit){
	            $("img.selectAllItem").attr("src","/tmall/image/site/cartNotSelected.png");
	            $("img.selectAllItem").attr("selectit","false")
	            $(".cartProductItemIfSelected").each(function(){
	                $(this).attr("src","/tmall/image/site/cartNotSelected.png");
	                $(this).attr("selectit","false");
	                $(this).parents("tr.cartProductItemTR").css("background-color","#fff");
	            });         
	        }
	        else{
	            $("img.selectAllItem").attr("src","/tmall/image/site/cartSelected.png");
	            $("img.selectAllItem").attr("selectit","selectit")
	            $(".cartProductItemIfSelected").each(function(){
	                $(this).attr("src","/tmall/image/site/cartSelected.png");
	                $(this).attr("selectit","selectit");
	                $(this).parents("tr.cartProductItemTR").css("background-color","#FFF8E1");
	            });             
	        }
	        syncCreateOrderButton();
	        calcCartSumPriceAndNumber();
	         
	    });
	  
	  $("button.createOrderButton").click(function(){
		  var param="";
		  $(".cartProductItemIfSelected").each(function(){
			  if("selectit"==$(this).attr("selectit")){
				  var oiid=$(this).attr("oiid");
				  param+="&id="+oiid;
			  }
		  });
		  param.substring(1);
		  location.href="buy?"+param;
	  });

  });
</script>
<div class="cartDiv">
    <div class="cartTitle pull-right">
        <span>已选商品  (不含运费)</span>
        <span class="cartTitlePrice">￥0.00</span>
        <button class="createOrderButton" disabled="disabled">结 算</button>
    </div>
     
    <div class="cartProductList">
        <table class="cartProductTable">
            <thead>
                <tr>
                    <th class="selectAndImage">
                            <img selectit="false" class="selectAllItem" src="/tmall/image/site/cartNotSelected.png">               
                    全选
                     
                    </th>
                    <th>商品信息</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th width="120px">金额</th>
                    <th class="operation">操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${ods }" var="oi">
                    <tr oiid="${oi.id}" class="cartProductItemTR">
                        <td>
                            <img selectit="false" oiid="${oi.id}" class="cartProductItemIfSelected" src="/tmall/image/site/cartNotSelected.png">
                            <a style="display:none" href="#nowhere"><img src="/tmall/image/site/cartSelected.png"></a>
                            <img class="cartProductImg"  src="/tmall/image/productSingle_middle/${oi.product.firstProductImage.id}.jpg">
                        </td>
                        <td>
                            <div class="cartProductLinkOutDiv">
                                <a href="foreproduct?pid=${oi.product.id}" class="cartProductLink">${oi.product.name}</a>
                                <div class="cartProductLinkInnerDiv">
                                    <img src="/tmall/image/site/creditcard.png" title="支持信用卡支付">
                                    <img src="/tmall/image/site/7day.png" title="消费者保障服务,承诺7天退货">
                                    <img src="/tmall/image/site/promise.png" title="消费者保障服务,承诺如实描述">
                                </div>
                            </div>
                             
                        </td>
                        <td>
                            <span class="cartProductItemOringalPrice">￥${oi.product.orignalPrice}</span>
                            <span  class="cartProductItemPromotionPrice">￥${oi.product.promotePrice}</span>
                             
                        </td>
                        <td>
                         
                            <div class="cartProductChangeNumberDiv">
                                <a  stock="${oi.product.stock}" oiid="${oi.id}" class="numberMinus" href="#nowhere">-</a>
                                <input  oiid="${oi.id}" stock="${oi.product.stock}" class="orderItemNumberSetting" autocomplete="off" value="${oi.number}">
                                <a  stock="${oi.product.stock}" oiid="${oi.id}" class="numberPlus" href="#nowhere">+</a>
                            </div>                    
                         </td>
                        <td >
                            <span class="cartProductItemSmallSumPrice" oiid="${oi.id}" promotePrice="${oi.product.promotePrice}" >
                            ￥<fmt:formatNumber type="number" value="${oi.product.promotePrice*oi.number}" minFractionDigits="2"/>
                            </span>
                         
                        </td>
                        <td>
                            <a class="deleteOrderItem" oiid="${oi.id}"  href="#nowhere">删除</a>
                        </td>
                    </tr>
                </c:forEach>              
            </tbody>
         
        </table>
    </div>
     
    <div class="cartFoot">
        <img selectit="false" class="selectAllItem" src="/tmall/image/site/cartNotSelected.png">
        <span>全选</span>
<!--         <a href="#">删除</a> -->
         
        <div class="pull-right">
            <span>已选商品 <span class="cartSumNumber" >0</span> 件</span>
             
            <span>合计 (不含运费): </span> 
            <span class="cartSumPrice" >￥0.00</span>
            <button class="createOrderButton" disabled="disabled" >结  算</button>
        </div>
         
    </div>
     
</div>

<%@include file="include/footer.jsp" %>