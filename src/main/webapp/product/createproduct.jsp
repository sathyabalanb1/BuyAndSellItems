<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Create Product</title>
</head>
<body>
<%@include file="../headerfooter/header.jsp"%>

<div class="container my-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<div class="card-header bg-primary text-white">
						<h4 class="mb-0">Create Product</h4>
					</div>
					<div class="card-body">
						<form name="myForm" id="myForm" action="/addproduct" method="post">
							<div class="mb-3">
								<label for="productname" class="form-label">Product Name</label> 
								<input type="text" name="productname" id="productname"class="form-control">
								<span class="error" id="nameError" style="color:red;"></span>
							</div>
							<div class="mb-3">
								<label for="price" class="form-label">Price</label> 
								<input type="text" class="form-control" name="price" id="price">
								<span class="error" id="priceError" style="color:red;"></span>
							</div>
							<div class="mb-3">
								<label for="productquantity" class="form-label">Quantity</label> 
								<input type="text" class="form-control" name="productquantity" id="productquantity">
								<span class="error" id="quantityError" style="color:red;"></span>
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
<%@include file="../headerfooter/footer.jsp"%>

</body>
<script src="./javascriptfiles/Productformvalidation.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
</html>