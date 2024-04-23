<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Login Form</title>
</head>
<body>

	<c:if test="${param.error!=null}">
		<p style="color: red;">Username or Password May be Incorrect</p>
	</c:if>
	
<%@include file="../headerfooter/header.jsp"%>


	<div class="container my-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<div class="card-header bg-primary text-white">
						<h4 class="mb-0">Login</h4>
					</div>
					<div class="card-body">
						<form action="/login" method="post">
							<div class="mb-3">
								<label for="username" class="form-label">User Name</label> 
								<input
									type="text" class="form-control" name="username" id="username"
									aria-describedby="emailHelp">
							</div>
							<div class="mb-3">
								<label for="password" class="form-label">Password</label> 
								<input type="password" class="form-control" name="password" id="password">
							</div>
							<div class="mb-3 snackappformbutton">
							<button type="submit" class="btn btn-primary">Login</button>
							</div>
						</form>
						<div class="mb-3">
							<a href="/oauth2/authorization/google"
									class="btn btn-primary btn-block"><i class="fa fa-google"></i>
									Sign in with <b>Google</b></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../headerfooter/footer.jsp"%>
	
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>