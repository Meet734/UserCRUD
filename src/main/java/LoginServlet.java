import java.io.IOException;

import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean isAuthenticated = authenticateUser(email, password);

        if (isAuthenticated) {
            request.getSession().setAttribute("email", email);
            response.sendRedirect("Home.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("Login.html").forward(request, response);
        }
    }

        private boolean authenticateUser(String email, String password) {
            boolean isAuthenticated = false;
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/temp", "root", "");

                String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    isAuthenticated = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            
            return isAuthenticated;
        }
}

