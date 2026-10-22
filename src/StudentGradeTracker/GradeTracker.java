package StudentGradeTracker;

import javafx.event.ActionEvent;

import java.util.ArrayList;

public class GradeTracker {
    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student) {

        students.add(student);
    }

    public void displayStudents() {
       if (students.isEmpty()){
           System.out.println("No students have been added.");
           return;
       }
       System.out.printf("%-15s | %-10s | %-10s | %-10s%n",
                "Name", "Average", "Highest", "Lowest");
       for (Student student : students ) {
           System.out.printf("%-15s | %-10.2f | %-10s | %-10s%n",
                   student.getName(),
                   student.getAverage(),
                   student.getHighestGrade(),
                   student.getLowestGrade());
       }
    }

    public void addGrade(ActionEvent actionEvent) {
    }
}
