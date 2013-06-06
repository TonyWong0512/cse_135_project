<%@page import="dao.*, model.*, java.sql.*, java.util.*" %>
<%
OrderDao odao = new OrderDao();
Map<String, Integer>totals = odao.getTotals();
for (Map.Entry<String, Integer> e : totals.entrySet()) {
%>
	<total>
	<key><%= e.getKey() %></key>
	<value><%= e.getValue() %></value>
	</total>
<%
}
%>