<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="bootstrap.min.css" rel="stylesheet">
</head>
<body>
<% String user = (String)pageContext.getSession().getAttribute("name"); %>
Hello 
<%= (user != null) ? user : "" %>