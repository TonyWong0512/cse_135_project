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
					<form action="/cse135/order" method="POST">
						<input type="hidden" name="id"
							value="<c:out value="${product_to_buy.id}" />"> <input
							type="hidden" name="action" value="add_to_cart" />
						<td><c:out value="${product_to_buy.name}" /></td>
						<td><input type="number" name="quantity"
							style="width: 45px; padding: 1px" value="0"></td>
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
					<th>Product</th>
					<th>Price</th>
					<th>Amount</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cart}" var="order">
					<tr>
						<td><c:out value="${order.product.name}" /></td>
						<td><c:out value="${order.product.price}" /></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<form class="form-horizontal span6" action="/cse135/order"
			method="POST">
			<fieldset>
				<input type="hidden" name="action" value="confirm_payment" />
				<legend>Payment</legend>
				<div class="control-group">
					<label class="control-label">Card Holder's Name</label>
					<div class="controls">
						<input type="text" class="input-block-level" pattern="\w+ \w+.*"
							title="Fill your first and last name" required>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Card Number</label>
					<div class="controls">
						<div class="row-fluid">
							<div class="span3">
								<input type="text" class="input-block-level" autocomplete="off"
									maxlength="4" pattern="\d{4}" title="First four digits"
									required>
							</div>
							<div class="span3">
								<input type="text" class="input-block-level" autocomplete="off"
									maxlength="4" pattern="\d{4}" title="Second four digits"
									required>
							</div>
							<div class="span3">
								<input type="text" class="input-block-level" autocomplete="off"
									maxlength="4" pattern="\d{4}" title="Third four digits"
									required>
							</div>
							<div class="span3">
								<input type="text" class="input-block-level" autocomplete="off"
									maxlength="4" pattern="\d{4}" title="Fourth four digits"
									required>
							</div>
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Card Expiry Date</label>
					<div class="controls">
						<div class="row-fluid">
							<div class="span9">
								<select class="input-block-level">
									<option>January</option>
									<option>February</option>
									<option>March</option>
									<option>April</option>
									<option>May</option>
									<option>June</option>
									<option>July</option>
									<option>August</option>
									<option>September</option>
									<option>October</option>
									<option>November</option>
									<option>December</option>
								</select>
							</div>
							<div class="span3">
								<select class="input-block-level">
									<option>2013</option>
									<option>2014</option>
									<option>2015</option>
									<option>2016</option>									
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Card CVV</label>
					<div class="controls">
						<div class="row-fluid">
							<div class="span3">
								<input type="text" class="input-block-level" autocomplete="off"
									maxlength="3" pattern="\d{3}"
									title="Three digits at back of your card" required>
							</div>
						</div>
					</div>
				</div>
				<div class="form-actions">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</fieldset>
		</form>
	</c:if>
	<c:if test="${cart == null}">
		<h4>Your cart is empty</h4>
	</c:if>
</div>
<%@include file="footer.jsp"%>