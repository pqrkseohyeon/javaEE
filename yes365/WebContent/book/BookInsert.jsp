<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<%@include file="../include/sidebar.jsp" %>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>도서등록</title>

  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/modern-business.css" rel="stylesheet">

</head>

<body>

    <!-- Contact Form -->
    <!-- In order to set the email address and subject line for the contact form go to the bin/contact_me.php file. -->
     <div class="col-lg-9 mb-4">
        <h2>도서등록</h2>
         <div class="row">
      <div class="col-lg-8 mb-4">
      
        <form name="sentMessage" id="contactForm" novalidate method="post" action="upload.do" enctype="multipart/form-data">
          <div class="control-group form-group">
            <div class="controls">
              <label>책제목:</label>
              <input type="text" class="form-control" id="title"  name="title" required data-validation-required-message="Please enter your name.">
              <p class="help-block"></p>
            </div>
          </div>
          <div class="control-group form-group">
            <div class="controls">
              <label>저자명:</label>
              <input type="text" class="form-control" id="Author" name="Author" required data-validation-required-message="Please enter your phone number.">
            </div>
          </div>
          <div class="control-group form-group">
            <div class="controls">
              <label>출판사:</label>
              <input type="text" class="form-control" id="publisher" name="publisher" required data-validation-required-message="Please enter your email address.">
            </div>
          </div>
           <div class="control-group form-group">
            <div class="controls">
              <label>출판일:</label>
              <input type="text" class="form-control" id="p_date" name="p_date" required data-validation-required-message="Please enter your email address.">
            </div>
          </div>
           <div class="control-group form-group">
            <div class="controls">
              <label>도서 가격:</label>
              <input type="text" class="form-control" id="price" name="price" required data-validation-required-message="Please enter your email address.">
            </div>
          </div>
           <div class="control-group form-group">
            <div class="controls">
              <label>책 이미지:</label><br/>
				<input type="file" name="uploadFile"><br/>
				<input type="submit" value="전송" >
            </div>
          </div>
          <div class="control-group form-group">
            <div class="controls">
              <label>도서 정보:</label>
              <textarea rows="10" cols="100" class="form-control" id="info" name="info" required data-validation-required-message="Please enter your message" maxlength="999" style="resize:none"></textarea>
            </div>
          </div>
          <div id="success"></div>
          <!-- For success/fail messages -->
          <button type="submit" class="btn btn-primary" id="sendMessageButton">책 업로드</button>
        </form>
      </div>
        
       
      </div>
    </div>
   
    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->

 
</body>
<%@include file="../include/footer.jsp" %>
</html>
