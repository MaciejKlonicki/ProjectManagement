<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Edit Document</title>
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
    .form-container input[type="number"] {
      width: 80%;
      padding: 10px;
      margin-bottom: 20px;
      border: 1px solid #dddddd;
      border-radius: 4px;
      font-size: 16px;
    }

    .form-container input[type="submit"] {
      background-color: #007bff;
      color: #ffffff;
      border: none;
      border-radius: 4px;
      padding: 10px 20px;
      font-size: 16px;
      cursor: pointer;
    }

    .form-container input[type="submit"]:hover {
      background-color: #0056b3;
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
  <h2>Edit Document</h2>
  <form action="edit-document" method="post">
    <input type="hidden" name="title" value="${document.title}">
    <label for="description">Description:</label>
    <input type="text" id="description" name="description" value="${document.description}" required><br><br>
    <label for="creator">Creator:</label>
    <input type="text" id="creator" name="creator" value="${document.creator}" required><br><br>
    <label for="topic">Topic:</label>
    <input type="text" id="topic" name="topic" value="${document.topic}" required><br><br>
    <label for="content">Content:</label>
    <input type="text" id="content" name="content" value="${document.content}" required><br><br>
    <label for="project_id">Project ID:</label>
    <input type="number" id="project_id" name="project_id" value="${document.projectId}" required><br><br>
    <input type="submit" value="Update Document">
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
