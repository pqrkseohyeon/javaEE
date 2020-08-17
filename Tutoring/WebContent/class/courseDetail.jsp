<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<style>
html, body, div, span, applet, object, iframes,
	p, blockquote, pre, abbr, acronym, address, big, quotes, code, del,
	dfn, em, img, ins, kbd, q, s, samp, small, strike, sub, sup, tt, var, u,
	i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table,
	caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas,
	details, embed, figure, figcaption, footer, header, hgroup, menu, nav,
	output, ruby, section, summary, time, mark, audio, video {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	do: inherit;
	vertical-align: baseline;
}
article, aside, details, figcaption, figure, footer, header, hgroup,
	menu, nav, section {
	display: block;
}
blockquote, q {
	quotes: none;
}
blockquote : before, blockquote : after, q : before, q : after {
	content: '';
	content: none;
}
table {
	border-collapse: collapse;
	border-spacing: 0;
}
a{
	text-decoration: none;
	color: black;
}
a:hover{
	text-decoration: none;
	color: green;
}
.card {
	height: 500px;
	width: 350px;
	border-radius: 15px;
	display: inline-block;
	margin-top: 20px;
	margin-bottom: 30px;
	margin-right: 40px;
	position: relative;
	box-shadow: 0 6px 0px 0 rgba(0, 0, 0, 0.2);
	overflow: hidden;
	float: left;
}
.card-header {
	-webkit-transition: 0.5s; /*사파리 & 크롬*/
    -moz-transition: 0.5s;  /*파이어폭스*/
    -ms-transition: 0.5s;	/*인터넷 익스플로러*/
    -o-transition: 0.5s;  /*오페라*/
    transition: 0.5s;
	width: 100%;
	height: 300px;
	border-radius: 15px 15px 0 0;
	background-color: gray;
	background-size: 100% 300px;
	background-repeat: no-repeat;	
}
.card-header-is_closed{
    background-color: #EF5A31 ;
    color: #FFF ;
    font-weight: bold ;
    text-align: center ;
    float: right;
    margin: 15px 15px 0 0;
    border-radius: 50%;
    font-weight: bold;
    padding: 10px 10px;
    line-height: 20px;
}
.card-body-header{
	line-height: 25px;
	margin: 10px 20px 0px 20px;
}
.card:hover .card-body-description {
    opacity: 1;
    -webkit-transition: .5s ease-in-out;
    -moz-transition: .5s ease-in-out;
    -ms-transition: .5s ease-in-out;
    -o-transition: .5s ease-in-out;
    transition : .5s ease-in-out;
    overflow: scroll;
    text-decoration: none;
}
.button{
	text-align: center;
	margin: 0 auto;
	padding: 20px;
	margin-bottom: 20px;
}
.content{
	width: 40%;
	height: auto;
	position: relative;
	display:inline;
	margin-bottom: 30px;
	margin-right: 20px;
}
.board{
	clear: both;
	width: 100%;
	height: auto;
}
</style>
<script>
$(document).ready(function(){
	getData(1, "", "", ${dto.classnum}); //페이지 로드 시 수강후기 리스트 보기
	
	//등록하기 클릭
	$("#subscribe").click(function(){ 
		if(${sessionScope.userid==null}){
			alert("로그인 필요");
			location.href="/Tutoring/member/login";
		}else{
			location.href="/Tutoring/member/view";
		} 
	})//subscribe
	
	//검색버튼 클릭
	$("#btnSearch").on("click", function(){
		getData(1, $("#field").val(), $("#word").val(), $("#classnum").val());
	})//btnSearch
	
	//후기등록 버튼 클릭
	$("#send").click(function(){ //후기등록 버튼 클릭
         if(${sessionScope.userid==null}){
              alert("로그인 필요");
              location.href="/Tutoring/member/login";
         }else{
	       	 	var classnum=$("#classnum").val();
				var userid=$("#userid").val();
				var content=$("#content").val();
				var subject=$("#subject").val();
				var postString="classnum="+classnum+"&userid="+userid+"&content="+content+"&subject="+subject;
	            $.ajax({
	                  type:    "post",
	                  url :    "/Tutoring/board/boardinsert",
	                  data:    postString,
	                  success:function(d){
	                	   alert("후기가 등록되었습니다!");
	                	   $("#result").html(d);
	                       document.getElementById("wFrm").reset();
	                  },
	                  beforeSend: showRequest, //빈칸 확인
	                  error: function(e){
	                       alert("error:"+e);
	                  }
	            })//ajax
         }
     })//send
}); //document ready

