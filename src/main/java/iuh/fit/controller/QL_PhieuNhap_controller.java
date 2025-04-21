package iuh.fit.controller;

import iuh.fit.App;
import iuh.fit.interfaces.PhieuNhapHang_interface;
import iuh.fit.entities.NhanVien;
import iuh.fit.entities.PhieuNhapHang;
import iuh.fit.entities.TaiKhoan;

import iuh.fit.security.Permission;
import iuh.fit.security.PermissionChecker;
import iuh.fit.security.SecurityContext;
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
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static iuh.fit.App.loadFXML;

public class QL_PhieuNhap_controller implements Initializable {

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
    private TableColumn<?, ?> cl_thanhTien;

    @FXML
    private TableColumn<?, ?> cl_thoiGian;

    @FXML
    private TableColumn<PhieuNhapHang, Integer> cl_txt;

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
    private TableView<PhieuNhapHang> table_PNhap;

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
        // No specific permission check needed here as the submenu items will be checked individually
        toolsSlider(quanLySubVBox, quanLySubMenuList);
        removeOtherMenus(quanLySubVBox);
    }

    @FXML
    void handleThongKeClick(MouseEvent event) {
        // Check permission before proceeding
        PermissionChecker.checkPermissionAndExecute(Permission.THONG_KE, () -> {
            toolsSlider(thongKeSubVBox, thongKeSubMenuList);
            removeOtherMenus(thongKeSubVBox);
        });
    }

    @FXML
    void handleTimKiemClick(MouseEvent event) throws IOException {
        // Check permission before proceeding
        PermissionChecker.checkPermissionAndExecute(Permission.QUAN_LY_SAN_PHAM, () -> {
            try {
                loadFXML("/fxml/TraCuu_gui.fxml");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện tra cứu: " + e.getMessage());
                toolsSlider(timKiemSubVBox, timKiemSubMenuList);
                removeOtherMenus(timKiemSubVBox);
                try {
                    App.loadFXML("TraCuu_gui");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    @FXML
    void toQLHoaDon(MouseEvent event) {
        // Check permission before proceeding
        PermissionChecker.checkPermissionAndExecute(Permission.QUAN_LY_HOA_DON, () -> {
            try {
                loadFXML("/fxml/QL_HoaDon_gui.fxml");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý hóa đơn!");
            }
        });
    }

    @FXML
    void toQLKhachHang(MouseEvent event) {
        // Check permission before proceeding
        PermissionChecker.checkPermissionAndExecute(Permission.QUAN_LY_KHACH_HANG, () -> {
            try {
                loadFXML("/fxml/QL_KhachHang_gui.fxml");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý khách hàng!");
            }
        });
    }

    @FXML
    void toQLNhanVien(MouseEvent event) {
        // Check permission before proceeding
        PermissionChecker.checkPermissionAndExecute(Permission.QUAN_LY_NHAN_VIEN, () -> {
            try {
                loadFXML("/fxml/QL_NhanVien_gui.fxml");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý nhân viên!");
            }
        });
    }

    @FXML
    void toQLPhieuNhap(MouseEvent event) {
        // Check permission before proceeding
        PermissionChecker.checkPermissionAndExecute(Permission.QUAN_LY_PHIEU_NHAP, () -> {
            try {
                loadFXML("/fxml/QL_PhieuNhap_gui.fxml");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý phiếu nhập!");
            }
        });
    }

    @FXML
    void toQLSanPham(MouseEvent event) {
        // Check permission before proceeding
        PermissionChecker.checkPermissionAndExecute(Permission.QUAN_LY_SAN_PHAM, () -> {
            try {
                loadFXML("/fxml/QL_SanPham_gui.fxml");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý sản phẩm!");
            }
        });
    }

    @FXML
    void toQLTaiKhoan(MouseEvent event) {
        // Check permission before proceeding
        PermissionChecker.checkPermissionAndExecute(Permission.QUAN_LY_TAI_KHOAN, () -> {
            try {
                loadFXML("/fxml/QL_TaiKhoan_gui.fxml");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý tài khoản!");
            }
        });
    }

    @FXML
    void toTKDoanhThu(MouseEvent event) {
        // Check permission before proceeding
        PermissionChecker.checkPermissionAndExecute(Permission.THONG_KE, () -> {
            try {
                loadFXML("/fxml/ThongKeDoanhThu_gui.fxml");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện thống kê doanh thu!");
            }
        });
    }

    @FXML
    void toTKSanPham(MouseEvent event) {
        // Check permission before proceeding
        PermissionChecker.checkPermissionAndExecute(Permission.THONG_KE, () -> {
            try {
                loadFXML("/fxml/ThongKeSanPham_gui.fxml");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện thống kê sản phẩm!");
            }
        });
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

    private void loadFXML(String fxmlPath) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene scene = new Scene(root);
        Stage stage = (Stage) p_gioHang.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    private void loadTableData() {
        try {
            // Sử dụng DAO interface
            System.setProperty("java.security.policy", "rmi.policy");
            System.setProperty("java.rmi.server.hostname", "LAPTOP-O8OOBHDK");
            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
            PhieuNhapHang_interface pNhapDAO = (PhieuNhapHang_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/phieuNhapHangDAO");

            // Xóa dữ liệu cũ trong table
            table_PNhap.getItems().clear();

            // Lấy danh sách phiếu nhập từ database
            ObservableList<PhieuNhapHang> listpNhap = FXCollections.observableArrayList(pNhapDAO.readAll());
            // Thiết lập cell value factory cho các cột
            cll_MaPhieuNhap.setCellValueFactory(new PropertyValueFactory<>("maPNH"));
            cll_maNV.setCellValueFactory(new PropertyValueFactory<>("maNV"));
            cll_tenNV.setCellValueFactory(new PropertyValueFactory<>("tenNV"));
            cll_SoLuongSP.setCellValueFactory(new PropertyValueFactory<>("tongSoLuongSP"));
            cl_thoiGian.setCellValueFactory(new PropertyValueFactory<>("thoiGian"));
            cl_thanhTien.setCellValueFactory(new PropertyValueFactory<>("thanhTien"));

            // Gán STT tự động
            cl_txt.setCellFactory(col -> new TableCell<PhieuNhapHang, Integer>() {
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
            table_PNhap.setItems(listpNhap);

            // Refresh table view
            table_PNhap.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void Xoa_Trang(MouseEvent event) {
        txt_MaPhieuNhap.setText("");
        txt_MaNV.setText("");
        txt_TenNV.setText("");
        txt_SoSP.setText("");
        txt_ThanhTien.setText("");
        txt_ThoiGian.setText("");

        // Xóa dữ liệu trong table
        table_PNhap.getItems().clear();
    }
    @FXML
    void moGD_TimkiemPhieuNhap(MouseEvent event) {
        try {
            loadFXML("/fxml/TraCuuPhieuNhap_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện tìm kiếm phiếu nhập!");
        }
    }

    @FXML
    void them_PNhap(MouseEvent event) {
        try{
            // Lấy thông tin từ các trường nhập liệu
            String maPhieuNhap = txt_MaPhieuNhap.getText();
            String maNV = txt_MaNV.getText();
            String tenNV = txt_TenNV.getText();
            int soLuongSP = Integer.parseInt(txt_SoSP.getText());
            double thanhTien = Double.parseDouble(txt_ThanhTien.getText());
            LocalDateTime thoiGian = LocalDateTime.parse(txt_ThoiGian.getText());

            // Tạo đối tượng PhieuNhapHang
            PhieuNhapHang phieuNhapHang = new PhieuNhapHang(maPhieuNhap, maNV, tenNV,thoiGian, soLuongSP, thanhTien);

            // Sử dụng DAO interface
            System.setProperty("java.security.policy", "rmi.policy");
            System.setProperty("java.rmi.server.hostname", "LAPTOP-O8OOBHDK");
            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
            PhieuNhapHang_interface pNhapDAO = (PhieuNhapHang_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/phieuNhapHangDAO");

            // Thêm phiếu nhập vào database
            pNhapDAO.create(phieuNhapHang);

            // Cập nhật lại dữ liệu trong table
            loadTableData();
        }catch (Exception e){
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể thêm phiếu nhập: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNhanVien();
        addMenusToMap();
        loadTableData();
        // Thiết lập sự kiện khi người dùng chọn một hàng trong bảng
        table_PNhap.setOnMouseClicked(event -> {
                PhieuNhapHang selectedPNH = table_PNhap.getSelectionModel().getSelectedItem();
                if (selectedPNH != null) {
                    txt_MaPhieuNhap.setText(selectedPNH.getMaPNH());
                    txt_MaNV.setText(selectedPNH.getMaNV());
                    txt_TenNV.setText(selectedPNH.getTenNV());
                    txt_SoSP.setText(String.valueOf(selectedPNH.getTongSoLuongSP()));
                    txt_ThanhTien.setText(String.valueOf(selectedPNH.getThanhTien()));
                    txt_ThoiGian.setText(String.valueOf(selectedPNH.getThoiGian()));
                }
        });
    }
}
