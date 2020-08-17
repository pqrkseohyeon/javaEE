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
</style>

<aside class="sidebar">
	<ul id="menu">
        <li><h5><a href="/Tutoring/class/ClassInsert.jsp"> 강의입력</a></h5></li>
        <li><h5><a href="/Tutoring/class/ClassList.jsp"> 강의목록</a></h5></li>
        <li><h5><a href="/Tutoring/member/Adminview"> 계정설정</a></h5></li>
	</ul>
</aside>

<div class="container" id="userInfo">
<br/>
<h5>계정설정</h5>
<div class="back2">
<form action="update" method="post" id="frm">
  <br/><br/>
  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">아이디</span>
    </div>
      <input type="text" class="form-control" name="userid" id="userid" value="${member.userid}" readonly="readonly">
  </div>
  <br/>
   
  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">이름</span>
    </div>
    <input type="text" class="form-control" id="name" value="${member.name}" name="name">
  </div>
  <br/>
  
  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">비밀번호</span>
    </div>
    <input type="password" class="form-control" id="pwd" value="${member.pwd}" name="pwd">
  	<input type="password" class="form-control" placeholder="비밀번호 확인" id="pwd_check" name="pwd_check">
  </div>
  <br/>
  
  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">이메일</span>
    </div>
    <input type="text" class="form-control" id="email" value="${member.email}" name="email">
  </div>
  <br/>
    
  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">우편번호</span>
    </div>
      <input type="text" id="sample6_postcode" name="sample6_postcode" value="${member.postcode}" readonly="readonly" class="form-control">
    <div class="col align-self-end" >
      <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호" class="btn btn-secondary"><br>
   	</div>
  </div>
  <br/>  

  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">주소</span>
    </div>
    <input type="text" id="sample6_address" name="sample6_address" value="${member.address}" placeholder="주소" class="form-control"><br>
  </div>

  <div class="input-group mb-3">
	<input type="text" id="sample6_detailAddress" name="sample6_detailAddress" value="${member.detailAddress}" placeholder="상세주소" class="form-control">
	<input type="text" id="sample6_extraAddress" name="sample6_extraAddress" value="${member.extraAddress}" placeholder="참고항목" class="form-control">
  </div>
  <div class="button">
  	<button  id="send"  class="btn btn-info">저장</button>
	<input type="button" class="btn btn-danger" value="회원탈퇴" onclick="delCheck('${member.userid}')">
  </div>
</form>
</div>

</div>
<br/><br/>
<%@ include file="../include/footer.jsp" %>
<script>
function delCheck(userid){
    if(confirm("탈퇴하시겠습니까? 삭제된 회원정보는 되돌릴 수 없습니다.")){
    	location.href="delete?userid="+userid;
    }
}
</script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>
<script>
var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
$(document).ready(function(){
	$("#send").click(function(){
		//이름이 공백일때
		if($("#name").val()==""){
			alert("이름을 입력하세요");
			return false;
		}
		//암호가 공백일때
		if($("#pwd").val()==""){
			alert("암호를 입력하세요");
			$("#pwd").focus();
			return false;
		}
		//암호확인이 공백일때
		if($("#pwd_check").val()==""){
			alert("암호확인 필수");
			$("#pwd_check").focus();
			return false;
		}
		//암호 일치확인
		if($("#pwd").val()!=$("#pwd_check").val()){
			alert("암호 불일치");
			$("#pwd_check").focus();
			return false;
		}
		if($("#pwd").val().length<6){
	        alert("비밀번호는 6글자 이상이어야 합니다.");
	        pw1.value="";
	        pw1.focus();
	    }
		//이메일이 공백일때
		if($("#email").val()==""){
			alert("이메일을 입력하세요");
			return false;
		}
		//이메일 양식
		if(!$("#email").val().match(reg_email)){
			alert("이메일을 정확하게 입력하세요");
			$("#email").focus();
			return false;
		}
		$("#frm").submit();
	})//send
});//document
</script>