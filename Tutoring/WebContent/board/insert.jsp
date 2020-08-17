<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<body>
<%-- <form action="/Tutoring/board/boardinsert" method="post">
<br/><br/>
  <!-- 수강후기 등록하고자 하는 강의 번호 -->
  <input type="hidden" id="classnum" name="classnum" value="${classnum}">
  
  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">강의명</span>
    </div>
      <input type="text" class="form-control" id="classname" name="classname" readonly="readonly" value="${classname}">
  	  <div class="input-group-prepend">
      <span class="input-group-text">작성자</span>
    </div>
      <input type="text" class="form-control" id="userid" name="userid" readonly="readonly" value="${userid}">
  </div>
  <br/>
  
  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">제목</span>
    </div>
      <input type="text" class="form-control" id="subject" name="subject">
  </div>
  <br/>
  
  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">내용</span>
    </div>
      <textarea rows="5" cols="20" class="form-control" id="content" name="content"></textarea>
  </div>
  
  <div class="button">
 	  <input type="reset" class="btn btn-gray" value="취소">
 	  <button  id="send"  class="btn btn-primary" >후기등록</button>
  </div>
</form> --%>
</body>
</html>
<script>
$("#send").click(function(){ //후기등록 버튼 클릭
	if($("#subject").val()==""){
		alert("제목을 입력하세요");
		$("#subject").focus();
		return false;
	}
	if($("#content").val()==""){
		alert("내용을 입력하세요");
		$("#content").focus();
		return false;
	}
	var classnum=$("#classnum").val();
	var classname=$("#classname").val();
	var userid=$("#userid").val();
	var content=$("#content").val();
	var subject=$("#subject").val();
	var postString="classname="+classname+"&classnum="+classnum+"&userid="+userid+"&content="+content+"&subject="+subject;
	$.ajax({
		type:	"post",
		url :	"/Tutoring/board/boardinsert",
		data:	postString,
		success:function(d){
			alert("후기 입력 성공");
		},
		error: function(e){
			alert("error:"+e);
		}
	})//ajax
})//send
</script>