<%@include file="header.jsp" %>
<div class="row">

	<div class="span3">
		<c:forEach var="category" items="${categories}">
			<a href="product?category=<c:out value="${category.name}" />"><c:out value="${category.name}" /></a><br>
		</c:forEach>
	</div>

	<form action="product" method="post" class="span6">
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

</div>

<div class="row">
<c:forEach var="product" items="${products}">
<div class="row span4">
<c:out value="${product.name}" /><br>
<c:out value="${product.SKU}" /><br>
<c:out value="${product.price}" /><br>
</div>
</c:forEach>
</div>
<%@include file="footer.jsp" %>
