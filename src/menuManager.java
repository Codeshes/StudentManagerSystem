import java.util.Scanner;

public class menuManager {
    StudentManager studManager = new StudentManager();
    Scanner sc = new Scanner(System.in);

    public void menuStart() {
        int choice = 0;
        try {

            System.out.println("""
                    [1]. Add user
                    [2]. View user""");
            choice = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid input");
            sc.nextLine();
        }
        switch (choice) {
            case 1 -> {
                System.out.println("Enter Student Name");
                String studentName = sc.nextLine();

                System.out.println("Enter student Course");
                String studentCourse = sc.nextLine();

                System.out.println("Enter Student Final Average");
                Double finalAverage = sc.nextDouble();
                sc.nextLine();

                Students s = new Students(studentName, studentCourse, finalAverage);
                studManager.addStudent(s);

            }
            case 2 -> {
                studManager.viewStudents();
            }
        }
    }
}
