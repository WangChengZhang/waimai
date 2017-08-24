<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>登出-外卖网-网上订餐平台</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="订餐网上订餐,外卖,快餐外卖,外卖网">
<meta http-equiv="description" content="网上订餐平台">
<%@include file="/WEB-INF/commonjspf/common.jspf"%>
<style type="text/css">
body {
	font-family: "黑体";
}

.jumbotron.head {
	color: #FFFFFF;
	background-color: #0391E6;
}

.alert-success {
	margin-left: auto;
	margin-right: auto;
	margin-top: 100px;
	margin-bottom: 100px;
}
</style>
<script type="text/javascript">
	$(function() {
		setTimeout(function() {
			$(location).attr('href', "${proPath}/login");
		}, 3000);
	});
</script>
</head>
<body>
	<div class="jumbotron head">
		<h1 class="text-center">外卖网</h1>
		<p class="text-center">网上订餐 快餐外卖</p>
	</div>
	<div class="alert alert-success text-center" role="alert">您已登出，请重新登陆，3秒后跳转！</div>
	<%@include file="/WEB-INF/commonjspf/footer.jspf"%>
</body>
</html>
