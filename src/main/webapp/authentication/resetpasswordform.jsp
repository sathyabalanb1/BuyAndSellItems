<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" ></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>

<meta charset="UTF-8">
<title>Reset Password Form</title>
</head>
<body>
<%@include file="../headerfooter/header.jsp"%>

<div class="container my-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<div class="card-header bg-primary text-white">
						<h4 class="mb-0">Reset  Password</h4>
					</div>
					<div class="card-body">
						<form name="myForm" id="myForm" action="/resetpassword" method="post">
							<input type="hidden" name="token" value="${token}" />
							<div class="mb-3">
								<label for="password" class="form-label">New Password</label> 
								<input type="password" class="form-control" name="password" id="password" required autofocus>
							</div>
							<div class="mb-3">
								<label for="confirm-password" class="form-label">Confirm Password</label> 
								<input type="password" class="form-control" name="confirm-password" id="confirm-password" oninput="checkPasswordMatch(this);" required>
								<span class="error" id="passwordError" style="color:red;"></span>
							</div>
							<div class="mb-3 snackappformbutton">
							<button type="submit" class="btn btn-primary">Submit</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
  <script src="../javascriptfiles/resetpasswordformvalidation.js"></script>
  
	<%@include file="../headerfooter/footer.jsp"%>

</html>