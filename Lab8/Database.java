package Lab8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A singleton implementation of a database object.
 */
public class Database {

    private static Database single_instance = null;
    private Connection connection = null;

    /**
     * Creates an instance of the class.
     */
    private Database() {
        openConnection();
    }

    /**
     * Opens the connection to the database.
     */
    public void openConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/musicalbums", "Ciprian", "sql");
            System.out.println("All good!");
        } catch (SQLException ex) {
            System.out.println("Error: unable to connect to database!");
            System.exit(2);
        }
    }

    /**
     * Closes the connection to the database.
     */
    public void closeConnection() {
        try {
            connection.close();
            System.out.println("All good!");
        } catch (SQLException ex) {
            System.out.println("Error: unable to correctly close the database!");
            System.exit(2);
        }
    }

    /**
     * Returns the singleton instance of the database (if it didn't exist until the call, it is created).
     * @return the instance of the <code>Database</code> class.
     */
    public static Database getInstance() {
        if (single_instance == null) {
            single_instance = new Database();
        }

        return single_instance;
    }
}
