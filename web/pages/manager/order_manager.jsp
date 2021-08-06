<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
		<%@include file="/pages/common/manger_meu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
				
			</tr>
			<c:forEach items="${requestScope.orders}" var="entry">
			<tr>
				<td>${entry.create_Time}</td>
				<td>${entry.price}</td>
				<td><a href="orderServlet?action=showOrderDetail&id=${entry.order_Id}">查看详情</a></td>
				<c:if test="${entry. status==0}">
					<td><a href="orderServlet?action=sendOrder&id=${entry.order_Id}">点击发货</a></td>
				</c:if>
				<c:if test="${entry. status==1}">
                  <td>已发货</td>
				</c:if>
				<c:if test="${entry. status==2}">
					<td>已签收</td>
				</c:if>
			</tr>
			</c:forEach>

			

		</table>
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>