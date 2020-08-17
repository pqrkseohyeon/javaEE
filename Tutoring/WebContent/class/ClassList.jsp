<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<script>
$(document).ready(function(){
	getData(1,"","");
	$("#btnSearch").on("click",function(){//검색버튼 클릭
		getData(1,$("#field").val(),$("#word").val());
	})
})//document
function getData(pageNum, field, word){
	$.get("ClassList",
		  {"pageNum":pageNum, "field":field, "word":word}, 
		  function(d){
		  $("#result").html(d);
	})
}
</script>
</head>
<body>
<div id="result"></div>
<div align = "center">
<form name = "search" id = "search" class="selectpicker" data-live-search="true">
	<select name = "field" id = "field">
		<option value = "classname">강의명</option>
	</select>
	<input type = "text" name = "word" id = "word">
	<input type = "button" value = "검색" id = "btnSearch"> 
</form>
</div>
<br/>
</body>
</html>
<%@ include file="/include/footer.jsp" %>