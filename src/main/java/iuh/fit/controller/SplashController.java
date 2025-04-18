package iuh.fit.controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import iuh.fit.App;
import iuh.fit.interfaces.SanPham_interface;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                // Cập nhật tiến trình
                updateProgress(0.1, 1.0);
                updateMessage("Đang khởi động ứng dụng...");
                Thread.sleep(800);

                // Kiểm tra kết nối cơ sở dữ liệu
                updateProgress(0.3, 1.0);
                updateMessage("Đang kết nối cơ sở dữ liệu...");
                Thread.sleep(800);

                // Kiểm tra kết nối đến server
                updateProgress(0.5, 1.0);
                updateMessage("Đang kết nối đến máy chủ...");
                boolean serverConnected = false;
                try {
                    Registry registry = LocateRegistry.getRegistry("localhost", 9090);
                    SanPham_interface sanPhamDao = (SanPham_interface) registry.lookup("sanPhamDAO");
                    // Thử gọi một phương thức để kiểm tra kết nối
                    sanPhamDao.readAll();
                    serverConnected = true;
                } catch (Exception e) {
                    System.err.println("Không thể kết nối đến máy chủ: " + e.getMessage());
                    e.printStackTrace();
                    serverConnected = false;
                }

                if (!serverConnected) {
                    updateProgress(1.0, 1.0);
                    updateMessage("Không thể kết nối đến máy chủ!");
                    Thread.sleep(1000);
                    return false;
                }

                // Tải dữ liệu
                updateProgress(0.7, 1.0);
                updateMessage("Đang tải dữ liệu...");
                Thread.sleep(800);

                // Hoàn tất
                updateProgress(1.0, 1.0);
                updateMessage("Hoàn tất!");
                Thread.sleep(500);

                return true;
            }
        };

        // Liên kết progress bar với task
        progressBar.progressProperty().bind(task.progressProperty());

        // Liên kết label status với task
        task.messageProperty().addListener((observable, oldValue, newValue) -> {
            updateStatus(newValue);
        });

        // Khi task hoàn thành, mở màn hình đăng nhập nếu kết nối thành công
        task.setOnSucceeded(event -> {
            Boolean serverConnected = task.getValue();
            System.out.println("Loading task completed, server connected: " + serverConnected);

            // Tạo một task riêng để mở màn hình đăng nhập sau khi splash hoàn tất
            new Thread(() -> {
                try {
                    // Đợi một chút trước khi mở màn hình đăng nhập
                    Thread.sleep(1000);

                    // Chuyển sang thread JavaFX để mở màn hình đăng nhập hoặc hiển thị thông báo lỗi
                    Platform.runLater(() -> {
                        try {
                            // Đóng màn hình splash
                            if (iuh.fit.AppPreloader.splashStage != null) {
                                iuh.fit.AppPreloader.splashStage.close();
                                System.out.println("Splash screen closed");
                            }

                            if (serverConnected) {
                                // Mở màn hình đăng nhập nếu kết nối thành công
                                App.openLoginGUI();
                                System.out.println("Login screen opened");
                            } else {
                                // Hiển thị thông báo lỗi nếu không kết nối được đến máy chủ
                                Alert alert = new Alert(Alert.AlertType.ERROR,
                                    "Không thể kết nối đến máy chủ. Vui lòng kiểm tra kết nối và thử lại sau.",
                                    ButtonType.OK);
                                alert.setTitle("Lỗi kết nối");
                                alert.setHeaderText("Không thể kết nối đến máy chủ");
                                alert.showAndWait().ifPresent(response -> {
                                    if (response == ButtonType.OK) {
                                        System.exit(0);
                                    }
                                });
                            }
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
}
