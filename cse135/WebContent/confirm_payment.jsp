<%@include file="header.jsp"%>

<div class="container-fluid" style="width: 60%">
	<h1>Thanks for shopping with us!</h1>
	<h3>Your Shopping Cart</h3>
	<table class="table">
		<thead>
			<tr>
				<th>Product</th>
				<th>Price</th>
				<th>Amount</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${last_cart}" var="order">
				<tr>
					<td><c:out value="${order.product.name}" /></td>
					<td><c:out value="${order.product.price}" /></td>
					<td><c:out value="${order.quantity}" /></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
</div>
<%@include file="footer.jsp"%>