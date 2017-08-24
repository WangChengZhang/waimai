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
<style type="text/css">
.panel .panel>.panel-heading:hover {
	cursor: pointer;
}
</style>
<script type="text/javascript">
	$(function() {

		$(".products>.panel-body").load("${proPath}/checkout/load", function(responseText, textStatus, XMLHttpRequest) {
			/*
			alert(responseText); //请求返回的内容
			alert(textStatus); //请求状态：success，error
			alert(XMLHttpRequest); //XMLHttpRequest对象
			*/
		});

		$(".address>.panel-body").load("${proPath}/address/load", function(responseText, textStatus, XMLHttpRequest) {
			/*
			alert(responseText); //请求返回的内容
			alert(textStatus); //请求状态：success，error
			alert(XMLHttpRequest); //XMLHttpRequest对象
			*/
		});

		$("#addaddress").load("${proPath}/address/addresssave", function(responseText, textStatus, XMLHttpRequest) {
			/*
			alert(responseText); //请求返回的内容
			alert(textStatus); //请求状态：success，error
			alert(XMLHttpRequest); //XMLHttpRequest对象
			*/
		});

		$(".confirm-order").click(function() {
			if ($(".products input:radio:checked").length > 0) {
				if ($(".address .panel-title input:radio:checked").length > 0) {
					$(location).attr('href', '${proPath}/checkout/orderconfirm?shopid=' + $(".products input:radio:checked").val() + '&custaddid=' + $(".address .panel-title input:radio:checked").val());
				} else {
					$(".order-alert").remove();
					$(this).before('<div class="alert alert-danger order-alert" role="alert">请选择收货地址！</div>');
				}
			} else {
				$(".order-alert").remove();
				$(this).before('<div class="alert alert-danger order-alert" role="alert">请选择商家！</div>');
			}
		});

	});
</script>
</head>
<body style="padding-top: 50px">
	<%@include file="/WEB-INF/commonjspf/navigator.jspf"%>
	<div class="jumbotron">
		<ol class="breadcrumb">
			<li class="active">选择商品</li>
			<li style="font-size:30px" class="text-warning">确认订单</li>
			<li class="active">生成订单</li>
		</ol>
	</div>
	<div class="container">
		<div class="row">
			<c:choose>
				<c:when test="${(not empty basket.count)&&basket.count>0}">
					<div class="col-md-6">
						<div class="panel panel-info products">
							<div class="panel-heading">
								<h3 class="panel-title">
									<p>请选择一个商家的商品</p>
								</h3>
							</div>
							<div class="panel-body"></div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="panel panel-info address">
							<div class="panel-heading">
								<h3 class="panel-title">
									<div class="container-fluid">
										<div class="row">
											<div class="col-md-6">
												<p>选择地址</p>
											</div>
											<div class="col-md-6">
												<p class="text-right">
													<button type="button"
														class="btn btn-primary btn-sm addaddress"
														data-toggle="collapse" data-target="#addaddress">添加新地址</button>
												</p>
											</div>
										</div>
									</div>
								</h3>
							</div>
							<div class="collapse" id="addaddress"></div>
							<div class="panel-body"></div>
						</div>
						<div class="panel panel-primary">
							<div class="panel-body">
								<button type="button"
									class="btn btn-lg btn-danger confirm-order">确认下单</button>
							</div>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="well well-lg">
						<p class="text-center">
							您的购物车是空，请先去<a href="${proPath}/place">下单</a>吧
						</p>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<%@include file="/WEB-INF/commonjspf/footer.jspf"%>
</body>
</html>