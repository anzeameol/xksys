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
		<title>add</title>
		<link rel="stylesheet" href="<%=basePath%>/css/style.css" />
		<script src="<%=basePath%>/js/jquery.min.js" type="text/javascript"></script>
	</head>
	<body>
		<div class="add"></div>
		<table class="tableadd">
			<tr>
				<td>用户名</td>
				<td><input type="text"></td>
				<td>用户名</td>
				<td><input type="text"></td>
				<td>用户名</td>
				<td><input type="text"></td>
				<td>用户名</td>
				<td><input type="text"></td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<button class="return">返回</button>
					<button class="save">提交</button>
				</td>
			</tr>
		</table>
	</body>
</html>
