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
<meta charset="UTF-8">
<title>Make Order</title>
<style type="text/css">
.customerid{
display:none;
}
</style>

</head>
<body>
<%@include file="../headerfooter/header.jsp"%>

<span class="error" id="requiredquantityerror" style="color:red;"></span>

<span class="error" id="ordersuccessmessage" style="color:red;"></span>		

<span class="error" id="emptycheckboxerror" style="color:red;"></span>							
					
							

<div class="container mt-4">

<form name="myForm" id="myForm" onsubmit="submitForm(event)">
		<table class="table table-primary">
		<thead class="thead-dark">
			<tr>
				<th scope="col">Sl.No</th>
				<th scope="col">Product Name</th>
				<th scope="col">Price</th>
				<th scope="col">Available Quantity</th>
				<th scope="col">Required Quantity</th>
				<th scope="col">Select Product</th>
			</tr>
		</thead>
		<tbody>

				<c:forEach var="temp" items="${products}" varStatus="loop">
				<tr>
					<td> ${loop.index+1}</td>
					<td><input name="productname_${temp.id}" value="${temp.productname}" readonly></td>
					<td><input name="productprice_${temp.id}" value="${temp.getPriceid().getPrice()}" readonly></td>		
					<td><input name="availablequantity" value="${temp.getInventoryid().getQuantity()}" readonly></td>
					<td>
					  <input type="number" id="quantity_${temp.id}" name="productquantity"  min="1" max="50"> 
					</td>
					<td>
					<input type="checkbox" name="productid" id="product_${temp.id}" value="${temp.id}">
					</td>
					<td class="customerid"><input  type="hidden" name="customerid" value="${customerid}" readonly></td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="6"><input class="btn btn-success" type="submit" id="submitButton" value="Make Order"></td>
				</tr>
			</tbody>
		</table>
			
</form>
</div>
<%@include file="../headerfooter/footer.jsp"%>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="./javascriptfiles/Orderedproducts.js"></script>
</html>