<%@include file="header.jsp"%>

<div class="container-fluid">
	<div class="span12">
		<div class="row">
			<div class="span4">
				<form action="dashboard" method="get" class="form-inline">
					<select name="rows">
						<option value="c">Customers</option>
						<option value="s">States</option>
					</select>
					<button type="submit" class="btn">Update</button>
				</form>
			</div>
		</div>
		<div class="row">
				<form action="dashboard" method="get" class="form-inline">
					<fieldset class="span12">
						
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


</div>
<%@include file="footer.jsp"%>
