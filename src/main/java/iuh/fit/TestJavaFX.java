package iuh.fit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class TestJavaFX extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/TraCuuPhieuNhap_gui.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();
            scene.getStylesheets().add(getClass().getResource("/styles/menu.css").toExternalForm());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
