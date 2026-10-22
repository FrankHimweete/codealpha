package StudentGradeTracker;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

public class StudentGradeTrackerApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                StudentGradeTrackerApp.class.getResource("student-grade-tracker-view.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        Label heading = new Label("Student Grade Tracker");
        stage.setFullScreen(false);


        var imageUrl = StudentGradeTrackerApp.class.getResource("img_1.png");

        if (imageUrl == null) {
            throw new IllegalStateException("img_1.png was not found");
        }

        Image icon = new Image(imageUrl.toExternalForm());
        stage.getIcons().add(icon);

        stage.setTitle("STUDENT GRADE SYSTEM");
        stage.setFullScreen(false);


      stage.setScene(scene);
      stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}