package iuh.fit.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import javafx.collections.FXCollections;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ThongKeDoanhThu_controller implements Initializable {

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
    private TableView<?> tbThongKe;

    @FXML
    private TableColumn<?, ?> tcThoiGian;

    @FXML
    private TableColumn<?, ?> tcDoanhThu;

    @FXML
    private TableColumn<?, ?> tcSoLuongHoaDon;

    @FXML
    private TableColumn<?, ?> tcSoLuongSanPham;

    @FXML
    private TableColumn<?, ?> tcTyLeTangTruong;

    Map<VBox, VBox> map = new HashMap<VBox, VBox>();

    private EntityManager em;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addMenusToMap();
        setupCharts();
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
        
        cbLoaiThongKe.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                updateChart(newVal);
            }
        });
        
        // Mặc định chọn thống kê theo tháng
        cbLoaiThongKe.getSelectionModel().select("Theo tháng");
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
        map.put(thongKeSubVBox, thongKeSubMenuList);

        /**
         * Remove the components from VBox on load of stage
         */
        for (Map.Entry<VBox, VBox> entry : map.entrySet()) {
            entry.getKey().getChildren().remove(entry.getValue());
        }
    }

    /**
     * Menu slider
     *
     * @param menu
     * @param subMenu
     */
    public void toolsSlider(VBox menu, VBox subMenu) {
        toolsSliderImpl(menu, subMenu);
    }

    private void toolsSliderImpl(VBox menu, VBox subMenu) {
        if (menu.getChildren().contains(subMenu)) {
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

    /**
     * Remove other menus
     *
     * @param menu
     */
    public void removeOtherMenus(VBox menu) {
        removeOtherMenusImpl(menu);
    }

    private void removeOtherMenusImpl(VBox menu) {
        for (Map.Entry<VBox, VBox> entry : map.entrySet()) {
            if (!entry.getKey().equals(menu))
                entry.getKey().getChildren().remove(entry.getValue());
        }
    }

    @FXML
    void handleGioHangClick(MouseEvent event) {
        // Xử lý sự kiện click vào giỏ hàng
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
    void handleTimKiemClick(MouseEvent event) {
        // Xử lý sự kiện click vào tìm kiếm
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
        String loaiThongKe = cbLoaiThongKe.getValue();
        String namString = cbNam.getValue();
        
        if (loaiThongKe == null || namString == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cảnh báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn loại thống kê và năm!");
            alert.showAndWait();
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
    }

    private void thongKeTheoThangTrongNam(XYChart.Series<String, Number> series, int nam) {
        String query = "SELECT MONTH(h.thoiGian) as thang, SUM(h.thanhTien) as doanhThu " +
                      "FROM HoaDon h " +
                      "WHERE YEAR(h.thoiGian) = :nam " +
                      "GROUP BY MONTH(h.thoiGian) " +
                      "ORDER BY MONTH(h.thoiGian)";

        TypedQuery<Object[]> typedQuery = em.createQuery(query, Object[].class);
        typedQuery.setParameter("nam", nam);
        List<Object[]> results = typedQuery.getResultList();

        // Khởi tạo dữ liệu cho 12 tháng
        Map<Integer, Double> doanhThuMap = new HashMap<>();
        for (int i = 1; i <= 12; i++) {
            doanhThuMap.put(i, 0.0);
        }

        // Cập nhật dữ liệu từ kết quả query
        for (Object[] result : results) {
            Integer thang = (Integer) result[0];
            Double doanhThu = ((Number) result[1]).doubleValue();
            doanhThuMap.put(thang, doanhThu);
        }

        // Thêm dữ liệu vào series
        for (int i = 1; i <= 12; i++) {
            series.getData().add(new XYChart.Data<>("Tháng " + i, doanhThuMap.get(i)));
        }
        
        series.setName("Doanh thu năm " + nam);
    }

    private void thongKeTheoQuyTrongNam(XYChart.Series<String, Number> series, int nam) {
        String query = "SELECT QUARTER(h.thoiGian) as quy, SUM(h.thanhTien) as doanhThu " +
                      "FROM HoaDon h " +
                      "WHERE YEAR(h.thoiGian) = :nam " +
                      "GROUP BY QUARTER(h.thoiGian) " +
                      "ORDER BY QUARTER(h.thoiGian)";

        TypedQuery<Object[]> typedQuery = em.createQuery(query, Object[].class);
        typedQuery.setParameter("nam", nam);
        List<Object[]> results = typedQuery.getResultList();

        // Khởi tạo dữ liệu cho 4 quý
        Map<Integer, Double> doanhThuMap = new HashMap<>();
        for (int i = 1; i <= 4; i++) {
            doanhThuMap.put(i, 0.0);
        }

        // Cập nhật dữ liệu từ kết quả query
        for (Object[] result : results) {
            Integer quy = (Integer) result[0];
            Double doanhThu = ((Number) result[1]).doubleValue();
            doanhThuMap.put(quy, doanhThu);
        }

        // Thêm dữ liệu vào series
        for (int i = 1; i <= 4; i++) {
            series.getData().add(new XYChart.Data<>("Quý " + i, doanhThuMap.get(i)));
        }
        
        series.setName("Doanh thu năm " + nam);
    }

    private void thongKeTheoNgay(XYChart.Series<String, Number> series) {
        // Lấy 30 ngày gần nhất
        LocalDateTime endDate = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
        LocalDateTime startDate = endDate.minusDays(29).withHour(0).withMinute(0).withSecond(0);

        String query = "SELECT DATE(h.thoiGian) as ngay, COUNT(h.maHD) as soHoaDon, " +
                      "SUM(h.thanhTien) as doanhThu " +
                      "FROM HoaDon h " +
                      "WHERE h.thoiGian BETWEEN :startDate AND :endDate " +
                      "GROUP BY DATE(h.thoiGian) " +
                      "ORDER BY DATE(h.thoiGian)";

        try {
            TypedQuery<Object[]> typedQuery = em.createQuery(query, Object[].class);
            typedQuery.setParameter("startDate", startDate);
            typedQuery.setParameter("endDate", endDate);
            
            List<Object[]> results = typedQuery.getResultList();
            
            // Khởi tạo map cho 30 ngày
            Map<LocalDate, Double> doanhThuMap = new HashMap<>();
            LocalDate currentDate = startDate.toLocalDate();
            while (!currentDate.isAfter(endDate.toLocalDate())) {
                doanhThuMap.put(currentDate, 0.0);
                currentDate = currentDate.plusDays(1);
            }
            
            // Cập nhật dữ liệu từ kết quả query
            for (Object[] result : results) {
                if (result[0] instanceof java.sql.Date) {
                    java.sql.Date sqlDate = (java.sql.Date) result[0];
                    LocalDate ngay = sqlDate.toLocalDate();
                    Double doanhThu = (result[2] != null) ? ((Number) result[2]).doubleValue() : 0.0;
                    doanhThuMap.put(ngay, doanhThu);
                }
            }
            
            // Thêm dữ liệu vào series theo thứ tự ngày
            currentDate = startDate.toLocalDate();
            while (!currentDate.isAfter(endDate.toLocalDate())) {
                Double doanhThu = doanhThuMap.getOrDefault(currentDate, 0.0);
                String ngayStr = currentDate.format(DateTimeFormatter.ofPattern("dd/MM"));
                series.getData().add(new XYChart.Data<>(ngayStr, doanhThu));
                currentDate = currentDate.plusDays(1);
            }
            
            series.setName("Doanh thu 30 ngày gần nhất");
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Lỗi khi thống kê theo ngày: " + e.getMessage());
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Có lỗi xảy ra khi thống kê theo ngày!");
            alert.showAndWait();
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

    @FXML
    void toTKDoanhThu(MouseEvent event) {
        try {
            System.out.println("Attempting to load ThongKeDoanhThu_gui.fxml");
            URL resource = getClass().getResource("/fxml/ThongKeDoanhThu_gui.fxml");
            if (resource == null) {
                System.err.println("FXML file not found!");
                return;
            }
            System.out.println("FXML file found at: " + resource.toString());
            loadFXML("/fxml/ThongKeDoanhThu_gui.fxml");
        } catch (Exception e) {
            System.err.println("Error loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void updateChart(String loaiThongKe) {
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
    }
}
