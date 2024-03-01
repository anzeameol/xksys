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
  <script src="<%=basePath%>/js/jquery-validation-1.19.5/lib/jquery.js"></script>
  <script src="<%=basePath%>/js/jquery-validation-1.19.5/dist/jquery.validate.js" type="text/javascript"></script>
  <script src="<%=basePath%>/js/jquery-validation-1.19.5/dist/localization/messages_zh.js" type="text/javascript"></script>
  <script type="text/javascript">
    $().ready(function() {
      $("#editForm").validate({
        rules:{
          adminPwd: {
            required:true,
            length:[6,25]
          }
        }
      });
    });
  </script>
</head>
<body>
<div class="add"></div>
<form id="editForm" action="<%=basePath%>/admin?method=passwordsubmit" method="post">
  <input type="hidden" name="adminID" value="${admin.adminID}">
  <table class="tableadd">
    <tr>
      <td>请输入新密码：</td>
      <td style="color: #FF253A"><input type="text" name="adminPwd"></td>
    </tr>
    <tr>
      <td colspan="4" align="left">
        <button class="return" type="button" onclick="window.history.back();">返回</button>
        <button class="save" type="submit">提交</button>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
