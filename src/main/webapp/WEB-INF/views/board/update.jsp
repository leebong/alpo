<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<div class="row "> 
			<h3 class="offset-2 col-8">게시판 글수정</h3>
		</div>
		<form method="post">
			<input type = "text" name = number value = "${board.number}" style = "display:none">
			<div class="row">		
				<div class="offset-2 col-8">
					<div class="form-group" >
			  			<label for="title">제목:</label>
			  			<input type="text" class="form-control" id="title" name="title"
			  			value=${update.title}>
					</div>
				</div>
				<div class="offset-2 col-8">
					<div class="form-group">
			  			<label for="author">작성자:</label>
			  			<input type="test" class="form-control" id="author" name="author"
			  			value=${update.author}>
					</div>
				</div>
				<div class="offset-2 col-8">
					<div class="form-group">
		  				<label for="comment">내용:</label>
		  				<textarea class="form-control" rows="6" id="comment" name="contents">
		  				${update.contents}
		  				</textarea>
		  				
					</div>
				</div>
				<div class="offset-2 col-8">
					<a href="/board/list">
					<button type="button" class="btn btn-primary">목록</button>

					<a href="/board/list">
					<button type="submit" class="btn btn-primary">완료</button>
					</a>
				</div>
			</div>
			</input>
		</form>
	</div>

</body>
</html>