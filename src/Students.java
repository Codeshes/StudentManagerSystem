public class Students {
    /*
    - Student ID
    - Student Name
    - Student Course
    - Student Grade(Final Ave);
     */

    protected int StudentId;

    protected String studentName;
    protected String studentCourse;
    protected Double FinalAverage;

    public Students(String studentName, String studentCourse, Double finalAverage) {
        this.studentName = studentName;
        this.studentCourse = studentCourse;
        this.FinalAverage = finalAverage;
    }

    public int getStudentId() {return StudentId;}
    public String getStudentName() { return studentName; }
    public String getStudentCourse() { return studentCourse; }
    public double getFinalAverage() { return FinalAverage; }

    public void setStudentName(String studentName) { this.studentName = studentName; }
    public void setStudentCourse(String studentCourse) { this.studentCourse = studentCourse; }
    public void setStudentAverage(Double finalAverage) { this.FinalAverage = finalAverage; }
}
