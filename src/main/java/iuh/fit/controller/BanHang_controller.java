package iuh.fit.controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import iuh.fit.App;
import iuh.fit.interfaces.CaLam_interface;
import iuh.fit.interfaces.ChiTietHoaDon_SanPham_interface;
import iuh.fit.interfaces.HoaDon_interface;
import iuh.fit.interfaces.KhachHang_interface;
import iuh.fit.interfaces.NhanVien_interface;
import iuh.fit.interfaces.SanPham_interface;
import iuh.fit.entities.*;
import iuh.fit.security.Permission;
import iuh.fit.security.PermissionChecker;
import iuh.fit.security.SecurityContext;
import iuh.fit.security.UIPermissionManager;
import iuh.fit.enums.PhuongThucThanhToan;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


public class BanHang_controller implements Initializable {

    @FXML
    private VBox banHangSubMenuList;

    @FXML
    private VBox banHangSubVBox;

    @FXML
    private Button btn_themSP;

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
    private Label lb_ngayLap;

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
    private Label lb_thoiGian;

    @FXML
    private Label lb_tongTien;

    @FXML
    private Label lb_tongThanhToan;

    @FXML
    private Label lb_giamGia;

    @FXML
    private Label lb_tongSoSP;

    @FXML
    private Label lb_tongSL;

    @FXML
    private Label lb_tamTinh;

    @FXML
    private TextField txt_tienKhachTra;

    @FXML
    private Label lb_tienThua;

    @FXML
    private Button btn_thanhToan;

    @FXML
    private Button btn_apDungMa;

    @FXML
    private Button btn_dangXuat;

    @FXML
    private TextField txt_maGiamGia;

    @FXML
    private TextArea ta_ghiChu;

    @FXML
    private RadioButton rb_tienMat;

    @FXML
    private RadioButton rb_chuyenKhoan;

    @FXML
    private RadioButton rb_the;

    @FXML
    private ToggleGroup phuongThucTT;

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
    private TableColumn<SanPham, Double> tcDonGia;

    @FXML
    private TableColumn<SanPham, String> tcMaSP;

    @FXML
    private TableColumn<SanPham, Integer> tcSTT;

    @FXML
    private TableColumn<SanPham, Integer> tcSoLuong;

    @FXML
    private TableColumn<SanPham, String> tcTenSP;

    @FXML
    private TableColumn<SanPham, Double> tcThanhTien;

    @FXML
    private TableView<SanPham> tableView;

    @FXML
    private TextField txt_timKiem;

    @FXML
    private VBox thongKeSubMenuList;

    @FXML
    private VBox thongKeSubVBox;

    @FXML
    private VBox timKiemSubMenuList;

    @FXML
    private VBox timKiemSubVBox;

    @FXML
    private TextField txt_nhapMa;

    @FXML
    private TextField txt_nhapSL;

    @FXML
    private TextField txt_sdt;

    @FXML
    private TextField txt_tenKH;

    @FXML
    private VBox vBox;

    @FXML
    private Label lb_chucVu;

    @FXML
    private Label lb_tenNV;
    @FXML
    private ComboBox<?> cb_nganHang;

    @FXML
    private GridPane grid_chuyenKhoan;

    @FXML
    private GridPane grid_the;

    @FXML
    private GridPane grid_tienMat;

    Map<VBox,VBox> map = new HashMap<VBox,VBox>();

    // DAO interfaces để truy xuất dữ liệu
    private SanPham_interface sanPhamDao;
    private HoaDon_interface hoaDonDao;
    private KhachHang_interface khachHangDao;
    private ChiTietHoaDon_SanPham_interface chiTietHoaDonDao;
    private CaLam_interface caLamDao;
    private NhanVien_interface nhanVienDao;

    // Danh sách các sản phẩm trong giỏ hàng
    public static ObservableList<SanPham> cartItems;

    // Số lượng sản phẩm trong giỏ hàng
    public static Map<String, Integer> productQuantities = new HashMap<>();
    public static String maHD;
    public static KhachHang khachHang;
    public static double tienKhachTra;
    public static double tienThua;
    public static double TongTien_HoaDon;
    public static double giamGia_HD;
    public static double TamTinh_HD;


