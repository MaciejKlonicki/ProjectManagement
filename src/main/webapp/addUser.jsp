<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add User</title>
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
            margin-bottom: 5px;
            border: 1px solid #dddddd;
            border-radius: 4px;
            font-size: 16px;
        }

        .password-info {
            display: none;
            font-size: 12px;
            color: #555555;
            margin-bottom: 15px;
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

        .error-message {
            color: #d9534f;
            font-size: 14px;
            margin-top: 10px;
            display: none;
        }

        .success-message {
            margin-top: 10px;
            color: #555555;
        }
    </style>
    <script>
        function showPasswordInfo() {
            var passwordInfo = document.getElementById("password-info");
            passwordInfo.style.display = "block";
        }

        function validateForm(event) {
            var password = document.getElementById("password").value;
            var errorMessage = document.getElementById("error-message");

            if (password.length < 6 || !/[a-zA-Z]/.test(password) || !/[^a-zA-Z0-9]/.test(password)) {
                errorMessage.textContent = "Password does not meet the requirements.";
                errorMessage.style.display = "block";
                event.preventDefault();
            } else {
                errorMessage.style.display = "none";
            }
        }
    </script>
</head>
<body>
<div class="form-container">
    <h2>Add User</h2>
    <form action="create-user-servlet" method="post" onsubmit="validateForm(event)">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required onfocus="showPasswordInfo()">
        <div id="password-info" class="password-info">
            Password must be at least 6 characters long, contain at least one letter and one special character.
        </div>
        <input type="submit" value="Add User">
        <div id="error-message" class="error-message"></div>
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
