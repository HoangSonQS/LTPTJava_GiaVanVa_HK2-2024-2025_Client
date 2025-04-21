package iuh.fit.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import iuh.fit.App;
import iuh.fit.interfaces.HoaDon_interface;
import iuh.fit.entities.NhanVien;
import iuh.fit.entities.TaiKhoan;
import iuh.fit.enums.LoaiHang;
import iuh.fit.security.Permission;
import iuh.fit.security.PermissionChecker;
import iuh.fit.security.SecurityContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.collections.FXCollections;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import java.util.Arrays;
import java.text.NumberFormat;
import java.util.Locale;


public class ThongKeSanPham_controller implements Initializable {


    public static class ThongKeSanPhamModel {
        private final StringProperty maSP;
        private final StringProperty tenSP;
        private final LongProperty soLuongBan;
        private final DoubleProperty doanhThu;
        private final DoubleProperty tyLe;

        public ThongKeSanPhamModel(String maSP, String tenSP, Long soLuongBan, Double doanhThu, Double tyLe) {
            this.maSP = new SimpleStringProperty(maSP);
            this.tenSP = new SimpleStringProperty(tenSP);
            this.soLuongBan = new SimpleLongProperty(soLuongBan);
            this.doanhThu = new SimpleDoubleProperty(doanhThu);
            this.tyLe = new SimpleDoubleProperty(tyLe);
        }

        public String getMaSP() {
            return maSP.get();
        }

        public StringProperty maSPProperty() {
            return maSP;
        }

        public void setMaSP(String maSP) {
            this.maSP.set(maSP);
        }

        public String getTenSP() {
            return tenSP.get();
        }

        public StringProperty tenSPProperty() {
            return tenSP;
        }

        public void setTenSP(String tenSP) {
            this.tenSP.set(tenSP);
        }

        public Long getSoLuongBan() {
            return soLuongBan.get();
        }

        public LongProperty soLuongBanProperty() {
            return soLuongBan;
        }

        public void setSoLuongBan(Long soLuongBan) {
            this.soLuongBan.set(soLuongBan);
        }

        public Double getDoanhThu() {
            return doanhThu.get();
        }

        public DoubleProperty doanhThuProperty() {
            return doanhThu;
        }

        public void setDoanhThu(Double doanhThu) {
            this.doanhThu.set(doanhThu);
        }

        public Double getTyLe() {
            return tyLe.get();
        }

        public DoubleProperty tyLeProperty() {
            return tyLe;
        }

        public void setTyLe(Double tyLe) {
            this.tyLe.set(tyLe);
        }
    }
    private HoaDon_interface hoaDonDao;

    @FXML
    private VBox banHangSubMenuList;

    @FXML
    private VBox banHangSubVBox;

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
    private VBox vBox;

    // Các thành phần mới cho thống kê sản phẩm
    @FXML
    private ComboBox<String> cbLoaiThongKe;

    @FXML
    private ComboBox<String> cbNam;

    @FXML
    private Button btnXemThongKe;

    @FXML
    private PieChart pieChart;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private TableView<ThongKeSanPhamModel> tbThongKe;

    @FXML
    private TableColumn<ThongKeSanPhamModel, String> tcMaSP;

    @FXML
    private TableColumn<ThongKeSanPhamModel, String> tcTenSP;

    @FXML
    private TableColumn<ThongKeSanPhamModel, Number> tcSoLuongBan;

    @FXML
    private TableColumn<ThongKeSanPhamModel, Number> tcDoanhThu;

    @FXML
    private TableColumn<ThongKeSanPhamModel, Number> tcTyLe;

    @FXML
    private ComboBox<String> cbLoaiHang;

    private EntityManager em;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Khởi tạo DAO interface
        try {
            System.setProperty("java.security.policy", "rmi.policy");
            System.setProperty("java.rmi.server.hostname", "LAPTOP-O8OOBHDK");
            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
            hoaDonDao = (HoaDon_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/hoaDonDAO");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể kết nối đến server: " + e.getMessage());
        }
        addMenusToMap();
        initializeNhanVien();
        setupCharts();
        setupTable();

        // Populate loại hàng ComboBox với giá trị đẹp
        ObservableList<String> loaiHangList = FXCollections.observableArrayList(
                "TẤT CẢ",
                LoaiHang.THUC_PHAM.getLoaiHang(),
                LoaiHang.DO_GIA_DUNG.getLoaiHang(),
                LoaiHang.THOI_TRANG_VA_PHU_KIEN.getLoaiHang()
        );
        cbLoaiHang.setItems(loaiHangList);
        cbLoaiHang.setValue("TẤT CẢ");

