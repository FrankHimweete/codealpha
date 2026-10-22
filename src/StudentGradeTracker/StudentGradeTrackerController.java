package StudentGradeTracker;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class StudentGradeTrackerController {


    @FXML
    private TextField studentNameField;

    @FXML
    private TextField gradeField;

    @FXML
    private ListView<Integer> gradesListView;

    @FXML
    private Button addGradeButton;

    @FXML
    private Button saveStudentButton;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ImageView backgroundImageView;

    private final GradeTracker tracker = new GradeTracker();

    @FXML
    private ListView<String> studentSummaryListView;

    @FXML

    private final ObservableList<Student> studentData =
            FXCollections.observableArrayList();

    @FXML
    private void addGrade() {
        try {
            int grade = Integer.parseInt(gradeField.getText().trim());

            if (grade < 0 || grade > 100) {
                showError("Enter a grade from 0 to 100.");
                return;
            }

            gradesListView.getItems().add(grade);
            gradeField.clear();

        } catch (NumberFormatException e) {
            showError("Enter a whole-number grade.");
        }
    }
    @FXML
    private void initialize() {
        studentNameField.setTextFormatter(new TextFormatter<String>(change -> {
            String newText = change.getControlNewText();

            if (newText.matches("[a-zA-Z ]*")) {
                return change;
            }

            return null;
        }));

        backgroundImageView.fitWidthProperty().bind(rootPane.widthProperty());
        backgroundImageView.fitHeightProperty().bind(rootPane.heightProperty());
        backgroundImageView.setPreserveRatio(false);
    }

    @FXML
    private void viewSummary(ActionEvent event) {
        try {
            Scene entryScene = ((Node) event.getSource())
                    .getScene();

            FXMLLoader loader = new FXMLLoader(
                    StudentGradeTrackerController.class
                            .getResource("student-summary-view.fxml")
            );

            Parent summaryRoot = loader.load();

            StudentSummaryController summaryController = loader.getController();
            summaryController.setStudents(studentData);
            summaryController.setEntryScene(entryScene);

            Stage stage = (Stage) entryScene.getWindow();
            stage.setScene(new Scene(summaryRoot, 1000, 700));

        } catch (IOException e) {
            showError("Could not open the summary page: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    private void saveStudent() {
        String name = studentNameField.getText().trim();

        if (name.isEmpty()) {
            showError("Enter a student name.");
            return;
        }
        if (!name.matches("[a-zA-Z]+( [a-zA-Z]+)*")) {
            showError("Enter letters only for the student name.");
            return;
        }

        if (gradesListView.getItems().isEmpty()) {
            showError("Add at least one grade.");
            return;
        }

        Student student = new Student(name);

        for (Integer grade : gradesListView.getItems()) {
            student.addGrade(grade);
        }

        tracker.addStudent(student);
        studentData.add(student);
        String summary = String.format(
                "%s | Average: %.2f | Highest: %.1f | Lowest: %.1f",
                student.getName(),
                student.getAverage(),
                student.getHighestGrade(),
                student.getLowestGrade()
        );

        new Alert(
                Alert.AlertType.INFORMATION,
                "Student saved successfully."
        ).showAndWait();

        studentNameField.clear();
        gradeField.clear();
        gradesListView.getItems().clear();
    }

    private void showError(String message) {
        new Alert(Alert.AlertType.ERROR, message).showAndWait();
    }
}