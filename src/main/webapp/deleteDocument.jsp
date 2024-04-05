<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Delete Document</title>
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

        .form-container input[type="text"] {
            width: 80%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #dddddd;
            border-radius: 4px;
            font-size: 16px;
        }

        .form-container input[type="submit"] {
            background-color: #d9534f;
            color: #ffffff;
            border: none;
            border-radius: 4px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
        }

        .form-container input[type="submit"]:hover {
            background-color: #c9302c;
        }

        .button-container {
            margin-top: 5px;
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
<div class="form-container">
    <h2>Delete Document</h2>
    <form action="delete-document-servlet" method="post">
        <label for="title">Document title:</label>
        <input type="text" id="title" name="title" required><br><br>
        <input type="submit" value="Delete Document">
    </form>
    <div class="button-container">
        <form action="welcomePage.jsp">
            <button class="back-button" type="submit">Back</button>
        </form>
        <div class="message-container">
            <% if (request.getAttribute("successMessage") != null) { %>
            <p class="success-message"><%= request.getAttribute("successMessage") %></p>
            <% } else if (request.getAttribute("errorMessage") != null) { %>
            <p class="error-message"><%= request.getAttribute("errorMessage") %></p>
            <% } %>
        </div>
    </div>
</div>
</body>
</html>
