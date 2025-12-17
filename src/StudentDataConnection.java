import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StudentDataConnection {

    private final String URL = "jdbc:mysql://localhost:3306/mydb";
    private final String USER = "root";
    private final String PASS = "pogisiwinter_1";

    // Method to get a database connection
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Connection failed! Check output console");
            e.printStackTrace();
            return null;
        }
    }
}