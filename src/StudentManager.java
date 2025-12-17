import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class StudentManager {

    private final StudentDataConnection DataBaseConnection = new StudentDataConnection();

    /*
    - Add Students
    - View Students
    - Delete Students
    - Update Students
     */
    public void addStudent(Students s) {
        String insertSQL = "INSERT INTO studentmanager (student_NAME, student_COURSE, student_GRADEAVERAGE) VALUES( ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {


            ps.setString(1, s.getStudentName());
            ps.setString(2, s.getStudentCourse());
            ps.setDouble(3, s.getFinalAverage());

            ps.executeUpdate();
            System.out.println("Student added Successfully!");


        } catch (SQLException e) {
            System.out.println("Error adding student " + e.getMessage());

        }
    }

    public void viewStudents() {
        String viewerSql = "SELECT * FROM studentmanager";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(viewerSql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("student_ID") +
                                ", Name: " + rs.getString("student_NAME") +
                                ", Course: " + rs.getString("student_COURSE") +
                                ", Average: " + rs.getDouble("student_GRADEAVERAGE")
                );
            }


        } catch (SQLException e) {
            System.out.println("error viewing Student " + e.getMessage());

        }
    }

    public void updateStudentNameAndAverage(int id, String newName, double newAverage) {
        String updateStudentNameAndAverageSQL = "UPDATE studentmanager SET student_NAME = ?, student_GRADEAVERAGE = ? "
                + "WHERE student_ID = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(updateStudentNameAndAverageSQL)) {

            ps.setString(1, newName);
            ps.setDouble(2, newAverage);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();

            System.out.println(rows > 0 ? "Updated Successfully." : "Student not Found");

            System.out.println("Updated Successfully: " + newName + " " + newAverage);

        } catch (SQLException e) {
            System.out.println("Error searching: " + e.getMessage());
        }
    }

}
