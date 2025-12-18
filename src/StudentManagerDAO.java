import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class StudentManagerDAO {

    private final StudentDataConnection DataBaseConnection = new StudentDataConnection();

    /*
    - Add Students
    - View Students
    - Delete Students
    - Update Students
     */
    protected void addStudent(Students s) {
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

    protected void viewStudents() {
        String viewerSql = "SELECT * FROM studentmanager";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(viewerSql);
             ResultSet rs = ps.executeQuery()) {

            int idWidth = 6;
            int nameWidth = 25;
            int courseWidth = 22;
            int avgWidth = 8;

            System.out.printf("+%" + idWidth + "s+%" + nameWidth + "s+%" + courseWidth + "s+%" + avgWidth + "s+\n",
                    "-".repeat(idWidth), "-".repeat(nameWidth), "-".repeat(courseWidth), "-".repeat(avgWidth));
            System.out.printf("| %-" + (idWidth - 1) + "s| %-" + (nameWidth - 1) + "s| %-" + (courseWidth - 1) + "s| %-" + (avgWidth - 1) + "s|\n",
                    "ID", "Name", "Course", "Average");
            System.out.printf("+%" + idWidth + "s+%" + nameWidth + "s+%" + courseWidth + "s+%" + avgWidth + "s+\n",
                    "-".repeat(idWidth), "-".repeat(nameWidth), "-".repeat(courseWidth), "-".repeat(avgWidth));

            boolean hasRows = false;
            while (rs.next()) {
                hasRows = true;
                System.out.printf("| %-" + (idWidth - 1) + "d| %-" + (nameWidth - 1) + "s| %-" + (courseWidth - 1) + "s| %-" + (avgWidth - 1) + ".2f|\n",
                        rs.getInt("student_ID"),
                        rs.getString("student_NAME"),
                        rs.getString("student_COURSE"),
                        rs.getDouble("student_GRADEAVERAGE"));
            }

            if (!hasRows) {
                System.out.println("| No students found in database |");
            }

            System.out.printf("+%" + idWidth + "s+%" + nameWidth + "s+%" + courseWidth + "s+%" + avgWidth + "s+\n",
                    "-".repeat(idWidth), "-".repeat(nameWidth), "-".repeat(courseWidth), "-".repeat(avgWidth));

        } catch (SQLException e) {
            System.out.println("Error viewing students: " + e.getMessage());
        }
    }


    protected void updateStudentNameCourseAndAverage(int id, String newName, String course, double newAverage) {
        String updateStudentNameAndAverageSQL = "UPDATE studentmanager SET student_NAME = ?, student_COURSE = ?, student_GRADEAVERAGE = ? "
                + "WHERE student_ID = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(updateStudentNameAndAverageSQL)) {

            ps.setString(1, newName);
            ps.setString(2, course);
            ps.setDouble(3, newAverage);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();

            System.out.println(rows > 0 ? "Updated Successfully." : "Student not Found");


        } catch (SQLException e) {
            System.out.println("Error Updating: " + e.getMessage());
        }
    }

    public Students getStudentById(int id) {
        String getStudentByIdSQL = "Select * FROM studentmanager WHERE student_ID = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(getStudentByIdSQL)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Students (
                            rs.getInt("student_ID"),
                            rs.getString("student_NAME"),
                            rs.getString("student_COURSE"),
                            rs.getDouble("student_GRADEAVERAGE"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error Getting data: " + e.getMessage());
        }
        return null;
    }

    protected void deleteStudentByItsID(int studentId) {
        String deleteStudentByItsIDSQL = "DELETE FROM studentmanager WHERE student_ID = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(deleteStudentByItsIDSQL)) {

            ps.setInt(1, studentId);

            int rows = ps.executeUpdate();

            System.out.println(rows > 0 ? "Deleted Successfully." : "Deletion Failed.");

        } catch (SQLException e) {
            System.out.println("Error deleting Student: " + e.getMessage());
        }
    }

    public void searchStudentIDNumber(int StudentNumberId) {
        String searchStudentIdNUMBERSQL = "SELECT * FROM studentmanager WHERE student_ID = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(searchStudentIdNUMBERSQL)) {

            ps.setInt(1, StudentNumberId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("STUDENT CREDENTIAL: " +
                            rs.getInt("student_ID") + " | " +
                            rs.getString("student_NAME") + " | " +
                            rs.getString("student_COURSE") + " | " +
                            rs.getDouble("student_GRADEAVERAGE"));
                } else {
                    System.out.println("No student found with the ID of " + StudentNumberId);
                }
            } catch (SQLException e) {
                System.out.println("Exception occurs: " + e.getMessage());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}