package iuh.fit.controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import iuh.fit.App;
import iuh.fit.interfaces.HoaDon_interface;
import iuh.fit.entities.NhanVien;
import iuh.fit.entities.TaiKhoan;
import iuh.fit.security.Permission;
import iuh.fit.security.PermissionChecker;
import iuh.fit.security.SecurityContext;
import jakarta.persistence.EntityManager;
import javafx.collections.FXCollections;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Tooltip;

public class ThongKeDoanhThu_controller implements Initializable {

    public static class ThongKeDoanhThuModel {
        private final StringProperty thoiGian;
        private final DoubleProperty doanhThu;
        private final IntegerProperty soLuongHoaDon;
        private final IntegerProperty soLuongSanPham;
        private final DoubleProperty tyLeTangTruong;

        public ThongKeDoanhThuModel(String thoiGian, Double doanhThu, Integer soLuongHoaDon, Integer soLuongSanPham, Double tyLeTangTruong) {
            this.thoiGian = new SimpleStringProperty(thoiGian);
            this.doanhThu = new SimpleDoubleProperty(doanhThu);
            this.soLuongHoaDon = new SimpleIntegerProperty(soLuongHoaDon);
            this.soLuongSanPham = new SimpleIntegerProperty(soLuongSanPham);
            this.tyLeTangTruong = new SimpleDoubleProperty(tyLeTangTruong);
        }

        public String getThoiGian() {
            return thoiGian.get();
        }

        public StringProperty thoiGianProperty() {
            return thoiGian;
        }

        public void setThoiGian(String thoiGian) {
            this.thoiGian.set(thoiGian);
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

        public Integer getSoLuongHoaDon() {
            return soLuongHoaDon.get();
        }

        public IntegerProperty soLuongHoaDonProperty() {
            return soLuongHoaDon;
        }

        public void setSoLuongHoaDon(Integer soLuongHoaDon) {
            this.soLuongHoaDon.set(soLuongHoaDon);
        }

        public Integer getSoLuongSanPham() {
            return soLuongSanPham.get();
        }

        public IntegerProperty soLuongSanPhamProperty() {
            return soLuongSanPham;
        }

        public void setSoLuongSanPham(Integer soLuongSanPham) {
            this.soLuongSanPham.set(soLuongSanPham);
        }

        public Double getTyLeTangTruong() {
            return tyLeTangTruong.get();
        }

        public DoubleProperty tyLeTangTruongProperty() {
            return tyLeTangTruong;
        }

        public void setTyLeTangTruong(Double tyLeTangTruong) {
            this.tyLeTangTruong.set(tyLeTangTruong);
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

    // Các thành phần mới cho thống kê doanh thu
    @FXML
    private ComboBox<String> cbLoaiThongKe;

    @FXML
    private ComboBox<String> cbNam;

    @FXML
    private Button btnXemThongKe;

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private TableView<ThongKeDoanhThuModel> tbThongKe;

    @FXML
    private TableColumn<ThongKeDoanhThuModel, String> tcThoiGian;

    @FXML
    private TableColumn<ThongKeDoanhThuModel, Number> tcDoanhThu;

    @FXML
    private TableColumn<ThongKeDoanhThuModel, Number> tcSoLuongHoaDon;

    @FXML
    private TableColumn<ThongKeDoanhThuModel, Number> tcSoLuongSanPham;

    @FXML
    private TableColumn<ThongKeDoanhThuModel, Number> tcTyLeTangTruong;

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
        // Check permission before proceeding
        PermissionChecker.checkPermissionAndExecute(Permission.BAN_HANG, () -> {
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
        });
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

    private EntityManager em;

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
        initializeNhanVien();
        addMenusToMap();
        setupCharts();
        setupTable();

        cbLoaiThongKe.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                try {
                    updateChart(newVal);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        cbNam.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                try {
                    String loaiThongKe = cbLoaiThongKe.getValue();
                    if (loaiThongKe != null) {
                        updateChart(loaiThongKe);
                    }
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Mặc định chọn thống kê theo tháng
        cbLoaiThongKe.getSelectionModel().select("Theo tháng");
    }

    // Các phương thức mới cho thống kê doanh thu
    private void setupCharts() {
        // Thiết lập biểu đồ
        lineChart.setTitle("Biểu đồ doanh thu");
        lineChart.setAnimated(true);

        // Thiết lập ComboBox loại thống kê
        ObservableList<String> loaiThongKeList = FXCollections.observableArrayList(
            "Theo ngày",
            "Theo tháng",
            "Theo quý"
        );
        cbLoaiThongKe.setItems(loaiThongKeList);

        // Thêm dữ liệu vào combobox năm
        populateYearComboBox();
    }

    private void setupTable() {
        // Thiết lập các cột cho bảng thống kê
        tcThoiGian.setCellValueFactory(new PropertyValueFactory<>("thoiGian"));
        tcDoanhThu.setCellValueFactory(new PropertyValueFactory<>("doanhThu"));
        tcSoLuongHoaDon.setCellValueFactory(new PropertyValueFactory<>("soLuongHoaDon"));
        tcSoLuongSanPham.setCellValueFactory(new PropertyValueFactory<>("soLuongSanPham"));
        tcTyLeTangTruong.setCellValueFactory(new PropertyValueFactory<>("tyLeTangTruong"));

        // Format doanh thu
        tcDoanhThu.setCellFactory(column -> new javafx.scene.control.TableCell<ThongKeDoanhThuModel, Number>() {
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

        // Format số lượng hóa đơn
        tcSoLuongHoaDon.setCellFactory(column -> new javafx.scene.control.TableCell<ThongKeDoanhThuModel, Number>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%,d", item.intValue()));
                }
            }
        });

        // Format số lượng sản phẩm
        tcSoLuongSanPham.setCellFactory(column -> new javafx.scene.control.TableCell<ThongKeDoanhThuModel, Number>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%,d", item.intValue()));
                }
            }
        });

