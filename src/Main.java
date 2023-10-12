import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Connection connection;

    public static void main(String[] args) {
        connection = DatabaseManager.conn();
        Scanner input = new Scanner(System.in);

        if (connection != null) {
          if (Login("alouh", "pogi")) {
        	  System.out.println("Login success");
        	  DatabaseManager.closeConn();
          } else {
        	  System.out.println("Login Failed");
          }
            
        }
    }

    static boolean Login(String uname, String upass) {
        try {
            String selectQuery = "SELECT * FROM users WHERE username=? and userPass=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, uname);
            preparedStatement.setString(2, upass);

            ResultSet resultSet = preparedStatement.executeQuery();

         if (resultSet.next()) {
        	 return true;
         } else {
        	 return false;
         }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
