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
                    window.location.href="<%=basePath%>/teacher?method=delete&tID="+$(this).attr("keyword");
                }
            });
        });
    </script>
</head>
<body>
<form action="<%=basePath%>/teacher?method=search" method="post">
<div class="condition">
    ID：<input type="text" name="tID">
    教工号：<input type="text" name="tNum">
    姓名：<input type="text" name="tName">
    <button type="submit">查询</button>
    <button type="button" onclick="window.location.href='<%=basePath%>/page/teacher/add.jsp'">新增</button>
</div>
</form>
<table class="tablelist">
    <thead>
    <tr>
        <th>ID</th>
        <th>教工号</th>
        <th>姓名</th>
        <th width="120px">操作</th>
    </tr>
    </thead>
    <c:forEach items="${list}" var="teacher">
    <tr>
        <td>${teacher.tID}</td>
        <td>${teacher.tNum}</td>
        <td>${teacher.tName}</td>
        <td>
            <button class="edit" type="button" onclick="window.location.href='<%=basePath%>/teacher?method=edit&tID=${teacher.tID}'">修改</button>
            <button class="remove" type="button" keyword="${teacher.tID}">删除</button>
        </td>
    </tr>
    </c:forEach>
</table>
<table class="page">
    <td>
        <button type="button" onclick="window.location.href='<%=basePath%>/teacher?method=firstpage'">首页</button>
        <button type="button" onclick="window.location.href='<%=basePath%>/teacher?method=lastpage'">上一页</button>
        <button type="button" onclick="window.location.href='<%=basePath%>/teacher?method=nextpage'">下一页</button>
        共${totRecord}条记录，每页${pageSize}条记录，当前第${curPage}/${totPage}页
    </td>
</table>
</body>
</html>
