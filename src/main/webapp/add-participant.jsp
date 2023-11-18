<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Participant View</title>
</head>
<body>
	<h2>Participant View</h2>
	<%-- Display a success message if available --%>
	<% if (request.getAttribute("successMessage") != null) { %>
	<p style="color: green;"><%= request.getAttribute("successMessage") %></p>
	<% } %>
	<%-- Your HTML content here, displaying participant data, etc. --%>
	<p>
		<strong>Name:</strong>
		<%= request.getAttribute("participantName") %></p>
	<p>
		<strong>Phone:</strong>
		<%= request.getAttribute("participantPhone") %></p>
	<p>
		<strong>Email:</strong>
		<%= request.getAttribute("participantEmail") %></p>
	<p>
		<strong>BatchName:</strong>
		<%= request.getAttribute("participantBatchName") %></p>

	<br>
	<a href="index.html">Go back to index</a>
</body>
</html>