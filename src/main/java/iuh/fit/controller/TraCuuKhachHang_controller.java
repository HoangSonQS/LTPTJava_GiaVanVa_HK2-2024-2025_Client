package iuh.fit.controller;

import iuh.fit.App;
import iuh.fit.interfaces.KhachHang_interface;
import iuh.fit.entities.KhachHang;
import iuh.fit.entities.NhanVien;
import iuh.fit.entities.TaiKhoan;
import iuh.fit.enums.LoaiHang;
import iuh.fit.security.Permission;
import iuh.fit.security.PermissionChecker;
import iuh.fit.security.SecurityContext;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.beans.property.SimpleStringProperty;
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
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


public class TraCuuKhachHang_controller implements Initializable {

    @FXML
    private VBox banHangSubMenuList;

    @FXML
    private VBox banHangSubVBox;

    @FXML
    private Button btn_TraCuu;

    @FXML
    private Button btn_qlKhachHang;

    @FXML
    private ComboBox<String> ccb_GiaoDien;

    @FXML
    private TableColumn<KhachHang, String> cl_maKH;

    @FXML
    private TableColumn<KhachHang, String> cl_sdt;

    @FXML
    private TableColumn<KhachHang, String> cl_stt;

    @FXML
    private TableColumn<KhachHang, String> cl_tenKH;

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
    private ImageView img_KhachHang;

    @FXML
    private ImageView img_taiKhoan;

    @FXML
    private ImageView img_taiKhoan1;

    @FXML
    private ImageView img_thongKe;

    @FXML
    private ImageView img_thongKeDoanhThu;

    @FXML
    private ImageView img_thongKeKhachHang;

    @FXML
    private ImageView img_timKiem;

    @FXML
    private Label lb_HoaDon;

    @FXML
    private Label lb_gioHang;

    @FXML
    private Label lb_hoaDon;

    @FXML
    private Label lb_maKH;

    @FXML
    private Label lb_nhanVien;

    @FXML
    private Label lb_phieuNhap;

    @FXML
    private Label lb_quanLy;

    @FXML
    private Label lb_KhachHang;

    @FXML
    private Label lb_sdt;

    @FXML
    private Label lb_taiKhoan;

    @FXML
    private Label lb_tenKH;

    @FXML
    private Label lb_thongKe;

    @FXML
    private Label lb_thongKeDoanhThu;

    @FXML
    private Label lb_thongKeKhachHang;

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
    private Pane p_KhachHang;

    @FXML
    private Pane p_taiKhoan;

    @FXML
    private Pane p_thongKe;

    @FXML
    private Pane p_thongKeDoanhThu;

    @FXML
    private Pane p_thongKeKhachHang;

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
    private TextField txt_maKH;


