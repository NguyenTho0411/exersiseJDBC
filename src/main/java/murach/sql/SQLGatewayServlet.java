/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package murach.sql;

import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author DELL
 */
@WebServlet(name = "SQLGatewayServlet", urlPatterns = {"/sqlGateway"})
public class SQLGatewayServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sqlStatement = request.getParameter("sqlStatement");
        String sqlResult = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://azure-server-murach-123.mysql.database.azure.com:3306/murach?zeroDateTimeBehavior=CONVERT_TO_NULL";
            String userName = "ductho";
            String password = "Tho0411@";
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();
            sqlStatement = sqlStatement.trim();
            if (sqlStatement.length() >= 6) {
                String type = sqlStatement.substring(0, 6);
                if (type.equalsIgnoreCase("select")) {
                    ResultSet resultSet = statement.executeQuery(sqlStatement);
                    sqlResult = SQLUtil.getHtmlTable(resultSet);
                    resultSet.close();
                } else {
                    int i = statement.executeUpdate(sqlStatement);
                    if (i == 0) {
                        sqlResult = "<p>The statement executed succesfully.</p>";
                    } else {
                        sqlResult = "<p>The statement executed successfully.<br/>"
                                + i + " row affected.</p>";

                    }
                }
            }
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            sqlResult = "<p>Error loading the database driver: <br>"
                    + e.getMessage() + "</p>";
        } catch (SQLException e) {
            sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
        }
        HttpSession session = request.getSession();
        session.setAttribute("sqlStatement", sqlStatement);
        session.setAttribute("sqlResult", sqlResult);
        String url ="/index.jsp";
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

}
