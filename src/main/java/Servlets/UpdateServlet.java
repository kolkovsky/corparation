package Servlets;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "UpdateServlet",
urlPatterns = "/update")
public class UpdateServlet extends HttpServlet {


    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("worker");
        String textarea = request.getParameter("text");

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM staff");
        }catch (SQLException E){
            response.sendRedirect("/errordb.jsp");
        }catch (ClassNotFoundException e){
            System.out.println("Drivet not found!");
        }

        getVacationRequest(textarea,user);
        response.sendRedirect("/emlpage.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    public void getVacationRequest(String textarea, User user)  {
        try {
            preparedStatement =
                    connection.prepareStatement("UPDATE staff set request_vacation = ? where FirstName = ? AND LastName = ?");
            preparedStatement.setString(3, user.getLname());
            preparedStatement.setString(2, user.getFname());
            preparedStatement.setString(1, textarea);
            preparedStatement.executeUpdate();
        }catch (SQLException E){
            System.out.println("Error in database!");
        }
    }


}
