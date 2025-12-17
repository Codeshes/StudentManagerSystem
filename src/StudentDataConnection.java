import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StudentDataConnection {

    protected final String URL = "jdbc:mysql://localhost:3306/mydb";
    protected final String USER = "root";
    protected final String PASS = "pogisiwinter_1";

    // Method to get a database connection
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Connection failed! Check output console: " + e.getMessage());
            return null;
        }
    }
}