<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<title>404</title>
<meta charset="utf-8">

<!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script>

</head>

<body>
	<div class="jumbotron">
		<h1 class="text-center">404</h1>
		<p class="text-center">页面不存在</p>
		<p class="text-center">
			<a class="btn btn-primary btn-lg"
				href="${pageContext.request.contextPath}/place" role="button">主页</a>
		</p>
	</div>
	<div class="jumbotron">
		<p class="text-center">&copy;章望成</p>
	</div>
</body>
</html>
