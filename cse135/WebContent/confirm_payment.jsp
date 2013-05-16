<%@include file="header.jsp"%>

<div class="container-fluid" style="width: 60%">
	<h1>Thanks for shopping with us!</h1>
	<h3>Your Shopping Cart</h3>
	<c:if test="${cart != null}">
		<table class="table">
			<thead>
				<tr>
					<th>Product</th>
					<th>Price</th>
					<th>Ammount</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cart}" var="order">
					<tr>
						<td><c:out value="${order.product.name}" /></td>
						<td><c:out value="${order.product.price}" /></td>
						<td><c:out value="${order.quantity}" /></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
	</c:if>
</div>
<%@include file="footer.jsp"%>