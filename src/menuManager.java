import java.util.Scanner;

public class menuManager {
    StudentManagerDAO studManager = new StudentManagerDAO();

    Scanner sc = new Scanner(System.in);

    public void menuStart() {
        int choice;
        while (true) {
            System.out.println("""
                    =============================
                         [1]. Add user
                         [2]. View user
                         [3]. Update user
                         [4]. Delete Student
                         [5]. Search User by ID
                         [0]. EXIT
                    =============================""");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input.");
                sc.nextLine();
                return;
            }
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Student Name: ");
                    String studentName = sc.nextLine().trim();

                    System.out.print("Enter student Course: ");
                    String studentCourse = sc.nextLine().trim();

                    if (studentName.isEmpty() || studentCourse.isEmpty()) {
                        System.out.println("FIELD CANNOT BE EMPTY");
                        break;
                    }


                    double finalAverage;
                    while (true) {
                        System.out.print("Enter Student Final Average: ");
                        String input = sc.nextLine().trim();

                        try {
                            finalAverage = Double.parseDouble(input);
                            if (finalAverage < 0) {
                                System.out.println("Average cannot be negative value");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid Input. Please enter a valid input");
                        }
                    }

                    Students s = new Students(studentName, studentCourse, finalAverage);
                    studManager.addStudent(s);

                }
                case 2 -> studManager.viewStudents();
                case 3 -> {

                    int id;
                    while (true) {
                        System.out.print("Enter Student ID to update: ");
                        String input = sc.nextLine().trim();

                        if (input.isEmpty()) {
                            System.out.println("Field cannot be empty.");
                            continue;
                        }

                        try {
                            id = Integer.parseInt(input);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid ID.");
                        }
                    }

                    Students existing = studManager.getStudentById(id);
                    if (existing == null) {
                        System.out.println("Student not found.");
                        break;
                    }

                    System.out.println("Press ENTER to keep current value.");

                    // Initialize with existing values
                    String newName = existing.getStudentName();
                    String newCourse = existing.getStudentCourse();
                    double newAverage = existing.getFinalAverage();

                    // Name
                    System.out.print("New Name [" + newName + "]: ");
                    String inputName = sc.nextLine().trim();
                    if (!inputName.isEmpty()) {
                        newName = inputName;
                    }

                    // Course
                    System.out.print("New Course [" + newCourse + "]: ");
                    String inputCourse = sc.nextLine().trim();
                    if (!inputCourse.isEmpty()) {
                        newCourse = inputCourse;
                    }

                    // Average
                    while (true) {
                        System.out.print("New Average [" + newAverage + "]: ");
                        String input = sc.nextLine().trim();

                        if (input.isEmpty()) {
                            break;
                        }

                        try {
                            double parsed = Double.parseDouble(input);
                            if (parsed < 0) {
                                System.out.println("Average cannot be negative.");
                                continue;
                            } else if (!input.isEmpty()) {
                                newAverage = parsed;
                            }
                            newAverage = parsed;
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number.");
                        }
                    }
                    studManager.updateStudentNameCourseAndAverage(id, newName, newCourse, newAverage);
                }
                case 4 -> {
                    int id;
                    while (true) {
                        System.out.print("Enter a student ID to be deleted in the data RECORDS: ");
                        String input = sc.nextLine();

                        if (input.isEmpty()) {
                            System.out.println("Field cannot be empty");
                            continue;
                        }

                        try {
                            id = Integer.parseInt(input);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid input.");
                        }
                    }

                    studManager.deleteStudentByItsID(id);
                }
                case 5 -> {
                    int searchById;
                    while (true) {
                        System.out.println("Enter student ID to SEARCH");
                        String input = sc.nextLine();

                        if (input.isEmpty()) {
                            System.out.println("Field cannot be empty");
                            continue;
                        }

                        try {
                            searchById = Integer.parseInt(input);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Enter a Valid Input.");
                        }
                    }
                    studManager.searchStudentIDNumber(searchById);
                }
                case 0 -> {
                    try {
                        System.out.print("Exiting");
                        for (int i = 0; i < 5; i++) {
                            Thread.sleep(500);
                            System.out.print(".");
                        }
                        System.exit(0);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}