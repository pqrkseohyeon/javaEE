<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>영어 수업</title>
  <!-- Bootstrap core CSS -->
  <link href="/Tutoring/vendor/bootstrap/css/bootstrap.css"  rel="stylesheet">
  <!-- Custom fonts for this template -->
  <link href="/Tutoring/vendor/fontawesome-free/css/all.css" rel="stylesheet">
  <link href="/Tutoring/vendor/simple-line-icons/css/simple-line-icons.css"  rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic"  rel="stylesheet" type="text/css">
  <!-- Custom styles for this template -->
  <link href="/Tutoring/css/landing-page.css" rel="stylesheet">
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  


</head>
<body>

  <!-- Navigation -->
  <nav class="navbar navbar-light bg-light static-top">
    <div class="container">
         <a class="navbar-brand" href="/Tutoring/index.jsp">Wiser English</a>
         <a class="navbar-nav" href="/Tutoring/include/about.jsp">와이저</a>
         <a class="navbar-nav" href="/Tutoring/class/courseList">과정</a>
    </div>
      <c:choose>
     	<c:when test="${empty sessionScope.userid}">
		    <ul class="navbar-nav">
		      <li class="nav-item">
		      	<a class="btn btn-gray" href="/Tutoring/member/login">로그인</a>
			    <a class="btn btn-primary" href="/Tutoring/member/insert">회원가입</a>
		      </li>
		  	</ul>
    	</c:when>
    	<c:when test="${sessionScope.admin==1}">
		    <ul class="navbar-nav">
		      <li class="nav-item">
		      	<a class="btn btn-gray" href="/Tutoring/member/Adminview">관리자</a>
			    <a class="btn btn-primary" href="/Tutoring/member/logout">로그아웃</a>
		      </li>
		  	</ul>
    	</c:when>
    	<c:when test="${sessionScope.admin==0}">
    		<ul class="navbar-nav">
		      <li class="nav-item">
		      	<a class="btn btn-gray" href="/Tutoring/member/view">내계정</a>
			    <a class="btn btn-info" href="/Tutoring/member/logout">로그아웃</a>
		      </li>
		  	</ul>
    	</c:when>
     </c:choose>  
  	
  </nav>
  
<!-- Bootstrap core JavaScript -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/Tutoring/vendor/jquery/jquery.js"></script>
<script src="/Tutoring/vendor/bootstrap/js/bootstrap.bundle.js"></script>