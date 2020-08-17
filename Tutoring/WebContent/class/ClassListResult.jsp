<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
h5 a{
 	text-decoration: none;
 	color: black;
 }
.back2{
	margin-top: 20px;
	background-color: #f8f9fa;
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
</head>
<body>
<aside class="sidebar">
	<ul id="menu">
        <li><h5><a href="/Tutoring/class/ClassInsert.jsp"> 강의입력</a></h5></li>
        <li><h5><a href="/Tutoring/class/ClassList.jsp"> 강의목록</a></h5></li>
        <li><h5><a href="/Tutoring/member/Adminview"> 계정설정</a></h5></li>
	</ul>
</aside>
<div class="container">
<br/>
<h5><a href="ClassList.jsp">강의목록(<span id="cntSpan">${count}</span>)</a></h5>
<div class="back2">
<table class="table table-hover">
	<thead>
		<tr>
			<th>번호</th>
			<th>강의분류</th>
	        <th>강의명</th>
	        <th>강의 주제</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${wclass}" var="wclass" varStatus="st">
		<tr>
			<td>${rowNo-st.index}</td>
			<c:if test="${wclass.clevel eq 'basic'}">
				<td>유창한 영어의 기본 요소</td>
			</c:if>
			<c:if test="${wclass.clevel eq 'career'}">
				<td>경력 개발</td>
			</c:if>
			<c:if test="${wclass.clevel eq 'expression'}">
				<td>자신을 표현해보세요</td>
			</c:if>
			<c:if test="${wclass.clevel eq 'test'}">
				<td>시험 준비</td>
			</c:if>
      		<td><a href="ClassDetail?num=${wclass.classnum}">${wclass.classname}</a></td>
      		<td>${wclass.topic}</td>
      	</tr>
		</c:forEach>
	</tbody>
</table>
</div>
</div>
<div align="center" class="text-success">
	<c:if test="${pu.startPage>pu.pageBlock }"> <!-- 이전 -->
		<a href="javascript:getData(${pu.startPage-pu.pageBlock},'${pu.field}','${pu.word}')">[이전]</a>
	</c:if>
	<c:forEach begin="${pu.startPage}" end="${pu.endPage}" var="i"><!-- 페이지 출력 -->
		<c:if test="${i==pu.currentPage}"> <!-- 현재 페이지 -->
			<c:out value="${i}"/>
		</c:if>
		<c:if test="${i!=pu.currentPage}"> <!-- 현재 페이지 아닌 경우 링크 부여-->
			<a href="javascript:getData(${i},'${pu.field}','${pu.word}')">${i}</a>
		</c:if>
	</c:forEach>
	<c:if test="${pu.endPage<pu.totPage}"> <!-- 다음-->
		<a href="javascript:getData(${pu.endPage+1},'${pu.field}','${pu.word}')">[다음]</a>
	</c:if>
</div>
<br/>