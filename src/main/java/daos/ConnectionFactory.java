package daos;

import com.mysql.cj.jdbc.Driver;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

// Connect to Database
public class ConnectionFactory {

    public static final String URL = "jdbc:mysql://localhost:3306/cars";
    public static final String USER = "danny";
    public static final String PASS = "password";
    static Logger demoLog =  Logger.getLogger("demoJDBC");



    // Get a connection to database and return connection object
    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    // Test Connection
    public static void main(String[] args) {
        demoLog.log(Level.INFO, "somestuff");
        System.out.println("-------- MySQL JDBC Connection Demo ------------");
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found !!");
            return;
        }

        System.out.println("MySQL JDBC Driver Registered!");

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);

            System.out.println("SQL Connection to database established!");

            //Statement example, with get[Whatever]:
            /*
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("select * from players");
            rs.first();
            System.out.println(rs.getString(2));
            System.out.println(rs.getString("last_name"));
            /**/

            // Read-only example:
            /*
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select * from players");
            //Printing the contents of the table
            System.out.println("Contents of the table: ");
            rs.beforeFirst();
            while(rs.next()) {
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", FirstName: " + rs.getString("first_name"));
                System.out.println(", LastName: " + rs.getString("last_name"));
            }
            System.out.println();
            //Moving the pointer to the starting point in the ResultSet
            rs.beforeFirst();
            //Adding 'Jr.' to the last name
            while(rs.next()) {
                //Retrieve by column name
                String newLast = rs.getString("last_name") + ", Jr.";
                rs.updateString( "last_name", newLast );
                rs.updateRow();
            }

            /**/

            //UPDATE example:
            /*
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from players");
            //Printing the contents of the table
            System.out.println("Contents of the table: ");
            rs.beforeFirst();
            while(rs.next()) {
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", FirstName: " + rs.getString("first_name"));
                System.out.println(", LastName: " + rs.getString("last_name"));
            }
            System.out.println();
            //Moving the pointer to the starting point in the ResultSet
            rs.beforeFirst();
            //Adding 'Jr.' to the last name
            while(rs.next()) {
                //Retrieve by column name
                String newLast = rs.getString("last_name") + ", Jr.";
                rs.updateString( "last_name", newLast );
                //rs.updateRow();
                //break;
            }
            System.out.println();
            rs.beforeFirst();
            while(rs.next()) {
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", FirstName: " + rs.getString("first_name"));
                System.out.println(", LastName: " + rs.getString("last_name"));
            }
            /**/

            //Prepared Statement Example
            /*
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM players WHERE id > ?");
            pstmt.setInt(1, 4);
            ResultSet rs = pstmt.executeQuery();
            rs.beforeFirst();
            while(rs.next()) {
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", FirstName: " + rs.getString("first_name"));
                System.out.println(", LastName: " + rs.getString("last_name"));
            }

            /**/


        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Connection Failed! Check output console");
            return;
        }
        finally {
            try
            {
                if(connection != null)
                    connection.close();
                System.out.println("Connection closed !!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


