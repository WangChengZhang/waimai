<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>

<title>确认订单-外卖网-网上订餐平台</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="订餐网上订餐,外卖,快餐外卖,外卖网">
<meta http-equiv="description" content="网上订餐平台">
<%@include file="/WEB-INF/commonjspf/common.jspf"%>
</head>
<body style="padding-top: 80px">
	<%@include file="/WEB-INF/commonjspf/navigator.jspf"%>
	<c:choose>
		<c:when test="${result eq 'shopnull'}">
			<div class="container">
				<div class="jumbotron">
					<h1 class="text-danger">未选择商家!</h1>
					<p>请重新下单！</p>
					<p>
						<a class="btn btn-primary btn-lg" href="${proPath}/checkout"
							role="button">重新下单</a>
					</p>
				</div>
			</div>
		</c:when>
		<c:when test="${result eq 'addressnull'}">
			<div class="container">
				<div class="jumbotron">
					<h1 class="text-danger">未选择地址!</h1>
					<p>请重新下单！</p>
					<p>
						<a class="btn btn-primary btn-lg" href="${proPath}/checkout"
							role="button">重新下单</a>
					</p>
				</div>
			</div>
		</c:when>
		<c:when test="${result eq 'success'}">
			<div class="container">
				<div class="jumbotron">
					<h1 class="text-success">订单生成成功!</h1>
					<p>
						<a class="btn btn-primary btn-lg" href="${proPath}/profile/order"
							role="button">查看订单</a> <a class="btn btn-primary btn-lg"
							href="${proPath}/place" role="button">主页</a>
					</p>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="container">
				<div class="jumbotron">
					<h1 class="text-danger">订单生成失败!</h1>
					<p>请重新下单！</p>
					<p>
						<a class="btn btn-primary btn-lg" href="${proPath}/checkout"
							role="button">重新下单</a>
					</p>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	<%@include file="/WEB-INF/commonjspf/footer.jspf"%>
</body>
</html>