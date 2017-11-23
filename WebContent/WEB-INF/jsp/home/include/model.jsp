<%@page contentType="text/html; charset=utf8" %>
<script type="text/javascript">
 $(function(){

	    $("button.loginSubmitButton").click(function(){
	        var name = $("#name").val();
	        var password = $("#password").val();
	         
	        if(0==name.length||0==password.length){
	            $("span.errorMessage").html("请输入账号密码");
	            $("div.loginErrorMessageDiv").show();           
	            return false;
	        }
	         
	        var page = "/tmall/home/loginAjax";
	        $.get(
	                page,
	                {"name":name,"password":password},
	                function(result){
	                    if("success"==result){
	                        location.reload();
	                    }
	                    else{
	                        $("span.errorMessage").html("账号密码错误");
	                        $("div.loginErrorMessageDiv").show();                       
	                    }
	                }
	        );          
	         
	        return true;
	    });
	    
	    
 });
</script>
<div class="modal" id="loginModal" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog loginDivInProductPageModalDiv">
            <div class="modal-content">
                 <div class="loginDivInProductPage">
                        <div class="loginErrorMessageDiv">
                            <div class="alert alert-danger" >
                              <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                                <span class="errorMessage"></span>
                            </div>
                        </div>
                             
                        <div class="login_acount_text">账户登录</div>
                        <div class="loginInput " >
                            <span class="loginInputIcon ">
                                <span class=" glyphicon glyphicon-user"></span>
                            </span>
                            <input id="name" name="name" placeholder="手机/会员名/邮箱" type="text">         
                        </div>
                         
                        <div class="loginInput " >
                            <span class="loginInputIcon ">
                                <span class=" glyphicon glyphicon-lock"></span>
                            </span>
                            <input id="password" name="password"  type="password" placeholder="密码" type="text">           
                        </div>
                                    <span class="text-danger">不要输入真实的天猫账号密码</span><br><br>
                        <div>
                            <a href="#nowhere">忘记登录密码</a> 
                            <a href="register.jsp" class="pull-right">免费注册</a> 
                        </div>
                        <div style="margin-top:20px">
                            <button class="btn btn-block redButton loginSubmitButton" type="submit">登录</button>
                        </div>
                    </div>    
           </div>
    </div>
</div>
 
<div class="modal" id="deleteConfirmModal" tabindex="-1" role="dialog" >
    <div class="modal-dialog deleteConfirmModalDiv">
       <div class="modal-content">
          <div class="modal-header">
            <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">确认删除？</h4>
          </div>
          <div class="modal-footer">
            <button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
            <button class="btn btn-primary deleteConfirmButton" id="submit" type="button">确认</button>
          </div>
        </div>
      </div>
</div>
