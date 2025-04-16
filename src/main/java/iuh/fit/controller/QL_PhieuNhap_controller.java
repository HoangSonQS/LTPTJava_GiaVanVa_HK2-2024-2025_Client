package iuh.fit.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static iuh.fit.App.loadFXML;

public class QL_PhieuNhap_controller {

    @FXML
    private VBox banHangSubMenuList;

    @FXML
    private VBox banHangSubVBox;

    @FXML
    private Button bn_XoaTrang;

    @FXML
    private Button btn_Them;

    @FXML
    private Button btn_timkiemHoaDon;

    @FXML
    private TableColumn<?, ?> cl_pttt;

    @FXML
    private TableColumn<?, ?> cl_thoiGian;

    @FXML
    private TableColumn<?, ?> cl_txt;

    @FXML
    private TableColumn<?, ?> cll_MaPhieuNhap;

    @FXML
    private TableColumn<?, ?> cll_SoLuongSP;

    @FXML
    private TableColumn<?, ?> cll_maNV;

    @FXML
    private TableColumn<?, ?> cll_tenNV;

    @FXML
    private ImageView img_HoaDon;

    @FXML
    private ImageView img_gioHang;

    @FXML
    private ImageView img_hoaDon;

    @FXML
    private ImageView img_nhanVien;

    @FXML
    private ImageView img_phieuNhap;

    @FXML
    private ImageView img_quanLy;

    @FXML
    private ImageView img_sanPham;

    @FXML
    private ImageView img_taiKhoan;

    @FXML
    private ImageView img_taiKhoan1;

    @FXML
    private ImageView img_thongKe;

    @FXML
    private ImageView img_thongKeDoanhThu;

    @FXML
    private ImageView img_thongKeSanPham;

    @FXML
    private ImageView img_timKiem;

    @FXML
    private Label lb_HoaDon;

    @FXML
    private Label lb_gioHang;

    @FXML
    private Label lb_hoaDon;

    @FXML
    private Label lb_nhanVien;

    @FXML
    private Label lb_phieuNhap;

    @FXML
    private Label lb_quanLy;

    @FXML
    private Label lb_sanPham;

    @FXML
    private Label lb_taiKhoan;

    @FXML
    private Label lb_thongKe;

    @FXML
    private Label lb_thongKeDoanhThu;

    @FXML
    private Label lb_thongKeSanPham;

    @FXML
    private Label lb_timKiem;

    @FXML
    private Pane p_HoaDon;

    @FXML
    private Pane p_gioHang;

    @FXML
    private Pane p_hoaDon;

    @FXML
    private Pane p_nhanVien;

    @FXML
    private Pane p_phieuNhap;

    @FXML
    private Pane p_quanLy;

    @FXML
    private Pane p_sanPham;

    @FXML
    private Pane p_taiKhoan;

    @FXML
    private Pane p_thongKe;

    @FXML
    private Pane p_thongKeDoanhThu;

    @FXML
    private Pane p_thongKeSanPham;

    @FXML
    private Pane p_timKiem;

    @FXML
    private VBox quanLySubMenuList;

    @FXML
    private VBox quanLySubVBox;

    @FXML
    private VBox thongKeSubMenuList;

    @FXML
    private VBox thongKeSubVBox;

    @FXML
    private VBox timKiemSubMenuList;

    @FXML
    private VBox timKiemSubVBox;

    @FXML
    private TextField txt_MaNV;

    @FXML
    private TextField txt_MaPhieuNhap;

    @FXML
    private TextField txt_SoSP;

    @FXML
    private TextField txt_TenNV;

    @FXML
    private TextField txt_ThanhTien;

    @FXML
    private TextField txt_ThoiGian;

    @FXML
    private VBox vBox;

    @FXML
    void handleGioHangClick(MouseEvent event) {

    }

    @FXML
    void handleQuanLyClick(MouseEvent event) {

    }

    @FXML
    void handleThongKeClick(MouseEvent event) {

    }

    @FXML
    void handleTimKiemClick(MouseEvent event) {
        try {
            loadFXML("/fxml/TraCuu_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện tra cứu!");
        }
    }

    @FXML
    void toQLHoaDon(MouseEvent event) {
        try {
            // Chuyển đến giao diện quản lý hóa đơn
            loadFXML("/fxml/QL_HoaDon_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý hóa đơn!");
        }
    }

    @FXML
    void toQLKhachHang(MouseEvent event) {
        try {
            // Chuyển đến giao diện quản lý khách hàng
            loadFXML("/fxml/QL_KhachHang_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý khách hàng!");
        }
    }

    @FXML
    void toQLNhanVien(MouseEvent event) {
        try {
            // Chuyển đến giao diện quản lý nhân viên
            loadFXML("/fxml/QL_NhanVien_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý nhân viên!");
        }
    }

    @FXML
    void toQLPhieuNhap(MouseEvent event) {
        try {
            // Chuyển đến giao diện quản lý phiếu nhập
            loadFXML("/fxml/QL_PhieuNhap_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý phiếu nhập!");
        }
    }

    @FXML
    void toQLSanPham(MouseEvent event) {
        try {
            // Chuyển đến giao diện quản lý sản phẩm
            loadFXML("/fxml/QL_SanPham_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý sản phẩm!");
        }
    }

    @FXML
    void toQLTaiKhoan(MouseEvent event) {
        try {
            // Chuyển đến giao diện quản lý tài khoản
            loadFXML("/fxml/QL_TaiKhoan_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý tài khoản!");
        }
    }

    @FXML
    void toTKDoanhThu(MouseEvent event) {
        try {
            // Chuyển đến giao diện thống kê doanh thu
            loadFXML("/fxml/ThongKeDoanhThu_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện thống kê doanh thu!");
        }
    }

    @FXML
    void toTKSanPham(MouseEvent event) {
        try {
            // Chuyển đến giao diện thống kê sản phẩm
            loadFXML("/fxml/ThongKeSanPham_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện thống kê sản phẩm!");
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private void loadFXML(String fxmlPath) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene scene = new Scene(root);
        Stage stage = (Stage) p_gioHang.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
