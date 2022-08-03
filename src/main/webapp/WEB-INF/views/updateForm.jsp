<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>수정페이지</h3>
<form action="update" method="post"
							enctype="multipart/form-data">
		<input type="text" value="${id }" name="id" readonly><br>
		<input type="text" value="${name }" name="name"><br>
		<input type="file" value="${file }" name="f"><br>
		<input type="submit" value="전송">
	</form>
</body>
</html>