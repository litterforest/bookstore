<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ include file="include/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书列表</title>
</head>
<body>
	<table border="1" width="600" >
		<c:choose>
			<c:when test="${not empty tbookList }">
			
				<c:forEach items="${tbookList }" var="book" >
					<tr>
						<td rowspan="4" ><img alt="" src="${ctx }${book.picurl}"></td>
						<td colspan="2" align="center" ><a href="${ctx }/tbook/form/${book.isbn }">${book.bname }</a></td>
					</tr>
					<tr>
						<th>商品价格</th>
						<td><fmt:formatNumber value="${book.price }" maxFractionDigits="2" minFractionDigits="2" groupingUsed="false"></fmt:formatNumber></td>
					</tr>
					<tr>
						<th>出版社</th>
						<td>${book.press }</td>
					</tr>
					<tr>
						<th>访问次数</th>
						<td>${book.pageViews }</td>
					</tr>
				</c:forEach>
				
			</c:when>
			<c:otherwise>
				<tr>目前还没有图书上架，请回头再来!</tr>
			</c:otherwise>
		</c:choose>
	</table>
</body>
</html>