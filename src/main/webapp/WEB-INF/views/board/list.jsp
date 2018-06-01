<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">	
	<link rel="stylesheet" href="/resources/css/list.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<title>Insert title here</title>
</head>
<body>
	<div class="container">
		 
		<div class = "text-center">
				<h3>[게시판] </h3><br>
		</div>		
		<div class="row">
			<form method = "post" action="/board/list">
				<!-- select list 창 -->
				<div class="form-group" style="display:inline-block" >
					<select class="form-control" name="searchType">
					    <option value="0" 
					    	<c:out value="${searchType==0?'selected':''}"/>
					    	>제목</option>
					    <option value="1"
					    	<c:out value="${searchType==1?'selected':''}"/>
					    >내용</option>
					    <option value="2"
					    	<c:out value="${searchType==2?'selected':''}"/>
					    >작성자</option>
					</select>
				</div>
				
				<!-- form : 텍스트입력창 -->
				<div class="form-group" style="display:inline-block">
					<input class="form-control" name = "search" value="${search}">
				</div>
				
				<!-- form : 검색버튼 -->
				<div style="display:inline-block">
					<button type = "submit" class="btn">검색</button>
				</div>
				<div>
					<h6 align="right"><a href="/logout" >로그아웃</a></h6>
				</div>				
			</form>		
		</div>

		<div class="row">
			<table class="table table-bordered table-sm table-hover">
			    <thead class = "text-center">
					<tr class="table table-primary">
			        	<th class="th-number">번호</th>
			        	<th class="th-title">제목</th>
			        	<th class="th-author">작성자</th>
			      	</tr>
			    </thead>
			    
			    <tbody>
			    	<!-- c:forEach 태그를 이용하여 리스트의 값을 하나씩 테이블 행에 출력 -->
			    	<c:forEach var="board" items="${list}">
				    	<tr class = "text-center">
			    			<td>${board.number}</td>
				        	<td id="td-contents"><a href="/board/detail?number=${board.number}">${board.title}</a></td>
				        	<td>${board.author}</td>
				        </tr>
			        </c:forEach>
			    </tbody>
			  </table>
		</div>
		<div class="row">

			<div class="form-contents">  
			  	<ul class="pagination">
			  	  <c:if test="${pm.prev}">
				  	<li class="page-item">
				  		<a class="page-link" href="/board/list?page=${pm.getStartPage()-1}&search=${search}&searchType=${searchType}">Previous</a>
				  	</li>
				  </c:if>
				  <c:forEach var="idx" begin="${pm.startPage}" end="${pm.endPage}">
					  <li class="page-item">
					  	<a class="page-link" href="/board/list?page=${idx}&search=${search}&searchType=${searchType}">
					  		<span <c:out value="${pm.page.page==idx?'class=pagenation-active':''}"/>>${idx}</span>
					  	</a>
					  </li>
				  </c:forEach>
				  <c:if test="${pm.next}">
				  	<li class="page-item">
				  		<a class="page-link" href="/board/list?page=${pm.getEndPage()+1}&search=${search}&searchType=${searchType}">Next</a>
				  	</li>
				  </c:if>	
				</ul>	  
			</div>
			
			<div class="form-write">
				  <a href="/board/list">
				  	<button class="btn btn-default"> 목록 </button>
				  </a>
				  <a href="/board/write" style="margin: 0 0 0 10px;">
				  	<button class="btn btn-default"> 글쓰기 </button>
				  </a>
			</div>
		  
		</div>
	</div>	
</body>
</html>