<%@include file="header.jsp" %>
<% model.Product product = (model.Product)request.getAttribute("product"); %>
<body>
<label>Name</label><c:out value="${product.name}" />
<label>SKU</label><c:out value="${product.SKU}" />
<label>List price</label><c:out value="${product.price}" />
<label>Category</label><c:out value="${product.category}" />
</body>
</html>