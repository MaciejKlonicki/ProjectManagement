<%@ page import="com.example.demo.model.Project" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Projects for User</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin-top: 75px;
            padding: 0;
        }

        .project-list {
            max-width: 600px;
            margin: auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .project-list h2 {
            color: #333333;
        }

        .project-list ul {
            list-style-type: none;
            padding: 0;
        }

        .project-list li {
            margin-bottom: 15px;
            padding: 10px;
            border: 1px solid #dddddd;
            border-radius: 4px;
            background-color: #f9f9f9;
        }

        .no-projects-message {
            color: #555555;
        }

        .back-button {
            margin-top: 20px;
            text-align: center;
        }

        .back-button button {
            background-color: #007bff;
            color: #ffffff;
            border: none;
            border-radius: 4px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
        }

        .back-button button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="project-list">
    <h2>Projects for User</h2>
    <%
        List<Project> projects = (List<Project>) request.getAttribute("projects");
        if (projects != null && !projects.isEmpty()) {
    %>
    <ul>
        <% for (Project project : projects) { %>
        <li><strong>Name:</strong> <%= project.getName() %>, <strong>Description:</strong> <%= project.getDescription() %>, <strong>Creator:</strong> <%= project.getCreator() %></li>
        <% } %>
    </ul>
    <% } else { %>
    <p class="no-projects-message">No projects found for the given user.</p>
    <% } %>
    <form class="back-button" action="welcomePage.jsp">
        <button type="submit">Back</button>
    </form>
</div>
</body>
</html>
