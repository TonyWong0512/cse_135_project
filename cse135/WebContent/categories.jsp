<%@include file="header.jsp" %>
	<c:if test="${message != null}" >
		<p><c:out value="${message}" /></p>
	</c:if>
	<h1>Categories List</h1>

	<table>
		<tr>
			<td>
				<!-- Add an HTML table header row to format the results -->
				<table border="1">
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Description</th>
					</tr>

					<tr>
						<form action="/cse135/category" method="POST">
							<input type="hidden" name="action" value="insert" />
							<th>&nbsp;</th>
							<th><input value="" name="name" size="15" /></th>
							<th><input value="" name="description" size="15" /></th>
							<th><input type="submit" value="Insert" /></th>
						</form>
					</tr>
					<c:forEach items="${categories}" var="category">
						<tr>


							<form action="/cse135/category" method="POST">
								<input type="hidden" name="action" value="update" /> <input
									type="hidden" name="id"
									value="<c:out value="${category.id}" />" />

								<%-- Get the id --%>
								<td><c:out value="${category.id}" /></td>

								<%-- Get the name --%>
								<td><input value="<c:out value="${category.name}" />"
									name="name" size="15" /></td>

								<%-- Get the description --%>
								<td><input
									value="<c:out value="${category.description}" />"
									name="description" size="15" /></td>

								<%-- Button --%>
								<td><input type="submit" value="Update"></td>
							</form>
							<form action="/cse135/category" method="POST">
								<input type="hidden" name="action" value="delete" /> <input
									type="hidden" value="<c:out value="${category.id}" />"
									name="id" />
								<%-- Button --%>
								<td><input type="submit" value="Delete" /></td>
							</form>

						</tr>
					</c:forEach>


				</table>
			</td>
		</tr>
	</table>
</body>
</html>