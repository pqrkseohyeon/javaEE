<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../include/header.jsp" %>
  <script>
  $(document).ready(function(){
		getData(1,"","");
		
		$("#btnSearch").on("click",function(){//검색버튼 클릭
			alert("Dd")
			getData(1,$("#field").val(),$("#word").val());
		})
		
	})//document
	function getData(pageNum, field, word){
		$.get("list",
			  {"pageNum":pageNum, "field":field, "word":word}, 
			  function(d){
				 
			    $("#result").html(d);
		})
	}
  </script>
  
<title>도서목록</title>
</head>
<body>
<a href="BookInsert.jsp">도서등록</a>
<div id="result"></div>
	<br/></br>
	<div align="center">
		<form name="search" id="search">
			<select name="field" id="field">
				<option value="title">제목</option>
				<option value="author">작가</option>
			</select>
			<input type="text" name="word" id="word">
			<input type="button" value="검색" id="btnSearch"> 
		</form>
	</div>

</body>
</html>