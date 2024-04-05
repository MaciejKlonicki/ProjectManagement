<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin-top: 75px;
            padding: 0;
        }

        .error-container {
            max-width: 400px;
            margin: auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .error-container h2 {
            margin-top: 0;
            color: #333333;
        }

        .error-message {
            color: #d9534f;
            margin-bottom: 20px;
        }

        .button-container {
            text-align: center;
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
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="error-container">
    <h2>Login Error</h2>
    <p class="error-message">Sorry, your login credentials are invalid. Please try again.</p>
    <div class="button-container">
        <form action="login.jsp">
            <button class="back-button" type="submit">Back</button>
        </form>
    </div>
</div>
</body>
</html>
