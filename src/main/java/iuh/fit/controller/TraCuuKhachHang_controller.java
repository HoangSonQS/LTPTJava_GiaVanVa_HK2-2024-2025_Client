package iuh.fit.controller;

import iuh.fit.App;
import iuh.fit.daos.KhachHang_dao;
import iuh.fit.entities.KhachHang;
import iuh.fit.entities.KhachHang;
import iuh.fit.enums.LoaiHang;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

        String maKhachHang = txt_maKH.getText();
        App.maTraCuu = maKhachHang;
        KhachHang sp = new KhachHang_dao().read(maKhachHang);
        lb_maKH.setText(sp.getMaKH());
        lb_tenKH.setText(sp.getTenKH());
        lb_sdt.setText(sp.getSdt());
        highlightMatchingRow(maKhachHang);
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
        ccb_GiaoDien.setValue("Sản phẩm");
        setupComboBoxHandler();
    }

    private void setupComboBoxHandler() {
        ccb_GiaoDien.setOnAction(event -> {
            String selectedValue = ccb_GiaoDien.getValue();
            switch (selectedValue) {
                case "Sản phẩm":
                    // Giữ nguyên giao diện hiện tại
                    break;
                case "Tài khoản":
                    try {
                        App.setRoot("TraCuuTaiKhoan_gui");
                    } catch (IOException e) {
                        showError("Lỗi chuyển giao diện", "Không thể mở giao diện Tra cứu tài khoản");
                    }
                    break;
                case "Hoá đơn":
                    try {
                        App.setRoot("TraCuuHoaDon_gui");
                    } catch (IOException e) {
                        showError("Lỗi chuyển giao diện", "Không thể mở giao diện Tra cứu hóa đơn");
                    }
                    break;
                case "Phiếu nhập":
                    try {
                        App.setRoot("TraCuuPhieuNhap_gui");
                    } catch (IOException e) {
                        showError("Lỗi chuyển giao diện", "Không thể mở giao diện Tra cứu phiếu nhập");
                    }
                    break;
                case "Nhân viên":
                    try {
                        App.setRoot("TraCuuNhanVien_gui");
                    } catch (IOException e) {
                        showError("Lỗi chuyển giao diện", "Không thể mở giao diện Tra cứu nhân viên");
                    }
                    break;
                case "Khách hàng":
                    try {
                        App.setRoot("TraCuuKhachHang_gui");
                    } catch (IOException e) {
                        showError("Lỗi chuyển giao diện", "Không thể mở giao diện Tra cứu khách hàng");
                    }
                    break;
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
            KhachHang_dao KhachHangDao = new KhachHang_dao();
            List<KhachHang> dssp = KhachHangDao.readAll();
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
    @FXML
    void toQLHoaDon(MouseEvent event) {

    }

    @FXML
    void toQLKhachHang(MouseEvent event) {

    }

    @FXML
    void toQLNhanVien(MouseEvent event) {

    }

    @FXML
    void toQLPhieuNhap(MouseEvent event) {

    }

    @FXML
    void toQLSanPham(MouseEvent event) {

    }

    @FXML
    void toQLTaiKhoan(MouseEvent event) {

    }

    @FXML
    void toTKDoanhThu(MouseEvent event) {

    }

    @FXML
    void toTKKhachHang(MouseEvent event) {

    }
    

}
