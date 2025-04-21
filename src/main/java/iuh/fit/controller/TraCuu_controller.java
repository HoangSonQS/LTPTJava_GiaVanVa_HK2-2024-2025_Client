package iuh.fit.controller;

import iuh.fit.App;
import iuh.fit.interfaces.HoaDon_interface;
import iuh.fit.interfaces.SanPham_interface;
import iuh.fit.entities.NhanVien;
import iuh.fit.entities.SanPham;
import iuh.fit.entities.TaiKhoan;
import iuh.fit.security.Permission;
import iuh.fit.security.PermissionChecker;
import iuh.fit.security.SecurityContext;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import java.util.*;

import javafx.scene.control.TableCell;
import iuh.fit.enums.LoaiHang;

public class TraCuu_controller implements Initializable {

    @FXML
    private VBox banHangSubMenuList;

    @FXML
    private VBox banHangSubVBox;

    @FXML
    private Button btn_TraCuu;

    @FXML
    private Button btn_qlSanPham;

    @FXML
    private ComboBox<String> cbb_GiaoDien;

    @FXML
    private TableColumn<SanPham, String> cl_giaBan;

    @FXML
    private TableColumn<SanPham, String> cl_giaNhap;

    @FXML
    private TableColumn<SanPham, String> cl_hsd;

    @FXML
    private TableColumn<SanPham, String> cl_loaiHang;

    @FXML
    private TableColumn<SanPham, String> cl_maSP;

    @FXML
    private TableColumn<SanPham, String> cl_ncc;

    @FXML
    private TableColumn<SanPham, String> cl_nsx;

    @FXML
    private TableColumn<SanPham, String> cl_tenSP;

    @FXML
    private TableColumn<SanPham, String> cl_slt;

    @FXML
    private TableColumn<SanPham, String> cl_tgcn;

    @FXML
    private TableColumn<SanPham, String> cl_txt;

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
    private Label lb_giaBan;

    @FXML
    private Label lb_giaNhap;

    @FXML
    private Label lb_hsd;

    @FXML
    private Label lb_loaiHang;

    @FXML
    private Label lb_maSP;

    @FXML
    private Label lb_ncc;

    @FXML
    private Label lb_nsx;

    @FXML
    private Label lb_slt;

    @FXML
    private Label lb_tenSP;

    @FXML
    private Label lb_tgcn;

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
    private TextField txt_maSP;

    @FXML
    private TableView<SanPham> tableSanPham;

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

