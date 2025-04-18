package iuh.fit.controller;

import iuh.fit.App;
import iuh.fit.daos.PhieuNhapHang_dao;
import iuh.fit.entities.PhieuNhapHang;
import iuh.fit.entities.SanPham;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class TraCuuPhieuNhap_controller implements Initializable{

    @FXML
    private VBox banHangSubMenuList;

    @FXML
    private VBox banHangSubVBox;

    @FXML
    private Button btn_TraCuu;

    @FXML
    private Button btn_qlPhieuNhap;

    @FXML
    private ComboBox<String> ccb_GiaoDien;

    @FXML
    private TableColumn<PhieuNhapHang, String> cl_maNV;

    @FXML
    private TableColumn<PhieuNhapHang, String> cl_maPN;

    @FXML
    private TableColumn<PhieuNhapHang, String> cl_stt;

    @FXML
    private TableColumn<PhieuNhapHang, String> cl_tenNV;

    @FXML
    private TableColumn<PhieuNhapHang, String> cl_thanhTien;

    @FXML
    private TableColumn<PhieuNhapHang, String> cl_thoiGian;

    @FXML
    private TableColumn<PhieuNhapHang, String> cl_tslsp;

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
    private Label lb_maNV;

    @FXML
    private Label lb_maPN;

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
    private Label lb_tenNV;

    @FXML
    private Label lb_thanhTien;

    @FXML
    private Label lb_thoiGian;

    @FXML
    private Label lb_thongKe;

    @FXML
    private Label lb_thongKeDoanhThu;

    @FXML
    private Label lb_thongKeSanPham;

    @FXML
    private Label lb_timKiem;

    @FXML
    private Label lb_tslsp;

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
    private TextField txt_maPN;

    @FXML
    private TableView<PhieuNhapHang> tablePhieuNhap;

    @FXML
    private VBox vBox;

    Map<VBox,VBox> map = new HashMap<VBox,VBox>();
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
        // Hiển thị menu bán hàng
        toolsSlider(banHangSubVBox, banHangSubMenuList);
        removeOtherMenus(banHangSubVBox);
    }

    @FXML
    void handleQuanLyClick(MouseEvent event) {
        toolsSlider(quanLySubVBox,quanLySubMenuList);
        removeOtherMenus(quanLySubVBox);
    }

    @FXML
    void handleThongKeClick(MouseEvent event) {
        toolsSlider(thongKeSubVBox,thongKeSubMenuList);
        removeOtherMenus(thongKeSubVBox);
    }

    @FXML
    void handleTimKiemClick(MouseEvent event) {
        // Hiển thị menu tìm kiếm
        toolsSlider(timKiemSubVBox, timKiemSubMenuList);
        removeOtherMenus(timKiemSubVBox);
    }

    @FXML
    void timKiem(MouseEvent event) {

        String maPhieuNhap = txt_maPN.getText();
        App.maTraCuu = maPhieuNhap;
        PhieuNhapHang pn = new PhieuNhapHang_dao().read(maPhieuNhap);
        lb_maPN.setText(pn.getMaPNH());
        lb_maNV.setText(pn.getMaNV());
        lb_tenNV.setText(pn.getTenNV());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        lb_thoiGian.setText(pn.getThoiGian().format(formatter));
        lb_tslsp.setText(String.valueOf(pn.getTongSoLuongSP()));
        lb_thanhTien.setText(String.valueOf(pn.getThanhTien()));
        highlightMatchingRow(maPhieuNhap);
    }

    private void highlightMatchingRow(String maPhieuNhap) {
        if (maPhieuNhap == null || maPhieuNhap.isEmpty()) {
            return;
        }

        for (int i = 0; i < tablePhieuNhap.getItems().size(); i++) {
            PhieuNhapHang phieuNhapHang = tablePhieuNhap.getItems().get(i);
            if (phieuNhapHang.getMaPNH().equals(maPhieuNhap)) {  // Sửa lại điều kiện so sánh
                // Select the row
                tablePhieuNhap.getSelectionModel().select(i);
                // Scroll to the row
                tablePhieuNhap.scrollTo(i);
                // Request focus
                tablePhieuNhap.requestFocus();
                break;
            }
        }
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
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
        ccb_GiaoDien.setItems(list);
        ccb_GiaoDien.setValue("Phiếu nhập");
        setupComboBoxHandler();
    }

    private void setupComboBoxHandler() {
        ccb_GiaoDien.setOnAction(event -> {
            String selectedValue = ccb_GiaoDien.getValue();
            if (selectedValue.equals("Phiếu nhập")) {
                return;
            }
            
            try {
                String fxmlFile = switch (selectedValue) {
                    case "Sản phẩm" -> "TraCuu_gui";
                    case "Tài khoản" -> "TraCuuTaiKhoan_gui";
                    case "Hoá đơn" -> "TraCuuHoaDon_gui";
                    case "Nhân viên" -> "TraCuuNhanVien_gui";
                    case "Khách hàng" -> "TraCuuKhachHang_gui";
                    default -> throw new IllegalArgumentException("Unexpected value: " + selectedValue);
                };
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlFile + ".fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) ccb_GiaoDien.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                
            } catch (Exception e) {
                showError("Lỗi chuyển giao diện", "Không thể mở giao diện Tra cứu " + selectedValue.toLowerCase());
                ccb_GiaoDien.setValue("Phiếu nhập");
            }
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
        cl_stt.setCellFactory(col -> new TableCell<PhieuNhapHang, String>() {
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
        cl_maPN.setCellValueFactory(new PropertyValueFactory<>("maPNH"));
        cl_maNV.setCellValueFactory(new PropertyValueFactory<>("maNV"));
        cl_tenNV.setCellValueFactory(new PropertyValueFactory<>("tenNV"));
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        cl_thoiGian.setCellValueFactory(cellData -> {
            LocalDateTime date = cellData.getValue().getThoiGian();
            if (date == null) return new SimpleStringProperty("");
            return new SimpleStringProperty(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        });
        cl_tslsp.setCellValueFactory(new PropertyValueFactory<>("tongSoLuongSP"));
        cl_thanhTien.setCellValueFactory(new PropertyValueFactory<>("thanhTien"));
    }

    private void loadTableData() {
        try {
            PhieuNhapHang_dao phieuNhapHangDao = new PhieuNhapHang_dao();
            List<PhieuNhapHang> dspn = phieuNhapHangDao.readAll();
            ObservableList<PhieuNhapHang> data = FXCollections.observableArrayList(dspn);
            tablePhieuNhap.setItems(data);
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
        tablePhieuNhap.setOnMouseClicked(event -> {
            PhieuNhapHang selectedPhieuNhap = tablePhieuNhap.getSelectionModel().getSelectedItem();
            if (selectedPhieuNhap != null) {
                updateLabels(selectedPhieuNhap);
            }
        });
    }

    private void updateLabels(PhieuNhapHang pn) {
        lb_maPN.setText(pn.getMaPNH());
        lb_maNV.setText(pn.getMaNV());
        lb_tenNV.setText(pn.getTenNV());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        lb_thoiGian.setText(pn.getThoiGian().format(formatter));
        lb_tslsp.setText(String.valueOf(pn.getTongSoLuongSP()));
        lb_thanhTien.setText(String.valueOf(pn.getThanhTien()));
    }
    @FXML
    void toQLHoaDon(MouseEvent event) {
        try {
            App.setRoot("QuanLyHoaDon_gui");
        } catch (IOException e) {
            showError("Lỗi chuyển giao diện", "Không thể mở giao diện Quản lý hóa đơn");
        }
    }

    @FXML
    void toQLKhachHang(MouseEvent event) {
        try {
            App.setRoot("QuanLyKhachHang_gui");
        } catch (IOException e) {
            showError("Lỗi chuyển giao diện", "Không thể mở giao diện Quản lý khách hàng");
        }
    }

    @FXML
    void toQLNhanVien(MouseEvent event) {
        try {
            App.setRoot("QuanLyNhanVien_gui");
        } catch (IOException e) {
            showError("Lỗi chuyển giao diện", "Không thể mở giao diện Quản lý nhân viên");
        }
    }

    @FXML
    void toQLPhieuNhap(MouseEvent event) {
        try {
            App.setRoot("QuanLyPhieuNhap_gui");
        } catch (IOException e) {
            showError("Lỗi chuyển giao diện", "Không thể mở giao diện Quản lý phiếu nhập");
        }
    }

    @FXML
    void toQLSanPham(MouseEvent event) {
        try {
            App.setRoot("QuanLySanPham_gui");
        } catch (IOException e) {
            showError("Lỗi chuyển giao diện", "Không thể mở giao diện Quản lý sản phẩm");
        }
    }

    @FXML
    void toQLTaiKhoan(MouseEvent event) {
        try {
            App.setRoot("QuanLyTaiKhoan_gui");
        } catch (IOException e) {
            showError("Lỗi chuyển giao diện", "Không thể mở giao diện Quản lý tài khoản");
        }
    }

    @FXML
    void toTKDoanhThu(MouseEvent event) {
        try {
            App.setRoot("ThongKeDoanhThu_gui");
        } catch (IOException e) {
            showError("Lỗi chuyển giao diện", "Không thể mở giao diện Thống kê doanh thu");
        }
    }

    @FXML
    void toTKSanPham(MouseEvent event) {
        try {
            App.setRoot("ThongKeSanPham_gui");
        } catch (IOException e) {
            showError("Lỗi chuyển giao diện", "Không thể mở giao diện Thống kê sản phẩm");
        }
    }
}
