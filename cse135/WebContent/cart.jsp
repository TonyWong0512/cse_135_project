<%@include file="header.jsp"%>
<h1>Products</h1>

<div class="container-fluid" style="width: 60%">
	<c:if test="${product_to_buy != null}">
		<h1>Do you really want to add this product to your Shopping Cart?</h1>
		<table class="table">
			<thead>
				<tr>
					<th>Name</th>
					<th>Quantity</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<form action="/cse135/order" method="GET">
						<input type="hidden" name="id" value="<c:out value="${product_to_buy.id}" />">
						<input type="hidden" name="action" value="add_to_cart" />
						<td><c:out value="${product_to_buy.name}" /></td>
						<td><input type="number" style="width: 45px; padding: 1px"
							value="0"></td>
						<td><input type="submit" value="Add"></td>
					</form>
				</tr>
			</tbody>
		</table>
	</c:if>


	<h3>Your Shopping Cart</h3>
	<c:if test="${cart != null}">
		<table class="table">
			<thead>
				<tr>
					<th>Name</th>
					<th>Quantity</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${cart}" var="product">
				<tr>
					<td><c:out value="${product.name}" /></td>
					<td><c:out value="${product.id}" /></td>				
				</tr>
			</tbody>
			</c:forEach>			
		</table>
	</c:if>
	<c:if test="${cart == null}">
		<h4>Your cart is empty</h4>
	</c:if>
</div>
<%@include file="footer.jsp"%>