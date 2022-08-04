<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
   function readURL(input) {
      var file = input.files[0] //파일에 대한 정보
      console.log(file)
      if (file != '') {
         var reader = new FileReader();
         reader.readAsDataURL(file); //파일의 정보를 토대로 파일을 읽고 
         reader.onload = function (e) { // 파일 로드한 값을 표현한다
          //e : 이벤트 안에 result값이 파일의 정보를 가지고 있다.
          console.log( e.target.result) 
          $('#preview').attr('src', e.target.result);
          }
      }
  }  
</script>

</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>수정페이지</h3>
<form action="modify" method="post"
							enctype="multipart/form-data">
		<input type="hidden" value="${dto.imgName }" name="origin">
		<input type="text" name="id" readonly value="${dto.id }"><br>
		<input type="text" name="name" value="${dto.name }"><br>
		<input type="file" name="file" onchange="readURL(this)"><br>
		<img src="download?file=${dto.imgName }" id="preview" width="100px" height="100px">
		<hr>
		<input type="submit" value="수정">
	</form>
</body>
</html>