    public void initialize(URL location, ResourceBundle resources) {
        // Khởi tạo các DAO interfaces
        try {
            System.setProperty("java.security.policy", "rmi.policy");
            System.setProperty("java.rmi.server.hostname", "LAPTOP-O8OOBHDK");
            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
            sanPhamDao = (SanPham_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/sanPhamDAO");
            hoaDonDao = (HoaDon_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/hoaDonDAO");
            khachHangDao = (KhachHang_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/khachHangDAO");
            chiTietHoaDonDao = (ChiTietHoaDon_SanPham_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/chiTietHoaDonSanPhamDAO");
            caLamDao = (CaLam_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/caLamDAO");
            nhanVienDao = (NhanVien_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/nhanVienDAO");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể kết nối đến server: " + e.getMessage());
        }

        // Khởi tạo các menu
        addMenusToMap();

        // Apply permissions to UI elements
        System.out.println("Applying permissions in BanHang_controller");
        System.out.println("Current user role: " + SecurityContext.getInstance().getCurrentRole());
        applyPermissions();

        // Hiển thị ngày và thời gian hiện tại
        displayCurrentDateTime();

        // Khởi tạo bảng giỏ hàng
        initializeTable();

        // Khởi tạo thông tin hóa đơn
        initializeInvoiceInfo();

        // Khởi tạo tổng tiền
        updateTotalAmount();

        // Khởi tạo thông tin nhân viên
        initializeNhanVien();
        System.out.println(App.user);

        // Thiết lập sự kiện cho các thành phần trong Pane thông tin đơn hàng
        setupInvoiceEvents();
    }

    /**
     * Add Menus to map
     */
    public void addMenusToMap() {
        addMenusToMapImpl();
    }

    private void addMenusToMapImpl() {
        map.put(banHangSubVBox, banHangSubMenuList);
        map.put(quanLySubVBox, quanLySubMenuList);
        map.put(timKiemSubVBox, timKiemSubMenuList);
        map.put(thongKeSubVBox,thongKeSubMenuList);

        /**
         * Remove the components from VBox on load of stage
         */
        for (Map.Entry<VBox,VBox> entry : map.entrySet()) {
            entry.getKey().getChildren().remove(entry.getValue());
        }
    }
    /**
     * Menu slider
     * @param menu
     * @param subMenu
     */
    public void toolsSlider(VBox menu,VBox subMenu){
        toolsSliderImpl(menu,subMenu);
    }

    private void toolsSliderImpl(VBox menu,VBox subMenu) {
        if(menu.getChildren().contains(subMenu)){
            final FadeTransition transition = new FadeTransition(Duration.millis(500), menu);
            transition.setFromValue(0.5);
            transition.setToValue(1);
            transition.setInterpolator(Interpolator.EASE_IN);
            menu.getChildren().remove(subMenu);
            transition.play();
        }else{
            final FadeTransition transition = new FadeTransition(Duration.millis(500), menu);
            transition.setFromValue(0.5);
            transition.setToValue(1);
            transition.setInterpolator(Interpolator.EASE_IN);
            menu.getChildren().add(subMenu);
            transition.play();
        }
    }
    /**
     * Remove other menus
     * @param menu
     */
    public void removeOtherMenus(VBox menu){
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
            // Hiển thị menu bán hàng
            if (banHangSubVBox != null && banHangSubMenuList != null) {
                toolsSlider(banHangSubVBox, banHangSubMenuList);
                removeOtherMenus(banHangSubVBox);
            }

            // Hiển thị giao diện bán hàng chính
            showMainSalesInterface();

            // Làm mới giỏ hàng
            updateTotalAmount();
        } catch (Exception e) {
            System.err.println("Lỗi trong handleGioHangClick: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void handleQuanLyClick(MouseEvent event) {
        // No specific permission check needed here as the submenu items will be checked individually
        toolsSlider(quanLySubVBox,quanLySubMenuList);
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
    void handleTimKiemClick(MouseEvent event) {
        // Check permission before proceeding
        PermissionChecker.checkPermissionAndExecute(Permission.QUAN_LY_SAN_PHAM, () -> {
            try {
                // Chuyển đến giao diện TraCuu_gui.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TraCuu_gui.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                // Lấy stage hiện tại
                Stage stage = (Stage) p_gioHang.getScene().getWindow();

                // Thiết lập scene mới
                stage.setScene(scene);
                stage.setTitle("Tra cứu sản phẩm");

                // Hiển thị stage
                stage.show();

                System.out.println("Chuyển đến giao diện tra cứu thành công");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện tra cứu: " + e.getMessage());

                // Nếu không thể mở giao diện tra cứu, hiển thị giao diện tìm kiếm trên giao diện hiện tại
                toolsSlider(timKiemSubVBox, timKiemSubMenuList);
                removeOtherMenus(timKiemSubVBox);

                // Hiển thị giao diện tìm kiếm
                showSearchInterface();
            }
        });
    }

    @FXML
    void toQLHoaDon(MouseEvent event) {
        // Check permission before proceeding
        PermissionChecker.checkPermissionAndExecute(Permission.QUAN_LY_HOA_DON, () -> {
            try {
                // Chuyển đến giao diện quản lý hóa đơn
                loadFXML("/fxml/QL_HoaDon_gui.fxml");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý hóa đơn!");
            }
        });
    }

    @FXML
    void toQLKhachHang(MouseEvent event) {
        // Check permission before proceeding
        PermissionChecker.checkPermissionAndExecute(Permission.QUAN_LY_KHACH_HANG, () -> {
            try {
                // Chuyển đến giao diện quản lý khách hàng
                loadFXML("/fxml/QL_KhachHang_gui.fxml");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý khách hàng!");
            }
        });
    }

    @FXML
    void toQLNhanVien(MouseEvent event) {
        // Check permission before proceeding
        PermissionChecker.checkPermissionAndExecute(Permission.QUAN_LY_NHAN_VIEN, () -> {
            try {
                // Chuyển đến giao diện quản lý nhân viên
                loadFXML("/fxml/QL_NhanVien_gui.fxml");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý nhân viên!");
            }
        });
    }

    @FXML
    void toQLPhieuNhap(MouseEvent event) {
        // Check permission before proceeding
        PermissionChecker.checkPermissionAndExecute(Permission.QUAN_LY_PHIEU_NHAP, () -> {
            try {
                // Chuyển đến giao diện quản lý phiếu nhập
                loadFXML("/fxml/QL_PhieuNhap_gui.fxml");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý phiếu nhập!");
            }
        });
    }

    @FXML
    void toQLSanPham(MouseEvent event) {
        // Check permission before proceeding
        PermissionChecker.checkPermissionAndExecute(Permission.QUAN_LY_SAN_PHAM, () -> {
            try {
                // Chuyển đến giao diện quản lý sản phẩm
                loadFXML("/fxml/QL_SanPham_gui.fxml");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý sản phẩm!");
            }
        });
    }

    @FXML
    void toQLTaiKhoan(MouseEvent event) {
        // Check permission before proceeding
        PermissionChecker.checkPermissionAndExecute(Permission.QUAN_LY_TAI_KHOAN, () -> {
            try {
                // Chuyển đến giao diện quản lý tài khoản
                loadFXML("/fxml/QL_TaiKhoan_gui.fxml");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý tài khoản!");
            }
        });
    }

    @FXML
    void toTKDoanhThu(MouseEvent event) {
        // Check permission before proceeding
        PermissionChecker.checkPermissionAndExecute(Permission.THONG_KE, () -> {
            try {
                // Hiển thị menu thống kê
                toolsSlider(thongKeSubVBox, thongKeSubMenuList);
                removeOtherMenus(thongKeSubVBox);

                // Load giao diện thống kê doanh thu
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ThongKeDoanhThu_gui.fxml"));
                Parent root = loader.load();

                // Lấy scene hiện tại
                Scene currentScene = p_gioHang.getScene();
                Stage currentStage = (Stage) currentScene.getWindow();

                // Tạo scene mới và áp dụng stylesheet
                Scene newScene = new Scene(root);
                newScene.getStylesheets().add(getClass().getResource("/styles/menu.css").toExternalForm());

                // Set scene mới và hiển thị
                currentStage.setScene(newScene);
                currentStage.show();

            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Không thể mở giao diện thống kê doanh thu!\nLỗi: " + e.getMessage());
                alert.showAndWait();
            }
        });
    }

    @FXML
    void toTKSanPham(MouseEvent event) {
        // Check permission before proceeding
        PermissionChecker.checkPermissionAndExecute(Permission.THONG_KE, () -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ThongKeSanPham_gui.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) p_gioHang.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện thống kê sản phẩm!");
            }
        });
    }

    @FXML
    void themSanPham(MouseEvent event) {
        // Lấy mã sản phẩm và số lượng từ các trường nhập liệu
        String maSP = txt_nhapMa.getText().trim();
        String soLuongStr = txt_nhapSL.getText().trim();

        // Kiểm tra dữ liệu nhập vào
        if (maSP.isEmpty()) {
            showAlert(AlertType.WARNING, "Thông báo", "Vui lòng nhập mã sản phẩm!");
            txt_nhapMa.requestFocus();
            return;
        }

        if (soLuongStr.isEmpty()) {
            showAlert(AlertType.WARNING, "Thông báo", "Vui lòng nhập số lượng!");
            txt_nhapSL.requestFocus();
            return;
        }

        try {
            int soLuong = Integer.parseInt(soLuongStr);
            if (soLuong <= 0) {
                showAlert(AlertType.WARNING, "Thông báo", "Số lượng phải lớn hơn 0!");
                txt_nhapSL.requestFocus();
                return;
            }

            // Tìm kiếm sản phẩm theo mã và thêm vào giỏ hàng
            addProductToCart(maSP, soLuong);

            // Xóa dữ liệu trong các trường nhập liệu
            txt_nhapMa.clear();
            txt_nhapSL.clear();
            txt_nhapMa.requestFocus();

        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Lỗi", "Số lượng phải là số nguyên!");
            txt_nhapSL.requestFocus();
        }
    }

    /**
     * Hiển thị ngày và thời gian hiện tại
     */
    private void displayCurrentDateTime() {
        // Hiển thị ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        lb_ngayLap.setText(currentDate.format(dateFormatter));

        // Hiển thị và cập nhật thời gian hiện tại mỗi giây
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            lb_thoiGian.setText(currentTime.format(timeFormatter));
        }), new KeyFrame(Duration.seconds(1)));

        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
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
            showAlert(AlertType.ERROR, "Lỗi", "Không thể lấy thông tin nhân viên: " + e.getMessage());
        }
    }

