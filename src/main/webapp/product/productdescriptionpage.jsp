<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" ></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<link href="//cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css"
	rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<title>Registration</title>  
</head>

<body>
<%@include file="../headerfooter/header.jsp"%>
<c:if test="${info == true}">
<p>You have successfully registered</p>
</c:if>

<c:if test="${param.error != null}">
<p>[[${session.SPRING_SECURITY_LAST_EXCEPTION.message}]]</p>
</c:if>
 <!--     
	<form name="myForm" id="myForm" action="/addDsuser" method="post">
		<table>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name" id="name"></td>
				<td><span class="error" id="nameError" style="color:red;"></span></td>
				<br>
				<br>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name="email" id="email"></td>
				<td><span class="error" id="emailError" style="color:red;"></span></td>
				<td><c:if test="${info == false}">
		<p style="color:red;">Mail ID Already Exist</p>
			            </c:if>
		</td>
				<br>
				<br>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" id="password"></td>
				<td><span class="error" id="passwordError" style="color:red;"></span></td>
				<br>
				<br>
			</tr>
			<tr>
				<td>Submit</td>
				<td><input type="submit" value="register"></td>
			</tr>			
		</table>
	</form>
-->	
   <span class="error" id="requiredquantityerror" style="color:red;"></span>
   <span class="error" id="cartsuccessmessage" style="color:green;"></span>
   
	<div class="container my-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<div class="card-header bg-primary text-white">
						<h4 class="mb-0">Product Description</h4>
					</div>
					<div class="card-body">
						<form name="myForm" id="myForm" action="" method="">
							<div class="mb-3">
								<label for="name" class="form-label">Product Name</label> 
								<input type="text" name="name" value="${product.productname}" id="name"class="form-control" readonly>
							</div>
							<div class="mb-3">
								<label for="price" class="form-label">Product Price</label> 
								<input type="text" name="name" value="${product.priceid.price}" id="price" class="form-control" readonly>
							</div>
							<div class="mb-3">
								<label for="aquantity" class="form-label">Available Quantity</label> 
								<input type="text" name="name" value="${product.inventoryid.quantity}" id="aquantity" class="form-control" readonly>
							</div>
							<div class="mb-3">
								<label for="rquantity" class="form-label">Required Quantity</label> 
								<input type="number" onchange="compareQuantity()" id="rquantity" name="rquantity"  min="1" max="50">
								<span class="error" id="rquantityError" style="color:red;"></span> 
								<input type="hidden" name="productid" id="productid" value="${product.id}">
								<input type="hidden" name="customerid" id="customerid" value="${customerid}">
							</div>
							<div class="mb-3 snackappformbutton">
							<button type="submit" id="submitButton" class="btn btn-primary">Add To Cart</button>
							</div>
							
							<div class="mb-3 snackappformbutton">
							<a class="btn btn-primary" href="" onclick="return confirm('Are you sure to buy this Product?')">Buy Now</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
<script>

</script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
<%@include file="../headerfooter/footer.jsp"%>
<script src="../javascriptfiles/Cartformvalidation.js"></script>

<script src="../javascriptfiles/Descriptionformvalidation.js"></script>
</html>