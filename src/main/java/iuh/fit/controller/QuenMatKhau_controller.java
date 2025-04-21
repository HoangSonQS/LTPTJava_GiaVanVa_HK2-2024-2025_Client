package iuh.fit.controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import iuh.fit.App;
import iuh.fit.entities.NhanVien;
import iuh.fit.entities.TaiKhoan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
            showAlert(AlertType.WARNING, "Cảnh báo", "Vui lòng nhập email!");
            txtEmail.requestFocus();
            return;
        }

        // Kiểm tra định dạng email
        if (!isValidEmail(email)) {
            showAlert(AlertType.WARNING, "Cảnh báo", "Email không đúng định dạng!");
            txtEmail.requestFocus();
            return;
        }

        try {
            // Thiết lập kết nối RMI
            System.setProperty("java.security.policy", "rmi.policy");
            System.setProperty("java.rmi.server.hostname", "LAPTOP-O8OOBHDK");
            Registry registry = LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);

            // Tìm kiếm NhanVien_interface
            iuh.fit.interfaces.NhanVien_interface nhanVienDao = (iuh.fit.interfaces.NhanVien_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/nhanVienDAO");

            // Tìm kiếm TaiKhoan_interface
            iuh.fit.interfaces.TaiKhoan_interface taiKhoanDao = (iuh.fit.interfaces.TaiKhoan_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/taiKhoanDAO");

            // Tìm nhân viên theo email
            List<NhanVien> nhanViens = nhanVienDao.readAllNhanVien();
            NhanVien nhanVien = null;

            for (NhanVien nv : nhanViens) {
                if (email.equalsIgnoreCase(nv.getEmail())) {
                    nhanVien = nv;
                    break;
                }
            }

            if (nhanVien != null) {
                // Tìm tài khoản liên kết với nhân viên này
                List<TaiKhoan> taiKhoans = taiKhoanDao.readAll();
                TaiKhoan taiKhoan = null;

                for (TaiKhoan tk : taiKhoans) {
                    if (tk.getNhanVien() != null && tk.getNhanVien().getMaNV().equals(nhanVien.getMaNV())) {
                        taiKhoan = tk;
                        break;
                    }
                }

                if (taiKhoan != null) {
                    // Hiển thị thông tin tài khoản
                    showAlert(AlertType.INFORMATION, "Thông tin tài khoản",
                            "Tên đăng nhập: " + taiKhoan.getTenDangNhap() + "\n" +
                            "Mật khẩu: " + taiKhoan.getMatKhau() + "\n\n" +
                            "Thông tin nhân viên:\n" +
                            "Họ tên: " + nhanVien.getTenNV() + "\n" +
                            "Email: " + nhanVien.getEmail());

                    // Close the dialog after showing the information
                    Stage stage = (Stage) btnResetPassword.getScene().getWindow();
                    stage.close();
                } else {
                    showAlert(AlertType.ERROR, "Lỗi", "Không tìm thấy tài khoản liên kết với nhân viên có email: " + email);
                }
            } else {
                showAlert(AlertType.ERROR, "Lỗi", "Không tìm thấy nhân viên với email: " + email);
            }
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi kết nối", "Không thể kết nối đến máy chủ: " + e.getMessage());
        }
    }

    /**
     * Xử lý sự kiện khi nhấn link quay lại đăng nhập
     */
    @FXML
    private void handleBackToLoginAction(ActionEvent event) {
        // Close the current stage (modal dialog)
        Stage stage = (Stage) linkBackToLogin.getScene().getWindow();
        stage.close();
    }

    /**
     * Kiểm tra định dạng email
     *
     * @param email Email cần kiểm tra
     * @return true nếu email hợp lệ, false nếu không
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
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
