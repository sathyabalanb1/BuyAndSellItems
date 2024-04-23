<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="../css/topnavigation.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet"
	href="https://site-assets.fontawesome.com/releases/v6.1.1/css/all.css">
<meta charset="UTF-8">
<title>Header</title>
</head>
<body>

<security:authorize access="isAnonymous()">
<nav class="navbar">
       	    <img src="../images/dsquadlogo.png" alt="Dsquad"/>
       	    <ul class="nav-links">
       <div class="menu">
       <li><a href="/registerform">Register</a></li>
       <li><a href="/loginform">Login</a></li>
       </div>
       </ul>
       </nav>
</security:authorize>

<security:authorize access="isAuthenticated() and hasAuthority('Dealer')">
	
	<security:authentication property="principal.username" var="username" />
	
	<nav class="navbar" style="width:100%;">
	    <img src="../images/dsquadlogo.png" alt="Dsquad"/>
	    <span style="padding-left:2%;"><i class="fa fa-user-alt" style="margin-right:5px;"></i>${username}</span>	    
	    <ul class="nav-links" style="padding-left:18%;">
	      <div class="menu">
	        
	        <li class="services">
	        <a href="">Product</a>
	        <ul class="dropdown">
	        <li><a href="/createproductform">Add Product</a></li>
	        <li><a href="/productlist">Product List</a></li>
	        </ul>	
	        </li>
	        
	        <li class="services">
	        <a href="">Orders</a>
	        <ul class="dropdown">
	        <li><a href="/orderdetails">All Orders</a></li>
	        <li><a href="/approvedorderlist">Approved Orders</a></li>
	        <li><a href="/rejectedlist">Rejected Orders</a></li>
	        <li><a href="/waitinglist">Waiting Orders</a></li>
	        </ul>	
	        </li>
	        	        
	        <li><a href="/logout">Logout</a></li>
	        
	        </div>
    </ul>
  </nav>
	        
    </security:authorize>
    
<security:authorize access="isAuthenticated() and hasAuthority('Customer')">
	
	<nav class="navbar" style="width:100%;">
	    <img src="../images/dsquadlogo.png" alt="Dsquad"/>
	    <span style="padding-left:2%;"><i class="fa fa-user-alt" style="margin-right:5px;"></i>${username}</span>	    
	    <ul class="nav-links" style="padding-left:18%;">
	      <div class="menu">
	        
	        <li><a href="/displayorderplacementform">Make Order</a></li>
	        	        
	        <li><a href="/logout">Logout</a></li>
	        
	        </div>
    </ul>
  </nav>
</security:authorize>



</body>
</html>