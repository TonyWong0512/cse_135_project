<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="product" method="post">
<label>Name</label><input type="text" name="name"></input>
<label>SKU</label><input type="text" name="sku"></input>
<label>List price</label><input type="text" name="price"></input>
<label>Category</label>
<select name="category">
<c:forEach var="category" items="${categories}">
	<option value="<c:out value="${category.ID}" />"><c:out value="${category.name}" /></option>
</c:forEach>
</select>
<input type="submit" value="Add product">
</form>

</body>
</html>