<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%
		String user = (String) pageContext.getSession()
				.getAttribute("name");
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
				<li><a href="product">Products</a></li>
				<li><a href="category">Categories</a></li>
				<%
					if (user != null) {
				%>
					<li><a href="order">Cart</a></li>
				<%
					}
				%>


			</ul>
		</div>
	</div>