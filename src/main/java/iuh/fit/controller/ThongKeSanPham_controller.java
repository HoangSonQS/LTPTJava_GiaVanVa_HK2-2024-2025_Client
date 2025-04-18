package iuh.fit.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import iuh.fit.enums.LoaiHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import javafx.collections.FXCollections;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.util.Duration;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import java.util.Arrays;

public class ThongKeSanPham_controller implements Initializable {

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
    private TableView<?> tbThongKe;

    @FXML
    private TableColumn<?, ?> tcMaSP;

    @FXML
    private TableColumn<?, ?> tcTenSP;

    @FXML
    private TableColumn<?, ?> tcSoLuongBan;

    @FXML
    private TableColumn<?, ?> tcDoanhThu;

    @FXML
    private TableColumn<?, ?> tcTyLe;

    @FXML
    private ComboBox<String> cbLoaiHang;

    private EntityManager em;

    Map<VBox, VBox> map = new HashMap<VBox, VBox>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
        addMenusToMap();
        setupCharts();

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
        checkDatabaseData();
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

    // Các phương thức mới cho thống kê sản phẩm
    private void setupCharts() {
        // Thiết lập biểu đồ tròn
        pieChart.setTitle("Tỷ lệ bán hàng theo sản phẩm");
        pieChart.setAnimated(true);

        // Thiết lập biểu đồ cột
        barChart.setTitle("Số lượng bán theo sản phẩm");
        barChart.setAnimated(true);
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

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT s.tenSP, ")
                    .append("COALESCE(SUM(ct.soLuongSP), 0) as soLuongBan, ")
                    .append("COALESCE(SUM(ct.soLuongSP * ct.donGia), 0) as doanhThu ")
                    .append("FROM SanPham s ")
                    .append("LEFT JOIN s.chiTietHoaDonSanPhams ct ")
                    .append("LEFT JOIN ct.hoaDon h ")
                    .append("WHERE 1=1 ");

            // Chỉ lọc theo năm nếu không phải "TẤT CẢ"
            if (!"TẤT CẢ".equals(nam)) {
                queryBuilder.append("AND YEAR(h.thoiGian) = :nam ");

                // Thêm điều kiện thời gian dựa trên loại thống kê
                // Nhưng không giới hạn chặt chẽ vào ngày/tháng/quý hiện tại
                if ("Theo ngày".equals(loaiThongKe)) {
                    // Lấy dữ liệu của ngày cuối cùng có trong hệ thống (thay vì ngày hiện tại)
                    queryBuilder.append("AND h.thoiGian IS NOT NULL ");
                } else if ("Theo tháng".equals(loaiThongKe)) {
                    // Lấy dữ liệu của tất cả các tháng trong năm đã chọn
                    // Không cần thêm điều kiện lọc tháng
                } else if ("Theo quý".equals(loaiThongKe)) {
                    // Lấy dữ liệu của quý hiện tại, nhưng có thể mở rộng nếu không có dữ liệu
                    queryBuilder.append("AND QUARTER(h.thoiGian) <= QUARTER(CURRENT_DATE) ");
                }
            }

            // Chỉ lọc theo loại hàng nếu không phải "TẤT CẢ"
            if (!"TẤT CẢ".equals(loaiHangDisplay)) {
                queryBuilder.append("AND s.loaiHang = :loaiHang ");
            }

            // Chỉ lấy các sản phẩm có trong hóa đơn (có bán được)
            queryBuilder.append("AND ct.soLuongSP IS NOT NULL ");
            queryBuilder.append("GROUP BY s.tenSP ");
            queryBuilder.append("ORDER BY soLuongBan DESC"); // Sắp xếp theo số lượng bán giảm dần

            // Debug log
            System.out.println("Final query: " + queryBuilder.toString());

            Query query = em.createQuery(queryBuilder.toString());

            // Set parameters
            if (!"TẤT CẢ".equals(nam)) {
                query.setParameter("nam", Integer.parseInt(nam));
            }

            if (!"TẤT CẢ".equals(loaiHangDisplay)) {
                LoaiHang loaiHangEnum = Arrays.stream(LoaiHang.values())
                        .filter(lh -> lh.getLoaiHang().equals(loaiHangDisplay))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Invalid loai hang: " + loaiHangDisplay));

                query.setParameter("loaiHang", loaiHangEnum);
                System.out.println("Setting loaiHang parameter: " + loaiHangEnum); // Debug log
            }

            // Execute query
            List<Object[]> results = query.getResultList();

            // Debug log
            System.out.println("Query results size: " + results.size());
            results.forEach(row -> {
                System.out.println("Row data: " + Arrays.toString(row));
            });

            if (results.isEmpty()) {
                showAlert(Alert.AlertType.INFORMATION, "Thông báo",
                        "Không có dữ liệu thống kê cho thời gian này!");
                return;
            }

            // Process and display results
            updateChartsAndTable(results);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi",
                    "Chi tiết lỗi: " + e.getMessage());
        }
    }

    // Thêm method để kiểm tra dữ liệu trong database
    private void checkDatabaseData() {
        try {
            // Kiểm tra phân bố loại hàng
            String checkQuery = "SELECT s.loaiHang, COUNT(s) FROM SanPham s GROUP BY s.loaiHang";
            List<Object[]> distribution = em.createQuery(checkQuery).getResultList();
            
            System.out.println("Phân bố loại hàng trong database:");
            distribution.forEach(row -> {
                System.out.println("Loại hàng: " + row[0] + ", Số lượng: " + row[1]);
            });

            // Kiểm tra chi tiết hóa đơn
            String checkSalesQuery = "SELECT s.loaiHang, COUNT(ct) " +
                                   "FROM SanPham s " +
                                   "LEFT JOIN s.chiTietHoaDonSanPhams ct " +
                                   "GROUP BY s.loaiHang";
            List<Object[]> sales = em.createQuery(checkSalesQuery).getResultList();
            
            System.out.println("\nPhân bố chi tiết hóa đơn theo loại hàng:");
            sales.forEach(row -> {
                System.out.println("Loại hàng: " + row[0] + ", Số lượng hóa đơn: " + row[1]);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method for showing alerts
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

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
}
