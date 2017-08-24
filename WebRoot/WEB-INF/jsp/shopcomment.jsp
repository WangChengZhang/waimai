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
							<li><a href="${proPath}/shop?shopid=${shop.shopid}">所有商品</a></li>
							<li class="active"><a href="javascript:void(0);">评价</a></li>
						</ul>
					</div>
				</nav>
				<div class="panel panel-default product-list">
					<div class="panel-body">
						<c:choose>
							<c:when test="${empty comments.results}">
								<div class="jumbotron">
									<p class="text-center">暂无评论</p>
								</div>
							</c:when>
							<c:otherwise>
								<ul class="media-list">
									<c:forEach items="${comments.results}" var="comment">
										<li class="media">
											<div class="media-left">
												<img class="media-object img-thumbnail"
													src="${proPath}/image?fileid=${comment.user.fkFilesIcon}"
													alt="..." width="60" height="60">
											</div>
											<div class="media-body">
												<h5 class="media-heading text-muted">${comment.user.name}:</h5>
												${comment.comment}
												<p class="text-success">菜品评分：${comment.dishScore}，态度评分：${comment.attitudeScore}</p>
											</div>
										</li>
									</c:forEach>
								</ul>
								<c:if test="${comments.totalpages>1}">
									<nav aria-label="Page navigation">
										<ul class="pagination">
											<c:forEach varStatus="i" begin="1"
												end="${comments.totalpages}" step="1">
												<c:choose>
													<c:when test="${comments.currentPage==i.count}">
														<li class="active"><a href="javascript:void(0);">${i.count}</a></li>
													</c:when>
													<c:otherwise>
														<li><a
															href="${proPath}/shop/comment?shopid=${shop.shopid}&page=${i.count}">${i.count}</a></li>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</ul>
									</nav>
								</c:if>
							</c:otherwise>
						</c:choose>
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
	<div class="animate-icon"></div>
	<%@include file="/WEB-INF/commonjspf/footer.jspf"%>
</body>
</html>