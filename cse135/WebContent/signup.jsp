<%@include file="header.jsp" %>
<form action="signup" method="post">
<label>Name</label><input type="text" name="name"></input>
<label>Role</label>
<select name="role">
<option value="owner">Owner</option>
<option value="customer">Customer</option>
</select>
<label>Age</label><input type="text" name="age"></input>
<label>State</label><input type="text" name="state"></input>
<input type="submit" value="Sign up">
</form>

</body>
</html>