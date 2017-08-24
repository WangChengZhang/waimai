<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>我的订单-外卖网-网上订餐平台</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="订餐网上订餐,外卖,快餐外卖,外卖网">
<meta http-equiv="description" content="网上订餐平台">
<%@include file="/WEB-INF/commonjspf/common.jspf"%>
<link href="${proPath}/resource/css/aside.css" rel="stylesheet"
	type="text/css">
</head>
<body style="padding-top: 80px">
	<%@include file="/WEB-INF/commonjspf/navigator.jspf"%>
	<%@include file="/WEB-INF/commonjspf/aside.jspf"%>
	<div class="container" style="min-height: 500px">
		<div class="row">
			<div class="col-xs-3">
				<ul class="nav nav-pills nav-stacked">
					<li role="presentation" class="active"><a
						href="javascript:void(0);">我的订单</a></li>
					<li role="presentation"><a href="${proPath}/profile/address">我的地址</a></li>
					<li role="presentation"><a href="${proPath}/profile">个人资料</a></li>
				</ul>
			</div>
			<div class="col-xs-9">
				<c:choose>
					<c:when test="${empty orders}">
						<div class="jumbotron">
							<p class="text-center">暂无订单</p>
						</div>
					</c:when>
					<c:otherwise>
						<c:forEach items="${orders}" var="order">
							<div class="panel panel-default">
								<div class="panel-heading">
									<div class="container-fluid">
										<div class="row">
											<div class="col-xs-3">
												<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
													value="${order.createTime}" type="both" />
											</div>
											<div class="col-xs-3">订单号：${order.orderid}</div>
											<div class="col-xs-3">
												商家：<a href="${proPath}/shop?shopid=${order.fkShopsShopid}">${order.shopname}</a>
											</div>
										</div>
									</div>
								</div>
								<div class="panel-body">
									<div class="container-fluid">
										<div class="row">
											<div class="col-md-6">
												<c:forEach items="${order.orderItems}" var="orderitem">
													<div class="media-object-default">
														<div class="media">
															<div class="media-left">
																<img class="media-object img-thumbnail"
																	src="${proPath}/image?fileid=${orderitem.product.fkFilesIcon}"
																	alt="..." width="100" height="100">
															</div>
															<div class="media-body">
																<p>${orderitem.product.name}</p>
																<p>单价:&yen;${orderitem.product.price}</p>
																<p>数量:${orderitem.number}</p>
															</div>
														</div>
													</div>
												</c:forEach>

											</div>
											<div class="col-md-6">
												<h3>
													合计：<span class="text-danger">&yen;${order.totalPrice}</span>
												</h3>
												<p>收货姓名：${order.custname}</p>
												<p>电话：${order.phone}</p>
												<p>收货地址：${order.address}</p>
											</div>
										</div>
									</div>
								</div>
								<div class="panel-footer">
									<div class="container-fluid">
										<div class="row">
											<div class="col-xs-6">
												<c:choose>
													<c:when test="${order.fkOrderStatesState eq '1'}">未收货</c:when>
													<c:when test="${order.fkOrderStatesState eq '2'}">已收货</c:when>
												</c:choose>
											</div>
											<div class="col-xs-6">
												<c:choose>
													<c:when test="${order.fkOrderStatesState eq '1'}">
														<button type="button" class="btn btn-primary"
															data-toggle="modal" data-target="#order${order.orderid}">评价订单</button>
													</c:when>
													<c:when test="${order.fkOrderStatesState eq '2'}">
														<button type="button" class="btn btn-primary"
															disabled="disabled">订单已经评价</button>
													</c:when>
												</c:choose>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<c:forEach items="${orders}" var="order">
		<c:if test="${order.fkOrderStatesState eq '1'}">
			<!-- Modal -->
			<div class="modal fade" id="order${order.orderid}" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">评价订单号：${order.orderid}</h4>
						</div>
						<div class="modal-body">
							<form>
								<div class="form-group">
									<label>评论：</label>
									<textarea class="form-control comment" rows="5" name="comment"
										maxlength="100" placeholder="最多100字"></textarea>
								</div>
								<div class="form-group">
									<label>态度评分：</label> <label class="radio-inline"> <input
										type="radio" name="attitudescore" class="attitudescore"
										value="0"> 0分
									</label> <label class="radio-inline"> <input type="radio"
										name="attitudescore" class="attitudescore" value="1">
										1分
									</label> <label class="radio-inline"> <input type="radio"
										name="attitudescore" class="attitudescore" value="2">
										2分
									</label> <label class="radio-inline"> <input type="radio"
										name="attitudescore" class="attitudescore" value="3">
										3分
									</label> <label class="radio-inline"> <input type="radio"
										name="attitudescore" class="attitudescore" value="4">
										4分
									</label> <label class="radio-inline"> <input type="radio"
										name="attitudescore" class="attitudescore" value="5">
										5分
									</label>
								</div>
								<div class="form-group">
									<label>菜品评分：</label> <label class="radio-inline"> <input
										type="radio" name="dishscore" class="dishscore" value="0">
										0分
									</label> <label class="radio-inline"> <input type="radio"
										name="dishscore" class="dishscore" value="1"> 1分
									</label> <label class="radio-inline"> <input type="radio"
										name="dishscore" class="dishscore" value="2"> 2分
									</label> <label class="radio-inline"> <input type="radio"
										name="dishscore" class="dishscore" value="3"> 3分
									</label> <label class="radio-inline"> <input type="radio"
										name="dishscore" class="dishscore" value="4"> 4分
									</label> <label class="radio-inline"> <input type="radio"
										name="dishscore" class="dishscore" value="5"> 5分
									</label>
								</div>
							</form>
							<input type="hidden" name="orderid" class="orderid"
								value="${order.orderid}">
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary docomment">评价</button>
						</div>
					</div>
				</div>
			</div>
		</c:if>
	</c:forEach>
	<script type="text/javascript">
		$(function() {
			$(".docomment").click(function() {
				if ($(this).parent().parent().find(".comment").val().length > 0) {
					if ($(this).parent().parent().find(".attitudescore:checked").val() != null && $(this).parent().parent().find(".dishscore:checked").val() != null) {
						var param = {
							orderid : $(this).parent().parent().find(".orderid").val(),
							comment : $(this).parent().parent().find(".comment").val(),
							attitudescore : $(this).parent().parent().find(".attitudescore:checked").val(),
							dishscore : $(this).parent().parent().find(".dishscore:checked").val()
						};
						$.post("${proPath}/profile/comment", param,
							function(json) {
								if (json.result == "success") {
									alert("评论成功");
									location.reload();
								}
							}, "json");
					} else {
						alert("请选择评分！");
					}
				} else {
					alert("请填写评论！");
				}
			});
		});
	</script>
	<%@include file="/WEB-INF/commonjspf/footer.jspf"%>
</body>
</html>