<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <td>类型：教师</td>
    </tr>
    <tr>
        <td>ID：${tID}</td>
    </tr>
    <tr>
        <td>教工号：${tNum}</td>
    </tr>
    <tr>
        <td>姓名：${tName}</td>
    </tr>
    <tr>
        <td colspan="4" align="center">
            <button class="edit" onclick="window.location.href='<%=basePath%>/teacher?method=passwordedit&tID=${tID}'">修改密码</button>
            <button class="return" type="button" onclick="window.location.href='<%=basePath%>/main.jsp'">返回</button>
        </td>
    </tr>
</table>
</body>
</html>
