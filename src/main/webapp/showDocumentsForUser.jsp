<%@ page import="com.example.demo.model.Document" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Documents for User</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin-top: 75px;
            padding: 0;
        }

        .documents-container {
            max-width: 800px;
            margin: auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .documents-container h2 {
            margin-top: 0;
            color: #333333;
        }

        .documents-container ul {
            list-style-type: none;
            padding: 0;
        }

        .document-item {
            margin-bottom: 20px;
            padding: 10px;
            border: 1px solid #dddddd;
            border-radius: 4px;
            background-color: #f9f9f9;
        }

        .document-item strong {
            color: #333333;
        }

        .no-documents {
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
<div class="documents-container">
    <h2>Documents for User</h2>
    <% List<Document> documents = (List<Document>) request.getAttribute("documents");
        if (documents != null && !documents.isEmpty()) { %>
    <ul>
        <% for (Document document : documents) { %>
        <li class="document-item">
            <strong>Title:</strong> <%= document.getTitle() %>,
            <strong>Description:</strong> <%= document.getDescription() %>,
            <strong>Creator:</strong> <%= document.getCreator() %>,
            <strong>Topic:</strong> <%= document.getTopic() %>,
            <strong>Project ID:</strong> <%= document.getProjectId() %>
        </li>
        <% } %>
    </ul>
    <% } else { %>
    <p class="no-documents">No documents found for the given user.</p>
    <% } %>
    <div class="button-container">
        <form action="welcomePage.jsp">
            <button class="back-button" type="submit">Back</button>
        </form>
    </div>
</div>
</body>
</html>
