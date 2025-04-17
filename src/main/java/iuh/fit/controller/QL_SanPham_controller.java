package iuh.fit.controller;

import iuh.fit.daos.PhieuNhapHang_dao;
import iuh.fit.daos.SanPham_dao;
import iuh.fit.entities.PhieuNhapHang;
import iuh.fit.entities.SanPham;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static iuh.fit.App.loadFXML;

public class QL_SanPham_controller implements Initializable {

    @FXML
    private VBox banHangSubMenuList;

    @FXML
    private VBox banHangSubVBox;

    @FXML
    private Button btnSua;

    @FXML
    private Button btnThem;

    @FXML
    private Button btnTimKiem;

    @FXML
    private Button btnXoa;

    @FXML
    private Button btnXoaTrang;

    @FXML
    private TableColumn<?, ?> cl_LoaiHang;

    @FXML
    private TableColumn<?, ?> cl_MaSP;

    @FXML
    private TableColumn<?, ?> cl_giaBan;

    @FXML
    private TableColumn<?, ?> cl_hanSD;

    @FXML
    private TableColumn<?, ?> cl_ngaySX;

    @FXML
    private TableColumn<?, ?> cl_nhaCC;

    @FXML
    private TableColumn<?, ?> cl_soLuongTon;

    @FXML
    private TableColumn<?, ?> cl_tenSP;

    @FXML
    private TableColumn<SanPham, Integer> cl_txt;

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
    private TableColumn<?, ?> lc_giaNhap;

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
    private TableView<SanPham> table_SP;

    @FXML
    private VBox thongKeSubMenuList;

    @FXML
    private VBox thongKeSubVBox;

    @FXML
    private VBox timKiemSubMenuList;

    @FXML
    private VBox timKiemSubVBox;

    @FXML
    private TextField txt_GiaBan;

    @FXML
    private TextField txt_GiaNhap;

    @FXML
    private TextField txt_HanSD;

    @FXML
    private TextField txt_LoaiHang;

    @FXML
    private TextField txt_MaSP;

    @FXML
    private TextField txt_NgaySX;

    @FXML
    private TextField txt_NhaCC;

    @FXML
    private TextField txt_SoLuongTon;

    @FXML
    private TextField txt_tenSP;

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
    void moGiaoDienTimKiemNV(MouseEvent event) {

    }

    @FXML
    void suaTTNV(MouseEvent event) {

    }

    @FXML
    void themNV(MouseEvent event) {

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

    @FXML
    void xoaNV(MouseEvent event) {

    }

    @FXML
    void xoaTrang(MouseEvent event) {

    }
    private void loadFXML(String fxmlPath) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene scene = new Scene(root);
        Stage stage = (Stage) p_gioHang.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    private void loadTableData() {
        try {
            // Tạo DAO object
            SanPham_dao spDAO = new SanPham_dao();

            // Xóa dữ liệu cũ trong table
            table_SP.getItems().clear();

            // Lấy danh sách phiếu nhập từ database
            ObservableList<SanPham> listSP = FXCollections.observableArrayList(spDAO.readAll());
            // Thiết lập cell value factory cho các cột
            cl_MaSP.setCellValueFactory(new PropertyValueFactory<>("maSP"));
            cl_tenSP.setCellValueFactory(new PropertyValueFactory<>("tenSP"));
            cl_LoaiHang.setCellValueFactory(new PropertyValueFactory<>("loaiHang"));
            cl_nhaCC.setCellValueFactory(new PropertyValueFactory<>("nhaCC"));
            lc_giaNhap.setCellValueFactory(new PropertyValueFactory<>("giaNhap"));
            cl_giaBan.setCellValueFactory(new PropertyValueFactory<>("giaBan"));
            cl_ngaySX.setCellValueFactory(new PropertyValueFactory<>("ngaySX"));
            cl_hanSD.setCellValueFactory(new PropertyValueFactory<>("hanSD"));
            cl_soLuongTon.setCellValueFactory(new PropertyValueFactory<>("soLuongTon"));


            // Gán STT tự động
            cl_txt.setCellFactory(col -> new TableCell<SanPham, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(String.valueOf(getIndex() + 1));
                    }
                }
            });
            // Cập nhật dữ liệu vào table
            table_SP.setItems(listSP);

            // Refresh table view
            table_SP.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTableData();
    }
}
