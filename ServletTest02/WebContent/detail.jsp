<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="update.ad" method="post" name="frm">
<input type="hidden" name="num" value="${dto.num}"> <%--사용자한테는 보여지지않지만, 서버에는 값을 전달해준다. --%>

		주소록 수정하기<br>

		이름 <input type="text" name="name" value="${dto.name}"><br>
		우편번호
		<input type="text" name="zipcode" size="3" value="${dto.zipcode }" >
		<input type ="button" value="검색" onclick="zipfinder()"><br>
		주소 <input type="text" name="addr" size="50" value="${dto.addr}"><br>
		전화번호<input type="text" name="tel" value="${dto.tel}"><br>
		
		<input type="submit" value="수정">
		<input type="button" value="삭제" onclick="location.href='delete.do?num=${dto.num}'">	
		<input type="reset" value="취소">

</form>

</body>
</html>