    private void showAlert(Alert.AlertType AlertType, String title, String content) {
        Alert alert = new Alert(AlertType);
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
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
            String maSanPham = txt_maSP.getText();
            App.maTraCuu = maSanPham;

            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("localhost", 9090);
            SanPham_interface sanPhamDao = (SanPham_interface) registry.lookup("sanPhamDAO");
            SanPham sp = sanPhamDao.read(maSanPham);
            lb_maSP.setText(sp.getMaSP());
            lb_tenSP.setText(sp.getTenSP());
            lb_ncc.setText(sp.getNhaCC());
            lb_slt.setText(String.valueOf(sp.getSoLuongTon()));
            lb_giaNhap.setText(String.valueOf(sp.getGiaNhap()));
            lb_giaBan.setText(String.valueOf(sp.getGiaBan()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            lb_nsx.setText(sp.getNgaySX().format(formatter));
            lb_hsd.setText(sp.getHanSD().format(formatter));
            lb_tgcn.setText(sp.getThoiGianCapNhat().format(formatter));
            lb_loaiHang.setText(sp.getLoaiHang().toString());
            highlightMatchingRow(maSanPham);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể tìm kiếm sản phẩm: " + e.getMessage());
        }
    }

    private void highlightMatchingRow(String maSanPham) {
        if (maSanPham == null || maSanPham.isEmpty()) {
            return;
        }

        for (int i = 0; i < tableSanPham.getItems().size(); i++) {
            SanPham sanPham = tableSanPham.getItems().get(i);
            if (sanPham.getMaSP().equals(maSanPham)) {  // Sửa lại điều kiện so sánh
                // Select the row
                tableSanPham.getSelectionModel().select(i);
                // Scroll to the row
                tableSanPham.scrollTo(i);
                // Request focus
                tableSanPham.requestFocus();
                break;
            }
        }
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
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
    }

    private void initializeComboBox() {
        ObservableList<String> list = FXCollections.observableArrayList(
                "Sản phẩm", "Tài khoản", "Hoá đơn", "Phiếu nhập", "Nhân viên", "Khách hàng"
        );
        cbb_GiaoDien.setItems(list);
        cbb_GiaoDien.setValue("Sản phẩm");
        setupComboBoxHandler();
    }

    private void setupComboBoxHandler() {
        cbb_GiaoDien.setOnAction(event -> {
            String selectedValue = cbb_GiaoDien.getValue();
            if (selectedValue.equals("Sản phẩm")) {
                return;
            }

            // Determine the required permission based on the selected value
            Permission requiredPermission = switch (selectedValue) {
                case "Tài khoản" -> Permission.QUAN_LY_TAI_KHOAN;
                case "Hoá đơn" -> Permission.QUAN_LY_HOA_DON;
                case "Phiếu nhập" -> Permission.QUAN_LY_PHIEU_NHAP;
                case "Nhân viên" -> Permission.QUAN_LY_NHAN_VIEN;
                case "Khách hàng" -> Permission.QUAN_LY_KHACH_HANG;
                default -> Permission.QUAN_LY_SAN_PHAM;
            };

            // Check permission before proceeding
            PermissionChecker.checkPermissionAndExecute(requiredPermission, () -> {
                try {
                    String fxmlFile = switch (selectedValue) {
                        case "Tài khoản" -> "TraCuuTaiKhoan_gui";
                        case "Hoá đơn" -> "TraCuuHoaDon_gui";
                        case "Phiếu nhập" -> "TraCuuPhieuNhap_gui";
                        case "Nhân viên" -> "TraCuuNhanVien_gui";
                        case "Khách hàng" -> "TraCuuKhachHang_gui";
                        default -> throw new IllegalArgumentException("Unexpected value: " + selectedValue);
                    };

                    loadFXML("/fxml/" + fxmlFile + ".fxml");

                } catch (Exception e) {
                    showError("Lỗi chuyển giao diện", "Không thể mở giao diện Tra cứu " + selectedValue.toLowerCase());
                    cbb_GiaoDien.setValue("Sản phẩm");
                }
            });
        });
    }

    private void loadFXML(String fxmlPath) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene scene = new Scene(root);
        Stage stage = (Stage) cbb_GiaoDien.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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
        cl_txt.setCellFactory(col -> new TableCell<SanPham, String>() {
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
        cl_maSP.setCellValueFactory(new PropertyValueFactory<>("maSP"));
        cl_tenSP.setCellValueFactory(new PropertyValueFactory<>("tenSP"));
        cl_ncc.setCellValueFactory(new PropertyValueFactory<>("nhaCC"));
        cl_slt.setCellValueFactory(new PropertyValueFactory<>("soLuongTon"));
        cl_giaNhap.setCellValueFactory(new PropertyValueFactory<>("giaNhap"));
        cl_giaBan.setCellValueFactory(new PropertyValueFactory<>("giaBan"));

        // Định dạng ngày tháng
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        // Cột Ngày sản xuất
        cl_nsx.setCellValueFactory(cellData -> {
            LocalDateTime date = cellData.getValue().getNgaySX();
            if (date == null) return new SimpleStringProperty("ngaySX");
            return new SimpleStringProperty(date.format(dateFormatter));
        });

        // Cột Hạn sử dụng
        cl_hsd.setCellValueFactory(cellData -> {
            LocalDateTime date = cellData.getValue().getHanSD();
            if (date == null) return new SimpleStringProperty("hanSD");
            return new SimpleStringProperty(date.format(dateFormatter));
        });

        // Cột Thời gian cập nhật
        cl_tgcn.setCellValueFactory(cellData -> {
            LocalDateTime date = cellData.getValue().getThoiGianCapNhat();
            if (date == null) return new SimpleStringProperty("thoiGianCapNhat");
            return new SimpleStringProperty(date.format(dateFormatter));
        });

        // Cột Loại hàng
        cl_loaiHang.setCellValueFactory(new PropertyValueFactory<>("loaiHang"));
    }

    private void loadTableData() {
        try {
            System.setProperty("java.security.policy", "rmi.policy");
            System.setProperty("java.rmi.server.hostname", "LAPTOP-O8OOBHDK");
            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
            SanPham_interface sanPhamDao = (SanPham_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/sanPhamDAO");
            List<SanPham> dssp = sanPhamDao.readAll();
            ObservableList<SanPham> data = FXCollections.observableArrayList(dssp);
            tableSanPham.setItems(data);
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
        tableSanPham.setOnMouseClicked(event -> {
            SanPham selectedSanPham = tableSanPham.getSelectionModel().getSelectedItem();
            if (selectedSanPham != null) {
                updateLabels(selectedSanPham);
            }
        });
    }

    private void updateLabels(SanPham sp) {
        try {
            // Các label đầu tiên
            lb_maSP.setText(sp.getMaSP());
            lb_tenSP.setText(sp.getTenSP());
            lb_ncc.setText(sp.getNhaCC());
            lb_slt.setText(String.valueOf(sp.getSoLuongTon()));
            lb_giaNhap.setText(String.valueOf(sp.getGiaNhap()));
            lb_giaBan.setText(String.valueOf(sp.getGiaBan()));

            // Xử lý các label ngày tháng
            if (sp.getNgaySX() != null) {
                lb_nsx.setText(sp.getNgaySX().format(dateFormatter));
            } else {
                lb_nsx.setText("Không có dữ liệu");
            }

            if (sp.getHanSD() != null) {
                lb_hsd.setText(sp.getHanSD().format(dateFormatter));
            } else {
                lb_hsd.setText("Không có dữ liệu");
            }

            if (sp.getThoiGianCapNhat() != null) {
                lb_tgcn.setText(sp.getThoiGianCapNhat().format(dateFormatter));
            } else {
                lb_tgcn.setText("Không có dữ liệu");
            }

            // Xử lý loại hàng
            if (sp.getLoaiHang() != null) {
                lb_loaiHang.setText(sp.getLoaiHang().toString());
            } else {
                lb_loaiHang.setText("Không có dữ liệu");
            }

            System.out.println("Đã cập nhật thông tin sản phẩm: " + sp.getMaSP());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Lỗi khi cập nhật thông tin sản phẩm: " + e.getMessage());
        }
    }

}


