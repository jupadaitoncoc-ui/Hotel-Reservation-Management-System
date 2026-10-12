package hotelreservationsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/harbour_haven_hotel"
            + "?useSSL=false"
            + "&serverTimezone=Asia/Manila"
            + "&allowPublicKeyRetrieval=true";

    private static final String USER = "root";

    private static final String PASSWORD = "";

    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("MySQL Driver found.");

            Connection connection =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD
                    );

            System.out.println("MySQL connection successful.");

            return connection;

        } catch (ClassNotFoundException e) {

            System.out.println(
                    "ERROR: MySQL JDBC Driver not found."
            );

            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println(
                    "ERROR: MySQL connection failed."
            );

            System.out.println(
                    "Message: " + e.getMessage()
            );

            System.out.println(
                    "Error Code: " + e.getErrorCode()
            );

            System.out.println(
                    "SQL State: " + e.getSQLState()
            );

            e.printStackTrace();
        }

        return null;
    }
}