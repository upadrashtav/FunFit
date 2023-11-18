<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Batch View</title>
</head>
<body>
	<h2>Batch View</h2>
	<%-- Display a success message if available --%>
	<% if (request.getAttribute("successMessage") != null) { %>
	<p style="color: green;"><%= request.getAttribute("successMessage") %></p>
	<% } %>
	<%-- Your HTML content here, displaying participant data, etc. --%>
	<p>
		<strong>Batch Id: </strong>
		<%= request.getAttribute("batchId") %></p>
	
	<p>
		<strong>Batch Name:</strong>
		<%= request.getAttribute("batchName") %></p>
	<p>
		<strong>Batch Instructor:</strong>
		<%= request.getAttribute("batchInstructor") %></p>
	<p>
		<strong>Capacity:</strong>
		<%= request.getAttribute("batchCapacity") %></p>
	<p>
		<strong>Batch Duration:</strong>
		<%= request.getAttribute("batchDuration") %></p>

	<br>
	<a href="index.html">Go back to index</a>
</body>
</html>