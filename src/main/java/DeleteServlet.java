import java.io.IOException;
import java.io.PrintWriter;

import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/temp";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get parameters from the request
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Delete data from the users table
        boolean deleted = deleteUser(email, password);

        // Prepare response
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Display result message
        out.println("<html><body>");
        if (deleted) {
            out.println("<h2>User data deleted successfully.</h2>");
        } else {
            out.println("<h2>Error: Unable to delete user data.</h2>");
        }
        out.println("<a href='Home.jsp'>Back to Home</a>");
        out.println("</body></html>");
    }

    private boolean deleteUser(String email, String password) {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                // Prepare SQL statement
                String sql = "DELETE FROM users WHERE email = ? AND password = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    // Set parameters
                    statement.setString(1, email);
                    statement.setString(2, password);

                    // Execute the delete statement
                    int rowsAffected = statement.executeUpdate();

                    // Check if any rows were affected
                    return rowsAffected > 0;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
