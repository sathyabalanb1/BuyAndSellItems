<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<link href="//cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Order Details</title>
</head>
<body>
<c:set value="${ordereddate}" var="dateString" />
<fmt:parseDate value="${dateString}" var="dateObject" pattern="yyyy-MM-dd" />
<%@include file="../headerfooter/header.jsp"%>


<div class="container mt-4">
<b>Name:</b>	${customername}<br>
<b>Ordered Date:</b>	<fmt:formatDate value="${dateObject}" pattern="dd-MM-yyyy"/><br>
<b>Order Status:</b>	${orderstatus}<br>
<b>Ordered Products:</b><br><br>
<table class="table table-primary">
<caption></caption>
<thead class="thead-dark">
				<tr>
					<th scope="col">Sl.No</th>
					<th scope="col">Product Name</th>
					<th scope="col">Required Quantity</th>
				</tr>
</thead>

<c:forEach var="temp" items="${orderedproducts}" varStatus="loop">
				<tr>
					<td>${loop.index+1}</td>
					<td>${temp.getProductid().getProductname()}</td>
					<td>${temp.getQuantity()}</td>
				</tr>
</c:forEach>
</table>
<c:if test="${orderstatus == 'Waiting for Approval'}">
<td><a class="btn btn-success" href="/approveorder?oid=${orderid}">ApproveOrder</a></td>   <td><a class="btn btn-danger" href="/disapproveorder?oid=${orderid}">DisApproveOrder</a></td>
</c:if>
</div>


<%@include file="../headerfooter/footer.jsp"%>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
</html>