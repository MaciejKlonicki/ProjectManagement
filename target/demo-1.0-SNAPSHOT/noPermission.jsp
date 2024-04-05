<!DOCTYPE html>
<html>
<head>
    <title>No Permission</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 75vh;
            text-align: center;
        }

        .container {
            max-width: 400px;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        h1 {
            margin: 0 0 20px;
            color: #333333;
        }

        p {
            color: #555555;
            margin-bottom: 20px;
        }

        a {
            background-color: #007bff;
            color: #ffffff;
            text-decoration: none;
            padding: 10px 20px;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }

        a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Permission Denied</h1>
    <p>${errorMessage}</p>
    <a href="javascript:history.back()">Go Back</a>
</div>
</body>
</html>
