package Servlets;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import model.Admin;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.AbstractDocument;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

@WebServlet(name = "DBconnection",
urlPatterns = "/dbconnection")
public class DBconnection extends HttpServlet {

    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private HashSet<User>  hashSet = new HashSet<User>();
    private  PreparedStatement preparedStatement;


    //Check User in DataBase
    public boolean getStatementDirector(String Fname, String Lname,String password){
        boolean flag = false;

        try{
            while (rs.next()) {
                if ((rs.getString(2).equals(Fname)) && rs.getString(3).equals(Lname)
                        && rs.getString(9).equals(password)) {
                    return true;
                } else {
                    flag = false;
                }
            }
        }catch (SQLException E){
            System.out.println("Ошибка при чтении данных из базы!");
        }
        return flag;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM staff");
        } catch (SQLException e) {
            throw new ServletException("Servlet Could not display records.", e);
        } catch (ClassNotFoundException e) {
            throw new ServletException("JDBC Driver not found.", e);
        }


        Admin admin = new Admin();
        hashSet = admin.getHashSet();
//        admin.getStatementDirector();
//        arrayList = admin.getArrayList();



        HttpSession session =request.getSession();
        session.setAttribute("hashset",hashSet);
//        session.setAttribute("userlist",arrayList);
//        arrayList = null;

        String Type = request.getParameter("Type");

        if (Type.equals("Manager")) {
            String fname = request.getParameter("first_name");
            String lname = request.getParameter("second_name");
            String password = request.getParameter("password");
            if(getStatementDirector(fname,lname,password)){
                session.setAttribute("fname",fname);
                session.setAttribute("lname",lname);
                session.setAttribute("password",password);
                response.sendRedirect("/managerpage.jsp");
            }else
                response.sendRedirect("/index.jsp");
        }

        if (Type.equals("Director")) {
            String fname = request.getParameter("first_name");
            String lname = request.getParameter("second_name");
            String password = request.getParameter("password");
            if(getStatementDirector(fname,lname,password)){
                session.setAttribute("fname",fname);
                session.setAttribute("lname",lname);
                session.setAttribute("password",password);
                response.sendRedirect("/dirpage.jsp");
            }else
                response.sendRedirect("/index.jsp");

        }

        if (Type.equals("Worker")) {
            String fname = request.getParameter("first_name");
            String lname = request.getParameter("second_name");
            String password = request.getParameter("password");
            if(getStatementDirector(fname,lname,password)){
                session.setAttribute("fname",fname);
                session.setAttribute("lname",lname);
                session.setAttribute("password",password);
                response.sendRedirect("/emlpage.jsp");
            }else
                response.sendRedirect("/index.jsp");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