    @FXML
    private TableView<KhachHang> tableKhachHang;

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
    void handleTimKiemClick(MouseEvent event) throws Exception {
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
                    loadFXML("/fxml/TraCuu_gui.fxml");
                } catch (Exception ex) {
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

    @FXML
    void timKiem(MouseEvent event) {
        try {
            String maKhachHang = txt_maKH.getText();
            App.maTraCuu = maKhachHang;

            System.setProperty("java.security.policy", "rmi.policy");
            System.setProperty("java.rmi.server.hostname", "LAPTOP-O8OOBHDK");
            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
            KhachHang_interface khachHangDao = (KhachHang_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/khachHangDAO");
            KhachHang sp = khachHangDao.read(maKhachHang);

            lb_maKH.setText(sp.getMaKH());
            lb_tenKH.setText(sp.getTenKH());
            lb_sdt.setText(sp.getSdt());
            highlightMatchingRow(maKhachHang);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể tìm kiếm khách hàng: " + e.getMessage());
        }
    }

    private void highlightMatchingRow(String maKhachHang) {
        if (maKhachHang == null || maKhachHang.isEmpty()) {
            return;
        }

        for (int i = 0; i < tableKhachHang.getItems().size(); i++) {
            KhachHang KhachHang = tableKhachHang.getItems().get(i);
            if (KhachHang.getMaKH().equals(maKhachHang)) {  // Sửa lại điều kiện so sánh
                // Select the row
                tableKhachHang.getSelectionModel().select(i);
                // Scroll to the row
                tableKhachHang.scrollTo(i);
                // Request focus
                tableKhachHang.requestFocus();
                break;
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initializeNhanVien();
            addMenusToMap();
            // Khởi tạo ComboBox
            initializeComboBox();

            // Khởi tạo các cột cho bảng
            initializeTableColumns();

            // Load dữ liệu vào bảng
            loadTableData();

            // Thêm sự kiện click cho bảng
            setupTableClickEvent();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể khởi tạo giao diện: " + e.getMessage());
        }
    }

    private void initializeComboBox() {
        ObservableList<String> list = FXCollections.observableArrayList(
                "Sản phẩm", "Tài khoản", "Hoá đơn", "Phiếu nhập", "Nhân viên", "Khách hàng"
        );
        ccb_GiaoDien.setItems(list);
        ccb_GiaoDien.setValue("Khách hàng");
        setupComboBoxHandler();
    }

    private void setupComboBoxHandler() {
        ccb_GiaoDien.setOnAction(event -> {
            String selectedValue = ccb_GiaoDien.getValue();
            if (selectedValue.equals("Khách hàng")) {
                return;
            }

            // Determine the required permission based on the selected value
            Permission requiredPermission = switch (selectedValue) {
                case "Sản phẩm" -> Permission.QUAN_LY_SAN_PHAM;
                case "Tài khoản" -> Permission.QUAN_LY_TAI_KHOAN;
                case "Hoá đơn" -> Permission.QUAN_LY_HOA_DON;
                case "Phiếu nhập" -> Permission.QUAN_LY_PHIEU_NHAP;
                case "Nhân viên" -> Permission.QUAN_LY_NHAN_VIEN;
                default -> Permission.QUAN_LY_KHACH_HANG;
            };

            // Check permission before proceeding
            PermissionChecker.checkPermissionAndExecute(requiredPermission, () -> {
                try {
                    String fxmlFile = switch (selectedValue) {
                        case "Sản phẩm" -> "TraCuu_gui";
                        case "Tài khoản" -> "TraCuuTaiKhoan_gui";
                        case "Hoá đơn" -> "TraCuuHoaDon_gui";
                        case "Phiếu nhập" -> "TraCuuPhieuNhap_gui";
                        case "Nhân viên" -> "TraCuuNhanVien_gui";
                        default -> throw new IllegalArgumentException("Unexpected value: " + selectedValue);
                    };

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlFile + ".fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) ccb_GiaoDien.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();

                } catch (Exception e) {
                    showError("Lỗi chuyển giao diện", "Không thể mở giao diện Tra cứu " + selectedValue.toLowerCase());
                    ccb_GiaoDien.setValue("Khách hàng");
                }
            });
        });
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void initializeTableColumns() {
        // Cột STT
        cl_stt.setCellFactory(col -> new TableCell<KhachHang, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.valueOf(getIndex() + 1));
                }
            }
        });

        // Các cột khác
        cl_maKH.setCellValueFactory(new PropertyValueFactory<>("maKH"));
        cl_tenKH.setCellValueFactory(new PropertyValueFactory<>("tenKH"));
        cl_sdt.setCellValueFactory(new PropertyValueFactory<>("sdt"));

    }

    private void loadTableData() {
        try {
            System.setProperty("java.security.policy", "rmi.policy");
            System.setProperty("java.rmi.server.hostname", "LAPTOP-O8OOBHDK");
            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
            KhachHang_interface khachHangDao = (KhachHang_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/khachHangDAO");
            List<KhachHang> dssp = khachHangDao.readAll();
            ObservableList<KhachHang> data = FXCollections.observableArrayList(dssp);
            tableKhachHang.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Lỗi khi tải dữ liệu");
            alert.setContentText("Đã xảy ra lỗi khi tải dữ liệu. Vui lòng thử lại sau.");
            alert.showAndWait();
        }
    }

    private void setupTableClickEvent() {
        tableKhachHang.setOnMouseClicked(event -> {
            KhachHang selectedKhachHang = tableKhachHang.getSelectionModel().getSelectedItem();
            if (selectedKhachHang != null) {
                updateLabels(selectedKhachHang);
            }
        });
    }

    private void updateLabels(KhachHang kh) {
        lb_maKH.setText(kh.getMaKH());
        lb_tenKH.setText(kh.getTenKH());
        lb_sdt.setText(kh.getSdt());
    }

    private void loadFXML(String fxmlPath) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene scene = new Scene(root);
        Stage stage = (Stage) p_gioHang.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
