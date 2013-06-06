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
			<select name="category" id="category_menu">
				<option value="product?name=<%=name%>&category=">All products</option>
				<c:forEach var="cat" items="${categories}">
					<option value="product?name=<%=name%>&category=<c:out value="${cat.id}" />" <c:if test="${cat.id == category}">selected</c:if> ><c:out value="${cat.name}" /></option>
				</c:forEach>
			</select>
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
			<label>Name</label><input type="text" name="name" id="name"></input><br>
			<label>SKU</label><input type="text" name="sku" id="sku"></input><br>
			<label>List price</label><input type="text" name="price" id="price"></input><br>
			<label>Category</label>
			<select name="category" id="category">
			<c:forEach var="cat" items="${categories}">
				<option value="<c:out value="${cat.id}" />"><c:out value="${cat.name}" /></option>
			</c:forEach>
			</select><br>
			<input type="submit" onClick="productAction(null,'insert'); return false;" value="Add product">
		</form>

	</div>

	<div class="row">
		<!-- <c:forEach var="product" items="${products}">
		<div class="span4">
			<c:out value="${product.name}" /><br>
			<c:out value="${product.SKU}" /><br>
			$<c:out value="${product.price}" /><br>
			<a href="editproduct?id=<c:out value="${product.id}" />">Edit</a>
			<a href="deleteproduct?id=<c:out value="${product.id}" />">Delete</a>
		</div>
		</c:forEach> -->
		<div class="span12">
				<table class="table" id="products_table">
					<tr>
						<th>ID</th>
						<th>SKU</th>
						<th>Name</th>
						<th>Price</th>
						<th>Category</th>
						<th>Options</th>
					</tr>
					<c:forEach items="${products}" var="product">
						<tr id="<c:out value="${product.id}" />">
			                <td>
			                    <c:out value="${product.id}" />
			                </td>
			                <td>
			                    <input id="sku_<c:out value="${product.id}" />" value="<c:out value="${product.SKU}" />" name="sku" size="15"/>
			                </td>
			                <td>
			                    <input id="name_<c:out value="${product.id}" />" value="<c:out value="${product.name}" />" name="name" size="15"/>
			                </td>
			                <td>
			                    <input id="price_<c:out value="${product.id}" />" value="<c:out value="${product.price}" />" name="price" size="15"/>
			                </td>
			                <td>
			                	<select name="category" id="category_<c:out value="${product.id}" />">
									<c:forEach var="cat" items="${categories}">
										<option value="<c:out value="${cat.id}" />" <c:if test="${cat.id == product.category}">selected</c:if>><c:out value="${cat.name}" />
										</option>
									</c:forEach>
								</select>
			                </td>
			                <td>
			                	<input onClick="productAction(<c:out value="${product.id}" />,'update');" type="button" value="Update"/>	                
			                	<input onClick="productAction(<c:out value="${product.id}" />,'delete'); return false;" type="button" value="Delete"/>
			                </td>			                
			            </tr>
					</c:forEach>
				</table>
			</div>
	</div>
</div>
<h3 id="response"></h3>
<script type="text/javascript" src="products_js.js"></script>
<script>
    document.getElementById("category_menu").onchange = function() {
        window.location.href = this.value;
    };
</script>
<%@include file="footer.jsp" %>
