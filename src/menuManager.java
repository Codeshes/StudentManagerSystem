import java.util.InputMismatchException;
import java.util.Scanner;

public class menuManager {
    StudentManager studManager = new StudentManager();
    Scanner sc = new Scanner(System.in);

    public void menuStart() {
        int choice = 0;
        try {

            System.out.println("""
                    [1]. Add user
                    [2]. View user
                    [3]. Update user
                    [4]. Delete Student""");
            choice = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid input");
            sc.nextLine();
        }
        switch (choice) {
            case 1 -> {
                try {


                    System.out.print("Enter Student Name: ");
                    String studentName = sc.nextLine();

                    System.out.print("Enter student Course: ");
                    String studentCourse = sc.nextLine();


                    double finalAverage;
                    while (true) {
                        System.out.print("Enter Student Final Average: ");
                        String input = sc.nextLine();

                        try {
                            finalAverage = Double.parseDouble(input);
                            if (finalAverage < 0) {
                                System.out.println("Average cannot be negative value");
                                continue;
                            }
                            break;
                        } catch(InputMismatchException e){
                            System.out.println("Invalid Input. Please enter a valid input");
                        }
                    }

                    if (studentName.isEmpty() && studentCourse.isEmpty()) System.out.println("FIELD CANNOT BE EMPTY");

                    Students s = new Students(studentName, studentCourse, finalAverage);
                    studManager.addStudent(s);

                } catch (InputMismatchException e) {
                    System.out.println("Invalid Input");
                    sc.nextLine();
                }

            }
            case 2 -> studManager.viewStudents();
            case 3 -> {
                System.out.print("Enter Student ID to edit Credentials: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Student Name Edit: ");
                String studentNewName = sc.nextLine();

                System.out.print("Enter new Student Average Grade: ");
                double studentNewGradeAverage = sc.nextDouble();

                studManager.updateStudentNameAndAverage(id, studentNewName, studentNewGradeAverage);
            }
            case 4 -> {
                System.out.print("Enter a STUDENT ID to be DELETED: ");
                int id = sc.nextInt();
                sc.nextLine();

                studManager.deleteStudentByItsID(id);
            }
        }
    }
}
