<%@include file="header.jsp"%>
<%@page import="java.util.List, model.*"%>
<%
List<Category> categories = (List<Category>)request.getAttribute("categories");
String[] states = (String[])request.getAttribute("states");
%>

<script type="text/javascript">
setInterval(function() {
	$.ajax({
		url: 'totals.jsp'
	}).done(update)
}, 2000);

function update(msg) {
	console.log('received msg with length ' + msg.length);
}
</script>

<div class="container-fluid">
	<div class="span12">
		<table>
			<tr><td>
			<% for (String state : states) { %>
				<td><%= state %></td>
			<% } %>
			</td></tr>
			<% for (Category category : categories) { %>
				<tr><td>
				<%= category.getName() %>
				<td></tr>
			<% } %>
		</table>
	</div>


</div>
<%@include file="footer.jsp"%>