        // Debug: Print all enum values and their display names
        System.out.println("Available LoaiHang values:");
        for (LoaiHang lh : LoaiHang.values()) {
            System.out.println(lh.name() + " -> " + lh.getLoaiHang());
        }

        // Add listener for loại hàng changes
        cbLoaiHang.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                handleXemThongKe();
            }
        });

        // Add listener for loại thống kê changes
        cbLoaiThongKe.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                handleXemThongKe();
            }
        });

        // Add listener for năm changes
        cbNam.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                handleXemThongKe();
            }
        });

        // Populate loại thống kê ComboBox
        ObservableList<String> loaiThongKeList = FXCollections.observableArrayList(
            "Theo ngày",
            "Theo tháng",
            "Theo quý"
        );
        cbLoaiThongKe.setItems(loaiThongKeList);
        cbLoaiThongKe.setValue("Theo tháng");

        // Populate năm ComboBox
        populateYearComboBox();

        // Thêm kiểm tra dữ liệu khi khởi tạo
//        checkDatabaseData();
    }


    // Các phương thức mới cho thống kê sản phẩm
    private void setupCharts() {
        // Thiết lập biểu đồ tròn
        pieChart.setTitle("Tỷ lệ bán hàng theo sản phẩm");
        pieChart.setAnimated(true);

        // Thiết lập biểu đồ cột
        barChart.setTitle("Số lượng bán theo sản phẩm");
        barChart.setAnimated(true);
    }

    private void setupTable() {
        // Thiết lập các cột cho bảng thống kê
        tcMaSP.setCellValueFactory(new PropertyValueFactory<>("maSP"));
        tcTenSP.setCellValueFactory(new PropertyValueFactory<>("tenSP"));
        tcSoLuongBan.setCellValueFactory(new PropertyValueFactory<>("soLuongBan"));
        tcDoanhThu.setCellValueFactory(new PropertyValueFactory<>("doanhThu"));
        tcTyLe.setCellValueFactory(new PropertyValueFactory<>("tyLe"));

        // Format số lượng bán
        tcSoLuongBan.setCellFactory(column -> new javafx.scene.control.TableCell<ThongKeSanPhamModel, Number>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%,d", item.longValue()));
                }
            }
        });

        // Format doanh thu
        tcDoanhThu.setCellFactory(column -> new javafx.scene.control.TableCell<ThongKeSanPhamModel, Number>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                    setText(currencyFormat.format(item.doubleValue()));
                }
            }
        });

        // Format tỷ lệ
        tcTyLe.setCellFactory(column -> new javafx.scene.control.TableCell<ThongKeSanPhamModel, Number>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%.2f%%", item.doubleValue()));
                }
            }
        });
    }

    /**
     * Thêm dữ liệu vào combobox năm
     */
    private void populateYearComboBox() {
        int currentYear = LocalDate.now().getYear();
        List<String> years = new ArrayList<>();

        // Thêm 10 năm gần nhất vào combobox
        for (int i = 0; i < 10; i++) {
            years.add(String.valueOf(currentYear - i));
        }

        cbNam.setItems(FXCollections.observableArrayList(years));

        // Chọn năm hiện tại làm mặc định
        cbNam.setValue(String.valueOf(currentYear));
    }

    @FXML
    void handleXemThongKe() {
        try {
            String loaiThongKe = cbLoaiThongKe.getValue();
            String nam = cbNam.getValue();
            String loaiHangDisplay = cbLoaiHang.getValue();

            // Debug log
            System.out.println("Selected values:");
            System.out.println("Loại thống kê: " + loaiThongKe);
            System.out.println("Năm: " + nam);
            System.out.println("Loại hàng display: " + loaiHangDisplay);

            if (loaiThongKe == null || nam == null || loaiHangDisplay == null) {
                showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng chọn đầy đủ thông tin!");
                return;
            }

            pieChart.getData().clear();
            barChart.getData().clear();

            updateCharts(nam, loaiThongKe, loaiHangDisplay);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi",
                    "Chi tiết lỗi: " + e.getMessage());
        }
    }

    private void updateCharts(String nam, String loaiThongKe, String loaiHangDisplay) {
        try {
            List<Object[]> results = hoaDonDao.getThongKeSanPham(nam, loaiThongKe, loaiHangDisplay);

            if (results.isEmpty()) {
                showAlert(Alert.AlertType.INFORMATION, "Thông báo",
                        "Không có dữ liệu thống kê cho thời gian này!");
                // Xóa dữ liệu cũ trong bảng
                tbThongKe.getItems().clear();
                return;
            }

            // Tính tổng doanh thu
            double totalRevenue = results.stream()
                    .mapToDouble(row -> ((Number) row[2]).doubleValue())
                    .sum();

            // Cập nhật biểu đồ và bảng
            updateChartsWithData(results, totalRevenue);
            updateTableWithData(results, totalRevenue);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi",
                    "Đã xảy ra lỗi khi thống kê dữ liệu: " + e.getMessage());
        }
    }

    private void checkDatabaseData() {
        try {
            List<Object[]> distribution = hoaDonDao.getLoaiHangDistribution();
            System.out.println("Phân bố loại hàng trong database:");
            distribution.forEach(row -> {
                System.out.println("Loại hàng: " + row[0] + ", Số lượng: " + row[1]);
            });

            List<Object[]> sales = hoaDonDao.getSalesDistribution();
            System.out.println("Phân bố doanh số theo loại hàng:");
            sales.forEach(row -> {
                System.out.println("Loại hàng: " + row[0] + ", Số lượng bán: " + row[1]);
            });
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi",
                    "Đã xảy ra lỗi khi kiểm tra dữ liệu: " + e.getMessage());
        }
    }

    private void updateChartsWithData(List<Object[]> results, double totalRevenue) {
        XYChart.Series<String, Number> barSeries = new XYChart.Series<>();
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();

        for (Object[] result : results) {
            String label = (String) result[0]; // tenSP
            Long soLuongBan = ((Number) result[1]).longValue();
            Double doanhThu = ((Number) result[2]).doubleValue();

            // Thêm vào biểu đồ cột
            barSeries.getData().add(new XYChart.Data<>(label, soLuongBan));

            // Thêm vào biểu đồ tròn nếu có doanh thu
            if (doanhThu > 0) {
                double percentage = (doanhThu / totalRevenue) * 100;
                String displayName = String.format("%s (%.1f%%)", label, percentage);
                pieData.add(new PieChart.Data(displayName, doanhThu));
            }
        }

        // Cập nhật biểu đồ
        barChart.getData().clear();
        barChart.getData().add(barSeries);

        pieChart.getData().clear();
        if (!pieData.isEmpty()) {
            pieChart.setData(pieData);
        }

        // Thêm tooltip cho biểu đồ cột
        barSeries.getData().forEach(data -> {
            Node node = data.getNode();
            Tooltip tooltip = new Tooltip(String.format(
                "%s\nSố lượng: %d",
                data.getXValue(),
                data.getYValue().intValue()
            ));
            Tooltip.install(node, tooltip);
        });

        // Thêm tooltip cho biểu đồ tròn
        pieChart.getData().forEach(data -> {
            Tooltip tooltip = new Tooltip(String.format(
                "%s\nDoanh thu: %.2f VNĐ",
                data.getName(),
                data.getPieValue()
            ));
            Tooltip.install(data.getNode(), tooltip);
        });
    }

    private void updateTableWithData(List<Object[]> results, double totalRevenue) {
        // Xóa dữ liệu cũ trong bảng
        tbThongKe.getItems().clear();

        // Tạo danh sách dữ liệu mới
        ObservableList<ThongKeSanPhamModel> tableData = FXCollections.observableArrayList();

        for (Object[] result : results) {
            String tenSP = (String) result[0];
            Long soLuongBan = ((Number) result[1]).longValue();
            Double doanhThu = ((Number) result[2]).doubleValue();
            Double tyLe = (doanhThu / totalRevenue) * 100;

            // Tạo mã sản phẩm từ tên sản phẩm (giải pháp tạm thời)
            String maSP = generateMaSPFromTenSP(tenSP);

            // Thêm vào danh sách dữ liệu
            tableData.add(new ThongKeSanPhamModel(maSP, tenSP, soLuongBan, doanhThu, tyLe));
        }

        // Cập nhật bảng
        tbThongKe.setItems(tableData);
    }

    /**
     * Tạo mã sản phẩm từ tên sản phẩm (giải pháp tạm thời)
     * Trong thực tế, mã sản phẩm nên được lấy từ database
     */
    private String generateMaSPFromTenSP(String tenSP) {
        if (tenSP == null || tenSP.isEmpty()) {
            return "SP000";
        }

        // Lấy các chữ cái đầu của từng từ trong tên sản phẩm
        StringBuilder maSP = new StringBuilder("SP");
        String[] words = tenSP.split("\\s+");

        for (String word : words) {
            if (!word.isEmpty()) {
                maSP.append(word.charAt(0));
            }
        }

        // Thêm số ngẫu nhiên để đảm bảo tính duy nhất
        int randomNum = (int) (Math.random() * 1000);
        maSP.append(String.format("%03d", randomNum));

        return maSP.toString().toUpperCase();
    }

    // Thêm method để kiểm tra dữ liệu trong database
