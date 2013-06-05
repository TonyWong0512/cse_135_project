<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="bootstrap.min.css" rel="stylesheet">
 <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
</head>
<body>
	<%
		String user = (String) pageContext.getSession()
				.getAttribute("name");
		String role = (String) pageContext.getSession()
				.getAttribute("role");
	%>

	<div class="navbar">
		<div class="navbar-inner">
			<a class="brand" href="#">Hello <%=(user != null) ? user : ""%></a>
			<ul class="nav">
				<li><a href="index.jsp">Home</a></li>
				<%
					if (user == null) {
				%>
				<li><a href="login">Login</a></li>
				<li><a href="signup">Sign up</a></li>
				<%
					}
				%>

				<li><a href="browse">Browse</a></li>

				<%
					if (user != null) {
						if (role.contains("owner")) {
				%>
					<li><a href="product">Products</a></li>
					<li><a href="category">Categories</a></li>
				<%
						}
				%>
					<li><a href="order">Cart</a></li>
				<%
					}
				%>


			</ul>
		</div>
	</div>