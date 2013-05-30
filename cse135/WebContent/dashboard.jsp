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
						<option value="c" ${states!=null ? "" : "selected"}>Customers</option>
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
								<input type="hidden" name="rows" ${states!=null ? 'value="s"' : 'value="c"'}>
								<select name="age" class="span2">
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
									<option value="AL">AL</option>
									<option value="AK">AK</option>
									<option value="AZ">AZ</option>
									<option value="AR">AR</option>
									<option value="CA">CA</option>
									<option value="CO">CO</option>
									<option value="CT">CT</option>
									<option value="DE">DE</option>
									<option value="DC">DC</option>
									<option value="FL">FL</option>
									<option value="GA">GA</option>
									<option value="HI">HI</option>
									<option value="ID">ID</option>
									<option value="IL">IL</option>
									<option value="IN">IN</option>
									<option value="IA">IA</option>
									<option value="KS">KS</option>
									<option value="KY">KY</option>
									<option value="LA">LA</option>
									<option value="ME">ME</option>
									<option value="MD">MD</option>
									<option value="MA">MA</option>
									<option value="MI">MI</option>
									<option value="MN">MN</option>
									<option value="MS">MS</option>
									<option value="MO">MO</option>
									<option value="MT">MT</option>
									<option value="NE">NE</option>
									<option value="NV">NV</option>
									<option value="NH">NH</option>
									<option value="NJ">NJ</option>
									<option value="NM">NM</option>
									<option value="NY">NY</option>
									<option value="NC">NC</option>
									<option value="ND">ND</option>
									<option value="OH">OH</option>
									<option value="OK">OK</option>
									<option value="OR">OR</option>
									<option value="PA">PA</option>
									<option value="RI">RI</option>
									<option value="SC">SC</option>
									<option value="SD">SD</option>
									<option value="TN">TN</option>
									<option value="TX">TX</option>
									<option value="UT">UT</option>
									<option value="VT">VT</option>
									<option value="VA">VA</option>
									<option value="WA">WA</option>
									<option value="WV">WV</option>
									<option value="WI">WI</option>
									<option value="WY">WY</option>
								</select> <select name="category" class="span2">
									<option value="">All categories</option>
									<c:forEach var="category" items="${categories}">
										<option value="<c:out value="${category.id}" />"
											<c:if test="${category.id} == ${product.id}">selected="selected"</c:if>>
											<c:out value="${category.name}" />
										</option>
									</c:forEach>
								</select> <select name="quarter" class="span2">
									<option value="">Full Year</option>
									<option value="w">Winter</option>
									<option value="sp">Spring</option>
									<option value="s">Summer</option>
									<option value="f">Fall</option>
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
								<c:forEach items="${products}" var="product">
									<th><c:out value="${product.product.name}" /></th>
								</c:forEach>

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
