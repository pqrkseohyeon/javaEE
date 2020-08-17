<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<body>
<h5 style="text-align: left"><span id="cntSpan">수강후기(${count}</span>)</h5>
<table class="table table-hover table-bordered table-sm">
	<thead>
		<tr>
			<th style="width: 8%">글번호</th>
			<th style="width: 13%">작성자</th>
			<th>제목</th>
			<th style="width: 8%">작성일</th>
			<th style="width: 7%">조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${board}" var="board" varStatus="st">
		<tr>
			<td>${rowNo-st.index}</td>
			<td>${board.userid}</td>
			<td><a href="/Tutoring/board/boardDetail?num=${board.num}">${board.subject}</a></td>
			<td>${board.reg_date}</td>
			<td>${board.readcount}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<div align="center">
	<c:if test="${pu.startPage>pu.pageBlock}"> <!-- 이전 -->
		<a href="javascript:getData(${pu.startPage-pu.pageBlock},'${pu.field}','${pu.word}', ${pu.classnum})">[이전]</a>
	</c:if>
	<c:forEach begin="${pu.startPage}" end="${pu.endPage}" var="i"><!-- 페이지 출력 -->
		<c:if test="${i==pu.currentPage}"> <!-- 현재 페이지 -->
			<c:out value="${i}"/>
		</c:if>
		<c:if test="${i!=pu.currentPage}"> <!-- 현재 페이지 아닌 경우 링크 부여-->
			<a href="javascript:getData(${i},'${pu.field}','${pu.word}', ${pu.classnum})">${i}</a>
		</c:if>
	</c:forEach>
	<c:if test="${pu.endPage<pu.totPage}"> <!-- 다음-->
		<a href="javascript:getData(${pu.endPage+1},'${pu.field}','${pu.word}', ${pu.classnum})">[다음]</a>
	</c:if>
</div>
</body>
</html>