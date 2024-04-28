<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #333;
            text-align: center;
        }
        p {
            color: #666;
            margin-bottom: 20px;
        }
        ul {
            list-style-type: none;
            padding: 0;
            text-align: center;
        }
        li {
            margin-bottom: 10px;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>

<body>
    <div class="container">
        <h1>Welcome to Home Page</h1>
        <p>You can perform various operations here:</p>
        <ul>
            <li><a href="UpdatePage.html" class="btn">Update Data</a></li>
            <li><a href="DeletePage.html" class="btn">Delete Data</a></li>
            <li><a href="ShowData.jsp" class="btn">Show All Data</a></li>
        </ul>
    </div>
</body>
</html>
