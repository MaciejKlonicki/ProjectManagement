<%@ page import="com.example.demo.model.UserProjectRelation" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Document Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin-top: 75px;
            padding: 0;
        }

        .details-container {
            max-width: 400px;
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

        .details-container p {
            margin-bottom: 10px;
            color: #555555;
        }

        .button-container {
            text-align: center;
            margin-top: 20px;
        }

        .back-button {
            background-color: #007bff;
            color: #ffffff;
            border: none;
            border-radius: 4px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
        }

        .back-button:hover {
            background-color: #999999;
        }
    </style>
</head>
<body>
<div class="details-container">
    <h2>Document Details</h2>
    <% UserProjectRelation documentDetails = (UserProjectRelation) request.getAttribute("documentDetails");
        if (documentDetails != null) { %>
    <p><strong>User ID:</strong> <%= documentDetails.getUserId() %></p>
    <p><strong>Project ID:</strong> <%= documentDetails.getProjectId() %></p>
    <% } else { %>
    <p>No details found for the given document.</p>
    <% } %>
    <div class="button-container">
        <form action="welcomePage.jsp">
            <button class="back-button" type="submit">Back</button>
        </form>
    </div>
</div>
</body>
</html>
