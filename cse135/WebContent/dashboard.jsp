<%@include file="header.jsp"%>
<%
	String roffs = request.getParameter("roff");
	roffs = (roffs == null) ? "" : roffs.trim();
	int roff = 0;
	try {
		roff = Integer.parseInt(roffs);
	} catch (Exception e) {

	}
	String coffs = request.getParameter("coff");
	coffs = (coffs == null) ? "" : coffs.trim();
	int coff = 0;
	try {
		coff = Integer.parseInt(coffs);
	} catch (Exception e) {

	}

	String age = request.getParameter("age");
	age = (age == null) ? "" : age.trim();

	String state = request.getParameter("state");
	state = (state == null) ? "" : state.trim();

	String category = request.getParameter("category");
	category = (category == null) ? "" : category.trim();

	String quarter = request.getParameter("quarter");
	quarter = (quarter == null) ? "" : quarter.trim();

	String rows = request.getParameter("rows");
	rows = (rows == null) ? "" : rows.trim();
%>
<div class="container-fluid">
	<div class="span12">
		<div class="row">
			<div class="span4">
				<form action="dashboard" method="get" class="form-inline">
					<select name="rows">
						<option value="c" ${states!=null ? "" : "selected"} >Customers</option>
						<option value="s" ${states!=null ? "selected" : ""}>States</option>
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
								<select name="age" class="span2">
									<option value="-1">All ages</option>
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
									<option value="-1">All states</option>
									<c:forEach var="state" items="${states}">
										<option value="<c:out value="${state}" />">
											<c:out value="${state}" />
										</option>
									</c:forEach>
								</select> <select name="category" class="span2">
									<option value="-1">All categories</option>
									<c:forEach var="category" items="${categories}">
										<option value="<c:out value="${category.id}" />"
											<c:if test="${category.id} == ${product.id}">selected="selected"</c:if>>
											<c:out value="${category.name}" />
										</option>
									</c:forEach>
								</select> <select name="quarter" class="span2">
									<option value="w">Full Year</option>
									<option value="w">Winter</option>
									<option value="w">Spring</option>
									<option value="w">Summer</option>
									<option value="w">Fall</option>
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
								<th>${states!=null ? "State" : "Customer"}</th>
								<th>Product 1</th>
								<th>Product 2</th>
								<th>Product 3</th>
								<th>Product 4</th>
								<th>Product 5</th>
								<th>Product 1</th>
								<th>Product 2</th>
								<th>Product 3</th>
								<th>Product 4</th>
								<th>Product 5</th>

							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${states!=null}">
									<c:forEach items="${states}" var="sale">
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
						href="?rows=<%=rows%>&roff=<%=roff%>&coff=<%=coff + 10%>&age=<%=age%>&state=<%=state%>&category=<%=category%>&quarter=<%=quarter%>">Next
						10 columns</a>
				</div>
			</div>
			<div class="row">
				<div class="span3">
					<a type="submit" class="btn"
						href="?rows=<%=rows%>&roff=<%=roff + 10%>&coff=<%=coff%>&age=<%=age%>&state=<%=state%>&category=<%=category%>&quarter=<%=quarter%>">Next
						10 rows</a>

				</div>
			</div>
		</div>
	</div>


</div>
<%@include file="footer.jsp"%>
