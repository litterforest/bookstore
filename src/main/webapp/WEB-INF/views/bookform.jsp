<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书详情</title>
</head>
<body>
	<form action="">
		<table border="1" width="600" >
			<tr>
				<td rowspan="5"><img alt="" src="${ctx }${book.picurl}"></td>
				<td colspan="2" align="center">${book.bname }</td>
			</tr>
			<tr>
				<th>书号</th>
				<td>${book.isbn }</td>
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
				<th>出版日期</th> 
				<td><fmt:formatDate value="${book.pdate }" pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td colspan="3" align="center" >
					<a href="${ctx }/comment/commentForm?isbn=${book.isbn}">评论</a>&nbsp;&nbsp;<a href="${ctx }/tbook/list" >返回</a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>