package StudentGradeTracker;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class StudentSummaryController {

    @FXML
    private TableView<Student> studentTableView;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private TableColumn<Student, Double> averageColumn;

    @FXML
    private TableColumn<Student, Double> highestColumn;

    @FXML
    private TableColumn<Student, Double> lowestColumn;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ImageView backgroundImageView;

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        averageColumn.setCellValueFactory(
                new PropertyValueFactory<>("average"));

        highestColumn.setCellValueFactory(
                new PropertyValueFactory<>("highestGrade"));

        lowestColumn.setCellValueFactory(
                new PropertyValueFactory<>("lowestGrade"));

        backgroundImageView.fitWidthProperty().bind(rootPane.widthProperty());
        backgroundImageView.fitHeightProperty().bind(rootPane.heightProperty());
        backgroundImageView.setPreserveRatio(false);
    }

    private Scene entryScene;

    public void setStudents(ObservableList<Student> students) {
        studentTableView.setItems(students);
    }

    public void setEntryScene(Scene entryScene) {
        this.entryScene = entryScene;
    }

    @FXML
    private void goBack(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.setScene(entryScene);
    }
}