<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>我的地址-外卖网-网上订餐平台</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="订餐网上订餐,外卖,快餐外卖,外卖网">
<meta http-equiv="description" content="网上订餐平台">
<%@include file="/WEB-INF/commonjspf/common.jspf"%>
<link href="${proPath}/resource/css/aside.css" rel="stylesheet"
	type="text/css">
<link href="${proPath}/resource/css/place.css" rel="stylesheet"
	type="text/css">
</head>

<body>
	<%@include file="/WEB-INF/commonjspf/navigator.jspf"%>
	<%@include file="/WEB-INF/commonjspf/aside.jspf"%>
	<div class="container place">
		<div class="row current-poi">
			<div class="col-md-offset-1 col-lg-offset-1">
				<div class="dropdown">
					<button class="btn btn-default dropdown-toggle" type="button"
						id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="true">
						切换地址<span class="caret"></span>
					</button>
					<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
						<c:forEach items="${pois}" var="poi">
							<li><a href="${proPath}/place?poiid=${poi.id}">${poi.name}</a></li>
						</c:forEach>
						<li role="separator" class="divider"></li>
						<li><a href="${proPath}/home"><span
								class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>修改收货地址</a></li>
					</ul>
					当前位置:${currentPoi.address},${currentPoi.name}
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-1 col-lg-1 text-center">商家分类:</div>
			<div class="col-md-10 col-lg-10">
				<ul class="nav nav-tabs">
					<c:choose>
						<c:when test="${empty place.currentShopCat}">
							<li role="presentation" class="active"><a
								href="javascript:void(0);">全部</a></li>
						</c:when>
						<c:otherwise>
							<li role="presentation"><a
								href="${proPath}/place?tag=${place.currentShopTag.tagid}">全部</a></li>
						</c:otherwise>
					</c:choose>
					<c:forEach items="${place.shopCatList}" var="shopcat">
						<c:choose>
							<c:when
								test="${shopcat.shopcatid==place.currentShopCat.shopcatid}">
								<li role="presentation" class="active"><a
									href="javascript:void(0);">${shopcat.name}</a></li>
							</c:when>
							<c:otherwise>
								<li role="presentation"><a
									href="${proPath}/place?cat=${shopcat.shopcatid}&tag=${place.currentShopTag.tagid}">${shopcat.name}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</div>
			<div class="col-md-offset-1 col-md-10 col-lg-offset-1 col-lg-10">
				<ul class="nav nav-pills">
					<c:choose>
						<c:when test="${empty place.currentShopTag}">
							<li role="presentation" class="active"><a
								href="javascript:void(0);">全部</a></li>
						</c:when>
						<c:otherwise>
							<li role="presentation"><a
								href="${proPath}/place?cat=${place.currentShopCat.shopcatid}">全部</a></li>
						</c:otherwise>
					</c:choose>
					<c:forEach items="${place.shopTagList}" var="shoptag">
						<c:choose>
							<c:when test="${shoptag.tagid==place.currentShopTag.tagid}">
								<li role="presentation" class="active"><a
									href="javascript:void(0);">${shoptag.name}</a></li>
							</c:when>
							<c:otherwise>
								<li role="presentation"><a
									href="${proPath}/place?cat=${place.currentShopCat.shopcatid}&tag=${shoptag.tagid}">${shoptag.name}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="row shops">
			<c:choose>
				<c:when test="${empty place.shopList}">
					<div class="jumbotron">
						<p class="text-center">附近没有找到符合条件的商家，换个筛选条件试试吧</p>
					</div>
				</c:when>
				<c:otherwise>
					<c:forEach items="${place.shopList}" var="shopli">
						<div class="col-md-4 col-lg-4">
							<div
								class="media <c:if test='${shopli.shop.fkShopStatesShopState==2}'>close</c:if>">
								<a href="${proPath}/shop?shopid=${shopli.shop.shopid}">
									<div class="media-left media-middle">
										<img class="media-object"
											src="${proPath}/image?fileid=${shopli.shop.fkFilesIcon}" alt="...">
									</div>
									<div class="media-body">
										<h4>${shopli.shop.name}</h4>
										<div class="progress">
											<div
												class="progress-bar progress-bar-warning progress-bar-striped active"
												role="progressbar" aria-valuenow="60" aria-valuemin="0"
												aria-valuemax="100" style="width: ${shopli.scorePercent}%">${shopli.shop.dishScore}分</div>
										</div>
										<p>月售${shopli.shop.monthlySale}单</p>
										<p>配送费${shopli.shop.deliveryPrice}元</p>
										<p>距离${shopli.distance}公里</p>
										<c:if test='${shopli.shop.fkShopStatesShopState==2}'>
											<p>商家休息中</p>
										</c:if>
									</div>
								</a>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<%@include file="/WEB-INF/commonjspf/footer.jspf"%>
</body>
</html>
