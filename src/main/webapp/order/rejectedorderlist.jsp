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
<title>Rejected Order List</title>
</head>
<body>
<%@include file="../headerfooter/header.jsp"%>

<div class="container mt-4">
		<table class="table table-primary">
			<caption>Rejected Order List</caption>

			<thead class="thead-dark">
				<tr>
					<th scope="col">Sl.No</th>
					<th scope="col">Customer Name</th>
					<th scope="col">Ordered Date</th>
					<th scope="col">View Order</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="temp" items="${rejecteditems}" varStatus="loop">
					<c:set value="${temp.getCreationtime()}" var="dateString" />
					<fmt:parseDate value="${dateString}" var="dateObject" pattern="yyyy-MM-dd" />
					<tr>
					<td>${loop.index+1}</td>
					<td>${temp.getCustomerid().getName()}</td>
					<td><fmt:formatDate value="${dateObject}" pattern="dd-MM-yyyy"/></td>
					<td><a href="/individualorderdetails?oid=${temp.getId()}">View Order</a></td>
				</tr>
			</c:forEach>
			</tbody>


		</table>
	</div>
<%@include file="../headerfooter/footer.jsp"%>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>

</html>