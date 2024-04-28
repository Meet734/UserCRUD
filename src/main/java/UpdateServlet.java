import java.io.IOException;
import java.io.PrintWriter;

import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/temp";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String oldUsername = request.getParameter("oldName");
        String oldEmail = request.getParameter("oldEmail");
        String oldPassword = request.getParameter("oldPassword");

        String newUsername = request.getParameter("newName");
        String newEmail = request.getParameter("newEmail");
        String newPassword = request.getParameter("newPassword");

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            String updateQuery = "UPDATE users SET email=?, password=?, username=? WHERE email=? AND password=? AND username=?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
            	pstmt.setString(3, newUsername);
                pstmt.setString(1, newEmail);
                pstmt.setString(2, newPassword);
                pstmt.setString(4, oldEmail);
                pstmt.setString(5, oldPassword);
                pstmt.setString(6, oldUsername);

                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    response.setContentType("text/html");
                    PrintWriter out = response.getWriter();
                    out.println("<html><body>");
                    out.println("<h2>Data updated successfully.</h2>");
                    out.println("<a href='Home.jsp'>Back to Home</a>");
                    out.println("</body></html>");
                } else {
                    response.setContentType("text/html");
                    PrintWriter out = response.getWriter();
                    out.println("<html><body>");
                    out.println("<h2>No data updated. Please check your input.</h2>");
                    out.println("<a href='UpdatePage.html'>Back to Update Page</a>");
                    out.println("</body></html>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
