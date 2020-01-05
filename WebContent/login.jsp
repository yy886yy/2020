<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理-登陆</title>
    <link rel="stylesheet" href="layui/css/layui.css"/>
    <link rel="stylesheet" href="images/login.css"/>
    <style>
    	.admin-header{
    		text-align: center;
    		font-size: 28px;
    		font-family: '微软雅黑';
    		font-weight: 100;
    		color:#fff;
    	}
    	.a-button{
    		width:300px;
    		margin-top:10px;
    	}
    	body{
    		background-image: url(images/bj.jpg);
    		background-repeat: no-repeat;
    		background-size:100% 100%;
			background-attachment:fixed;
    	}
    </style>
</head>
<body>
<div id="container">
    <div></div>
    <div class="admin-login-background">
        <div class="admin-header">
       	微商管理系统
        </div>
        <form class="layui-form"  method="post" action="${pageContext.request.contextPath}/login.do">
            <div>
                <i class="layui-icon layui-icon-username admin-icon admin-icon-username"></i>
                <input type="text" name="username" placeholder="请输入用户名"
                		id="username"
                       autocomplete="off"
                       class="layui-input admin-input admin-input-username">
            </div>
            <div>
                <i class="layui-icon layui-icon-password admin-icon admin-icon-password"></i>
                <input type="password" name="password" id="password"
                       placeholder="请输入密码"
                       autocomplete="off"
                       class="layui-input admin-input">
            </div> 
            <div>
                <input type="text" name="code"
                		id="code"
                       placeholder="请输入验证码"
                       autocomplete="off"
                       class="layui-input admin-input admin-input-verify">
                <img class="admin-captcha codeimg" id="codeimg" 
                	src="${pageContext.request.contextPath}/img/code.do"
                     onclick="updateVerify()">
            </div> 
            <a class="layui-btn a-button"  href="javascript:void(0);" onclick="login();" >登陆</a>
        </form>
    </div>
</div>
<script src="layui/layui.js"></script>
<script src="images/jquery.js"></script>
<script>
	//用户登陆方法login();
	function login(){
		var username=$("#username").val();
		var password=$("#password").val();
		var code=$("#code").val();
		if(username==null || username==""){//判断用户名是否输入
			alert("用户名不能为空");
			return false;
		}
		if(password==null || password==""){//判断密码是否输入
			alert("密码不能为空");
			return false;
		}
		if(code==null || code==""){//判断验证码是否输入
			alert("验证码不能为空");
			return false;
		}
		$.ajax({
			url:"${pageContext.request.contextPath}/login.do",
			type:"POST",
			data:{"username":username,"password":password,"code":code},
			dataType:"json",
			success:function(data){
				if(data=="3"){
					//layui弹出提示框
					layui.use(['layer', 'form'], function(){
						  layer.open({
							  title: '提示信息'
							  ,content: '验证码错误，请重新输入！'
							});     
						});
					//更新验证码
					updateVerify();
					return false;
				}else if(data=="2"){
					//layui弹出提示框
					layui.use(['layer', 'form'], function(){
						  layer.open({
							  title: '提示信息'
							  ,content: '您输入的账号或者密码错误！'
							});     
						});
					//更新验证码
					updateVerify();
					return false;					
				}else if(data=="4"){
					//layui弹出提示框
					layui.use(['layer', 'form'], function(){
						  layer.open({
							  title: '提示信息'
							  ,content: '您账号已经停用！！！'
							});     
						});
					//更新验证码
					updateVerify();
					return false;					
				}
				window.location.href="${pageContext.request.contextPath}/user/returnIndex.do";
			}
		})
	}
	//切换验证码
	function updateVerify() {
        var imgSrc = $("#codeimg");
        var src = imgSrc.attr("src");
        imgSrc.attr("src", chgUrl(src));
    }  
	//加入时间戳，去缓存机制   
    function chgUrl(url) {
		//获取时间戳
        var timestamp = (new Date()).valueOf();
        if ((url.indexOf("&")) >= 0) {
            url = url + "&timestamp=" + timestamp;
        } else {
            url = url + "?timestamp=" + timestamp;
        }
        return url;
    }
</script>
</body>
</html>



