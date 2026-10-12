package hotelreservationsystem;

import java.sql.Connection;

public class Databasetest {

    public static void main(String[] args) {

        System.out.println("Starting database test...");

        Connection connection =
                DatabaseConnection.getConnection();

        if (connection != null) {

            System.out.println("==============================");
            System.out.println("HARBOUR HAVEN DATABASE CONNECTED");
            System.out.println("==============================");

        } else {

            System.out.println("==============================");
            System.out.println("DATABASE CONNECTION FAILED");
            System.out.println("==============================");
        }
    }
}