        // Format tỷ lệ tăng trưởng
        tcTyLeTangTruong.setCellFactory(column -> new javafx.scene.control.TableCell<ThongKeDoanhThuModel, Number>() {
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
    void handleXemThongKe() throws RemoteException {
        String loaiThongKe = cbLoaiThongKe.getValue();
        String namString = cbNam.getValue();

        if (loaiThongKe == null || namString == null) {
            showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng chọn loại thống kê và năm!");
            return;
        }

        int nam = Integer.parseInt(namString);
        lineChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        switch (loaiThongKe) {
            case "Theo ngày":
                thongKeTheoNgay(series);
                break;
            case "Theo tháng":
                thongKeTheoThangTrongNam(series, nam);
                break;
            case "Theo quý":
                thongKeTheoQuyTrongNam(series, nam);
                break;
        }

        lineChart.getData().add(series);

        // Thêm tooltip cho biểu đồ
        for (XYChart.Data<String, Number> data : series.getData()) {
            Node node = data.getNode();
            Tooltip tooltip = new Tooltip(String.format(
                "%s\nDoanh thu: %,.0f VNĐ",
                data.getXValue(),
                data.getYValue().doubleValue()
            ));
            Tooltip.install(node, tooltip);
        }

        // Cập nhật bảng dữ liệu
        updateTableData(loaiThongKe, nam, series);
    }

    private void thongKeTheoThangTrongNam(XYChart.Series<String, Number> series, int nam) throws RemoteException {
        Map<Integer, Double> doanhThuMap = hoaDonDao.getDoanhThuMapTheoThang(nam);

        series.setName("Doanh thu theo tháng năm " + nam);
        for (int i = 1; i <= 12; i++) {
            double doanhThu = doanhThuMap.get(i);
            series.getData().add(new XYChart.Data<>("Tháng " + i, doanhThu));
        }
    }

    private void thongKeTheoQuyTrongNam(XYChart.Series<String, Number> series, int nam) throws RemoteException {
        Map<Integer, Double> doanhThuMap = hoaDonDao.getDoanhThuMapTheoQuy(nam);

        series.setName("Doanh thu theo quý năm " + nam);
        for (int i = 1; i <= 4; i++) {
            double doanhThu = doanhThuMap.get(i);
            series.getData().add(new XYChart.Data<>("Quý " + i, doanhThu));
        }
    }

    private void thongKeTheoNgay(XYChart.Series<String, Number> series) throws RemoteException {
        LocalDateTime endDate = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
        LocalDateTime startDate = endDate.minusDays(29).withHour(0).withMinute(0).withSecond(0);

        Map<LocalDate, Double> doanhThuMap = hoaDonDao.getDoanhThuMapTheoNgay(startDate, endDate);

        series.setName("Doanh thu 30 ngày gần nhất");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");

        LocalDate currentDate = startDate.toLocalDate();
        while (!currentDate.isAfter(endDate.toLocalDate())) {
            Double doanhThu = doanhThuMap.get(currentDate);
            String ngayStr = currentDate.format(formatter);
            series.getData().add(new XYChart.Data<>(ngayStr, doanhThu));
            currentDate = currentDate.plusDays(1);
        }
    }

    private void loadFXML(String fxmlPath) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) vBox.getScene().getWindow(); // Sử dụng vBox hoặc bất kỳ control nào đang có trong scene
        stage.setScene(scene);
        stage.show();
    }

