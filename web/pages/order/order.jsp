<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="orderServlet?action=showMyOrders">我的订单</span>
		<%@include file="/pages/common/login_succes_meu.jsp"%>
	</div>
	
	<div id="main">
		
		<table>

			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>

				<td>操作</td>

			</tr>
          <c:forEach items="${requestScope.order}" var="entry">
			<tr>
				<td>${entry.create_Time}</td>
				<td>${entry.price}</td>
				<c:if test="${entry. status==0}">
				<td>未发货</td>
				</c:if>

				<c:if test="${entry. status==1}">
					<td>已发货</td>
				</c:if>
				<c:if test="${entry. status==2}">
					<td>已签收</td>
				</c:if>
				<td><a href="orderServlet?action=showOrderDetail&id=${entry.order_Id}">查看详情</a></td>
				<c:if test="${entry. status==1}">
				<td><a href="orderServlet?action=receiverOrder&id=${entry.order_Id}">签收</a></td>
				</c:if>
			</tr>

			</c:forEach>
			

		</table>
		
	
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>