    /**
     * Khởi tạo thông tin hóa đơn
     */
    private void initializeInvoiceInfo() {
        try {
            // Hiển thị ngày lập hóa đơn
            if (lb_ngayLap != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                lb_ngayLap.setText(LocalDate.now().format(formatter));
            }

            // Thiết lập giá trị mặc định cho các trường
            if (lb_giamGia != null) {
                lb_giamGia.setText("0 VNĐ");
            }

            if (lb_tongThanhToan != null) {
                lb_tongThanhToan.setText("0 VNĐ");
            }

            if (lb_tongSoSP != null) {
                lb_tongSoSP.setText("0");
            }

            if (lb_tongSL != null) {
                lb_tongSL.setText("0");
            }

            if (lb_tamTinh != null) {
                lb_tamTinh.setText("0 VNĐ");
            }

            if (txt_maGiamGia != null) {
                txt_maGiamGia.setText("");
            }

            if (ta_ghiChu != null) {
                ta_ghiChu.setText("");
            }

            // Thiết lập phương thức thanh toán mặc định
            if (rb_tienMat != null) {
                rb_tienMat.setSelected(true);
            }

            // Thiết lập giá trị mặc định cho tiền khách trả và tiền thừa
            if (txt_tienKhachTra != null) {
                txt_tienKhachTra.setText("");
            }

            if (lb_tienThua != null) {
                lb_tienThua.setText("0 VNĐ");
                lb_tienThua.setStyle("-fx-font-weight: bold; -fx-text-fill: #009900;");
            }

            // Mặc định, nút thanh toán bị vô hiệu hóa cho đến khi khách hàng trả đủ tiền
            if (btn_thanhToan != null) {
                btn_thanhToan.setDisable(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Lỗi khi khởi tạo thông tin hóa đơn: " + e.getMessage());
        }
    }

    /**
     * Thiết lập sự kiện cho các thành phần trong Pane thông tin đơn hàng
     */
    private void setupInvoiceEvents() {
        try {
            // Sự kiện khi nhấn nút áp dụng mã giảm giá
            if (btn_apDungMa != null) {
                btn_apDungMa.setOnAction(event -> apDungMaGiamGia());
            }

            // Sự kiện khi thay đổi phương thức thanh toán
            if (phuongThucTT != null) {
                phuongThucTT.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
                    updateTotalAmount();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Lỗi khi thiết lập sự kiện cho thông tin hóa đơn: " + e.getMessage());
        }
    }

    /**
     * Khởi tạo bảng giỏ hàng
     */
    private void initializeTable() {
        try {
            // Khởi tạo các cột cho bảng giỏ hàng
            tcSTT.setCellValueFactory(cellData -> {
                int index = cartItems.indexOf(cellData.getValue()) + 1;
                return javafx.beans.binding.Bindings.createObjectBinding(() -> index);
            });

            tcMaSP.setCellValueFactory(new PropertyValueFactory<>("maSP"));
            tcTenSP.setCellValueFactory(new PropertyValueFactory<>("tenSP"));

            // Số lượng sản phẩm trong giỏ hàng
            tcSoLuong.setCellValueFactory(cellData -> {
                SanPham sp = cellData.getValue();
                Integer quantity = productQuantities.getOrDefault(sp.getMaSP(), 0);
                return javafx.beans.binding.Bindings.createObjectBinding(() -> quantity);
            });

            tcDonGia.setCellValueFactory(new PropertyValueFactory<>("giaBan"));

            // Thành tiền = giá bán * số lượng
            tcThanhTien.setCellValueFactory(cellData -> {
                SanPham sp = cellData.getValue();
                Integer quantity = productQuantities.getOrDefault(sp.getMaSP(), 0);
                Double thanhTien = sp.getGiaBan() * quantity;
                return javafx.beans.binding.Bindings.createObjectBinding(() -> thanhTien);
            });

            // Định dạng hiển thị tiền tệ cho cột đơn giá và thành tiền
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

            tcDonGia.setCellFactory(column -> new javafx.scene.control.TableCell<SanPham, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(currencyFormat.format(item));
                    }
                }
            });

            tcThanhTien.setCellFactory(column -> new javafx.scene.control.TableCell<SanPham, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(currencyFormat.format(item));
                    }
                }
            });

            // Khởi tạo danh sách giỏ hàng trống
            cartItems = FXCollections.observableArrayList();
            if (tableView != null) {
                tableView.setItems(cartItems);

                // Thêm sự kiện nhấp đúp vào bảng
                tableView.setOnMouseClicked(this::handleSearchResultDoubleClick);

                // Thêm context menu cho bảng
                setupTableContextMenu();
            } else {
                System.out.println("TableView is null. Check your FXML file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error initializing table: " + e.getMessage());
        }
    }

    /**
     * Thêm sản phẩm vào giỏ hàng
     */
    private void addProductToCart(String maSP, int soLuong) {
        try {
            // Tìm kiếm sản phẩm theo mã
            SanPham sanPham = sanPhamDao.read(maSP);

            if (sanPham == null) {
                showAlert(AlertType.ERROR, "Lỗi", "Không tìm thấy sản phẩm có mã " + maSP);
                return;
            }

            // Kiểm tra số lượng tồn kho
            if (sanPham.getSoLuongTon() < soLuong) {
                showAlert(AlertType.WARNING, "Cảnh báo", "Số lượng sản phẩm trong kho không đủ (còn " + sanPham.getSoLuongTon() + ")");
                return;
            }

            // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
            boolean productExists = false;

            for (SanPham sp : cartItems) {
                if (sp.getMaSP().equals(maSP)) {
                    // Nếu sản phẩm đã tồn tại, tăng số lượng
                    int currentQuantity = productQuantities.getOrDefault(maSP, 0);
                    productQuantities.put(maSP, currentQuantity + soLuong);
                    productExists = true;
                    break;
                }
            }

            // Nếu sản phẩm chưa có trong giỏ hàng, thêm mới
            if (!productExists) {
                BanHang_controller.cartItems.add(sanPham);
                productQuantities.put(maSP, soLuong);
            }

            // Cập nhật lại bảng
            tableView.refresh();

            // Cập nhật tổng tiền
            updateTotalAmount();

            // Hiển thị thông báo thành công
            showAlert(AlertType.INFORMATION, "Thông báo", "Đã thêm sản phẩm vào giỏ hàng!");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể thêm sản phẩm vào giỏ hàng: " + e.getMessage());
        }
    }

    /**
     * Hiển thị giao diện bán hàng chính
     */
    private void showMainSalesInterface() {
        // Làm mới form nhập liệu
        txt_nhapMa.clear();
        txt_nhapSL.clear();
        txt_tenKH.clear();
        txt_sdt.clear();

        // Focus vào trường nhập mã sản phẩm
        txt_nhapMa.requestFocus();
    }

    /**
     * Xóa sản phẩm khỏi giỏ hàng
     */
    private void removeProductFromCart(String maSP) {
        try {
            // Tìm sản phẩm trong giỏ hàng
            SanPham productToRemove = null;
            for (SanPham sp : cartItems) {
                if (sp.getMaSP().equals(maSP)) {
                    productToRemove = sp;
                    break;
                }
            }

            // Nếu tìm thấy sản phẩm, xóa khỏi giỏ hàng
            if (productToRemove != null) {
                BanHang_controller.cartItems.remove(productToRemove);
                productQuantities.remove(maSP);
                tableView.refresh();

                // Cập nhật tổng tiền
                updateTotalAmount();

                showAlert(AlertType.INFORMATION, "Thông báo", "Đã xóa sản phẩm khỏi giỏ hàng!");
            } else {
                showAlert(AlertType.WARNING, "Thông báo", "Không tìm thấy sản phẩm trong giỏ hàng!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể xóa sản phẩm khỏi giỏ hàng: " + e.getMessage());
        }
    }

    /**
     * Tính tổng tiền hàng (chưa bao gồm thuế và giảm giá)
     */
    private double calculateSubtotal() {
        double subtotal = 0;
        for (SanPham sp : cartItems) {
            int quantity = productQuantities.getOrDefault(sp.getMaSP(), 0);
            subtotal += sp.getGiaBan() * quantity;
        }
        return subtotal;
    }

    /**
     * Tính thuế VAT (8% tổng tiền hàng)
     */
    private double calculateVAT(double subtotal) {
        return subtotal * 0.1; // 8% VAT
    }

    /**
     * Tính giảm giá
     */
    private double calculateDiscount(double subtotal) {
        try {
            if (lb_giamGia != null && !lb_giamGia.getText().equals("0 VNĐ")) {
                try {
                    String giamGiaStr = lb_giamGia.getText().replaceAll("[^\\d]", "");
                    return Double.parseDouble(giamGiaStr);
                } catch (Exception e) {
                    // Không làm gì nếu không thể chuyển đổi
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Tính tổng tiền của giỏ hàng (bao gồm thuế và giảm giá)
     */
    private double calculateTotal() {
        double subtotal = calculateSubtotal();
        double vat = calculateVAT(subtotal);
        double discount = calculateDiscount(subtotal);

        return subtotal + vat - discount;
    }

    /**
     * Tạo hóa đơn từ giỏ hàng
     */
    private void createInvoice() {
        try {
            // Check if the cart is empty
            if (cartItems.isEmpty()) {
                showAlert(AlertType.WARNING, "Thông báo", "Giỏ hàng trống!");
                return;
            }

            // Validate customer information
            String tenKH = txt_tenKH.getText().trim();
            String sdt = txt_sdt.getText().trim();

            // Generate invoice ID
            maHD = "HD" + System.currentTimeMillis();

            // Handle shift (CaLam)
            String maCa = "CA" + System.currentTimeMillis();
            CaLam caLam = caLamDao.read("CA001");
            if (caLam == null) {
                caLam = new CaLam();
                caLam.setMaCa(maCa);
                caLam.setGioBatDau(java.time.LocalDateTime.now());
                caLam.setGioKetThuc(java.time.LocalDateTime.now().plusHours(8));
                caLam.setTrangThai(true);

                if (App.taiKhoan != null) {
                    caLam.setTaiKhoan(App.taiKhoan);
                } else {
                    showAlert(AlertType.WARNING, "Cảnh báo", "Không có tài khoản đăng nhập, sử dụng tài khoản mặc định");
                    return;
                }

                caLamDao.create(caLam);
            }

            // Handle customer (KhachHang)
            khachHang = null;
            if (!tenKH.isEmpty() && !sdt.isEmpty()) {
                khachHang = khachHangDao.findByPhone(sdt);
                if (khachHang == null) {
                    khachHang = new KhachHang();
                    khachHang.setMaKH("KH" + System.currentTimeMillis());
                    khachHang.setTenKH(tenKH);
                    khachHang.setSdt(sdt);
                    khachHangDao.create(khachHang);
                }
            } else {
                khachHang = khachHangDao.read("KH001");
                if (khachHang == null) {
                    khachHang = new KhachHang();
                    khachHang.setMaKH("KH001");
                    khachHang.setTenKH("Khách hàng vãng lai");
                    khachHang.setSdt("0000000000");
                    khachHangDao.create(khachHang);
                }
            }

            // Check if hoaDonDao is null
            if (hoaDonDao == null) {
                System.out.println("hoaDonDao is null, trying to reconnect...");
                try {
                    Registry registry = LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
                    hoaDonDao = (HoaDon_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/hoaDonDAO");
                    if (hoaDonDao == null) {
                        System.out.println("Failed to reconnect to hoaDonDao");
                        showAlert(AlertType.ERROR, "Lỗi kết nối", "Không thể kết nối đến dịch vụ hóa đơn!");
                        return;
                    }
                } catch (Exception e) {
                    System.out.println("Error reconnecting to hoaDonDao: " + e.getMessage());
                    e.printStackTrace();
                    showAlert(AlertType.ERROR, "Lỗi kết nối", "Không thể kết nối đến dịch vụ hóa đơn: " + e.getMessage());
                    return;
                }
            }

            // Create invoice (HoaDon)
            System.out.println("Creating invoice with ID: " + maHD);
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaHD(maHD);
            hoaDon.setThoiGian(java.time.LocalDateTime.now());

            int totalQuantity = productQuantities.values().stream().mapToInt(Integer::intValue).sum();
            System.out.println("Total quantity: " + totalQuantity);
            hoaDon.setTongSoLuongSP(totalQuantity);

            // Tính tổng tiền thanh toán
            double tongTien = calculateTotal();
            System.out.println("Total amount: " + tongTien);
            hoaDon.setThanhTien(tongTien);

            // Lấy thông tin tiền khách trả và tiền thừa
            tienKhachTra = 0;
            tienThua = 0;
            try {
                tienKhachTra = Double.parseDouble(txt_tienKhachTra.getText().trim());
                // Tính tiền thừa
                tienThua = tienKhachTra - tongTien;
                System.out.println("Customer payment: " + tienKhachTra + ", Change: " + tienThua);
            } catch (NumberFormatException e) {
                // Nếu không thể chuyển đổi, sử dụng giá trị mặc định
                tienKhachTra = tongTien;
                tienThua = 0;
                System.out.println("Using default payment values: " + tienKhachTra + ", Change: " + tienThua);
            }

            // Lưu thông tin tiền khách trả và tiền thừa vào hóa đơn
            // Nếu có trường tương ứng trong entity HoaDon
            // hoaDon.setTienKhachTra(tienKhachTra);
            // hoaDon.setTienThua(tienThua);

            // Xác định phương thức thanh toán dựa trên lựa chọn
            PhuongThucThanhToan phuongThuc = PhuongThucThanhToan.Tien_Mat;
            if (rb_chuyenKhoan != null && rb_chuyenKhoan.isSelected()) {
                phuongThuc = PhuongThucThanhToan.Chuyen_Khoan;
                System.out.println("Payment method: Bank transfer");
            } else if (rb_the != null && rb_the.isSelected()) {
                phuongThuc = PhuongThucThanhToan.The_Ngan_Hang;
                System.out.println("Payment method: Card");
            } else {
                System.out.println("Payment method: Cash");
            }
            hoaDon.setPhuongThucTT(phuongThuc);

            System.out.println("Setting shift: " + caLam.getMaCa());
            hoaDon.setCaLam(caLam);

            System.out.println("Setting customer: " + khachHang.getMaKH() + " - " + khachHang.getTenKH());
            hoaDon.setKhachHang(khachHang);

            hoaDon.setNhanVien(App.taiKhoan.getNhanVien());

            System.out.println("Saving invoice to database...");
            boolean result = hoaDonDao.create(hoaDon);
            if (!result) {
                System.out.println("Failed to create invoice in database");
                showAlert(AlertType.ERROR, "Lỗi", "Không thể tạo hóa đơn trong CSDL!");
                return;
            }
            System.out.println("Invoice saved successfully");

            // Check if chiTietHoaDonDao is null
            if (chiTietHoaDonDao == null) {
                System.out.println("chiTietHoaDonDao is null, trying to reconnect...");
                try {
                    Registry registry = LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
                    chiTietHoaDonDao = (ChiTietHoaDon_SanPham_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/chiTietHoaDonDAO");
                    if (chiTietHoaDonDao == null) {
                        System.out.println("Failed to reconnect to chiTietHoaDonDao");
                        showAlert(AlertType.ERROR, "Lỗi kết nối", "Không thể kết nối đến dịch vụ chi tiết hóa đơn!");
                        return;
                    }
                } catch (Exception e) {
                    System.out.println("Error reconnecting to chiTietHoaDonDao: " + e.getMessage());
                    e.printStackTrace();
                    showAlert(AlertType.ERROR, "Lỗi kết nối", "Không thể kết nối đến dịch vụ chi tiết hóa đơn: " + e.getMessage());
                    return;
                }
            }

            // Create invoice details (ChiTietHoaDon_SanPham)
            System.out.println("Creating invoice details for " + cartItems.size() + " products");
            for (SanPham sp : cartItems) {
                int soLuong = productQuantities.getOrDefault(sp.getMaSP(), 0);
                System.out.println("Processing product: " + sp.getMaSP() + " - " + sp.getTenSP() + ", quantity: " + soLuong);

                // Check stock availability
                if (sp.getSoLuongTon() < soLuong) {
                    System.out.println("Insufficient stock for product: " + sp.getMaSP() + ", available: " + sp.getSoLuongTon() + ", needed: " + soLuong);
                    showAlert(AlertType.WARNING, "Thông báo", "Sản phẩm " + sp.getTenSP() + " không đủ số lượng trong kho!");
                    return;
                }

                try {
                    // Create the composite ID
                    ChiTietHoaDon_SanPhamId chiTietId = new ChiTietHoaDon_SanPhamId();
                    chiTietId.setMaHD(maHD);
                    chiTietId.setMaSP(sp.getMaSP());
                    System.out.println("Created composite ID: " + chiTietId.getMaHD() + " - " + chiTietId.getMaSP());

                    // Create the detail object
                    ChiTietHoaDon_SanPham chiTiet = new ChiTietHoaDon_SanPham();
                    chiTiet.setId(chiTietId);
                    chiTiet.setSoLuongSP(soLuong);
                    chiTiet.setDonGia(sp.getGiaBan());
                    chiTiet.setHoaDon(hoaDon);
                    chiTiet.setSanPham(sp);
                    System.out.println("Created invoice detail object with quantity: " + soLuong + ", price: " + sp.getGiaBan());

                    // Save the detail
                    System.out.println("Saving invoice detail to database...");
                    boolean detailResult = chiTietHoaDonDao.create(chiTiet);
                    if (!detailResult) {
                        System.out.println("Failed to create invoice detail in database");
                        showAlert(AlertType.ERROR, "Lỗi", "Không thể tạo chi tiết hóa đơn trong CSDL!");
                    } else {
                        System.out.println("Invoice detail saved successfully");
                    }
                } catch (Exception e) {
                    System.out.println("Error creating invoice detail: " + e.getMessage());
                    e.printStackTrace();
                }

                // Update stock
                try {
                    System.out.println("Updating stock for product: " + sp.getMaSP() + ", current stock: " + sp.getSoLuongTon() + ", reducing by: " + soLuong);
                    sp.setSoLuongTon(sp.getSoLuongTon() - soLuong);
                    boolean updateResult = sanPhamDao.update(sp);
                    if (!updateResult) {
                        System.out.println("Failed to update stock in database");
                        showAlert(AlertType.ERROR, "Lỗi", "Không thể cập nhật số lượng tồn trong CSDL!");
                    } else {
                        System.out.println("Stock updated successfully, new stock: " + sp.getSoLuongTon());
                    }
                } catch (Exception e) {
                    System.out.println("Error updating stock: " + e.getMessage());
                    e.printStackTrace();
                }
            }

            // Clear cart and refresh UI
            cartItems.clear();
            productQuantities.clear();
            tableView.refresh();
            txt_tenKH.clear();
            txt_sdt.clear();
            txt_nhapMa.clear();
            txt_nhapSL.clear();

            // Xóa thông tin hóa đơn
            if (txt_maGiamGia != null) {
                txt_maGiamGia.clear();
            }
            if (ta_ghiChu != null) {
                ta_ghiChu.clear();
            }
            if (lb_giamGia != null) {
                lb_giamGia.setText("0 VNĐ");
            }

            // Xóa thông tin tiền khách trả và tiền thừa
            if (txt_tienKhachTra != null) {
                txt_tienKhachTra.clear();
            }
            if (lb_tienThua != null) {
                lb_tienThua.setText("0 VNĐ");
                lb_tienThua.setStyle("-fx-font-weight: bold; -fx-text-fill: #009900;");
            }

            // Vô hiệu hóa nút thanh toán cho đến khi khách hàng trả đủ tiền
            if (btn_thanhToan != null) {
                btn_thanhToan.setDisable(true);
            }

            // Cập nhật tổng tiền
            updateTotalAmount();

            // Show success message
            showAlert(AlertType.INFORMATION, "Thành công", "Đã tạo hóa đơn thành công!");

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể tạo hóa đơn: " + e.getMessage());
        }
    }

    /**
     * Hiển thị giao diện tìm kiếm
     */
    private void showSearchInterface() {
        try {
            // Làm mới form tìm kiếm
            txt_timKiem.clear();
            txt_timKiem.requestFocus();

            // Khởi tạo dữ liệu tìm kiếm nếu cần
            initializeSearchData();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể hiển thị giao diện tìm kiếm: " + e.getMessage());
        }
    }

    /**
     * Khởi tạo dữ liệu tìm kiếm
     */
    private void initializeSearchData() {
        try {
            // Tạo danh sách sản phẩm cho tìm kiếm
            ObservableList<SanPham> searchResults = FXCollections.observableArrayList();

            // Lấy tất cả sản phẩm từ database
            List<SanPham> allProducts = sanPhamDao.readAll();
            searchResults.addAll(allProducts);

            // Hiển thị kết quả tìm kiếm trong bảng
            // Sử dụng bảng hiện tại để hiển thị kết quả tìm kiếm
            tableView.setItems(searchResults);


            // Thêm sự kiện cho trường tìm kiếm
            setupSearchField();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể khởi tạo dữ liệu tìm kiếm: " + e.getMessage());
        }
    }

    /**
     * Thiết lập sự kiện cho trường tìm kiếm
     */
    private void setupSearchField() {
        // Thêm sự kiện khi nhập vào trường tìm kiếm
        txt_timKiem.textProperty().addListener((observable, oldValue, newValue) -> {
            performSearch(newValue);
        });
    }

    /**
     * Thực hiện tìm kiếm sản phẩm
     */
    private void performSearch(String keyword) {
        try {
            if (keyword == null || keyword.trim().isEmpty()) {
                // Nếu từ khóa trống, hiển thị tất cả sản phẩm
                List<SanPham> allProducts = sanPhamDao.readAll();
                tableView.setItems(FXCollections.observableArrayList(allProducts));
                return;
            }

            // Tìm kiếm sản phẩm theo từ khóa
            List<SanPham> searchResults = new ArrayList<>();
            List<SanPham> allProducts = sanPhamDao.readAll();

            // Lọc sản phẩm theo từ khóa (mã hoặc tên)
            String keywordLower = keyword.toLowerCase();
            for (SanPham sp : allProducts) {
                if (sp.getMaSP().toLowerCase().contains(keywordLower) ||
                        sp.getTenSP().toLowerCase().contains(keywordLower)) {
                    searchResults.add(sp);
                }
            }

            // Cập nhật bảng và trạng thái
            tableView.setItems(FXCollections.observableArrayList(searchResults));
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể thực hiện tìm kiếm: " + e.getMessage());
        }
    }

    /**
     * Xử lý sự kiện khi nhấp đúp vào sản phẩm trong kết quả tìm kiếm
     */
    @FXML
    private void handleSearchResultDoubleClick(MouseEvent event) {
        if (event.getClickCount() == 2) { // Nhấp đúp
            SanPham selectedProduct = tableView.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                // Chuyển sang giao diện bán hàng và thêm sản phẩm vào giỏ hàng
                handleGioHangClick(null);

                // Điền thông tin sản phẩm vào form
                txt_nhapMa.setText(selectedProduct.getMaSP());
                txt_nhapSL.setText("1"); // Mặc định số lượng là 1
                txt_nhapSL.requestFocus();
            }
        }
    }

    /**
     * Thiết lập context menu cho bảng giỏ hàng
     */
    private void setupTableContextMenu() {
        // Tạo context menu
        ContextMenu contextMenu = new ContextMenu();

        // Tạo menu item Xóa
        MenuItem deleteItem = new MenuItem("Xóa sản phẩm");
        deleteItem.setOnAction(event -> {
            // Lấy sản phẩm được chọn
            SanPham selectedProduct = tableView.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                // Xóa sản phẩm khỏi giỏ hàng
                removeProductFromCart(selectedProduct.getMaSP());
            }
        });

        // Tạo menu item Xóa tất cả
        MenuItem clearAllItem = new MenuItem("Xóa tất cả");
        clearAllItem.setOnAction(event -> {
            // Xóa tất cả sản phẩm trong giỏ hàng
            clearCart();
        });

        // Tạo menu item Sửa số lượng
        MenuItem editItem = new MenuItem("Sửa số lượng");
        editItem.setOnAction(event -> {
            // Lấy sản phẩm được chọn
            SanPham selectedProduct = tableView.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                // Hiển thị hộp thoại nhập số lượng mới
                editProductQuantity(selectedProduct);
            }
        });

        // Thêm các menu item vào context menu
        contextMenu.getItems().addAll(deleteItem, editItem, clearAllItem);

        // Gán context menu cho bảng
        tableView.setContextMenu(contextMenu);
    }

    /**
     * Sửa số lượng sản phẩm trong giỏ hàng
     */
    private void editProductQuantity(SanPham product) {
        try {
            // Tạo dialog để nhập số lượng mới
            TextInputDialog dialog = new TextInputDialog(productQuantities.getOrDefault(product.getMaSP(), 1).toString());
            dialog.setTitle("Sửa số lượng");
            dialog.setHeaderText("Sản phẩm: " + product.getTenSP());
            dialog.setContentText("Nhập số lượng mới:");

            // Hiển thị dialog và đợi kết quả
            dialog.showAndWait().ifPresent(result -> {
                try {
                    // Chuyển kết quả thành số
                    int newQuantity = Integer.parseInt(result);

                    // Kiểm tra số lượng hợp lệ
                    if (newQuantity <= 0) {
                        showAlert(AlertType.WARNING, "Cảnh báo", "Số lượng phải lớn hơn 0!");
                        return;
                    }

                    // Kiểm tra số lượng tồn kho
                    if (newQuantity > product.getSoLuongTon()) {
                        showAlert(AlertType.WARNING, "Cảnh báo", "Số lượng vượt quá số lượng tồn kho (" + product.getSoLuongTon() + ")!");
                        return;
                    }

                    // Cập nhật số lượng
                    productQuantities.put(product.getMaSP(), newQuantity);

                    // Cập nhật bảng và tổng tiền
                    tableView.refresh();
                    updateTotalAmount();

                    showAlert(AlertType.INFORMATION, "Thông báo", "Đã cập nhật số lượng sản phẩm!");
                } catch (NumberFormatException e) {
                    showAlert(AlertType.ERROR, "Lỗi", "Số lượng phải là số nguyên!");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể sửa số lượng sản phẩm: " + e.getMessage());
        }
    }

    /**
     * Xóa tất cả sản phẩm trong giỏ hàng
     */
    private void clearCart() {
        try {
            // Kiểm tra xem giỏ hàng có trống không
            if (cartItems.isEmpty()) {
                showAlert(AlertType.INFORMATION, "Thông báo", "Giỏ hàng đã trống!");
                return;
            }

            // Hiển thị hộp thoại xác nhận
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận xóa");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc chắn muốn xóa tất cả sản phẩm trong giỏ hàng?");

            // Nếu người dùng nhấn OK
            if (alert.showAndWait().get() == javafx.scene.control.ButtonType.OK) {
                // Xóa tất cả sản phẩm trong giỏ hàng
                cartItems.clear();
                productQuantities.clear();
                tableView.refresh();

                // Cập nhật tổng tiền
                updateTotalAmount();

                showAlert(AlertType.INFORMATION, "Thông báo", "Đã xóa tất cả sản phẩm trong giỏ hàng!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể xóa giỏ hàng: " + e.getMessage());
        }
    }

    /**
     * Xử lý sự kiện khi nhấn nút đăng xuất
     */
    @FXML
    private void handleDangXuatClick(MouseEvent event) {
        try {
            // Hiển thị hộp thoại xác nhận
            Alert alert = new Alert(AlertType.CONFIRMATION);
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
            showAlert(AlertType.ERROR, "Lỗi", "Không thể đăng xuất: " + e.getMessage());
        }
    }

    /**
     * Áp dụng mã giảm giá (gọi từ FXML)
     */
    @FXML
    private void apDungMaGiamGia(MouseEvent event) {
        apDungMaGiamGia();
    }

    /**
     * Áp dụng mã giảm giá
     */
    private void apDungMaGiamGia() {
        try {
            if (txt_maGiamGia != null) {
                String maGiamGia = txt_maGiamGia.getText().trim();

                if (maGiamGia.isEmpty()) {
                    showAlert(AlertType.WARNING, "Cảnh báo", "Vui lòng nhập mã giảm giá!");
                    return;
                }

                // TODO: Kiểm tra mã giảm giá trong cơ sở dữ liệu
                // Đây là một ví dụ đơn giản
                double giamGia = 0;

                if (maGiamGia.equals("SALE10")) {
                    giamGia = calculateSubtotal() * 0.1; // Giảm 10%
                } else if (maGiamGia.equals("SALE20")) {
                    giamGia = calculateSubtotal() * 0.2; // Giảm 20%
                } else if (maGiamGia.equals("SALE50")) {
                    giamGia = calculateSubtotal() * 0.5; // Giảm 50%
                } else {
                    showAlert(AlertType.ERROR, "Lỗi", "Mã giảm giá không hợp lệ!");
                    return;
                }
                giamGia_HD = giamGia;

                // Cập nhật giảm giá
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                if (lb_giamGia != null) {
                    lb_giamGia.setText(currencyFormat.format(giamGia));
                }

                // Cập nhật tổng thanh toán
                double tongTien = calculateSubtotal() + calculateVAT(calculateSubtotal()) - giamGia;
                if (lb_tongThanhToan != null) {
                    lb_tongThanhToan.setText(currencyFormat.format(tongTien));
                }

                showAlert(AlertType.INFORMATION, "Thành công", "Áp dụng mã giảm giá thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể áp dụng mã giảm giá: " + e.getMessage());
        }
    }

    /**
     * Cập nhật tổng tiền trong giỏ hàng
     */
    private void updateTotalAmount() {
        try {
            // Tính tổng số sản phẩm (số loại sản phẩm)
            int tongSoSP = cartItems.size();
            if (lb_tongSoSP != null) {
                lb_tongSoSP.setText(String.valueOf(tongSoSP));
            }

            // Tính tổng số lượng (tổng số mặt hàng)
            int tongSL = 0;
            for (Integer quantity : productQuantities.values()) {
                tongSL += quantity;
            }
            if (lb_tongSL != null) {
                lb_tongSL.setText(String.valueOf(tongSL));
            }

            // Tính tạm tính (tổng tiền hàng chưa bao gồm thuế)
            double tamTinh = calculateSubtotal();


            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            if (lb_tamTinh != null) {
                lb_tamTinh.setText(currencyFormat.format(tamTinh));
            }
            TamTinh_HD = tamTinh;
            System.out.println("TamTinh_HD => " + TamTinh_HD);
            // Tính tổng tiền (bao gồm thuế)
            double total = calculateTotal();
            if (lb_tongTien != null) {
                lb_tongTien.setText(currencyFormat.format(total));
            }

            // Cập nhật tổng thanh toán
            double tongTien = total;

            // Trừ giảm giá nếu có
            if (lb_giamGia != null && !lb_giamGia.getText().equals("0 VNĐ")) {
                try {
                    String giamGiaStr = lb_giamGia.getText().replaceAll("[^\\d]", "");
                    double giamGia = Double.parseDouble(giamGiaStr);
                    tongTien -= giamGia;
                } catch (Exception e) {
                    // Không làm gì nếu không thể chuyển đổi
                }
            }

            // Cập nhật tổng thanh toán
            if (lb_tongThanhToan != null) {
                lb_tongThanhToan.setText(currencyFormat.format(tongTien));
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi và đặt giá trị mặc định
            if (lb_tongSoSP != null) {
                lb_tongSoSP.setText("0");
            }
            if (lb_tongSL != null) {
                lb_tongSL.setText("0");
            }
            if (lb_tamTinh != null) {
                lb_tamTinh.setText("0 VNĐ");
            }
            if (lb_tongTien != null) {
                lb_tongTien.setText("0 VNĐ");
            }
            if (lb_tongThanhToan != null) {
                lb_tongThanhToan.setText("0 VNĐ");
            }
        }
    }

    /**
     * Xử lý sự kiện khi nhấn nút thanh toán
     */
    @FXML
    void thanhToan(MouseEvent event) throws IOException {
        if (cartItems.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Thông báo", "Giỏ hàng trống!");
            return;
        }

        if (txt_tienKhachTra.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Thông báo", "Vui lòng nhập số tiền khách trả!");
            txt_tienKhachTra.requestFocus();
            return;
        }

        if (lb_tienThua.getText().startsWith("-")) {
            showAlert(Alert.AlertType.WARNING, "Thông báo", "Số tiền khách trả chưa đủ!");
            txt_tienKhachTra.requestFocus();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận thanh toán");
        alert.setHeaderText(null);

        StringBuilder content = new StringBuilder("Thông tin thanh toán:\n");
        content.append("Tổng sản phẩm: ").append(cartItems.size()).append("\n");

        String tongTien = lb_tongThanhToan != null ? lb_tongThanhToan.getText() : lb_tongTien.getText();
        content.append("Tổng thanh toán: ").append(tongTien).append("\n");

        String phuongThuc = rb_chuyenKhoan.isSelected() ? "Chuyển khoản" : rb_the.isSelected() ? "Thẻ" : "Tiền mặt";
        content.append("Phương thức thanh toán: ").append(phuongThuc).append("\n");

        if (ta_ghiChu != null && !ta_ghiChu.getText().trim().isEmpty()) {
            content.append("Ghi chú: ").append(ta_ghiChu.getText().trim()).append("\n");
        }

        content.append("\nBạn có chắc chắn muốn thanh toán?");
        alert.setContentText(content.toString());

        if (alert.showAndWait().get() == javafx.scene.control.ButtonType.OK) {
            // 🛑 Backup giỏ hàng trước khi bị xóa
            ObservableList<SanPham> itemsCopy = FXCollections.observableArrayList(cartItems);
            Map<String, Integer> quantitiesCopy = new HashMap<>(productQuantities);
            double tamTinhBackup = TamTinh_HD;
            double tongTienBackup = TongTien_HoaDon;
            // Cập nhật lại tổng tiền lần cuối
            updateTotalAmount();

            // Tạo hóa đơn & xóa giỏ hàng
            createInvoice();

            // Mở giao diện hóa đơn
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HoaDon_gui.fxml"));
            Parent root = loader.load();

            HoaDon_controller hoaDonController = loader.getController();
            hoaDonController.setCartData(itemsCopy, quantitiesCopy);
            hoaDonController.setHoaDonInfo(
                    tamTinhBackup,
                    tongTienBackup,
                    tienKhachTra,
                    tienThua,
                    giamGia_HD,
                    maHD,
                    khachHang != null ? khachHang.getMaKH() : "KH001",
                    App.taiKhoan.getNhanVien().getMaNV()
            );

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Hóa Đơn");
            stage.show();
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

    /**
     * Apply permissions to UI elements
     */
    private void applyPermissions() {
        System.out.println("Starting permission application");

        // Check if the security context is properly initialized
        SecurityContext securityContext = SecurityContext.getInstance();
        if (securityContext.getCurrentRole() == null) {
            System.out.println("WARNING: Current role is null in SecurityContext");
            return;
        }

        System.out.println("Current role: " + securityContext.getCurrentRole());
        System.out.println("Permissions are now checked at function call level");
        System.out.println("Finished applying permissions");
    }

    /**
     * Load FXML file
     */
    private void loadFXML(String fxmlPath) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) p_gioHang.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @FXML
    private void togglePaymentMethod() {
        // Show/hide appropriate payment fields based on selection
        boolean isCashSelected = rb_tienMat.isSelected();
        boolean isTransferSelected = rb_chuyenKhoan.isSelected();
        boolean isCardSelected = rb_the.isSelected();

        grid_tienMat.setVisible(isCashSelected);
        grid_tienMat.setManaged(isCashSelected);

        grid_chuyenKhoan.setVisible(isTransferSelected);
        grid_chuyenKhoan.setManaged(isTransferSelected);

        grid_the.setVisible(isCardSelected);
        grid_the.setManaged(isCardSelected);
    }

    /**
     * Tính tiền thừa khi khách hàng thanh toán
     * Được gọi khi người dùng nhập tiền khách trả
     */
    @FXML
    private void tinhTienThua(KeyEvent event) {
        try {
            // Lấy tổng tiền thanh toán
            String tongTienText = lb_tongThanhToan.getText();
            // Loại bỏ các ký tự không phải số
            tongTienText = tongTienText.replaceAll("[^\\d]", "");
            double tongTien = Double.parseDouble(tongTienText);
            TongTien_HoaDon = tongTien;

            // Lấy số tiền khách trả
            String tienKhachTraText = txt_tienKhachTra.getText().trim();

            // Kiểm tra xem trường nhập có trống không
            if (tienKhachTraText.isEmpty()) {
                lb_tienThua.setText("0 VNĐ");
                lb_tienThua.setStyle("-fx-font-weight: bold; -fx-text-fill: #009900;");
                return;
            }

            // Kiểm tra xem trường nhập có chứa ký tự không phải số không
            if (!tienKhachTraText.matches("\\d+")) {
                // Nếu có, loại bỏ các ký tự không phải số
                tienKhachTraText = tienKhachTraText.replaceAll("[^\\d]", "");
                // Cập nhật lại trường nhập
                txt_tienKhachTra.setText(tienKhachTraText);
                // Đặt con trỏ vào cuối trường nhập
                txt_tienKhachTra.positionCaret(tienKhachTraText.length());
            }

            // Nếu trường nhập vẫn trống sau khi loại bỏ các ký tự không phải số
            if (tienKhachTraText.isEmpty()) {
                lb_tienThua.setText("0 VNĐ");
                lb_tienThua.setStyle("-fx-font-weight: bold; -fx-text-fill: #009900;");
                return;
            }

            // Chuyển đổi sang số
            double tienKhachTra = Double.parseDouble(tienKhachTraText);
            // Tính tiền thừa
            double tienThua = tienKhachTra - tongTien;

            // Định dạng tiền tệ với dấu phân cách hàng nghìn
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            String tienThuaFormatted = currencyFormat.format(tienThua);

            // Hiển thị tiền thừa với màu sắc tương ứng
            if (tienThua >= 0) {
                // Tiền thừa dương hoặc bằng 0 - màu xanh
                lb_tienThua.setText(tienThuaFormatted);
                lb_tienThua.setStyle("-fx-font-weight: bold; -fx-text-fill: #009900;");

                // Kiểm tra xem tiền khách trả có đủ không
                if (tienKhachTra >= tongTien) {
                    // Nếu đủ, cho phép thanh toán
                    btn_thanhToan.setDisable(false);

                }
            } else {
                // Tiền thừa âm - màu đỏ
                lb_tienThua.setText(tienThuaFormatted);
                lb_tienThua.setStyle("-fx-font-weight: bold; -fx-text-fill: #FF0000;");

                // Nếu tiền khách trả không đủ, không cho phép thanh toán
                btn_thanhToan.setDisable(true);
            }
        } catch (NumberFormatException e) {
            // Xử lý lỗi chuyển đổi số
            lb_tienThua.setText("0 VNĐ");
            lb_tienThua.setStyle("-fx-font-weight: bold; -fx-text-fill: #009900;");
            e.printStackTrace();
        }
    }


}