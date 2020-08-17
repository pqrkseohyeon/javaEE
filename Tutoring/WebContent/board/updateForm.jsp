<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>    
<script>
//취소 함수
function back(){
	if(confirm("나가겠습니까? 변경사항은 저장되지 않습니다.")){
		location.href="/Tutoring/board/boardDetail?num=${dto.num}";
	}
}
</script>
<form action="/Tutoring/board/boardUpdate" method="post" id="wFrm">
<div class="container">
			<br/><br/>
			  <!-- 수강후기 등록하고자 하는 강의 번호 -->
			  <input type="hidden" id="classnum" name="classnum" value="${dto.classnum}">
			  
			  <!-- 게시글 번호 -->
			  <input type="hidden" id="num" name="num" value="${dto.num}">
			  
			  <div class="input-group mb-3"><div class="input-group-prepend">
			  	  <span class="input-group-text">작성자</span></div>
			      <input type="text" class="form-control" id="userid" name="userid" readonly="readonly" value="${dto.userid}">
			  </div>
			  <br/>
			  
			  <div class="input-group mb-3"><div class="input-group-prepend">
			      <span class="input-group-text">제목</span></div>
			      <input type="text" class="form-control" id="subject" name="subject" value="${dto.subject}">
			  </div>
			  <br/>
			  
			  <div class="input-group mb-3"><div class="input-group-prepend">
			      <span class="input-group-text">내용</span></div>
			      <textarea rows="5" cols="20" class="form-control" id="content" name="content">${dto.content}</textarea>
			  </div>
			  
			  <div class="button" align="center">
			 	  <input type="reset" class="btn btn-gray" value="취소" onclick="javascript:back()">
			 	  <input type="submit" class="btn btn-primary" value="수정">
			  </div>
			  <br/>
			</form>
</div>
<%@ include file="/include/footer.jsp" %>