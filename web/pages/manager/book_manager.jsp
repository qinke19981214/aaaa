<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		<%@include file="/pages/common/manger_meu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>		

			

			

			<c:forEach items="${requestScope.page.items}" var="book">
			<tr>
				<td>${book.name}</td>
				<td>${book.price}</td>
				<td>${book.author}</td>
				<td>${book.sales}</td>
				<td>${book.stock}</td>
				<td><a href="manager/bookServlet?action=getBook&id=${book.id}&method=update&pageNo=${requestScope.page.pageNo}">修改</a></td>
				<td><a href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
			</tr>
			</c:forEach>
			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?method=add&pageNo=${requestScope.page.pageTotal}&pageSize=${requestScope.page.pageSize}&pageTotalCount=${requestScope.page.pageTotalCount}">添加图书</a></td>
			</tr>	
		</table>
     <%--分页条的开始		--%>
		<div id="page_nav">
			<%-- 大于首页才显示--%>
			<c:if test="${requestScope.page.pageNo>1}">
			<a href="${requestScope.page.url}&pageNo=1">首页</a>
			<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
			</c:if>
			<%--页码输出的开始--%>
				<%--情况1:如果总页码小于等于5的情况，页码的范围是:1-总页码--%>
          <c:choose>
          <c:when test="${requestScope.page.pageTotal<=5}">
             <c:forEach begin="1" end="${requestScope.page.pageTotal}">
              <c:if test="${i==requestScope.page.pageNo}">
               【${i}】
              </c:if>
                 <c:if test="${i!=requestScope.page.pageNo}">
                 <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                 </c:if>

             </c:forEach>
          </c:when>

<c:when test="${requestScope.page.pageTotal>5}">
    <%--情况2:如果总页码大于5的情况，--%>
           <c:choose>
               <%--当前页面显示是前三页--%>
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

              <%--显示尾页三页--%>
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