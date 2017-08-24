<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>

<title>${shop.name}-外卖网-网上订餐平台</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="订餐网上订餐,外卖,快餐外卖,外卖网">
<meta http-equiv="description" content="网上订餐平台">
<%@include file="/WEB-INF/commonjspf/common.jspf"%>
<link href="${proPath}/resource/css/aside.css" rel="stylesheet"
	type="text/css">
<style type="text/css">
body {
	position: relative;
}

.jumbotron.shop {
	background-image: -webkit-linear-gradient(180deg, rgba(55, 29, 0, 1.00)
		0%, rgba(104, 78, 0, 1.00) 54.40%, rgba(52, 55, 0, 1.00) 100%);
	background-image: -moz-linear-gradient(180deg, rgba(55, 29, 0, 1.00) 0%,
		rgba(104, 78, 0, 1.00) 54.40%, rgba(52, 55, 0, 1.00) 100%);
	background-image: -o-linear-gradient(180deg, rgba(55, 29, 0, 1.00) 0%,
		rgba(104, 78, 0, 1.00) 54.40%, rgba(52, 55, 0, 1.00) 100%);
	background-image: linear-gradient(270deg, rgba(55, 29, 0, 1.00) 0%,
		rgba(104, 78, 0, 1.00) 54.40%, rgba(52, 55, 0, 1.00) 100%);
	color: #FFFFFF;
}

.container .row .col-sm-6 {
	position: relative;
}

.row .col-sm-6 .shop-information {
	overflow-y: hidden;
	position: absolute;
	left: 0px;
	width: 100%;
	background-color: #544500;
	z-index: 1;
}

.jumbotron .col-sm-6 img {
	border-radius: 50px;
	border: 3px solid #BCA85B;
	overflow-x: hidden;
	overflow-y: hidden;
}

.products {
	margin-left: 150px;
	margin-right: 100px;
}

.cat {
	background-color: #F7DBB1;
}

.cat.nav-stacked {
	position: fixed;
	top: 80px;
	left: 5px;
}

.products .panel {
	background-color: #F8F8F8;
}

.products .panel .media-object-default {
	background-color: #FFFFFF;
	padding-top: 5px;
	height: 180px;
	margin-bottom: 20px;
}

.back-to-top {
	position: fixed;
	right: 100px;
	bottom: 50px;
	background-color: #F8F8F8;
}

.animate-icon {
	width: 50px;
	height: 50px;
	position: fixed;
	top: -50px;
	border-radius: 25px;
	background-color: #FF0000;
}
</style>
<script type="text/javascript">
	$(function() {
		$(".shop-information").slideUp(0);

		$(".thisshop").hover(function() {
			$(".shop-information").slideDown("fast");
		}, function() {
			$(".shop-information").slideUp(0);
		});

		//滚动监听
		$(window).scroll(function() {
			if ($("#cats .active").length > 0) {
				$(".cat").addClass("nav-stacked");
			} else {
				$(".cat").removeClass("nav-stacked");
			}
		});

		// 滚动窗口来判断按钮显示或隐藏
		$('.back-to-top').fadeOut(0);

		$(window).scroll(function() {
			if ($(this).scrollTop() > 150) {
				$('.back-to-top').fadeIn(100);
			} else {
				$('.back-to-top').fadeOut(100);
			}
		});

		$('.back-to-top').click(function(e) {
			if ($('html').scrollTop()) {
				$('html').animate({
					scrollTop : 0
				}, 500);
				return false;
			}
			$('body').animate({
				scrollTop : 0
			}, 500);
			return false;
		});

		//显示格式
		$(".glth").click(function() {
			$(".single-product").removeClass("col-sm-6").removeClass("col-sm-12");
			$(".single-product").addClass("col-sm-6");
			$(this).removeClass("active");
			$(".glth-list").removeClass("active");
			$(this).addClass("active");
		});

		$(".glth-list").click(function() {
			$(".single-product").removeClass("col-sm-6").removeClass("col-sm-12");
			$(".single-product").addClass("col-sm-12");
			$(this).removeClass("active");
			$(".glth").removeClass("active");
			$(this).addClass("active");
		});

		//购物车动画与加入购物车
		$(".addbasket").click(function(e) {
			//相对浏览器的原点的坐标 
			var xx = e.originalEvent.x || e.originalEvent.layerX;
			var yy = e.originalEvent.y || e.originalEvent.layerY;
			$(".animate-icon").css({
				"left" : xx + "px",
				"top" : yy + "px"
			});
			$(".animate-icon").animate({
				"left" : "2000px",
				"top" : "150px",
			}, 600, "swing");

			$.ajax({
				url : "${proPath}/basket/addproduct",
				cache : false,
				dataType : "text",
				data : "productid=" + $(this).next().val(),
				success : function() {
					$(".basket").load("${proPath}/basket/load");
				}
			});
		});


	});
