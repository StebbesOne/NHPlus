package datastorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton Class for making a SQL connection
 */
public class ConnectionBuilder {
    private static Connection conn;

    /**
     * Initializes the connection
     */
    private ConnectionBuilder() {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            System.out.println("Working Directory = " + System.getProperty("user.dir"));

            conn = DriverManager.getConnection("jdbc:hsqldb:db/nursingHomeDB;user=SA;password=SA");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Treiberklasse konnte nicht gefunden werden!");
        } catch (SQLException e) {
            System.out.println("Verbindung zur Datenbank konnte nicht aufgebaut werden!");
            e.printStackTrace();
        }
    }

    /**
     * Returns the connection
     *
     * @return SQL Connection
     */
    public static Connection getConnection() {
        if (conn == null) {
            new ConnectionBuilder();
        }
        return conn;
    }

    /**
     * Closes the connection
     */
    public static void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
