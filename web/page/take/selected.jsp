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
                if(confirm("确定要退课？"))
                {
                    window.location.href="<%=basePath%>/take?method=untake&cID="+$(this).attr("keyword");
                }
            });
        });
    </script>
</head>
<body>
<table class="tablelist">
    <thead>
    <tr>
        <th>课程ID</th>
        <th>课程名</th>
        <th>老师名</th>
        <th>成绩</th>
        <th width="120px">操作</th>
    </tr>
    </thead>
    <c:forEach items="${list}" var="sc">
        <tr>
            <td>${sc.cID}</td>
            <td>${sc.cName}</td>
            <td>${sc.tName}</td>
            <td>${sc.grade}</td>
            <td>
                <button class="remove" type="button" keyword="${sc.cID}">退课</button>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