</script>
</head>
<body data-spy="scroll" data-target="#cats" data-offset="70"
	style="padding-top: 50px">
	<%@include file="/WEB-INF/commonjspf/navigator.jspf"%>
	<%@include file="/WEB-INF/commonjspf/aside.jspf"%>
	<div class="jumbotron shop">
		<div class="container">
			<div class="row">
				<div class="col-sm-6 thisshop">
					<div class="media">
						<div class="media-left">
							<img class="media-object"
								src="${proPath}/image?fileid=${shop.fkFilesIcon}" alt="..."
								width="100" height="100">
						</div>
						<div class="media-body">
							<h2 class="media-heading">
								${shop.name}
								<c:if test="${shop.fkShopStatesShopState==2}">
									<span class="badge">商家休息</span>
								</c:if>
							</h2>
							<div class="progress">
								<div
									class="progress-bar progress-bar-warning progress-bar-striped active"
									style="width: ${shop.dishScorePercent}%">${shop.dishScore}分</div>
							</div>
							<h5>月售${shop.monthlySale}单</h5>
						</div>
					</div>
					<div class="shop-information">
						<div class="container">
							<div class="row">
								<div class="col-md-6">
									<h5>菜品评分</h5>
									<div class="progress">
										<div
											class="progress-bar progress-bar-warning progress-bar-striped active"
											style="width: ${shop.dishScorePercent}%">${shop.dishScore}分</div>
									</div>
									<h5>态度评分</h5>
									<div class="progress">
										<div
											class="progress-bar progress-bar-warning progress-bar-striped active"
											style="width: ${shop.attScorePercent}%">${shop.attitudeScore}分</div>
									</div>
								</div>
								<div class="col-md-6">
									<h5>商家地址：${shop.address}</h5>
									<h5>营业时间：${shop.openTime }-${shop.closeTime}</h5>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-2">
					<p class="text-center">起送价${shop.basePrice}元</p>
				</div>
				<div class="col-sm-2">
					<p class="text-center">配送费${shop.deliveryPrice}元</p>
				</div>
				<div class="col-sm-2">
					<p class="text-center">距离${shop.distance}公里</p>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid products">
		<div class="row">
			<div class="col-xs-9">
				<nav class="navbar navbar-default">
					<div class="container-fluid">
						<ul class="nav navbar-nav">
							<li class="active"><a href="javascript:void(0);">所有商品</a></li>
							<li><a href="${proPath}/shop/comment?shopid=${shop.shopid}">评价</a></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="${proPath}/shop?shopid=${shop.shopid}">默认排序</a></li>
							<li><a href="${proPath}/shop/bysales?shopid=${shop.shopid}">按销量</a></li>
							<li class="active"><a href="javascript:void(0);">按评价</a></li>
							<li><a href="${proPath}/shop/byprice?shopid=${shop.shopid}">按价格</a></li>
							<li class="active glth"><a href="javascript:void(0);"><span
									class="glyphicon glyphicon-th"></span></a></li>
							<li class="glth-list"><a href="javascript:void(0);"><span
									class="glyphicon glyphicon-th-list"></span></a></li>
						</ul>
					</div>
				</nav>
				<div class="panel panel-default product-list">
					<div class="panel-body">
						<div class="container-fluid">
							<c:forEach items="${shop.productCats}" var="cat">
								<div class="row">
									<c:forEach items="${cat.products}" var="product">
										<div class="col-sm-6 single-product">
											<div class="media-object-default">
												<div class="media">
													<div class="media-left">
														<a href="javascript:void(0);" data-toggle="modal"
															data-target="#modal${product.productid}"><img
															class="media-object img-thumbnail"
															src="${proPath}/image?fileid=${product.fkFilesIcon}"
															alt="..." width="100" height="100"></a>
													</div>
													<div class="media-body">
														<h4 class="media-heading">${product.name}</h4>
														<p>${product.simpleDisc}</p>
														<div class="container-fluid">
															<div class="row">
																<div class="col-sm-6">
																	<div class="progress">
																		<div
																			class="progress-bar progress-bar-warning progress-bar-striped active"
																			style="width: ${product.dishScorePercent}%">${product.dishScore}分</div>
																	</div>
																	<h3 class="text-danger">&yen; ${product.price}</h3>
																</div>
																<div class="col-sm-6">
																	<p>月售${product.monthlySale}</p>
																	<button type="button" class="btn btn-primary addbasket">加入购物车</button>
																	<input type="hidden" name="productid"
																		value="${product.productid}">
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>

								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-3">
				<div class="panel panel-primary">
					<div class="panel-heading">商家公告</div>
					<div class="panel-body">${shop.announcement}</div>
				</div>
			</div>
		</div>
	</div>
	<div class="back-to-top">
		<button type="button" class="btn btn-default btn-lg">
			<span class="glyphicon glyphicon-circle-arrow-up text-center"></span>
		</button>
	</div>

	<c:forEach items="${shop.productCats}" var="cat">
		<c:forEach items="${cat.products}" var="product">
			<!-- Modal -->
			<div class="modal fade" id="modal${product.productid}" tabindex="-1"
				role="dialog">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">${product.name}</h4>
						</div>
						<div class="modal-body">
							<div class="media">
								<div class="media-left">
									<img class="media-object"
										src="${proPath}/image?fileid=${empty product.fkFilesPicture ? product.fkFilesIcon : product.fkFilesPicture}"
										alt="..." width="200" height="200">
								</div>
								<div class="media-body">
									<h3 class="media-heading text-danger text-center">&yen;
										${product.price}</h3>
								</div>
							</div>
							<p>${product.description}</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary addbasket">加入购物车</button>
							<input type="hidden" name="productid"
								value="${product.productid}">
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</c:forEach>
	<div class="animate-icon"></div>
	<%@include file="/WEB-INF/commonjspf/footer.jspf"%>
</body>
</html>