//    private void checkDatabaseData() {
//        try {
//            // Kiểm tra phân bố loại hàng
//            String checkQuery = "SELECT s.loaiHang, COUNT(s) FROM SanPham s GROUP BY s.loaiHang";
//            List<Object[]> distribution = em.createQuery(checkQuery).getResultList();
//
//            System.out.println("Phân bố loại hàng trong database:");
//            distribution.forEach(row -> {
//                System.out.println("Loại hàng: " + row[0] + ", Số lượng: " + row[1]);
//            });
//
//            // Kiểm tra chi tiết hóa đơn
//            String checkSalesQuery = "SELECT s.loaiHang, COUNT(ct) " +
//                                   "FROM SanPham s " +
//                                   "LEFT JOIN s.chiTietHoaDonSanPhams ct " +
//                                   "GROUP BY s.loaiHang";
//            List<Object[]> sales = em.createQuery(checkSalesQuery).getResultList();
//
//            System.out.println("\nPhân bố chi tiết hóa đơn theo loại hàng:");
//            sales.forEach(row -> {
//                System.out.println("Loại hàng: " + row[0] + ", Số lượng hóa đơn: " + row[1]);
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    private void updateChartsAndTable(List<Object[]> results) {
        // Khởi tạo series cho biểu đồ cột
        XYChart.Series<String, Number> barSeries = new XYChart.Series<>();
        barSeries.setName("Số lượng bán");

        // Khởi tạo danh sách dữ liệu cho biểu đồ tròn
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();

        // Tính tổng doanh thu để tính phần trăm
        double totalRevenue = results.stream()
                .mapToDouble(result -> ((Number) result[2]).doubleValue())
                .sum();

        for (Object[] result : results) {
            String label = (String) result[0]; // tenSP
            Long soLuongBan = ((Number) result[1]).longValue();
            Double doanhThu = ((Number) result[2]).doubleValue();

            // Thêm vào biểu đồ cột
            barSeries.getData().add(new XYChart.Data<>(label, soLuongBan));

            // Thêm vào biểu đồ tròn nếu có doanh thu
            if (doanhThu > 0) {
                double percentage = (doanhThu / totalRevenue) * 100;
                String displayName = String.format("%s (%.1f%%)", label, percentage);
                pieData.add(new PieChart.Data(displayName, doanhThu));
            }
        }

        // Cập nhật biểu đồ
        barChart.getData().clear();
        barChart.getData().add(barSeries);

        pieChart.getData().clear();
        if (!pieData.isEmpty()) {
            pieChart.setData(pieData);
        }

        // Thêm tooltip cho biểu đồ cột
        barSeries.getData().forEach(data -> {
            Node node = data.getNode();
            Tooltip tooltip = new Tooltip(String.format(
                "%s\nSố lượng: %d",
                data.getXValue(),
                data.getYValue().intValue()
            ));
            Tooltip.install(node, tooltip);
        });

        // Thêm tooltip cho biểu đồ tròn
        pieData.forEach(data -> {
            Tooltip tooltip = new Tooltip(String.format(
                "%s\nDoanh thu: %.2f VNĐ",
                data.getName(),
                data.getPieValue()
            ));
            Tooltip.install(data.getNode(), tooltip);
        });
    }

    // Thêm phương thức loadFXML để xử lý việc chuyển đổi giao diện
    private void loadFXML(String fxmlPath) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) vBox.getScene().getWindow(); // Sử dụng vBox hoặc bất kỳ control nào đang có trong scene
        stage.setScene(scene);
        stage.show();
    }
}