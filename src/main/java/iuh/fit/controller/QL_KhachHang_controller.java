package iuh.fit.controller;

import iuh.fit.App;
import iuh.fit.daos.HoaDon_dao;
import iuh.fit.daos.KhachHang_dao;
import iuh.fit.entities.HoaDon;
import iuh.fit.entities.KhachHang;
import iuh.fit.entities.NhanVien;
import iuh.fit.entities.TaiKhoan;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
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
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
    private Label lb_tenNV;
    @FXML
    private Label lb_chucVu;

    Map<VBox,VBox> map = new HashMap<VBox,VBox>();
    @FXML
    private Button btn_dangXuat;

    public void addMenusToMap() {
        addMenusToMapImpl();
    }

    private void addMenusToMapImpl() {
        map.put(banHangSubVBox, banHangSubMenuList);
        map.put(quanLySubVBox, quanLySubMenuList);
        map.put(timKiemSubVBox, timKiemSubMenuList);
        map.put(thongKeSubVBox, thongKeSubMenuList);

        for (Map.Entry<VBox,VBox> entry : map.entrySet()) {
            entry.getKey().getChildren().remove(entry.getValue());
        }
    }

    public void toolsSlider(VBox menu, VBox subMenu) {
        toolsSliderImpl(menu, subMenu);
    }

    private void toolsSliderImpl(VBox menu, VBox subMenu) {
        if(menu.getChildren().contains(subMenu)) {
            final FadeTransition transition = new FadeTransition(Duration.millis(500), menu);
            transition.setFromValue(0.5);
            transition.setToValue(1);
            transition.setInterpolator(Interpolator.EASE_IN);
            menu.getChildren().remove(subMenu);
            transition.play();
        } else {
            final FadeTransition transition = new FadeTransition(Duration.millis(500), menu);
            transition.setFromValue(0.5);
            transition.setToValue(1);
            transition.setInterpolator(Interpolator.EASE_IN);
            menu.getChildren().add(subMenu);
            transition.play();
        }
    }

    public void removeOtherMenus(VBox menu) {
        removeOtherMenusImpl(menu);
    }

    private void removeOtherMenusImpl(VBox menu) {
        for (Map.Entry<VBox,VBox> entry : map.entrySet()) {
            if(!entry.getKey().equals(menu))
                entry.getKey().getChildren().remove(entry.getValue());
        }
    }

    @FXML
    void handleGioHangClick(MouseEvent event) {
        try {
            if (banHangSubVBox != null && banHangSubMenuList != null) {
                toolsSlider(banHangSubVBox, banHangSubMenuList);
                removeOtherMenus(banHangSubVBox);
                loadFXML("/fxml/BanHang_gui.fxml");
            }
        } catch (Exception e) {
            System.err.println("Lỗi trong handleGioHangClick: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void handleQuanLyClick(MouseEvent event) {
        toolsSlider(quanLySubVBox, quanLySubMenuList);
        removeOtherMenus(quanLySubVBox);
    }

    @FXML
    void handleThongKeClick(MouseEvent event) {
        toolsSlider(thongKeSubVBox, thongKeSubMenuList);
        removeOtherMenus(thongKeSubVBox);
    }

    @FXML
    void handleTimKiemClick(MouseEvent event) throws IOException {
        try {
            loadFXML("/fxml/TraCuu_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện tra cứu: " + e.getMessage());
            toolsSlider(timKiemSubVBox, timKiemSubMenuList);
            removeOtherMenus(timKiemSubVBox);
            App.loadFXML("TraCuu_gui");
        }
    }


    @FXML
    void toQLHoaDon(MouseEvent event) {
        try {
            loadFXML("/fxml/QL_HoaDon_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý hóa đơn!");
        }
    }

    @FXML
    void toQLKhachHang(MouseEvent event) {
        try {
            loadFXML("/fxml/QL_KhachHang_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý khách hàng!");
        }
    }

    @FXML
    void toQLNhanVien(MouseEvent event) {
        try {
            loadFXML("/fxml/QL_NhanVien_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý nhân viên!");
        }
    }

    @FXML
    void toQLPhieuNhap(MouseEvent event) {
        try {
            loadFXML("/fxml/QL_PhieuNhap_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý phiếu nhập!");
        }
    }

    @FXML
    void toQLSanPham(MouseEvent event) {
        try {
            loadFXML("/fxml/QL_SanPham_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý sản phẩm!");
        }
    }

    @FXML
    void toQLTaiKhoan(MouseEvent event) {
        try {
            loadFXML("/fxml/QL_TaiKhoan_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý tài khoản!");
        }
    }

    @FXML
    void toTKDoanhThu(MouseEvent event) {
        try {
            loadFXML("/fxml/ThongKeDoanhThu_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện thống kê doanh thu!");
        }
    }

    @FXML
    void toTKSanPham(MouseEvent event) {
        try {
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
    private void handleDangXuatClick(MouseEvent event) {
        try {
            // Hiển thị hộp thoại xác nhận
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận đăng xuất");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc chắn muốn đăng xuất?");

            // Nếu người dùng nhấn OK
            if (alert.showAndWait().get() == javafx.scene.control.ButtonType.OK) {
                // Chuyển về màn hình đăng nhập
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login_gui.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                // Lấy stage hiện tại
                Stage stage = (Stage) btn_dangXuat.getScene().getWindow();

                // Thiết lập scene mới
                stage.setScene(scene);
                stage.setTitle("Đăng nhập");

                // Xóa thông tin đăng nhập hiện tại
                App.taiKhoan = null;
                App.user = null;
                App.ma = null;

                // Hiển thị stage
                stage.show();

                System.out.println("Đã đăng xuất thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể đăng xuất: " + e.getMessage());
        }
    }
    private void initializeNhanVien() {
        try {
            TaiKhoan taiKhoan = App.taiKhoan;
            System.out.println(taiKhoan);
            NhanVien nhanVien = taiKhoan.getNhanVien();
            lb_tenNV.setText(nhanVien.getTenNV());
            lb_chucVu.setText(nhanVien.getChucVu().toString());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể lấy thông tin nhân viên: " + e.getMessage());
        }
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
        initializeNhanVien();
        addMenusToMap();
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
