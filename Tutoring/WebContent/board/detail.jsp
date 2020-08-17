<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/include/header.jsp" %>
<style>
a{
	text-decoration: none;
	color: black;
}
a:hover{
	text-decoration: none;
	color: green;
}
</style>
<script>
$(document).ready(function(){
	//문서 열자마자 댓글 리스트를 불러옴
	$.getJSON("/Tutoring/board/commentList", 
				{num:$("#num").val()}, 
				function(d){
					var htmlStr="<table class='table table-striped'>";
					$.each(d.carr, function(key, val){ 
						htmlStr+="<tr>";
						htmlStr+="<th style='width:10%;'>"+val.userid+"</th>";
						htmlStr+="<td style='width:60%;'>"+val.msg+"</td>";
						htmlStr+="<td style='width:20%;'>"+val.reg_date+"</td>";
						
						if(val.userid == '${sessionScope.userid}'){
							htmlStr+="<td style='width:10%;'><a href='javascript:cdelete("+val.cnum+","+val.bnum+")'><img src='/Tutoring/img/delete.png' style='height:20px; width:20px;'></a></td>";
						}else{
							htmlStr+="<td></td>";
						}
						
						htmlStr+="</tr>";
					})
						htmlStr+="</table>";
						$("#commentResult").html(htmlStr);
				}
	)
	
	//댓글 입력 버튼
	$("#commentBtn").on("click", function(){
		if($("#msg").val()==""){
			alert("댓글을 작성해주세요");
			$("#msg").focus();
			return false;
		}else{
			$.ajax({
				type: "get",
				url: "commentInsert",
				data: {"msg":$("#msg").val(), "num":$("#num").val()},
				success: function(d){
						var htmlStr="<table class='table table-striped'>";
						d=JSON.parse(d);
						$.each(d.carr, function(key, val){ 
							
							htmlStr+="<tr>";
							htmlStr+="<th>"+val.userid+"</th>";
							htmlStr+="<td>"+val.msg+"</td>";
							htmlStr+="<td>"+val.reg_date+"</td>";
							
							if(val.userid == '${sessionScope.userid}'){
								htmlStr+="<td style='width:10%;'><a href='a href='javascript:cdelete("+val.cnum+","+val.bnum+")'><img src='/Tutoring/img/delete.png' style='height:20px; width:20px;'></a></td>";
							}else{
								htmlStr+="<td></td>";
							}
							htmlStr+="</tr>";
						})
							htmlStr+="</table>";
							$("#commentResult").html(htmlStr);
				},
				error: function(e){
					alert("error:"+e);
				}
			})//ajax
		}
	})//commentBtn
})//document

//게시글 삭제 함수
function fdelete(num, classnum){
	if(confirm("게시물을 삭제 하시겠습니까?")){
		location.href="delete?num=${dto.num}&classnum=${dto.classnum}";
	}
}

//댓글 삭제 함수
function cdelete(cnum, bnum){
	if(confirm("댓글을 삭제 하시겠습니까?")){
		$.getJSON("/Tutoring/board/cdelete", 
				{"cnum":cnum,"bnum":bnum}, 
				function(d){
					var htmlStr="<table class='table table-striped'>";
					$.each(d.carr, function(key, val){ 
						htmlStr+="<tr>";
						htmlStr+="<th style='width:10%;'>"+val.userid+"</th>";
						htmlStr+="<td style='width:60%;'>"+val.msg+"</td>";
						htmlStr+="<td style='width:20%;'>"+val.reg_date+"</td>";
						
						if(val.userid == '${sessionScope.userid}'){
							htmlStr+="<td style='width:10%;'><a href='javascript:cdelete("+val.cnum+","+val.bnum+")'><img src='/Tutoring/img/delete.png' style='height:20px; width:20px;'></a></td>";
						}else{
							htmlStr+="<td></td>";
						}
						
						htmlStr+="</tr>";
					})
						htmlStr+="</table>";
						$("#commentResult").html(htmlStr);
				}
	)
	}
}
</script>
</head>
<body>
  <br/>
<div class="container">
<!-- 액션처리를 위한 값들 -->
<input type="hidden" id="num" name="num" value="${dto.num}"> <!-- 수강후기 게시글 번호 -->
<input type="hidden" id="classnum" name="classnum" value="${dto.classnum}">
<c:set var="classnum" value="${dto.classnum}" />
<!-- 수강 후기 글보기-->
<div><a href="/Tutoring/class/CourseDetail?num=${dto.classnum}"><b>〈</b> 뒤로가기</a></div>
<br/>
	  <h5>수강후기</h5>
	  <br/>
	  <table class="table table-bordered table-sm">
	  <tr>
	 	<td style="width: 15%; background-color: #f8f9fa; text-align: center">글번호</td>
	 	<td style="width: 40%;">${dto.num}</td>
	 	<td style="width: 30%; background-color: #f8f9fa; text-align: center">조회수</td>
	 	<td style="width: 15%; text-align: right;">${dto.readcount}</td>
	  </tr>
	  <tr>
	 	<td style="background-color: #f8f9fa; text-align: center;">작성자</td>
	 	<td>${dto.userid}</td>
	 	<td style="background-color: #f8f9fa; text-align: center;">작성일</td>
	 	<td style="text-align: right">${dto.reg_date}</td>
	  </tr>
	  <tr>
	 	<td style="background-color: #f8f9fa; text-align: center">제목</td>
	 	<td colspan="3">${dto.subject}</td>
	  </tr>
	  <tr style="height: 150px">
	 	<td style="background-color: #f8f9fa; vertical-align: middle; text-align: center">글 내용</td>
	 	<td colspan="3">${dto.content}</td>
	  </tr>
	</table>
<!-- 수강후기 글보기 끝 -->
<!-- 수정, 삭제 버튼 보이기 -->
<c:set var="loginUserid" value="${sessionScope.userid}" />
<c:set var="userid" value="${dto.userid}" />
<c:if test="${loginUserid eq userid}">
		<div align="center" id="button">
			<input type="button" id="upBoard" name="upBoard" value="수정 " class="btn btn-info btn-sm" onclick="location.href='boardview?num=${dto.num}'"> 
			<input type="button" id="delBtn" name="delBtn" value="삭제" class="btn btn-danger btn-sm" onclick="javascript:fdelete(${dto.num}, ${dto.classnum})">
		</div> 
		<br/>
</c:if>
<!-- 수정, 삭제 버튼 보이기 끝-->
<!-- 댓글 리스트 영역 -->
	<div id="commentResult">
	</div>
<!-- 댓글 리스트 영역 끝-->
<!-- 댓글 달기 영역-->
	<div id="commentDiv" align="center">
		<textarea rows="2" cols="20" id="msg" name="msg" class="table table-bordered table-sm"></textarea>
<c:if test="${not empty sessionScope.userid}">
		<input type="button" id="commentBtn" name="commentBtn" value="댓글 " class="btn btn-info btn-sm">
</c:if>
	</div>
	<br/>
<!-- 댓글 달기 영역 끝 -->
</div>
	<br/><br/>
</body>
<%@ include file="/include/footer.jsp" %>