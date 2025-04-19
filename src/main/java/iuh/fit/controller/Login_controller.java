package iuh.fit.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import iuh.fit.App;
import iuh.fit.interfaces.TaiKhoan_interface;
import iuh.fit.entities.TaiKhoan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Controller cho màn hình đăng nhập
 */
public class Login_controller implements Initializable {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink linkForgotPassword;

    private TaiKhoan_interface taiKhoanDao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Khởi tạo DAO interface
        try {
            System.setProperty("java.security.policy", "rmi.policy");
            System.setProperty("java.rmi.server.hostname", "LAPTOP-O8OOBHDK");
            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
            taiKhoanDao = (TaiKhoan_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/taiKhoanDAO");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể kết nối đến server: " + e.getMessage());
        }

        // Thêm sự kiện Enter cho các trường nhập liệu
        txtUsername.setOnKeyPressed(this::handleEnterKey);
        txtPassword.setOnKeyPressed(this::handleEnterKey);
    }

    /**
     * Xử lý sự kiện nhấn Enter
     */
    private void handleEnterKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            login();
        }
    }

    /**
     * Xử lý sự kiện nhấn nút đăng nhập
     */
    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        login();
    }

    /**
     * Xử lý sự kiện quên mật khẩu
     */
    @FXML
    private void handleForgotPasswordAction(ActionEvent event) {
        try {
            App.openQuenMK();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện quên mật khẩu!");
        }
    }

    /**
     * Thực hiện đăng nhập
     */
    private void login() {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();

        // Validate input
        if (username.isEmpty()) {
            showAlert(AlertType.WARNING, "Cảnh báo", "Vui lòng nhập tên đăng nhập!");
            txtUsername.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            showAlert(AlertType.WARNING, "Cảnh báo", "Vui lòng nhập mật khẩu!");
            txtPassword.requestFocus();
            return;
        }

        // Check login
        try {
            TaiKhoan taiKhoan = taiKhoanDao.findByUsernameandPassword(username, password);
            if (taiKhoan != null) {
                // Save login information
                App.taiKhoan = taiKhoan;
                App.user = username;
                App.ma = taiKhoan.getMaTaiKhoan();
                txtUsername.getScene().getWindow().hide();
                // Close the login screen and open the main GUI
                App.openMainGUI();
            } else {
                showAlert(AlertType.ERROR, "Lỗi đăng nhập", "Tên đăng nhập hoặc mật khẩu không đúng!");
                txtPassword.clear();
                txtPassword.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Lỗi khi đăng nhập: " + e.getMessage());
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
