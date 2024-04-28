<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Show Data</title>
	<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>User Data</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Password</th>
        </tr>
        <% 
            try {
                String Url = "jdbc:mysql://localhost:3306/temp";
                String User = "root";
                String Password = "";

                Class.forName("com.mysql.cj.jdbc.Driver");

                try (Connection connection = DriverManager.getConnection(Url, User, Password)) {
                    String sql = "SELECT * FROM users";
                    try (PreparedStatement statement = connection.prepareStatement(sql);
                         ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            int id = resultSet.getInt("id");
                            String username = resultSet.getString("username");
                            String email = resultSet.getString("email");
                            String password = resultSet.getString("password");
        %>
        <tr>
            <td><%= id %></td>
            <td><%= username %></td>
            <td><%= email %></td>
            <td><%= password %></td>
        </tr>
        <% 
                        }
                    }
                }
            } catch (ClassNotFoundException | SQLException e) {
                out.println("Error: " + e.getMessage());
            }
        %>
    </table>
</body>
</html>
