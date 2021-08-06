<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2021/5/30
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
    <a href="orderServlet?action=showMyOrders">我的订单</a>
    <a href="http://localhost:8080/BookProject2/userServlet?action=logout">注销</a>&nbsp;
    <a href="index.jsp">返回</a>
</div>
