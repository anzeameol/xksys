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
    <title>列表</title>
    <link rel="stylesheet" href="<%=basePath%>/css/style.css"/>
    <script src="<%=basePath%>/js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function (){
            $('.remove').click(function (){
                if(confirm("确定要删除？"))
                {
                    window.location.href="<%=basePath%>/course?method=delete&cID="+$(this).attr("keyword");
                }
            });
        });
    </script>
</head>
<body>
<form action="<%=basePath%>/course?method=search" method="post">
<div class="condition">
    ID：<input type="text" name="cID">
    课程名：<input type="text" name="cName">
    老师ID：<input type="text" name="tID">
    <button type="submit">查询</button>
    <button type="button" onclick="window.location.href='<%=basePath%>/page/course/add.jsp'">新增</button>
</div>
</form>
<table class="tablelist">
    <thead>
    <tr>
        <th>ID</th>
        <th>课程名</th>
        <th>老师ID</th>
        <th width="120px">操作</th>
    </tr>
    </thead>
    <c:forEach items="${list}" var="course">
    <tr>
        <td>${course.cID}</td>
        <td>${course.cName}</td>
        <td>${course.tID}</td>
        <td>
            <button class="edit" type="button" onclick="window.location.href='<%=basePath%>/course?method=edit&cID=${course.cID}'">修改</button>
            <button class="remove" type="button" keyword="${course.cID}">删除</button>
        </td>
    </tr>
    </c:forEach>
</table>
<table class="page">
    <td>
        <button type="button" onclick="window.location.href='<%=basePath%>/course?method=firstpage'">首页</button>
        <button type="button" onclick="window.location.href='<%=basePath%>/course?method=lastpage'">上一页</button>
        <button type="button" onclick="window.location.href='<%=basePath%>/course?method=nextpage'">下一页</button>
        共${totRecord}条记录，每页${pageSize}条记录，当前第${curPage}/${totPage}页
    </td>
</table>
</body>
</html>
