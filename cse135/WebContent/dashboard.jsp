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
	$(msg).find("total").each(
			function() {
				key = $(this).find('key').text();
				value = $(this).find('value').text();
				document.getElementById(key).innerHTML = '$' + value;
			});
}
</script>

<div class="container-fluid">
	<div class="span12">
		<table>
			<tr><th></th>
			<% for (String state : states) { %>
				<th><%= state %></th>
			<% } %>
			</tr>
			<% for (Category category : categories) {
				if (category == null || category.getName() == null) continue;
				 %>
				<tr>
				<td><%= category.getName() %><td>
				<% for (String state : states) { %>
				<td id=<%= category.getId() + "," + state %>>$0.00</td>
				<% } %>
				</tr>
			<% } %>
		</table>
	</div>


</div>
<%@include file="footer.jsp"%>