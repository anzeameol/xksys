<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>首页</title>
		<link rel="stylesheet" href="<%=basePath%>/css/style.css" />
		<script src="<%=basePath%>/js/jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
				$('.menux p').click(function(){
					$(this).next('ul').slideToggle(200);
				});
				$('.menux ul a').click(function(){
					$('iframe').attr("src",$(this).attr("url"));
					$('.menutitle').html($(this).attr("title"));
				});
				$('.user ul a').click(function(){
					$('iframe').attr("src",$(this).attr("url"));
					$('.menutitle').html($(this).attr("title"));
				});
				$('.logout').click(function (){
					if(window.confirm("确定要退出登录吗？"))
					{
						window.location.href = "logout";
					}
				});
			})
		</script>
	</head>
	<body>
		<div class="header">
			<div class="logo">xk.sjtu.edu.cn</div>
			<div class="user">
				${userName}
				<ul>
					<c:if test="${type==2}">
						<li><a href="javascript:void(0);" url="<%=basePath%>/admin?method=info&adminID=${userID}" title="个人信息">个人信息</a></li>
					</c:if>
					<c:if test="${type==1}">
						<li><a href="javascript:void(0);" url="<%=basePath%>/teacher?method=info&tID=${userID}" title="个人信息">个人信息</a></li>
					</c:if>
					<c:if test="${type==0}">
						<li><a href="javascript:void(0);" url="<%=basePath%>/student?method=info&stuID=${userID}" title="个人信息">个人信息</a></li>
					</c:if>
					<li><a href="javascript:void(0);" class="logout">退出登录</a></li>
				</ul>
			</div>
		</div>
		<div class="left">
			<div class="title">系统功能</div>
			<div class="menux">
				<c:if test="${type==2}">
				<p>管理员操作</p>
				<ul>
					<li>
						<a href="javascript:void(0);" url="<%=basePath%>/student?method=list&type=init" title="学生管理">学生管理</a>
					</li>
					
					<li>
						<a href="javascript:void(0);" url="<%=basePath%>/teacher?method=list&type=init" title="老师管理">老师管理</a>
					</li>
					<li>
						<a href="javascript:void(0)" url="<%=basePath%>/course?method=list&type=init" title="课程管理">课程管理</a>
					</li>
				</ul>
				</c:if>
				<c:if test="${type==1}">
				<p>老师操作</p>
				<ul>
					<li>
						<a href="javascript:void(0);" url="<%=basePath%>/teacher?method=grade&tID=${userID}" title="评分">评分</a>
					</li>
				</ul>
				</c:if>
				<c:if test="${type==0}">
				<p>学生操作</p>
				<ul>
					<li>
						<a href="javascript:void(0);" url="<%=basePath%>/take?method=list&type=init&stuID=${userID}" title="选课">选课</a>
					</li>
					
					<li>
						<a href="javascript:void(0);" url="<%=basePath%>/take?method=selected&stuID=${userID}" title="已选课程">已选课程</a>
					</li>
					<li>
						<a href="javascript:void(0);" url="<%=basePath%>/take?method=history&stuID=${userID}" title="历史选课">历史选课</a>
					</li>
				</ul>
				</c:if>
			</div>
		</div>
		<div class="main">
			<div class="location">
				<span class="menutitle">首页</span>
			</div>
			<iframe src="main.jsp" width="100%" height="100%"></iframe>
		</div>
	</body>
</html>
