package daos;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Connect to Database
public class ConnectionFactory {

    public static final String URL = "jdbc.sql://localhost:3306/testdb";
    public static final String USER = "testuser";
    public static final String PASS = "testpass";


    // Get a connection to database and return connection object
    public static Connection getConnection(){
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

    // Test Connection
    public static void main(String[] args) {
        Connection connection = ConnectionFactory.getConnection();
    }


}
