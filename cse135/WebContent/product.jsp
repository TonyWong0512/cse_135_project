<%@include file="header.jsp" %>
<%
String name = request.getParameter("name");
String category = request.getParameter("category");
name = (name == null) ? "" : name.trim();
category = (category == null) ? "" : category.trim();
%>
<div class="container-fluid">
<h1>Products</h1>
<div class="row">
	<div class="span3">
		<h2>Categories</h2>
		<a href="product?name=<%=name%>&category=">All products</a><br>
		<c:forEach var="category" items="${categories}">
			<a href="product?name=<%=name%>&category=<c:out value="${category.ID}" />"><c:out value="${category.name}" /></a><br>
		</c:forEach>
	</div>
	
	<form action="product" method="get" class="span4">
		<h2>Search</h2>
		<label>Name</label>
		<input type="text" name="name" value="<%=name%>"></input><br>
		<input type="hidden" name="category" value="<%=category%>"></input>
		<input type="submit" value="Search">
	</form>
	
	<form action="product" method="post" class="span4">
		<h2>Add</h2>
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
<h2>Results</h2>
	<c:forEach var="product" items="${products}">
	<div class="span4">
		<c:out value="${product.name}" /><br>
		<c:out value="${product.SKU}" /><br>
		<c:out value="${product.price}" /><br>
		<a href="editproduct?ID=<c:out value="${product.ID}" />">Edit</a>
		<a href="deleteproduct?ID=<c:out value="${product.ID}" />">Delete</a>
	</div>
	</c:forEach>
</div>
</div>
<%@include file="footer.jsp" %>
