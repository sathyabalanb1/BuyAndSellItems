<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

<meta charset="UTF-8">
<title>Forgot Password</title>
</head>
<body>
<%@include file="../headerfooter/header.jsp"%>

<c:if test="${param.error != null}">
		<p style="color: red;">Token Expired.Please click on Forgot Password again</p>
</c:if>

<c:if test="${message !=null}">
		<p style="color: green;">Email sent to you to Reset Password.Please check Spam folder if mail not visible!</p>
</c:if>

<c:if test="${tokenerror !=null}">
		<p style="color: red;">Invalid Token</p>
</c:if>

<c:if test="${param.mailiderror != null}">
		<p style="color: red;">User Not Exist</p>
</c:if>


<div class="container my-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<div class="card-header bg-primary text-white">
						<h4 class="mb-0">Forgot Password</h4>
					</div>
					<div class="card-body">
						<form name="myForm" id="myForm" action="/forgot_password" method="post">
							<div class="mb-3">
								<label for="email" class="form-label">Email ID</label> 
								<input type="text" name="email" id="email" class="form-control">
								<span class="error" id="mailError" style="color: red;"></span>
							</div>
							<div class="mb-3 snackappformbutton">
							<button type="submit" onclick="isValidMail(event)" class="btn btn-primary">Submit</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
	<%@include file="../headerfooter/footer.jsp"%>
<script src="../javascriptfiles/forgotpassworduservalidation.js"></script>


</html>