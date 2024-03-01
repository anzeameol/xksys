<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=utf-8" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>评分</title>
  <link rel="stylesheet" href="<%=basePath%>/css/style.css"/>
  <script src="<%=basePath%>/js/jquery.min.js" type="text/javascript"></script>
</head>
<body>
<table class="tablelist">
  <thead>
  <tr>
    <th>课程ID</th>
    <th>课程名</th>
    <th>学生ID</th>
    <th>学生姓名</th>
    <th>成绩</th>
    <th width="120px">操作</th>
  </tr>
  </thead>
  <c:forEach items="${list}" var="sc">
    <tr>
      <td>${sc.cID}</td>
      <td>${sc.cName}</td>
      <td>${sc.stuID}</td>
      <td>${sc.stuName}</td>
      <td>${sc.grade}</td>
      <td>
        <button class="edit" type="button" onclick="window.location.href='<%=basePath%>/teacher?method=gradeedit&takeID=${sc.takeID}&tID=${sc.tID}'">修改</button>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
