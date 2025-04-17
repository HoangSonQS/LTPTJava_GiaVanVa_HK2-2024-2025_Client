package iuh.fit.controller;

import iuh.fit.App;
import iuh.fit.daos.SanPham_dao;
import iuh.fit.entities.SanPham;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.beans.property.ReadOnlyStringWrapper;
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

        String maSanPham = txt_maSP.getText();
        App.maTraCuu = maSanPham;
        SanPham sp = new SanPham_dao().read(maSanPham);
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
            if (date == null) return new SimpleStringProperty("");
            return new SimpleStringProperty(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        });

        // Cột Hạn sử dụng
        cl_hsd.setCellValueFactory(cellData -> {
            LocalDateTime date = cellData.getValue().getHanSD();
            if (date == null) return new SimpleStringProperty("");
            return new SimpleStringProperty(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        });

        // Cột Thời gian cập nhật
        cl_tgcn.setCellValueFactory(cellData -> {
            LocalDateTime date = cellData.getValue().getThoiGianCapNhat();
            if (date == null) return new SimpleStringProperty("");
            return new SimpleStringProperty(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        });

        // Cột Loại hàng
        cl_loaiHang.setCellValueFactory(cellData -> {
            LoaiHang loaiHang = cellData.getValue().getLoaiHang();
            if (loaiHang == null) return new SimpleStringProperty("");
            return new SimpleStringProperty(loaiHang.toString());
        });
    }

    private void loadTableData() {
        try {
            SanPham_dao sanPhamDao = new SanPham_dao();
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
        lb_maSP.setText(sp.getMaSP());
        lb_tenSP.setText(sp.getTenSP());
        lb_ncc.setText(sp.getNhaCC());
        lb_slt.setText(String.valueOf(sp.getSoLuongTon()));
        lb_giaNhap.setText(String.valueOf(sp.getGiaNhap()));
        lb_giaBan.setText(String.valueOf(sp.getGiaBan()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        lb_nsx.setText(sp.getNgaySX().format(formatter));
        lb_hsd.setText(sp.getHanSD().format(formatter));
        lb_tgcn.setText(sp.getThoiGianCapNhat().format(formatter));
        lb_loaiHang.setText(sp.getLoaiHang().toString());
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
    void toTKSanPham(MouseEvent event) {

    }

}