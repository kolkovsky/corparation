package model;


import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Admin  {

    protected static final String URL ="jdbc:mysql://localhost:3306/test";
    protected static final String ADMIN ="root";
    protected static final String PASSWORD ="root";
    // JDBC variables for opening and managing connection
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;
    public HashSet<User> hashSet = new HashSet<User>();


    public Admin(){

    }
    public Connection getConnection() {
        return connection;
    }

    private void getStatementDirector() {
       boolean flag = false;

       try{
           Class.forName("com.mysql.jdbc.Driver");
           connection = DriverManager.getConnection(URL, ADMIN, PASSWORD);
           statement = connection.createStatement();
           resultSet = statement.executeQuery("SELECT * FROM staff");

           while (resultSet.next()) {
               User user = new User();
               user.setId(resultSet.getInt(1));
               user.setFname(resultSet.getString(2));
               user.setLname(resultSet.getString(3));
               user.setEmail(resultSet.getString(4));
               user.setDepartment(resultSet.getString(5));
               user.setSalary(resultSet.getInt(6));
               user.setVacation(resultSet.getInt(7));
               user.setRequest_vacation(resultSet.getString(8));

               hashSet.add(user);
            }
       } catch (SQLException E){
           System.out.println("Ошибка подключения к БД АДМИНА!");
        }catch (ClassNotFoundException S){
           System.out.println("Не удалось зарегестрировать драйвер!");
       }
    }


    public void deleteStatement(int clearId) throws  SQLException{
        PreparedStatement preparedStatement =
                connection.prepareStatement("DELETE FROM NOTEBOOK WHERE id = ? ");
        preparedStatement.setInt(1,clearId);
        preparedStatement.executeUpdate();

    }

    public HashSet<User> getHashSet() {
        getStatementDirector();
        return hashSet;
    }
}
