<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix ="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
<form:form action ="${pageContext.request.contextPath}/logout" method="POST">
<input type ="submit" value ="Logout" class="add-button" style="float:right;"/>
</form:form>
<a href="http://localhost:8080/YourMart-PMP-Admin-Panel/seller/list">View Sellers</a><br>
<a href="http://localhost:8080/YourMart-PMP-Admin-Panel/product/list">View Products</a><br>
<a href="http://localhost:8080/YourMart-PMP-Admin-Panel/categories">View Categories</a><br>
</body>
</html>