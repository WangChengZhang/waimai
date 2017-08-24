<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="proPath" value="${pageContext.request.contextPath}" />
<c:choose>
	<c:when test="${empty addresses}">
		<div class="well">
			<button type="button" class="btn btn-primary btn-lg addaddress"
				data-toggle="collapse" data-target="#addaddress">添加新地址</button>
		</div>
	</c:when>
	<c:otherwise>
		<c:forEach items="${addresses}" var="address">

			<div class="panel panel-default singleaddress">
				<div class="panel-heading">
					<h3 class="panel-title">
						<input type="radio" name="address" value="${address.custaddid}">
						${address.name}
						<c:choose>
							<c:when test="${address.gender==1}">先生</c:when>
							<c:otherwise>女士</c:otherwise>
						</c:choose>
						${address.phone}
					</h3>
				</div>
				<div class="panel-body">
					<span class="glyphicon glyphicon-map-marker"></span>
					${address.poi.address},${address.poi.name},${address.address}
				</div>
			</div>

		</c:forEach>
	</c:otherwise>
</c:choose>

<script type="text/javascript">
	$(function() {
		$(".singleaddress input:radio").on("click", function() {
			$(".singleaddress").removeClass("panel-default").removeClass("panel-primary").addClass("panel-default");
			$(this).parent().parent().parent().removeClass("panel-default").removeClass("panel-primary").addClass("panel-primary");
		});
	});
</script>