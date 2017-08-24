<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="proPath" value="${pageContext.request.contextPath}" />
<c:choose>
	<c:when test="${(not empty basket.count)&&basket.count>0}">
		<div class="container" style="max-width:300px">
			<c:forEach items="${basket.productList}" var="product">
				<div class="row">
					<div class="col-xs-4 col-md-4 col-lg-4">${product.name}</div>
					<div class="col-xs-5 col-md-5 col-lg-5">
						<input type="hidden" class="productid"
							value="${product.productid}">
						<div class="input-group input-group-sm">
							<span class="input-group-btn">
								<button class="btn btn-default sub" type="button">-</button>
							</span> <input type="text" class="form-control product-count"
								value="${product.count}"> <span class="input-group-btn">
								<button class="btn btn-default add" type="button">+</button>
							</span>
						</div>
					</div>
					<div class="col-xs-3 col-md-3 col-lg-3">
						<span class="badge">${product.price}元</span>
					</div>
				</div>
			</c:forEach>
			<div class="row">
				共<span class="text-success"><strong>${basket.count}</strong></span>份，总计<span
					class="text-success"><strong>${basket.totalPrice}</strong></span>元
				<button type="button" class="btn btn-default btn-block clear-basket">清空购物车</button>
				<button type="button" class="btn btn-danger btn-block"
					onclick="javascript:window.location.href='${proPath}/checkout'">去结算</button>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="jumbotron">
			<p class="text-center">购物车为空</p>
		</div>
	</c:otherwise>
</c:choose>
<script type="text/javascript">
	$(function() {
		$.ajaxSetup({
			cache : false //关闭AJAX相应的缓存 
		});

		if ("${basket.count}") {
			$("aside h1 .badge").text("${basket.count}");
		} else {
			$("aside h1 .badge").text("0");
		}
		//点击物品增加按钮
		$("aside .basket .add").on("click", function() {
			$.ajax({
				url : "${proPath}/basket/add",
				cache : false,
				dataType : "text",
				data : "productid=" + $(this).parentsUntil(".row").children(".productid").val(),
				success : function() {
					$(".basket").load("${proPath}/basket/load");
				}
			});
		});

		//点击物品减少按钮
		$("aside .basket .sub").on("click", function() {
			$.ajax({
				url : "${proPath}/basket/sub",
				cache : false,
				dataType : "text",
				data : "productid=" + $(this).parentsUntil(".row").children(".productid").val(),
				success : function() {
					$(".basket").load("${proPath}/basket/load");
				}
			});
		});

		//手动输入商品数量
		$("aside .basket .product-count").on("change", function() {
			$.ajax({
				url : "${proPath}/basket/change",
				cache : false,
				dataType : "text",
				data : "productid=" + $(this).parentsUntil(".row").children(".productid").val() + "&count=" + $(this).val(),
				success : function() {
					$(".basket").load("${proPath}/basket/load");
				}
			});
		});

		//清空购物车
		$(".clear-basket").on("click", function() {
			$.ajax({
				url : "${proPath}/basket/clear",
				cache : false,
				success : function() {
					$(".basket").load("${proPath}/basket/load");
				}
			});
		});
	});
</script>