    private void updateChart(String loaiThongKe) throws RemoteException {
        String namString = cbNam.getValue();
        if (namString == null) {
            return;
        }

        int nam = Integer.parseInt(namString);
        lineChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        switch (loaiThongKe) {
            case "Theo ngày":
                thongKeTheoNgay(series);
                break;
            case "Theo tháng":
                thongKeTheoThangTrongNam(series, nam);
                break;
            case "Theo quý":
                thongKeTheoQuyTrongNam(series, nam);
                break;
        }

        lineChart.getData().add(series);

        // Thêm tooltip cho biểu đồ
        for (XYChart.Data<String, Number> data : series.getData()) {
            Node node = data.getNode();
            Tooltip tooltip = new Tooltip(String.format(
                "%s\nDoanh thu: %,.0f VNĐ",
                data.getXValue(),
                data.getYValue().doubleValue()
            ));
            Tooltip.install(node, tooltip);
        }

        // Cập nhật bảng dữ liệu
        updateTableData(loaiThongKe, nam, series);
    }

    private void updateTableData(String loaiThongKe, int nam, XYChart.Series<String, Number> series) {
        // Xóa dữ liệu cũ trong bảng
        tbThongKe.getItems().clear();

        if (series.getData().isEmpty()) {
            return;
        }

        // Tạo danh sách dữ liệu mới
        ObservableList<ThongKeDoanhThuModel> tableData = FXCollections.observableArrayList();

        // Lấy dữ liệu từ series
        List<XYChart.Data<String, Number>> chartData = series.getData();

        // Tính toán tỷ lệ tăng trưởng
        for (int i = 0; i < chartData.size(); i++) {
            XYChart.Data<String, Number> data = chartData.get(i);
            String thoiGian = data.getXValue();
            Double doanhThu = data.getYValue().doubleValue();

            // Giả lập số lượng hóa đơn và sản phẩm (trong thực tế sẽ lấy từ database)
            Integer soLuongHoaDon = (int) (doanhThu / 500000); // Giả sử mỗi hóa đơn trung bình 500,000 VNĐ
            Integer soLuongSanPham = soLuongHoaDon * 3; // Giả sử mỗi hóa đơn có trung bình 3 sản phẩm

            // Tính tỷ lệ tăng trưởng
            Double tyLeTangTruong = 0.0;
            if (i > 0) {
                Double doanhThuTruoc = chartData.get(i-1).getYValue().doubleValue();
                if (doanhThuTruoc > 0) {
                    tyLeTangTruong = ((doanhThu - doanhThuTruoc) / doanhThuTruoc) * 100;
                }
            }

            // Thêm vào danh sách dữ liệu
            tableData.add(new ThongKeDoanhThuModel(thoiGian, doanhThu, soLuongHoaDon, soLuongSanPham, tyLeTangTruong));
        }

        // Cập nhật bảng
        tbThongKe.setItems(tableData);
    }
}
