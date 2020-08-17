<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
a{
	text-decoration: none;
}
a:hover{
	text-decoration: none;
}
.back2{
	margin-top: 20px;
	background-color: #f8f9fa;
	border-radius: 20px;
}
#frm{
	margin: 0 auto;
	width: 50%;
}
.button{
	text-align: center;
	margin: 0 auto;
	padding: 20px;
}
.sidebar{
	float: left;
	width: 200px;
	height:200px;
	margin-top: 60px;
	margin-left: 60px;
}
.sidebar ul{
	list-style: none;
}
.sidebar ul a{
	line-height: 2em;
}
.thumb-image{
 float:left;
 width:200px;
 position:relative;
 padding:5px;
}
</style>
<aside class="sidebar">
	<ul id="menu">
        <li><h5><a href="/Tutoring/class/ClassInsert.jsp"> 강의입력</a></h5></li>
        <li><h5><a href="/Tutoring/class/ClassList.jsp"> 강의목록</a></h5></li>
        <li><h5><a href="/Tutoring/member/Adminview"> 계정설정</a></h5></li>
	</ul>
</aside>
<div class="container">
<br/>
<h5>강의입력</h5>
<div class="back2">
<form action="ClassInsert" method="post" id="frm" enctype="multipart/form-data">
  <br/><br/>
  	<div id="image-holder"></div>
  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">강의 이미지</span>
    </div>
      <input type="file" class="form-control" id="uploadFile" name="uploadFile"><br>
  </div>
  <br/>
  
  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">강의분류</span>
    </div>
      <select name="clevel" id="clevel" name="clevel">
      	<option value="basic" class="form-control">유창한 영어의 기본 요소</option>
      	<option value="career" class="form-control">경력 개발</option>
      	<option value="expression" class="form-control">자신을 표현해보세요</option>
      	<option value="test" class="form-control">시험 준비</option>
      </select>
  </div>
  <br/>
  
  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">강의명</span>
    </div>
      <input type="text" class="form-control" id="classname" name="classname">
  </div>
  <br/>
   
  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">강의 주제</span>
    </div>
      <input type="text" class="form-control" id="topic" name="topic">
  </div>
  <br/>
  
  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">강의설명</span>
    </div>
      <textarea rows="5" cols="20" class="form-control" id="content" name="content"></textarea>
  </div>
  
  <div class="button">
 	  <input type="reset" class="btn btn-gray" value="취소">
 	  <button  id="send"  class="btn btn-primary">강의등록</button>
  </div>
</form>
</div>
</div>
<br/><br/>
<%@ include file="../include/footer.jsp" %>
<script>
$(document).ready(function(){
	$("#send").click(function(){
		if($("#classname").val()==""){
			alert("강의명을 입력하세요");
			$("#classname").focus();
			return false;
		}
		if($("#topic").val()==""){
			alert("강의주제를 입력하세요");
			$("#topic").focus();
			return false;
		}
		if($("#content").val()==""){
			alert("강의설명을 입력하세요");
			$("#content").focus();
			return false;
		}
		$("#frm").submit();
	})//send
	$("#uploadFile").on('change', function () {
        if (typeof (FileReader) != "undefined") {
            var image_holder = $("#image-holder");
            image_holder.empty();
            var reader = new FileReader();
            reader.onload = function (e) {
                $("<img />", {
                    "src": e.target.result,
                    "class": "thumb-image"
                }).appendTo(image_holder);
            }
            image_holder.show();
            reader.readAsDataURL($(this)[0].files[0]);
        } else {
            alert("이 브라우저에서 지원하지 않는 확장자 입니다.");
        }
    });
});//document
</script>