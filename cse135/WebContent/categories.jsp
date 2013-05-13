<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Categories</title>
</head>
<body>
	<h1>Categories List</h1>

	<table>
		<tr>
			<td>
				<%-- Import the java.sql package --%> <%@ page import="java.sql.*"%>
				<%-- -------- Open Connection Code -------- --%> 
				<%
				 	Connection conn = null;
				 	PreparedStatement pstmt = null;
				 	ResultSet rs = null;
				
				 	try {
				 		// Registering Postgresql JDBC driver with the DriverManager
				 		Class.forName("org.postgresql.Driver");
				
				 		// Open a connection to the database using DriverManager
				 		conn = DriverManager
				 				.getConnection("jdbc:postgresql://localhost/postgres?"
				 						+ "user=postgres&password=postgres");
 				%>
 	 			<%-- -------- SELECT Statement Code -------- --%> <%
 				// Create the statement
 				Statement statement = conn.createStatement();

		 		// Use the created statement to SELECT
		 		// the student attributes FROM the Student table.
		 		rs = statement.executeQuery("SELECT * FROM categories");
				 %> 
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

					<%-- -------- Iteration Code -------- --%>
					<%
						// Iterate over the ResultSet
							while (rs.next()) {
					%>

					<tr>
						<form action="/cse135/category" method="PUT">
							<input type="hidden" name="action" value="update" /> 
							<input type="hidden" name="id" value="<%=rs.getInt("id")%>" />

							<%-- Get the id --%>
							<td><%=rs.getInt("id")%></td>

							<%-- Get the name --%>
							<td><input value="<%=rs.getString("name")%>"
								name="name" size="15" /></td>

							<%-- Get the description --%>
							<td><input value="<%=rs.getString("description")%>"
								name="description" size="15" /></td>

							<%-- Button --%>
							<td><input type="submit" value="Update"></td>
						</form>
						<form action="/cse135/category" method="DELETE">
							<input type="hidden" name="action" value="delete" /> <input
								type="hidden" value="<%=rs.getInt("id")%>" name="id" />
							<%-- Button --%>
							<td><input type="submit" value="Delete" /></td>
						</form>
					</tr>

					<%
						}
					%>

					<%-- -------- Close Connection Code -------- --%>
					<%
						// Close the ResultSet
							rs.close();

							// Close the Statement
							statement.close();

							// Close the Connection
							conn.close();
						} catch (SQLException e) {

							// Wrap the SQL exception in a runtime exception to propagate
							// it upwards
							throw new RuntimeException(e);
						} finally {
							// Release resources in a finally block in reverse-order of
							// their creation

							if (rs != null) {
								try {
									rs.close();
								} catch (SQLException e) {
								} // Ignore
								rs = null;
							}
							if (pstmt != null) {
								try {
									pstmt.close();
								} catch (SQLException e) {
								} // Ignore
								pstmt = null;
							}
							if (conn != null) {
								try {
									conn.close();
								} catch (SQLException e) {
								} // Ignore
								conn = null;
							}
						}
					%>

				</table>
			</td>
		</tr>
	</table>
</body>
</html>