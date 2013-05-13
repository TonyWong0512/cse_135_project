<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="bootstrap.min.css" rel="stylesheet">
</head>
<body>
<form action="product" method="post">
<label>Name</label><input type="text" name="name"></input><br>
<label>SKU</label><input type="text" name="sku"></input><br>
<label>List price</label><input type="text" name="price"></input><br>
<label>Category</label>
<select name="category">
<c:forEach var="category" items="${categories}">
	<option value="<c:out value="${category.ID}" />"><c:out value="${category.name}" /></option>
</c:forEach>
</select><br>
<input type="submit" value="Add product">
</form>
<div class="row">
<c:forEach var="product" items="${products}">
<div class="row span4">
<c:out value="${product.name}" /><br>
<c:out value="${product.SKU}" /><br>
<c:out value="${product.price}" /><br>
<c:out value="${product.category}" /><br>
</div>
</c:forEach>
</div>
</body>
</html>