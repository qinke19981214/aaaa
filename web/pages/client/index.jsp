<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
	<%@include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
     $(function () {



         $("button.addToCart").click(function (){
             var bookId   =   $(this).attr("bookId");

         location.href="http://localhost:8080/BookProject2/cartServlet?action=addItem&id="+bookId;

         });
         
     });
    </script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<div>
              <%--用户没有登入显示的信息				--%>



				<c:if test="${empty sessionScope.user.username}">
					<a href="pages/user/login.jsp">登录</a> |
					<a href="pages/user/regist.jsp">注册</a>
				</c:if>
             <%--用户登入之后显示的信息				  --%>
				  <c:if test="${ not empty sessionScope.user.username}">
			<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
			<a href="orderServlet?action=showMyOrders">我的订单</a>
					  <a href="http://localhost:8080/BookProject2/userServlet?action=logout">注销</a>&nbsp;
				  </c:if>




				<a href="pages/cart/cart.jsp">购物车</a>
				<a href="pages/manager/manager.jsp">后台管理</a>
			</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="client/bookServlet" method="get">
					<input type="hidden" name="action" value="pageByPrice">
					价格：<input id="min" type="text" name="min" value="${min}"> 元 -
						<input id="max" type="text" name="max" value="${max}"> 元
						<input type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">
                <%--//购物车为空--%>
                <c:if test="${empty sessionScope.cart.map}">
                    <span></span>
                    <div>
                        <span style="color: red">当前购物车为空</span>
                    </div>




                </c:if>
                    <%-- 购物车不为空的时候 --%>
                    <c:if test="${not empty sessionScope.cart.map}">


                        <span>您的购物车中有${sessionScope.cart.totalCount}件商品</span>
                        <div>
                            您刚刚将<span style="color: red">${sessionScope.lastName}</span>加入到了购物车中
                        </div>


                </c:if>




			</div>



			<c:forEach items="${requestScope.page.items}" var="book">
			<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="" src="${book.imgPath}" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">书名:</span>
						<span class="sp2">${book.name}</span>
					</div>
					<div class="book_author">
						<span class="sp1">作者:</span>
						<span class="sp2">${book.author}</span>
					</div>
					<div class="book_price">
						<span class="sp1">价格:</span>
						<span class="sp2">￥${book.price}</span>
					</div>
					<div class="book_sales">
						<span class="sp1">销量:</span>
						<span class="sp2">${book.sales}</span>
					</div>
					<div class="book_amount">
						<span class="sp1">库存:</span>
						<span class="sp2">${book.stock}</span>
					</div>
					<div class="book_add">
						<button bookId="${book.id}"    class="addToCart">加入购物车</button>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>

		<%--分页条的开始		--%>
		<div id="page_nav">
			<%-- 大于首页才显示--%>
			<c:if test="${requestScope.page.pageNo>1}">
				<a href="${requestScope.page.url}&pageNo=1">首页</a>
				<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
			</c:if>
			<%--页码输出的开始--%>

            <c:choose>
				<%--情况1:如果总页码小于等于5的情况，页码的范围是:1-总页码--%>
				<c:when test="${requestScope.page.pageTotal<=5}">
					<c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
					<c:if test="${i==requestScope.page.pageNo}">
					【${i}】
					</c:if>
						<c:if test="${i!=requestScope.page.pageNo}">
						<a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
						</c:if>
					</c:forEach>
              </c:when>
				<%--	情况2：如果总页码大于5--%>
				<c:when test="${requestScope.page.pageTotal>5}">
				<c:choose>
				<%--第一种小情况当前页面为前三页--%>
					<c:when test="${requestScope.page.pageNo<=3}">
						<c:forEach begin="1" end="5" var="i">
						<c:if test="${i==requestScope.page.pageNo}">
							【${i}】
						</c:if>
						<c:if test="${i!=requestScope.page.pageNo}">
							<a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
						</c:if>
					</c:forEach>
				</c:when>

              <%--第二种小情况当前页面为最后三页--%>
              <c:when test="${requestScope.page.pageNo>requestScope.page.pageTotal-3}">

				  <c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
					  <c:if test="${i==requestScope.page.pageNo}">
						  【${i}】
					  </c:if>
					  <c:if test="${i!=requestScope.page.pageNo}">
						  <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
					  </c:if>
				  </c:forEach>
            </c:when>

               <%--第二种小情况当前页面在中间--%>
					<c:otherwise>
					<c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">

						<c:if test="${i==requestScope.page.pageNo}">
							【${i}】
						</c:if>
						<c:if test="${i!=requestScope.page.pageNo}">
							<a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
						</c:if>

					</c:forEach>


					</c:otherwise>

			</c:choose>

				</c:when>

	</c:choose>

<%--页码输出的结束--%>



			<c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
				<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
				<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
			</c:if>
			共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
			到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
			<input id="searchPageBtn" type="button" value="确定">
			<script>
				$(function () {
					//调到指定的页码
					$("#searchPageBtn").click(function () {
						var  pageNo=$("#pn_input").val();
						//总页码书
						var pageTotal =	${requestScope.page.pageTotal};

						//javaScript语言提供了一个Location地址栏对象
						//它有一个属性叫href,它可以获取浏览器地址中的地址
						//href属性可读可写
						if(pageNo>=1&&pageNo<=pageTotal){

							location.href="${pageScope.basePath}${requestScope.page.url}&pageNo="+pageNo;

						}
					});

				});
			</script>
		</div>
		<%--分页条的结束		--%>
	</div>
	
	<%@include file="/pages/common/footer.jsp"%>


</body>
</html>