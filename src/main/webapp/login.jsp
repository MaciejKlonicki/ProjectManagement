<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin-top: 150px;
            padding: 0;
        }

        .login-container {
            max-width: 400px;
            margin: auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .login-container h2 {
            margin-top: 0;
            color: #333333;
        }

        .login-form label {
            display: block;
            margin-bottom: 6px;
            color: #555555;
        }

        .login-form input[type="text"],
        .login-form input[type="password"] {
            width: 90%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #dddddd;
            border-radius: 4px;
            font-size: 16px;
        }

        .login-form input[type="submit"] {
            background-color: #007bff;
            color: #ffffff;
            border: none;
            border-radius: 4px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
        }

        .login-form input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .error-message {
            color: #d9534f;
            margin-bottom: 20px;
        }

        .login-form.disabled input {
            background-color: #e9ecef;
            cursor: not-allowed;
        }

        .login-form.disabled input[type="submit"] {
            background-color: #6c757d;
            cursor: not-allowed;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>Login</h2>
    <form class="login-form <%= request.getAttribute("disabled") != null && request.getAttribute("disabled").equals("true") ? "disabled" : "" %>" action="login-servlet" method="post">
        <c:if test="${not empty errorMessage}">
            <p class="error-message">${errorMessage}</p>
        </c:if>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" <%= request.getAttribute("disabled") != null && request.getAttribute("disabled").equals("true") ? "disabled" : "" %> required>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" <%= request.getAttribute("disabled") != null && request.getAttribute("disabled").equals("true") ? "disabled" : "" %> required>
        <input type="submit" value="Log in" <%= request.getAttribute("disabled") != null && request.getAttribute("disabled").equals("true") ? "disabled" : "" %>>
    </form>
</div>
</body>
</html>
