package iuh.fit.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import iuh.fit.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

/**
 * Controller cho màn hình quên mật khẩu
 */
public class QuenMatKhau_controller implements Initializable {

    @FXML
    private TextField txtEmail;

    @FXML
    private Button btnResetPassword;

    @FXML
    private Hyperlink linkBackToLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Khởi tạo các thành phần nếu cần
    }

    /**
     * Xử lý sự kiện khi nhấn nút lấy lại mật khẩu
     */
    @FXML
    private void handleResetPasswordAction(ActionEvent event) {
        String email = txtEmail.getText().trim();
        
        // Kiểm tra dữ liệu nhập vào
        if (email.isEmpty()) {
            showAlert(AlertType.WARNING, "Cảnh báo", "Vui lòng nhập email hoặc số điện thoại!");
            txtEmail.requestFocus();
            return;
        }
        
        // TODO: Thực hiện gửi email hoặc tin nhắn để lấy lại mật khẩu
        
        // Hiển thị thông báo thành công
        showAlert(AlertType.INFORMATION, "Thông báo", "Hệ thống đã gửi hướng dẫn lấy lại mật khẩu đến " + email);
        
        // Quay lại màn hình đăng nhập
        try {
            App.openLoginGUI();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện đăng nhập!");
        }
    }

    /**
     * Xử lý sự kiện khi nhấn link quay lại đăng nhập
     */
    @FXML
    private void handleBackToLoginAction(ActionEvent event) {
        try {
            App.openLoginGUI();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện đăng nhập!");
        }
    }
    
    /**
     * Hiển thị thông báo
     */
    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
