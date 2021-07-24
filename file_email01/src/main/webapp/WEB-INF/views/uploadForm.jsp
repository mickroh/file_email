<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>파일 업로드</h1>
	<form action="${contextPath }/upload" enctype="multipart/form-data" method="post">
	
	<input type="text" name="id" placeholder="input id"><br>
	<input type="text" name="name" placeholder="input name"><br>
	<input type="file" name="file" ><br>
	<input type="submit" value="업로드" ><br>
	</form>
	<hr>
	<a href="${contextPath }/views">파일 보기</a>
</body>
</html>