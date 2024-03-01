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
                    window.location.href="<%=basePath%>/student?method=delete&stuID="+$(this).attr("keyword");
                }
            });
        });
    </script>
</head>
<body>
<form action="<%=basePath%>/student?method=search" method="post">
<div class="condition">
    ID：<input type="text" name="stuID">
    学号：<input type="text" name="stuNum">
    姓名：<input type="text" name="stuName">
    <button type="submit">查询</button>
    <button type="button" onclick="window.location.href='<%=basePath%>/page/student/add.jsp'">新增</button>
</div>
</form>
<table class="tablelist">
    <thead>
    <tr>
        <th>ID</th>
        <th>学号</th>
        <th>姓名</th>
        <th width="120px">操作</th>
    </tr>
    </thead>
    <c:forEach items="${list}" var="student">
    <tr>
        <td>${student.stuID}</td>
        <td>${student.stuNum}</td>
        <td>${student.stuName}</td>
        <td>
            <button class="edit" type="button" onclick="window.location.href='<%=basePath%>/student?method=edit&stuID=${student.stuID}'">修改</button>
            <button class="remove" type="button" keyword="${student.stuID}">删除</button>
        </td>
    </tr>
    </c:forEach>
</table>
<table class="page">
    <td>
        <button type="button" onclick="window.location.href='<%=basePath%>/student?method=firstpage'">首页</button>
        <button type="button" onclick="window.location.href='<%=basePath%>/student?method=lastpage'">上一页</button>
        <button type="button" onclick="window.location.href='<%=basePath%>/student?method=nextpage'">下一页</button>
        共${totRecord}条记录，每页${pageSize}条记录，当前第${curPage}/${totPage}页
    </td>
</table>
</body>
</html>