//전체보기 함수
function getData(pageNum, field, word, classnum){
	$.get("/Tutoring/board/boardlist", 
			{"pageNum":pageNum, "field":field, "word":word, "classnum":classnum},
			function(d){
			$("#result").html(d);
	})
}//getData

//빈칸확인 함수
function showRequest(){
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
	return true;
}
</script>
<body>
<div class="container">
	<br/>
<!-- 강의 소개 -->
	<div><a href="/Tutoring/class/courseList"><b>〈</b> 모든 과정</a></div>
	<div class="card">
		<form action="subcribe" method="post" id="frm">
			<!-- 카드 영역 -->
			<div class="card-header" style="background-image: url('/Tutoring/upload/${dto.uploadFile}');">
			</div>
			<div class="card-body">
				<div class="card-body-header" >
					<h5>${dto.classname}</h5>
					<p class = "card-body-nickname" style="font-size:0.9em;">${dto.topic}</p>
				</div>
				<div class="button">
					<input type="button" class="btn btn-info" value="등록하기" id="subscribe" style="width: 100%;">
				</div>
			</div>
			<!-- 카드 영역 끝 -->
			<br/>
		</form>
	</div>
	<br/>
	<div class="content">
		<p style="font-size: 1.25em;">개요</p><br/>
		<p>왜 이런 과정을 수강해야 하나요?</p>
		<br/>
		<span style="color: gray;">${dto.content}</span>
	</div>
<!-- 강의 소개 끝 -->
	
<!-- 게시판 -->
	<div class="board">
		<br/>
		
		<!-- 검색 시작-->
		<div align="right" id="searchDiv">
		<form name="search" id="search">
			<select name="field" id="field">
				<option value="userid">작성자</option>
				<option value="subject">제목</option>
			</select>
			<input type="text" name="word" id="word">
			<input type="button" value="찾기" id="btnSearch" class="btn btn-outline-secondary btn-sm">
			<a href="#coll"  id="writeBtn" class="btn btn-outline-dark btn-sm" data-toggle="collapse">글쓰기</a>
		</form>
		</div><!-- 검색 끝-->
		
		<!-- 수강후기 게시판 -->
		<div id="result" align="center"></div>
		
		<!-- 글쓰기 폼 영역 -->
		  <div id="coll" class="collapse">
		   <form action="/Tutoring/board/boardinsert" method="post" id="wFrm">
			<br/><br/>
			  <!-- 수강후기 등록하고자 하는 강의 번호 -->
			  <input type="hidden" id="classnum" name="classnum" value="${dto.classnum}">
			  
			  <div class="input-group mb-3"><div class="input-group-prepend">
			      <span class="input-group-text">강의명</span></div>
			      <input type="text" class="form-control" id="classname" name="classname" readonly="readonly" value="${dto.classname}">
			  	  <div class="input-group-prepend"><span class="input-group-text">작성자</span></div>
			      <input type="text" class="form-control" id="userid" name="userid" readonly="readonly" value="${sessionScope.userid}">
			  </div>
			  <br/>
			  
			  <div class="input-group mb-3"><div class="input-group-prepend">
			      <span class="input-group-text">제목</span></div>
			      <input type="text" class="form-control" id="subject" name="subject">
			  </div>
			  <br/>
			  
			  <div class="input-group mb-3"><div class="input-group-prepend">
			      <span class="input-group-text">내용</span></div>
			      <textarea rows="5" cols="20" class="form-control" id="content" name="content"></textarea>
			  </div>
			  
			  <div class="button">
			 	  <input type="reset" class="btn btn-gray" value="취소">
			 	  <input type="button" class="btn btn-primary" id="send" value="후기등록">
			  </div>
			</form>
		  </div>
		  <!-- 글쓰기 폼 영역  끝-->
	</div>
<!-- 게시판 끝-->
</div>
<!-- container 끝-->
</body>
<%@ include file="/include/footer.jsp" %>