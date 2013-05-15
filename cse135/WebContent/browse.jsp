<%@include file="header.jsp"%>
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
			<a href="browse?name=<%=name%>&category=">All products</a><br>
			<c:forEach var="category" items="${categories}">
				<a
					href="browse?name=<%=name%>&category=<c:out value="${category.id}" />"><c:out
						value="${category.name}" /></a>
				<br>
			</c:forEach>
		</div>

		<form action="browse" method="get" class="span4">
			<h2>Search</h2>
			<label>Name</label> <input type="text" name="name" value="<%=name%>"></input><br>
			<input type="hidden" name="category" value="<%=category%>"></input> <input
				type="submit" value="Search">
		</form>
	</div>

	<div class="row">
		<h2>Results</h2>
		<c:forEach var="product" items="${products}">
			<div class="span4">
				<a href="order?action=add&id=<c:out value="${product.id}" />"><c:out
						value="${product.name}" /></a><br>
				<c:out value="${product.SKU}" />
				<br> $
				<c:out value="${product.price}" />
				<br>
			</div>
		</c:forEach>
	</div>
</div>
<%@include file="footer.jsp"%>
