<%@include file="header.jsp"%>
<h1>Products</h1>

<div class="container-fluid" style="width: 60%">
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
					<td>Product 1</td>
					<td><input type="number" style="width: 45px; padding: 1px"
						value="0"></td>
					<td><input type="submit" value="Add"></td>
				</form>
			</tr>
		</tbody>
	</table>

	<table class="table">
		<thead>
			<tr>
				<th>
					<h3>Your Shopping Cart</h3>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Product 1</td>
				<td>Small</td>
				<td><input type="number" style="width: 45px; padding: 1px"
					value="0"></td>
				<td>Medium</td>
				<td><input type="number" style="width: 45px; padding: 1px"
					value="0"></td>
				<td>Large</td>
				<td><input type="number" style="width: 45px; padding: 1px"
					value="0"></td>
				<td>Ultra</td>
				<td><input type="number" style="width: 45px; padding: 1px"
					value="0"></td>
			</tr>
			<tr>
				<td>Product 2</td>
				<td>Small</td>
				<td><input type="number" style="width: 45px; padding: 1px"
					value="0"></td>
				<td>Medium</td>
				<td><input type="number" style="width: 45px; padding: 1px"
					value="0"></td>
				<td>Large</td>
				<td><input type="number" style="width: 45px; padding: 1px"
					value="0"></td>
				<td>Ultra</td>
				<td><input type="number" style="width: 45px; padding: 1px"
					value="0"></td>
			</tr>
		</tbody>
	</table>

</div>
<%@include file="footer.jsp"%>