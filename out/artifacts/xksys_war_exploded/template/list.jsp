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
		<title>列表</title>
		<link rel="stylesheet" href="<%=basePath%>/css/style.css" />
		<script src="<%=basePath%>/js/jquery.min.js" type="text/javascript"></script>
	</head>
	<body>
		<div class="condition">
			用户名：<input type="text">
			用户名：<input type="text">
			用户名：<input type="text">
			用户名：<input type="text">
			<button>查询</button>
			<button onclick="window.location.href='add.html'">新增</button>
		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th>1</th>
					<th>1</th>
					<th>1</th>
					<th>1</th>
					<th width="120px">操作</th>
				</tr>
			</thead>
			<tr>
				<td>2</td>
				<td>2</td>
				<td>2</td>
				<td>2</td>
				<td>
					<button class="edit">修改</button>
					<button class="remove">删除</button>
				</td>
			</tr>
		</table>
		<table class="page">
			<td>
				<button>首页</button>
				<button>上一页</button>
				<button>下一页</button>
				<button>尾页</button>
			</td>
		</table>
	</body>
</html>
