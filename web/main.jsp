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
		<title>首页</title>
		<style type="text/css">
			*{
				font-size: 50px;
				color: #00aa00;
				text-align: center;
			}
		</style>
	</head>
	<body>
	欢迎使用上海脚痛大学选课系统！
	</body>
</html>
