package StudentGradeTracker;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main (String[]  args){


        GradeTracker tracker = new GradeTracker();

        Scanner scanner = new Scanner(System.in);

        // Prompt user to enter there name
        int numberOfStudents = 0;

        while (numberOfStudents <= 0) {
            System.out.println("Enter number of students registered: ");

            if (scanner.hasNextInt()) {
                numberOfStudents = scanner.nextInt();

                if (numberOfStudents <= 0) {
                    System.out.println("Enter at least one student.");
                }
            } else {
                System.out.println("Please enter a whole number.");
                scanner.next(); // discard the text that wasn't a number
            }
        }

        scanner.nextLine();
        scanner.nextLine();

        for(int i = 1; i <= numberOfStudents; i++){
            System.out.println("Student " + i + " : ");


            String name = getValidString(scanner,"Enter Student name: " );

            Student student = new Student(name);



            int numberOfGrades = getValidInteger(scanner, "Number of grades for" + name + " ? ");

            while (numberOfGrades <= 0){
                System.out.println("Enter at least one grade: ");
                numberOfGrades = scanner.nextInt();
            }
                int gradesEntered = 1;

                while (gradesEntered <= numberOfGrades) {
                    System.out.print("Enter grade " + (gradesEntered) + ": ");
                    int grade = scanner.nextInt();

                    boolean accepted = student.addGrade(grade);

                    if (accepted) {
                        gradesEntered++;
                    } else {
                        System.out.println("Enter a grade from 0 to 100.");
                    }
                }


            scanner.nextLine();
            tracker.addStudent(student);
        }

        tracker.displayStudents();


    }
    public static int getValidInteger(Scanner scanner, String message){
        while (true) {
            try{
                System.out.print(message);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number ");
                scanner.nextLine();
            }
        }
    }

    public static String getValidString(Scanner scanner, String message){
        while(true){
            System.out.print(message);

            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Invalid input. Please try again");
            } else if (!input.matches("[a-zA-Z]+( [a-zA-Z]+)*")) {
                System.out.println("Invalid input. Please enter letters only.");
            }
            else{
                return input;
            }
        }
    }



}
