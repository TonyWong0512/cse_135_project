<%@include file="header.jsp"%>

<div class="container-fluid">
	<div class="span12">
		<div class="row">
			<div class="span4">
				<form action="dashboard" method="get" class="form-inline">
					<select name="rows">
						<option value="c" ${salesByStates!=null ? "" : "selected"}>Customers</option>
						<option value="s" ${salesByStates!=null ? "selected" : ""}>States</option>
					</select>
					<button type="submit" class="btn">Update</button>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="row">
				<div class="span12">
					<form action="dashboard" method="get" class="form-inline">
						<fieldset>

							<legend>Filter</legend>
							<div class="span10">
								<input type="hidden" name="rows"
									${salesByStates!=null ? 'value="s"' : 'value="c"'}> <select
									name="age" class="span2">
									<option value="">All ages</option>
									<%
										int i = 0;
										while (i < 99) {
									%>
									<option value="<%=i%>"><%=i + "-" + (i + 9)%></option>
									<%
										i += 10;
										}
									%>
								</select> <select name="state" class="span2">
									<option value="">All states</option>
									<c:forEach var="state2" items="${states}">
										<option value="<c:out value="${state2}" />"
											${state.equals(state2) ? "selected" : ""}>
											<c:out value="${state2}" />
										</option>
									</c:forEach>
								</select> <select name="category" class="span2">
									<option value="">All categories</option>
									<c:forEach var="category2" items="${categories}">
										<option value="<c:out value="${category2.id}" />" ${(category == category2.id) ? "selected" : ""}>
											<c:out value="${category2.name}" />
										</option>
									</c:forEach>
								</select> <select name="quarter" class="span2">
									<option value="">Full Year</option>
									<option value="w" ${quarter.equals("w") ? "selected" : ""}>Winter</option>
									<option value="sp" ${quarter.equals("sp") ? "selected" : ""}>Spring</option>
									<option value="s" ${quarter.equals("s") ? "selected" : ""}>Summer</option>
									<option value="f" ${quarter.equals("f") ? "selected" : ""}>Fall</option>
								</select>
							</div>
							<div class="span1">
								<button type="submit" class="btn">Update</button>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
			<div class="row">
				<div class="span11">
					<table class="table table-hover table-bordered">
						<thead>
							<tr>
								<th>${salesByStates!=null ? "State" : "Customer"}</th>
								<c:forEach items="${products}" var="product">
									<th><c:out value="${product.product.name}" /></th>
								</c:forEach>

							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${salesByStates!=null}">
									<c:forEach items="${salesByStates}" var="sale">
										<tr>
											<td><c:out value="${sale.state}" /></td>
											<td>Apple</td>
											<td>Chair</td>
											<td>Table</td>
											<td>Table</td>
											<td>Apple</td>
											<td>Chair</td>
											<td>Table</td>
											<td>Table</td>
											<td>Table</td>
											<td>Table</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:forEach items="${customers}" var="customer">
										<tr>
											<td><c:out value="${customer.customer.name}" /></td>
											<td>Apple</td>
											<td>Chair</td>
											<td>Table</td>
											<td>Table</td>
											<td>Apple</td>
											<td>Chair</td>
											<td>Table</td>
											<td>Table</td>
											<td>Table</td>
											<td>Table</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
				<div class="span1">
					<a type="submit" class="btn"
						href="?rows=${rows}&roff=${roff}&coff=${coff+10}&age=${age}&state=${state}&category=${category}&quarter=${quarter}">Next
						10 columns</a>
				</div>
			</div>
			<div class="row">
				<div class="span3">
					<a type="submit" class="btn"
						href="?rows=${rows}&roff=${roff+10}&coff=${coff}&age=${age}&state=${state}&category=${category}&quarter=${quarter}">Next
						10 rows</a>

				</div>
			</div>
		</div>
	</div>


</div>
<%@include file="footer.jsp"%>
