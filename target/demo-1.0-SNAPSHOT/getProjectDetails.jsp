<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Get Project Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin-top: 75px;
            padding: 0;
        }

        .project-details-form {
            max-width: 400px;
            margin: auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .project-details-form h2 {
            margin-top: 0;
            color: #333333;
        }

        .project-details-form label {
            display: block;
            margin-bottom: 6px;
            color: #555555;
        }

        .project-details-form input[type="text"] {
            width: 80%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #dddddd;
            border-radius: 4px;
            font-size: 16px;
        }

        .project-details-form input[type="submit"] {
            background-color: #007bff;
            color: #ffffff;
            border: none;
            border-radius: 4px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
        }

        .project-details-form input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .back-button {
            margin-top: 5px;
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
<div class="project-details-form">
    <h2>Get Project Details</h2>
    <form action="get-project-details" method="post">
        <label for="projectName">Project Name:</label>
        <input type="text" id="projectName" name="projectName" required>
        <input type="submit" value="Show Details">
    </form>
    <form class="back-button" action="welcomePage.jsp">
        <button type="submit">Back</button>
    </form>
</div>
</body>
</html>
