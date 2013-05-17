<%@include file="header.jsp"%>
<% model.Product product = (model.Product)request.getAttribute("product"); %>

<form action="editproduct" method="post" class="span4">
	<h2>Edit</h2>
	<input type="hidden" name="id" value="<%= product.getId() %>">
	<label>Name</label><input type="text" name="name" value="<%=product.getName()%>"></input><br>
	<label>SKU</label><input type="text" name="sku" value="<%=product.getSKU()%>"></input><br>
	<label>List price</label><input type="text" name="price" value="<%=product.getPrice()%>"></input><br>
	<label>Category</label>
	<select name="category">
	<c:forEach var="category" items="${categories}">
		<option value="<c:out value="${category.id}" />" <c:if test="${category.id} == ${product.id}">selected="selected"</c:if>><c:out value="${category.name}" />
		</option>
	</c:forEach>
	</select><br>
	<input type="submit" value="Update product">
</form>