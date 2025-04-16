package iuh.fit.controller;

import iuh.fit.App;
import iuh.fit.daos.SanPham_dao;
import iuh.fit.entities.SanPham;
import iuh.fit.entities.TaiKhoan;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
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
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

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

    void timKiem(MouseEvent event) {

        String maSanPham = txt_maSP.getText();
        App.ma = maSanPham;
        SanPham sp = new SanPham_dao().read(maSanPham);
        lb_maSP.setText(sp.getMaSP());
        lb_tenSP.setText(sp.getTenSP());
        lb_ncc.setText(sp.getNhaCC());
        lb_slt.setText(String.valueOf(sp.getSoLuongTon()));
        lb_giaNhap.setText(String.valueOf(sp.getGiaNhap()));
        lb_giaBan.setText(String.valueOf(sp.getGiaBan()));
        lb_nsx.setText(sp.getNgaySX().toString());
        lb_hsd.setText(sp.getHanSD().toString());
        lb_tgcn.setText(sp.getThoiGianCapNhat().toString());
        lb_loaiHang.setText(sp.getLoaiHang().toString());
        highlightMatchingRow(maSanPham);
    }

    private void highlightMatchingRow(String maTaiKhoan) {
        for (int i = 0; i < tableSanPham.getItems().size(); i++) {
            SanPham sanPham = tableSanPham.getItems().get(i);
            if (sanPham.getMaSP().equals(sanPham)) {
                // Select the row (important)
                tableSanPham.getSelectionModel().select(i);
                // Set focus to the row
                tableSanPham.getFocusModel().focus(i);
                // Highlight the row (optional, but recommended)
                tableSanPham.getFocusModel().focus(i);
                tableSanPham.getSelectionModel().focus(i);
                break;
            }
        }
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lb_tenSP.setText("");
        lb_tenSP.setText("");
        lb_ncc.setText("");
        ObservableList<String> list = FXCollections.observableArrayList("Sản phẩm", "Tài khoản", "Hoá đơn", "Phiếu nhập", "Nhân viên", "Khách hàng");
        cbb_GiaoDien.setItems(list);
        cbb_GiaoDien.setValue("Sản phẩm");
        // Xử lý sự kiện chọn ComboBox để chuyển giao diện
        cbb_GiaoDien.setOnAction(event -> {
            String selectedValue = cbb_GiaoDien.getValue();
            switch (selectedValue) {
                case "Sản phẩm":
                    try {
                        App.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/TraCuu_gui.fxml"))));
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case "Tài khoản":
                    try {
                        App.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/TraCuuTaiKhoan_gui.fxml"))));
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case "Hoá đơn":
                    try {
                        App.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/TraCuuHoaDon_gui.fxml"))));
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case "Phiếu nhập":
                    try {
                        App.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/TraCuuPhieuNhap_gui.fxml"))));
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case "Nhân viên":
                    try {
                        App.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/TraCuuNhanVien_gui.fxml"))));
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case "Khách hàng":
                    try {
                        App.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/TraCuuKhachHang_gui.fxml"))));
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Không tìm thấy giao diện phù hợp!");
                    break;
            }
        });
        cl_txt.setCellFactory(col -> {
            return new TableCell<SanPham, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        // Số thứ tự = index + 1
                        setText(String.valueOf(getIndex() + 1));
                    }
                }
            };
        });


        cl_maSP.setCellValueFactory(new PropertyValueFactory<>("MaSP"));
        cl_tenSP.setCellValueFactory(new PropertyValueFactory<>("TenSP"));
        cl_ncc.setCellValueFactory(new PropertyValueFactory<>("NhaCC"));
        cl_slt.setCellValueFactory(new PropertyValueFactory<>("SoLuongTon"));
        cl_giaNhap.setCellValueFactory(new PropertyValueFactory<>("GiaNhap"));
        cl_giaBan.setCellValueFactory(new PropertyValueFactory<>("GiaBan"));
        cl_nsx.setCellValueFactory(new PropertyValueFactory<>("NgaySX"));
        cl_hsd.setCellValueFactory(new PropertyValueFactory<>("HanSD"));
        cl_tgcn.setCellValueFactory(new PropertyValueFactory<>("ThoiGianCapNhat"));
        cl_loaiHang.setCellValueFactory(new PropertyValueFactory<>("LoaiHang"));

        loadTableData();
        tableSanPham.setOnMouseClicked(event -> {
            SanPham selectedSanPham = tableSanPham.getSelectionModel().getSelectedItem();
            if (selectedSanPham != null) {
                lb_maSP.setText(selectedSanPham.getMaSP());
                lb_tenSP.setText(selectedSanPham.getTenSP());
                lb_ncc.setText(selectedSanPham.getNhaCC());
                lb_slt.setText(String.valueOf(selectedSanPham.getSoLuongTon()));
                lb_giaNhap.setText(String.valueOf(selectedSanPham.getGiaNhap()));
                lb_giaBan.setText(String.valueOf(selectedSanPham.getGiaBan()));
                lb_nsx.setText(selectedSanPham.getNgaySX().toString());
                lb_hsd.setText(selectedSanPham.getHanSD().toString());
                lb_tgcn.setText(selectedSanPham.getThoiGianCapNhat().toString());
                lb_loaiHang.setText(selectedSanPham.getLoaiHang().toString());
            }
        });
        loadTableData();
        tableSanPham.setOnMouseClicked(event -> {
            SanPham selectedSanPham = tableSanPham.getSelectionModel().getSelectedItem();
            if (selectedSanPham != null) {
                lb_maSP.setText(selectedSanPham.getMaSP());
                lb_tenSP.setText(selectedSanPham.getTenSP());
                lb_ncc.setText(selectedSanPham.getNhaCC());
                lb_slt.setText(String.valueOf(selectedSanPham.getSoLuongTon()));
                lb_giaNhap.setText(String.valueOf(selectedSanPham.getGiaNhap()));
                lb_giaBan.setText(String.valueOf(selectedSanPham.getGiaBan()));
                lb_nsx.setText(selectedSanPham.getNgaySX().toString());
                lb_hsd.setText(selectedSanPham.getHanSD().toString());
                lb_tgcn.setText(selectedSanPham.getThoiGianCapNhat().toString());
                lb_loaiHang.setText(selectedSanPham.getLoaiHang().toString());
            }
        });

        addUserLogin();
    }
    private void loadTableData() {
        try {
            KhuyenMai_DAO kmdao = new KhuyenMai_DAO();
            ArrayList<KhuyenMai> dskm = kmdao.getAllKhuyenMai();

            ObservableList<KhuyenMai> observableList = FXCollections.observableArrayList(dskm);
            tableKhuyenMai.setItems(observableList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void addUserLogin() {
        TaiKhoan tk = App.;
        maNV.setText(String.valueOf(tk.getNhanVien().getIdNhanVien()));
        tenNV.setText(String.valueOf(tk.getNhanVien().getTenNhanVien()));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addMenusToMap();
    }
}
