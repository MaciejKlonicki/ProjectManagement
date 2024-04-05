<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.model.Project" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

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

        .table-container {
            max-width: 800px;
            margin: auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #dddddd;
        }

        th {
            background-color: #f2f2f2;
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
<div class="table-container">
    <table>
        <thead>
        <tr>
            <th>Project name</th>
            <th>Description</th>
            <th>Creator</th>
        </tr>
        </thead>
        <tbody>
        <% List<Project> projects = (List<Project>) request.getAttribute("projects"); %>
        <% for(Project project : projects) { %>
        <tr>
            <td><%= project.getName() %></td>
            <td><%= project.getDescription() %></td>
            <td><%= project.getCreator() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <div class="back-button">
        <form action="welcomePage.jsp">
            <button type="submit">Back</button>
        </form>
    </div>
</div>
</body>
</html>
