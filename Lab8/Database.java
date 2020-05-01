package Lab8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Database single_instance = null;
    private Connection connection = null;

    private Database() {
        openConnection();
    }

    public void openConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/musicalbums", "Ciprian", "sql");
            System.out.println("All good!");
        } catch (SQLException ex) {
            System.out.println("Error: unable to connect to database!");
            System.exit(2);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
            System.out.println("All good!");
        } catch (SQLException ex) {
            System.out.println("Error: unable to correctly close the database!");
            System.exit(2);
        }
    }

    public static Database getInstance() {
        if (single_instance == null) {
            single_instance = new Database();
        }

        return single_instance;
    }
}
