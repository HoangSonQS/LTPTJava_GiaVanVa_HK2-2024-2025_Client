package iuh.fit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Lớp để chạy ứng dụng trực tiếp từ màn hình đăng nhập
 */
public class LoginApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            System.out.println("LoginApp: Loading Login_gui.fxml");
            
            // Load giao diện đăng nhập
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login_gui.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Đăng nhập");
            primaryStage.show();
            
            System.out.println("LoginApp: Login screen opened successfully");
        } catch (Exception e) {
            System.err.println("LoginApp: Error loading Login_gui.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
