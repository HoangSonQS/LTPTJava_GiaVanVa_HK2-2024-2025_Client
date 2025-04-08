package iuh.fit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Lớp để chạy ứng dụng trực tiếp từ màn hình bán hàng
 */
public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            System.out.println("MainApp: Loading BanHang_gui.fxml");
            
            // Load giao diện bán hàng
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/BanHang_gui.fxml"));
            Scene scene = new Scene(root);
            
            // Thêm stylesheet nếu có
            try {
                scene.getStylesheets().add(getClass().getResource("/styles/menu.css").toExternalForm());
            } catch (Exception e) {
                System.err.println("MainApp: Could not load stylesheet: " + e.getMessage());
            }
            
            // Thiết lập stage
            primaryStage.setScene(scene);
            primaryStage.setTitle("Hệ thống quản lý bán hàng");
            
            // Thiết lập kích thước stage theo màn hình chính
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            primaryStage.setX(primaryScreenBounds.getMinX());
            primaryStage.setY(primaryScreenBounds.getMinY());
            primaryStage.setWidth(primaryScreenBounds.getWidth());
            primaryStage.setHeight(primaryScreenBounds.getHeight());
            
            // Hiển thị stage
            primaryStage.show();
            
            System.out.println("MainApp: Main screen opened successfully");
        } catch (Exception e) {
            System.err.println("MainApp: Error loading BanHang_gui.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
