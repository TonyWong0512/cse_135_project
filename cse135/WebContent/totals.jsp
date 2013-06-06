<% response.setContentType("text/xml") ; %>
<totals>
<%@page import="dao.*, model.*, java.sql.*, java.util.*" %>
<%
OrderDao odao = new OrderDao();
Map<String, Double>totals = odao.getTotals();
for (Map.Entry<String, Double> e : totals.entrySet()) {
%>

	<total>
	<key><%= e.getKey() %></key>
	<value><%= e.getValue() %></value>
	</total>

<%
}
%>
</totals>