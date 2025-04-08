package iuh.fit.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import iuh.fit.App;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller cho màn hình Splash
 */
public class SplashController implements Initializable {

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblVersion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Thiết lập phiên bản
        lblVersion.setText("Phiên bản 1.0.0");

        // Tạo hiệu ứng mờ dần cho label
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), lblStatus);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.3);
        fadeTransition.setCycleCount(Timeline.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();

        // Khởi động quá trình tải ứng dụng
        startLoadingProcess();
    }

    /**
     * Khởi động quá trình tải ứng dụng
     */
    private void startLoadingProcess() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                // Cập nhật tiến trình
                updateProgress(0.1, 1.0);
                updateMessage("Đang khởi động ứng dụng...");
                Thread.sleep(800);

                // Kiểm tra kết nối cơ sở dữ liệu
                updateProgress(0.3, 1.0);
                updateMessage("Đang kết nối cơ sở dữ liệu...");
                Thread.sleep(800);

                // Kiểm tra dữ liệu
                updateProgress(0.5, 1.0);
                updateMessage("Đang kiểm tra dữ liệu...");
                Thread.sleep(800);

                // Tải dữ liệu
                updateProgress(0.7, 1.0);
                updateMessage("Đang tải dữ liệu...");
                Thread.sleep(800);

                // Hoàn tất
                updateProgress(1.0, 1.0);
                updateMessage("Hoàn tất!");
                Thread.sleep(500);

                return null;
            }
        };

        // Liên kết progress bar với task
        progressBar.progressProperty().bind(task.progressProperty());

        // Liên kết label status với task
        task.messageProperty().addListener((observable, oldValue, newValue) -> {
            updateStatus(newValue);
        });

        // Khi task hoàn thành, mở màn hình đăng nhập
        task.setOnSucceeded(event -> {
            System.out.println("Loading task completed, opening login screen");

            // Tạo một task riêng để mở màn hình đăng nhập sau khi splash hoàn tất
            new Thread(() -> {
                try {
                    // Đợi một chút trước khi mở màn hình đăng nhập
                    Thread.sleep(1000);

                    // Chuyển sang thread JavaFX để mở màn hình đăng nhập
                    Platform.runLater(() -> {
                        try {
                            // Đóng màn hình splash
                            if (iuh.fit.AppPreloader.splashStage != null) {
                                iuh.fit.AppPreloader.splashStage.close();
                                System.out.println("Splash screen closed");
                            }

                            // Mở màn hình đăng nhập
                            App.openLoginGUI();
                            System.out.println("Login screen opened");
                        } catch (Exception e) {
                            System.err.println("Error in Platform.runLater when opening login screen: " + e.getMessage());
                            e.printStackTrace();
                        }
                    });
                } catch (Exception e) {
                    System.err.println("Error in thread when opening login screen: " + e.getMessage());
                    e.printStackTrace();
                }
            }).start();
        });

        // Bắt đầu task trong một thread riêng
        new Thread(task).start();
    }

    /**
     * Cập nhật trạng thái hiển thị
     */
    private void updateStatus(String status) {
        if (lblStatus != null) {
            Platform.runLater(() -> lblStatus.setText(status));
        }
    }

    /**
     * Kiểm tra các chức năng cần thiết khi khởi động
     */
    public void checkFunctions() {
        // Phương thức này không cần thiết nữa vì đã được xử lý trong startLoadingProcess()
        // Giữ lại để tương thích với code hiện tại
    }

    /**
     * Phương thức main để có thể chạy trực tiếp từ SplashController nếu cần
     */
    public static void main(String[] args) {
        try {
            // Tạo một stage mới
            Stage stage = new Stage();

            // Load màn hình splash
            FXMLLoader loader = new FXMLLoader(SplashController.class.getResource("/fxml/Splash.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Thiết lập và hiển thị stage
            stage.setScene(scene);
            stage.initStyle(javafx.stage.StageStyle.UNDECORATED);
            stage.show();

            // Lấy controller để bắt đầu quá trình tải
            SplashController controller = loader.getController();

            // Bắt đầu quá trình tải trong một thread riêng
            new Thread(() -> {
                try {
                    Thread.sleep(1000); // Đợi 1 giây
                    Platform.runLater(() -> {
                        try {
                            // Đóng màn hình splash
                            stage.close();

                            // Mở màn hình đăng nhập
                            iuh.fit.App.openLoginGUI();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
