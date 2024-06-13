<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Project management</title>
    <style>
        .logout-button {
            float: right;
            margin-top: 10px;
            margin-right: 20px;
        }

        .logout-button button {
            background-color: #d9534f;
            color: #ffffff;
            border: none;
            padding: 5px 10px;
            border-radius: 3px;
            cursor: pointer;
        }

        .operation-button {
            display: inline-block;
            margin-right: 10px;
            margin-bottom: 10px;
            padding: 6px 12px;
            border: none;
            border-radius: 3px;
            background-color: #666666;
            color: #ffffff;
            font-size: 14px;
            cursor: pointer;
            text-decoration: none;
            text-align: center;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin-top: 75px;
            padding: 0;
        }

        .success-container {
            max-width: 600px;
            margin: auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .success-container h2 {
            color: #33cc33;
            margin-top: 0;
        }

        .success-message {
            color: #555555;
        }

        .success-links {
            margin-top: 20px;
        }

        .success-links a {
            display: block;
            margin-bottom: 10px;
            color: white;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="success-container">
    <div class="logout-button">
        <form action="logout-servlet" method="post">
            <button type="submit">Log out</button>
        </form>
    </div>
    <h2>Successful Login</h2>
    <p class="success-message">Congratulations! You have successfully logged in.</p>
    <div class="success-links">
        <a class="operation-button" href="addUser.jsp">Add user</a>
        <a class="operation-button" href="addProject.jsp">Add project</a>
        <a class="operation-button" href="assignNewRole.jsp">Add new role</a>
        <a class="operation-button" href="deleteProject.jsp">Delete project</a>
        <a class="operation-button" href="get-all-projects-servlet">Get all projects</a>
        <a class="operation-button" href="getProjectsForUser.jsp">Show projects for user</a>
        <a class="operation-button" href="getProjectDetails.jsp">Show project details</a>
        <a class="operation-button" href="updateProject.jsp">Update project</a>
        <a class="operation-button" href="addDocument.jsp">Add document</a>
        <a class="operation-button" href="chooseDocument.jsp">Edit document</a>
        <a class="operation-button" href="deleteDocument.jsp">Delete document</a>
        <a class="operation-button" href="getDocumentDetails.jsp">Show document details</a>
        <a class="operation-button" href="getDocumentsForUser.jsp">Show documents for user</a>
    </div>
</div>
</body>
</html>
