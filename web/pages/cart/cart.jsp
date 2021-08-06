<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/pages/common/head.jsp"%>
    <script>
        $(function () {
        	//给删除绑定单击事件
            $("a.deleteItem").click(function () {
                confirm("你确定要删除"+$(this).parent().parent().find("td:first").text()+"吗?");

            });
			//给清空给购物车绑定单击事件
            $(".cart_span #clear").click(function () {
            	confirm("你确定要"+$(this).text())

			});
            //给修改数目绑定单击事件
            $(".updateCount").change(function () {

            //获取商品名称
				var name=$(this).parent().parent().find("td:first").text();
                //获取商品数量
                var count=this.value;




				//获取商品编号id
				var id=$(".updateCount").attr("boId");


                var flag=confirm("你确定要将【"+name+"】商品修改数量为: "+count+"吗?");
                if (flag){
                    location.href="http://localhost:8080/BookProject2/cartServlet?action=updateItem&count="+count+"&id="+id;

                }else {
                    //defaultValue表示表单项Dom对象默认的值
                    this.value=this.defaultValue;
                }



















			});

        });




    </script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%@include file="/pages/common/login_succes_meu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td style="text-align: right">操作</td>
			</tr>
			<c:if test="${not empty  requestScope.items}">
			<c:forEach items="${requestScope.items}" var="entry">



				<tr>
					<td>${entry.name}</td>
					<td>${entry.count}</td>
					<td>${entry.price}</td>
					<td>${entry.total_Price}</td>
					<td></td>

				</tr>

			</c:forEach>
				<td colspan="5" style="text-align: right"><a style="font-size: 18px" href="orderServlet?action=salesReturn&id=${requestScope.id}">退单</a> </td>
			</c:if>





           <c:if test="${empty  requestScope.items}">
            <c:if test="${ empty sessionScope.cart.map}">
                <tr>
                    <td colspan="5"><a href="index.jsp">亲, 当前购物车为空! 快跟小伙伴们去浏览商品吧!!!</a></td>

                </tr>


            </c:if>

            <c:if test="${not empty sessionScope.cart.map}">

                <c:forEach  items="${sessionScope.cart.map}" var="entry">

                    <tr>
                        <td>${entry.value.name}</td>
                        <td>
						<input class="updateCount" boId="${entry.value.id}" type="text" style="width: 80px;" value="${entry.value.count}">
						</td>
                        <td>${entry.value.price}</td>
                        <td>${entry.value.totalPrice}</td>
                        <td style="text-align: right"><a  class="deleteCount"  href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
                    </tr>
                </c:forEach>


            </c:if>


		</table>
		<c:if test="${not empty sessionScope.cart.map}">
			<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
			<span class="cart_span"><a  id="clear" href="cartServlet?action=clearItem">清空购物车</a></span>
			<span class="cart_span"><a    href="orderServlet?action=createOrder">去结账</a></span>
		</div>
		</c:if>

		</c:if>
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>