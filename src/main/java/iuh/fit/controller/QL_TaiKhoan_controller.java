package iuh.fit.controller;

import iuh.fit.App;
import iuh.fit.interfaces.NhanVien_interface;
import iuh.fit.interfaces.TaiKhoan_interface;
import iuh.fit.entities.NhanVien;
import iuh.fit.entities.TaiKhoan;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

public class QL_TaiKhoan_controller implements Initializable {

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
    private TableColumn<TaiKhoan, String> cl_MaNV;

    @FXML
    private TableColumn<?, ?> cl_ThoiGian;

    @FXML
    private TableColumn<?, ?> cl_maTK;

    @FXML
    private TableColumn<?, ?> cl_tenDN;

    @FXML
    private TableColumn<TaiKhoan, Integer> cl_txt;

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
    private TableView<TaiKhoan> table_TK;

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
    private TextField txt_MaTK;

    @FXML
    private TextField txt_ThoiGian;

    @FXML
    private TextField txt_tenDN;

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


    @FXML
    void xoaTK(MouseEvent event) {
        try{
            // Lấy mã tài khoản từ ô nhập liệu
            String maTK = txt_MaTK.getText();

            // Sử dụng DAO interface
            System.setProperty("java.security.policy", "rmi.policy");
            System.setProperty("java.rmi.server.hostname", "LAPTOP-O8OOBHDK");
            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
            TaiKhoan_interface tkDAO = (TaiKhoan_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/taiKhoanDAO");

            // Xóa tài khoản trong database
            tkDAO.delete(maTK);

            // Cập nhật lại dữ liệu trong bảng
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể xóa tài khoản: " + e.getMessage());
        }
    }

    @FXML
    void xoaTrang(MouseEvent event) {
        txt_MaTK.setText("");
        txt_MaNV.setText("");
        txt_tenDN.setText("");
        txt_ThoiGian.setText("");

        // Xóa dữ liệu trong bảng
        table_TK.getItems().clear();
    }@FXML
    void moGiaoDienTimKiemTK(MouseEvent event) {
        try {
            loadFXML("/fxml/TraCuuTaiKhoan_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện tra cứu tài khoản: " + e.getMessage());
        }

    }

    @FXML
    void suaTTTK(MouseEvent event) {
        try{
            // Lấy mã tài khoản từ ô nhập liệu
            String maTK = txt_MaTK.getText();
            String maNV = txt_MaNV.getText();
            String tenDN = txt_tenDN.getText();
            if(tenDN.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Tên đăng nhập không được để trống!");
                return;
            }
            LocalDateTime thoiGian = LocalDateTime.parse(txt_ThoiGian.getText());
            if(thoiGian == null) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Thời gian không được để trống!");
                return;
            }

            // Sử dụng DAO interface
            System.setProperty("java.security.policy", "rmi.policy");
            System.setProperty("java.rmi.server.hostname", "LAPTOP-O8OOBHDK");
            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
            NhanVien_interface nv_dao = (NhanVien_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/nhanVienDAO");
            // Lấy thông tin nhân viên từ database
            NhanVien nhanVien = nv_dao.readNhanVien(maNV);
            TaiKhoan tk = new TaiKhoan(maTK,tenDN, null,thoiGian, nhanVien);

            // Cập nhật thông tin tài khoản trong database
            TaiKhoan_interface tkDAO = (TaiKhoan_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/taiKhoanDAO");
            tkDAO.update(tk);

            // Cập nhật lại dữ liệu trong bảng
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể cập nhật thông tin tài khoản: " + e.getMessage());
        }
    }

    @FXML
    void themTK(MouseEvent event) {
        try{
            // Lấy thông tin từ các ô nhập liệu
            String maTK = taoMaTK();
            String maNV = QL_NhanVien_controller.taoMaNV();
            String tenDN = txt_tenDN.getText();
            if(tenDN.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Tên đăng nhập không được để trống!");
                return;
            }
            LocalDateTime thoiGian = LocalDateTime.parse(txt_ThoiGian.getText());

            // Sử dụng DAO interface
            System.setProperty("java.security.policy", "rmi.policy");
            System.setProperty("java.rmi.server.hostname", "LAPTOP-O8OOBHDK");
            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
            NhanVien_interface nv_dao = (NhanVien_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/nhanVienDAO");
            // Lấy thông tin nhân viên từ database
            NhanVien nhanVien = nv_dao.readNhanVien(maNV);
            TaiKhoan tk = new TaiKhoan(maTK,tenDN, null,thoiGian, nhanVien);

            // Thêm tài khoản vào database
            TaiKhoan_interface tkDAO = (TaiKhoan_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/taiKhoanDAO");
            tkDAO.create(tk);

            // Cập nhật lại dữ liệu trong bảng
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể thêm tài khoản: " + e.getMessage());
        }
    }

    private String taoMaTK() {
        LocalDate today = LocalDate.now();
        String day = String.format("%02d", today.getDayOfMonth());
        String month = String.format("%02d", today.getMonthValue());
        String year = String.valueOf(today.getYear()).substring(2); // 2 số cuối năm

        String prefix = "TK" + year + month + day;

        Random rand = new Random();
        int soNgauNhien = 100000 + rand.nextInt(900000); // Tạo số ngẫu nhiên 6 chữ số

        String maKH = prefix + soNgauNhien;

        return maKH;
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
            TaiKhoan_interface tkDAO = (TaiKhoan_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/taiKhoanDAO");

            // Xóa dữ liệu cũ trong table
            table_TK.getItems().clear();

            // Lấy danh sách tài khoản từ database
            ObservableList<TaiKhoan> listTK = FXCollections.observableArrayList(tkDAO.readAll());
            // Thiết lập cell value factory cho các cột
            cl_maTK.setCellValueFactory(new PropertyValueFactory<>("maTaiKhoan"));
            cl_MaNV.setCellValueFactory(cellData -> {
                NhanVien nv = cellData.getValue().getNhanVien();
                return new SimpleStringProperty(nv != null ? nv.getMaNV() : "");
            });
            cl_tenDN.setCellValueFactory(new PropertyValueFactory<>("tenDangNhap"));
            cl_ThoiGian.setCellValueFactory(new PropertyValueFactory<>("thoiGianDangNhap"));


            // Gán STT tự động
            cl_txt.setCellFactory(col -> new TableCell<TaiKhoan, Integer>() {
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
            table_TK.setItems(listTK);

            // Refresh table view
            table_TK.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txt_MaTK.setEditable(false);
        txt_MaNV.setEditable(false);
        initializeNhanVien();
        addMenusToMap();
        loadTableData();
        // Thiết lập sự kiện khi người dùng chọn một hàng trong bảng
        table_TK.setOnMouseClicked(event -> {
                TaiKhoan selectedTaiKhoan = table_TK.getSelectionModel().getSelectedItem();
                if (selectedTaiKhoan != null) {
                    txt_MaTK.setText(selectedTaiKhoan.getMaTaiKhoan());
                    txt_MaNV.setText(selectedTaiKhoan.getNhanVien().getMaNV());
                    txt_tenDN.setText(selectedTaiKhoan.getTenDangNhap());
                    txt_ThoiGian.setText(selectedTaiKhoan.getThoiGianDangNhap().toString());
                }
        });
    }
}
