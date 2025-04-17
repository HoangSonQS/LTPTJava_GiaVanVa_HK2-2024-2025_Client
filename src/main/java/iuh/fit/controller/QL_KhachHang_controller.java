package iuh.fit.controller;

import iuh.fit.daos.HoaDon_dao;
import iuh.fit.daos.KhachHang_dao;
import iuh.fit.entities.HoaDon;
import iuh.fit.entities.KhachHang;
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

public class QL_KhachHang_controller implements Initializable {

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
    private TableColumn<?, ?> cl_SDT;

    @FXML
    private TableColumn<?, ?> cl_TenKH;

    @FXML
    private TableColumn<?, ?> cl_maKH;

    @FXML
    private TableColumn<KhachHang, Integer> cl_txt;

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
    private TableView<KhachHang> table_KH;

    @FXML
    private VBox thongKeSubMenuList;

    @FXML
    private VBox thongKeSubVBox;

    @FXML
    private VBox timKiemSubMenuList;

    @FXML
    private VBox timKiemSubVBox;

    @FXML
    private TextField txt_MaKH;

    @FXML
    private TextField txt_SDT;

    @FXML
    private TextField txt_TenKH;

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

    @FXML
    void moGiaoDienTimKiemKH(MouseEvent event) {
        try{
            loadFXML("/fxml/TraCuuKhachHang_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện tìm kiếm khách hàng!");
        }
    }

    @FXML
    void suaKH(MouseEvent event) {
        try {
            // Lấy thông tin từ các trường nhập liệu
            String maKH = txt_MaKH.getText();
            String tenKH = txt_TenKH.getText();
            String sdt = txt_SDT.getText();

            // Tạo đối tượng KhachHang mới
            KhachHang khachHang = new KhachHang(maKH, tenKH, sdt);

            // Tạo DAO object
            KhachHang_dao khDAO = new KhachHang_dao();

            // Cập nhật thông tin khách hàng vào database
            khDAO.update(khachHang);

            // Tải lại dữ liệu vào bảng
            loadTableData();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể cập nhật thông tin khách hàng!");
        }
    }

    @FXML
    void themKH(MouseEvent event) {
        try{
            // Lấy thông tin từ các trường nhập liệu
            String maKH = txt_MaKH.getText();
            String tenKH = txt_TenKH.getText();
            String sdt = txt_SDT.getText();

            // Tạo đối tượng KhachHang mới
            KhachHang khachHang = new KhachHang(maKH, tenKH, sdt);

            // Tạo DAO object
            KhachHang_dao khDAO = new KhachHang_dao();

            // Thêm khách hàng vào database
            khDAO.create(khachHang);

            // Tải lại dữ liệu vào bảng
            loadTableData();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể thêm khách hàng!");
        }

    }
    @FXML
    void xoaKH(MouseEvent event) {
        try{
            // Lấy thông tin từ các trường nhập liệu
            String maKH = txt_MaKH.getText();

            // Tạo DAO object
            KhachHang_dao khDAO = new KhachHang_dao();

            // Xóa khách hàng khỏi database
            khDAO.delete(maKH);

            // Tải lại dữ liệu vào bảng
            loadTableData();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể xóa khách hàng!");
        }
    }

    @FXML
    void xoaTrang(MouseEvent event) {
        txt_MaKH.setText("");
        txt_TenKH.setText("");
        txt_SDT.setText("");
        table_KH.getSelectionModel().clearSelection();
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
            KhachHang_dao khDAO = new KhachHang_dao();

            // Xóa dữ liệu cũ trong table
            table_KH.getItems().clear();

            // Lấy danh sách hóa đơn từ database
            ObservableList<KhachHang> listKH = FXCollections.observableArrayList(khDAO.readAll());
            // Thiết lập cell value factory cho các cột
            cl_maKH.setCellValueFactory(new PropertyValueFactory<>("maKH"));
            cl_TenKH.setCellValueFactory(new PropertyValueFactory<>("tenKH"));
            cl_SDT.setCellValueFactory(new PropertyValueFactory<>("sdt"));

            // Gán STT tự động
            cl_txt.setCellFactory(col -> new TableCell<KhachHang, Integer>() {
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
            table_KH.setItems(listKH);

            // Refresh table view
            table_KH.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTableData();
        // Thiết lập sự kiện click cho các cột trong table
        table_KH.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                KhachHang selectedKhachHang = table_KH.getSelectionModel().getSelectedItem();
                if (selectedKhachHang != null) {
                    txt_MaKH.setText(selectedKhachHang.getMaKH());
                    txt_TenKH.setText(selectedKhachHang.getTenKH());
                    txt_SDT.setText(selectedKhachHang.getSdt());
                }
            }
        });
    }
}
