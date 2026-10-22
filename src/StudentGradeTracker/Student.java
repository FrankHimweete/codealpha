package StudentGradeTracker;
import java.util.ArrayList;

public class Student {
    private String name;
    private ArrayList<Double> grades;


    // Student Constractor

    public Student(String name){
        this.name = name;
        this.grades = new ArrayList<>();
    }

    // Grade Validitity varification
    public boolean addGrade(double grade) {

        if(grade < 0 || grade > 100){

            return false;
        }

        grades.add(grade);
        return true;
    }

    // Getter method for name

    public String getName() {
        return name;
    }

    public double getAverage(){
        if(grades.isEmpty()){
            return 0;
        }

        int total = 0;

        for(Double grade : grades ){
            total += grade;
        }

        return(double) total / grades.size();
    }
    // method that gets the highest grade
    public Double getHighestGrade() {
        if(grades.isEmpty()){
            return 0.0;
        }

        Double highest = grades.get(0);

        for (Double grade: grades) {
            if (grade > highest) {
                highest = grade;
            }
        }

        return highest;
    }

    public Double getLowestGrade() {
        if(grades.isEmpty()){
            return 0.0;

        }

        Double lowest = grades.get(0);

        for (Double grade : grades){
            if(grade <  lowest){
                lowest = grade;
            }
        }
        return lowest;
    }

}
