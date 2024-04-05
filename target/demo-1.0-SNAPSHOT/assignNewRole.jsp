<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add new role</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin-top: 75px;
            padding: 0;
        }

        .form-container {
            max-width: 400px;
            margin: auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .form-container h2 {
            margin-top: 0;
            color: #333333;
        }

        .form-container label {
            display: block;
            margin-bottom: 6px;
            color: #555555;
        }

        .form-container input[type="text"],
        .form-container input[type="email"],
        .form-container input[type="password"] {
            width: 80%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #dddddd;
            border-radius: 4px;
            font-size: 16px;
        }

        .form-container .button-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .form-container input[type="submit"],
        .form-container button {
            background-color: #007bff;
            color: #ffffff;
            border: none;
            border-radius: 4px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
        }

        .form-container input[type="submit"]:hover,
        .form-container button:hover {
            background-color: #0056b3;
        }

        .success-message, .error-message {
            margin-top: 10px;
            color: #555555;
        }

        .error-message {
            color: #d9534f;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Add new role</h2>
    <form action="new-role-servlet" method="post">
        <label for="user_id">User ID:</label>
        <input type="text" id="user_id" name="user_id" required>
        <label for="project_id">Project ID:</label>
        <input type="text" id="project_id" name="project_id" required>
        <label for="role_id">Role ID:</label>
        <input type="text" id="role_id" name="role_id" required>
        <div class="button-container">
            <input type="submit" value="Add new role">
        </div>
    </form>
    <form action="welcomePage.jsp">
        <button style="margin-top: 5px" type="submit">Back</button>
    </form>
    <div class="message-container">
        <% if (request.getAttribute("successMessage") != null) { %>
        <p class="success-message"><%= request.getAttribute("successMessage") %></p>
        <% } else if (request.getAttribute("errorMessage") != null) { %>
        <p class="error-message"><%= request.getAttribute("errorMessage") %></p>
        <% } %>
    </div>
</div>
</body>
</html>
