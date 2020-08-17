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
	color: black;
}
.card:hover{
    background-color: #eee;
}
.card {
	height: 400px;
	width: 350px;
	border-radius: 15px;
	display: inline-block;
	margin-top: 30px;
	margin-bottom: 30px;
	position: relative;
	box-shadow: 0 6px 0px 0 rgba(0, 0, 0, 0.2);
	overflow: hidden;
}
.card-header {
	-webkit-transition: 0.5s; /*사파리 & 크롬*/
    -moz-transition: 0.5s;  /*파이어폭스*/
    -ms-transition: 0.5s;	/*인터넷 익스플로러*/
    -o-transition: 0.5s;  /*오페라*/
    transition: 0.5s;
	width: 100%;
	height: 230px;
	border-radius: 15px 15px 0 0;
	background-color: gray;
	background-size: 100% 230px;
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
</style>
<body>
<br/>
<div class="container">
<h3>과정 둘러보기</h3>
<hr>
<p style="font-size:1.25em;">유창한 영어의 기본 요소</p>
<p style="font-size:0.9em;">유창한 영어로 대화하며 자신감을 키워보세요. 간단한 대화부터 복잡한 시사 문제에 대해 논리정연한 의견을 표현하는 것까지, 다양한 레벨의 과정을 통해 모두가 무언가를 배울 수 있습니다.</p>
<div class="card-columns" style="column-count:4;">
	<c:forEach items="${dto}" var="dto">
		<c:if test="${dto.clevel eq 'basic'}">
		<a href="CourseDetail?num=${dto.classnum}">
			<div class="card">
				<div class="card-header" style="background-image: url('/Tutoring/upload/${dto.uploadFile}');">
				</div>
				<div class="card-body">
					<div class="card-body-header" >
						<h5>${dto.classname}</h5>
						<p class = "card-body-nickname" style="font-size:0.9em;">${dto.topic}</p>
					</div>
				</div>
			</div>
		</a>
		</c:if>
	</c:forEach>
</div>
<br/><br/>

<p style="font-size:1.25em;">경력 개발</p>
<p style="font-size:0.9em;">해외 환경에서 비즈니스 관련 대화를 하려면 유창한 영어 실력만으로는 부족합니다. 언어와 비언어로 이루어진 다문화 커뮤니케이션은 경력 개발의 강력한 도구입니다.</p>
<div class="card-columns" style="column-count:4;">
	<c:forEach items="${dto}" var="dto">
		<c:if test="${dto.clevel eq 'career'}">
		<a href="CourseDetail?num=${dto.classnum}">
			<div class="card">
				<div class="card-header" style="background-image: url('/Tutoring/upload/${dto.uploadFile}');">
				</div>
				<div class="card-body">
					<div class="card-body-header" >
						<h5>${dto.classname}</h5>
						<p class = "card-body-nickname" style="font-size:0.9em;">${dto.topic}</p>
					</div>
				</div>
			</div>
		</a>
		</c:if>
	</c:forEach>
</div>
<br/><br/>

<p style="font-size:1.25em;">자신을 표현해보세요</p>
<p style="font-size:0.9em;">영어를 배우면 전 세계 사람들과 더 쉽게 교류할 수 있습니다. 요즘 화두가 되는 다양한 주제를 탐색하면서 특정 분야 어휘를 확장하고, 다른 문화와 관점에 대해 배워보세요!</p>
<div class="card-columns" style="column-count:4;">
	<c:forEach items="${dto}" var="dto">
		<c:if test="${dto.clevel eq 'expression'}">
		<a href="CourseDetail?num=${dto.classnum}">
			<div class="card">
				<div class="card-header" style="background-image: url('/Tutoring/upload/${dto.uploadFile}');">
				</div>
				<div class="card-body">
					<div class="card-body-header" >
						<h5>${dto.classname}</h5>
						<p class = "card-body-nickname" style="font-size:0.9em;">${dto.topic}</p>
					</div>
				</div>
			</div>
		</a>
		</c:if>
	</c:forEach>
</div>
<br/><br/>

<p style="font-size:1.25em;">시험 준비</p>
<p style="font-size:0.9em;">최신 시험 기출 문제를 연습해 다가오는 영어 말하기 시험에 대비하세요.</p>
<div class="card-columns" style="column-count:4;">
	<c:forEach items="${dto}" var="dto">
		<c:if test="${dto.clevel eq 'test'}">
		<a href="CourseDetail?num=${dto.classnum}">
			<div class="card">
				<div class="card-header" style="background-image: url('/Tutoring/upload/${dto.uploadFile}');">
				</div>
				<div class="card-body">
					<div class="card-body-header" >
						<h5>${dto.classname}</h5>
						<p class = "card-body-nickname" style="font-size:0.9em;">${dto.topic}</p>
					</div>
				</div>
			</div>
		</a>
		</c:if>
	</c:forEach>
</div>
<br/><br/>

</div>
</body>
</html>
<%@ include file="/include/footer.jsp" %>