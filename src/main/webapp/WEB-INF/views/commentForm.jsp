<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书评论</title>
</head>
<body>
	<div id="commentList">
		<c:forEach items="${commentList }" var="comment" >
			<div>${comment.commentContent } - <fmt:formatDate value="${comment.createDate }" pattern="yyyy-MM-dd HH:mm:ss" /></div>
		</c:forEach>
	</div>
	<form action="${ctx}/comment/saveComment" method="post" >
		<input type="hidden" name="isbn" value="${isbn }"  >
		<table border="1" width="600" >
		
			<tr>
				<td >评论内容：</td>
				<td ><textarea id="commentContent" name="commentContent" rows="" cols=""></textarea></td>
			</tr>
			
			<tr>
			
				<td colspan="2" align="center" >
					<input type="submit" value="提交 " >&nbsp;&nbsp;<a href="${ctx }/tbook/form/${isbn }" >返回</a>
				</td>
				
			</tr>
			
		</table>
	</form>
	<script type="text/javascript">
		/* function submit_onclick(isbn)
		{
			$.getJSON("${ctx}/comment/saveComment", { isbn: isbn, commentContent: $("#commentContent").text() }, function(data){
				if (data.status == "success")
				{
					var commentDiv = $("<div></div>");
					$("#commentList").append();
				}
				else if (data.status == "fail")
				{
					alert(data.msg);
				}
			});
			return false;
		} */
	</script>
</body>
</html>