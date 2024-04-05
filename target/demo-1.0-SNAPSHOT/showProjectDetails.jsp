<%@ page import="com.example.demo.model.UserProjectRelation" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Project Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin-top: 75px;
            padding: 0;
        }

        .details-container {
            max-width: 600px;
            margin: auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .details-container h2 {
            margin-top: 0;
            color: #333333;
        }

        .details-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        .details-table th, .details-table td {
            border: 1px solid #dddddd;
            padding: 8px;
            text-align: left;
        }

        .details-table th {
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
<div class="details-container">
    <h2>Project Details</h2>
    <% List<UserProjectRelation> projectDetails = (List<UserProjectRelation>) request.getAttribute("projectDetails");
        if (projectDetails != null && !projectDetails.isEmpty()) { %>
    <table class="details-table">
        <tr>
            <th>User ID</th>
            <th>Project ID</th>
            <th>Role ID</th>
        </tr>
        <% for (UserProjectRelation detail : projectDetails) { %>
        <tr>
            <td><%= detail.getUserId() %></td>
            <td><%= detail.getProjectId() %></td>
            <td><%= detail.getRoleId() %></td>
        </tr>
        <% } %>
    </table>
    <% } else { %>
    <p>No details found for the given project.</p>
    <% } %>
    <form class="back-button" action="welcomePage.jsp">
        <button type="submit">Back</button>
    </form>
</div>
</body>
</html>
