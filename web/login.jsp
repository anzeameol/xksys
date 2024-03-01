<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>登录页面</title>
	<style type="text/css">
		*{
			margin: 0px;
			padding: 0px;
			font-family: "microsoft sans serif";
		}
		html,body{
			background-image: url(<%=basePath%>/img/login.jpg);
			background-size: 100%,100%;
			height: 100%;
		}
		.login{
			background-color: rgba(255,255,255,.8);
			position: absolute;
			top: 25%;
			bottom: 25%;
			left: 60%;
			right: 10%;
			border-radius: 5px;
		}
		.title,.username,.password,.loginbutton,.select,.tips{
			position: absolute;
			width: 100%;
		}
		input{
			height: 35px;
			background-color: #EEEEEE;
			width: 60%;
		}
		button{
			background-color: dodgerblue;
			height: 35px;
			width: 50%;
			color: #FFFFFF;
			font-size: 15px;
		}
		.title{
			top: 0%;
			bottom: 80%;
			text-align: center;
			font-size: 25px;
			font-weight: bold;
			padding-top: 10px;
			box-sizing: border-box;
		}
		.username{
			top: 20%;
			bottom: 60%;
			left: 10%;
		}
		.password{
			top: 40%;
			bottom: 40%;
			left: 10%;
		}
		.loginbutton{
			top: 60%;
			bottom: 20%;
			left: 25%;
		}
		.select{
			top: 80%;
			bottom: 10%;
			left: 35%;
		}
		select{
			width: 30%;
			height: 35px;
			border: 1px solid;
		}
		.tips{
			top: 90%;
			bottom: 0%;
			font-size: 12px;
			color: #FF253A;
			text-align: center;
			padding-top: 10px;
		}
	</style>
</head>
<body>
<div class="login">
	<div class="title">
		学生选课系统
	</div>
	<form action="login" method="post">
		<div class="username">
			账户：<input type="text" name="userName" />
		</div>
		<div class="password">
			密码：<input type="password" name="password" />
		</div>
		<div class="loginbutton">
			<button type="submit">登录</button>
		</div>
		<div class="select">
			<select name="type">
				<option value="">请选择登录类型</option>
				<option value="0">学生</option>
				<option value="1">老师</option>
				<option value="2">管理员</option>
			</select>
		</div>
		<div class="tips">
			${error}
		</div>
	</form>
</div>
</body>
</html>
