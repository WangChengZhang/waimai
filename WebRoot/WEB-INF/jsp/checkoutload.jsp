<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="proPath" value="${pageContext.request.contextPath}" />
<c:forEach items="${shops}" var="shop">
	<div class="panel panel-default singleshop">
		<div class="panel-heading">
			<h3 class="panel-title" value="${shop.totalprice}">
				<input type="radio" name="shopid" value="${shop.shopid}"> <span>${shop.name},配送费<span
					class="badge">${shop.deliveryPrice}元</span></span>
			</h3>
		</div>
		<div class="panel-body">
			<div class="container-fluid">
				<c:forEach items="${shop.products}" var="product">
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
			</div>
		</div>
	</div>
</c:forEach>

<div class="well">没有选择商家</div>
<script type="text/javascript">
	$(function() {
		$.ajaxSetup({
			cache : false //关闭AJAX相应的缓存 
		});

		if (!($(".singleshop").length > 0)) {
			location.reload();
		}

		//点击物品增加按钮
		$(".products .add").on("click", function() {
			$.ajax({
				url : "${proPath}/basket/add",
				cache : false,
				dataType : "text",
				data : "productid=" + $(this).parentsUntil(".row").children(".productid").val(),
				success : function() {
					$(".products>.panel-body").load("${proPath}/checkout/load");
				}
			});
		});

		//点击物品减少按钮
		$(".products .sub").on("click", function() {
			$.ajax({
				url : "${proPath}/basket/sub",
				cache : false,
				dataType : "text",
				data : "productid=" + $(this).parentsUntil(".row").children(".productid").val(),
				success : function() {
					$(".products>.panel-body").load("${proPath}/checkout/load");
				}
			});
		});

		//手动输入商品数量
		$(".products .product-count").on("change", function() {
			$.ajax({
				url : "${proPath}/basket/change",
				cache : false,
				dataType : "text",
				data : "productid=" + $(this).parentsUntil(".row").children(".productid").val() + "&count=" + $(this).val(),
				success : function() {
					$(".products>.panel-body").load("${proPath}/checkout/load");
				}
			});
		});

		$(".singleshop input:radio").on("click", function() {
			$(".singleshop").removeClass("panel-default").removeClass("panel-primary").addClass("panel-default");
			$(this).parent().parent().parent().removeClass("panel-default").removeClass("panel-primary").addClass("panel-primary");
			$(".products .well").html('已经选择' + $(this).next().text() + '，合计<span style="font-size:30px" class="text-danger">&yen;' + $(this).parent().attr("value") + '</span>')
		});
	});
</script>