package iuh.fit.controller;

import iuh.fit.App;
import iuh.fit.interfaces.HoaDon_interface;
import iuh.fit.entities.HoaDon;
import iuh.fit.entities.NhanVien;
import iuh.fit.entities.TaiKhoan;
import iuh.fit.enums.PhuongThucThanhToan;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class QL_HoaDon_controller implements Initializable {

    @FXML
    private VBox banHangSubMenuList;

    @FXML
    private VBox banHangSubVBox;

    @FXML
    private Button bn_XoaTrang;

    @FXML
    private Button btn_Them;

    @FXML
    private Button btn_timkiemHoaDon;

    @FXML
    private TableColumn<?, ?> cl_maHD;

    @FXML
    private TableColumn<?, ?> cl_maKH;

    @FXML
    private TableColumn<?, ?> cl_maNV;

    @FXML
    private TableColumn<HoaDon, PhuongThucThanhToan> cl_pttt;

    @FXML
    private TableColumn<?, ?> cl_thanhTien;

    @FXML
    private TableColumn<?, ?> cl_thoiGian;

    @FXML
    private TableColumn<HoaDon, Integer> cl_txt;

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
    private TableColumn<?, ?> lc_slsp;

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
    private TextField txt_MaHD;

    @FXML
    private TextField txt_MaKH;

    @FXML
    private TextField txt_MaNV;

    @FXML
    private ComboBox<PhuongThucThanhToan> txt_Pttt;

    @FXML
    private TextField txt_SoSP;

    @FXML
    private TextField txt_ThanhTien;

    @FXML
    private TextField txt_ThoiGian;

    @FXML
    private VBox vBox;

    @FXML
    private TableView<HoaDon> table_HD;

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
        toolsSlider(quanLySubVBox, quanLySubMenuList);
        removeOtherMenus(quanLySubVBox);
    }

    @FXML
    void handleThongKeClick(MouseEvent event) {
        toolsSlider(thongKeSubVBox, thongKeSubMenuList);
        removeOtherMenus(thongKeSubVBox);
    }

    @FXML
    void handleTimKiemClick(MouseEvent event) throws IOException {
        try {
            loadFXML("/fxml/TraCuu_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện tra cứu: " + e.getMessage());
            toolsSlider(timKiemSubVBox, timKiemSubMenuList);
            removeOtherMenus(timKiemSubVBox);
            App.loadFXML("TraCuu_gui");
        }
    }


    @FXML
    void toQLHoaDon(MouseEvent event) {
        try {
            loadFXML("/fxml/QL_HoaDon_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý hóa đơn!");
        }
    }

    @FXML
    void toQLKhachHang(MouseEvent event) {
        try {
            loadFXML("/fxml/QL_KhachHang_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý khách hàng!");
        }
    }

    @FXML
    void toQLNhanVien(MouseEvent event) {
        try {
            loadFXML("/fxml/QL_NhanVien_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý nhân viên!");
        }
    }

    @FXML
    void toQLPhieuNhap(MouseEvent event) {
        try {
            loadFXML("/fxml/QL_PhieuNhap_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý phiếu nhập!");
        }
    }

    @FXML
    void toQLSanPham(MouseEvent event) {
        try {
            loadFXML("/fxml/QL_SanPham_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý sản phẩm!");
        }
    }

    @FXML
    void toQLTaiKhoan(MouseEvent event) {
        try {
            loadFXML("/fxml/QL_TaiKhoan_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý tài khoản!");
        }
    }

    @FXML
    void toTKDoanhThu(MouseEvent event) {
        try {
            loadFXML("/fxml/ThongKeDoanhThu_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện thống kê doanh thu!");
        }
    }

    @FXML
    void toTKSanPham(MouseEvent event) {
        try {
            loadFXML("/fxml/ThongKeSanPham_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện thống kê sản phẩm!");
        }
    }

    private void showAlert(AlertType alertType, String title, String content) {
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

    private void loadTableData() {
        try {
            // Sử dụng DAO interface
            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("localhost", 9090);
            HoaDon_interface hdDAO = (HoaDon_interface) registry.lookup("hoaDonDAO");

            // Xóa dữ liệu cũ trong table
            table_HD.getItems().clear();

            // Lấy danh sách hóa đơn từ database
            ObservableList<HoaDon> listHD = FXCollections.observableArrayList(hdDAO.readAll());
            // Thiết lập cell value factory cho các cột
            cl_maHD.setCellValueFactory(new PropertyValueFactory<>("maHD"));
            cl_maNV.setCellValueFactory(new PropertyValueFactory<>("maNV"));
            cl_maKH.setCellValueFactory(new PropertyValueFactory<>("maKH"));
            lc_slsp.setCellValueFactory(new PropertyValueFactory<>("tongSoLuongSP"));
            cl_pttt.setCellValueFactory(new PropertyValueFactory<>("phuongThucTT"));
            cl_pttt.setCellFactory(column -> new TableCell<HoaDon, PhuongThucThanhToan>() {
                @Override
                protected void updateItem(PhuongThucThanhToan item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        switch (item) {
                            case Tien_Mat -> setText("Tiền mặt");
                            case Chuyen_Khoan -> setText("Chuyển khoản");
                            case The_Ngan_Hang-> setText("Thẻ ngân hàng");
                        }
                    }
                }
            });

            cl_thoiGian.setCellValueFactory(new PropertyValueFactory<>("thoiGian"));
            cl_thanhTien.setCellValueFactory(new PropertyValueFactory<>("thanhTien"));

            // Gán STT tự động
            cl_txt.setCellFactory(col -> new TableCell<HoaDon, Integer>() {
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
            table_HD.setItems(listHD);

            // Refresh table view
            table_HD.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFXML(String fxmlPath) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene scene = new Scene(root);
        Stage stage = (Stage) p_gioHang.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void ThemHD(MouseEvent event) {
        try{

        }catch (Exception e){
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể thêm hóa đơn!");
        }
    }

    @FXML
    void TimKiemHD(MouseEvent event) {
        try {
            loadFXML("/fxml/TraCuuHoaDon_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện tra cứu hóa đơn!");
        }
    }

    @FXML
    void XoaTrang(MouseEvent event) {
        txt_MaKH.setText("");
        txt_MaNV.setText("");
        txt_Pttt.setValue(null);
        txt_SoSP.setText("");
        txt_ThanhTien.setText("");
        txt_ThoiGian.setText("");
        table_HD.getSelectionModel().clearSelection();
        txt_MaHD.setText("");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addMenusToMap();
        initializeNhanVien();
        loadTableData();

        // Cấu hình ComboBox txt_Pttt chỉ 1 lần
        txt_Pttt.getItems().setAll(PhuongThucThanhToan.values());

        txt_Pttt.setCellFactory(lv -> new ListCell<PhuongThucThanhToan>() {
            @Override
            protected void updateItem(PhuongThucThanhToan item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getPhuongThucThanhToan());  // Gọi getDisplayName() để hiển thị tên đẹp
                }
            }
        });

// Tùy chỉnh cách hiển thị trên nút ComboBox khi đã chọn
        txt_Pttt.setButtonCell(new ListCell<PhuongThucThanhToan>() {
            @Override
            protected void updateItem(PhuongThucThanhToan item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getPhuongThucThanhToan());  // Gọi getDisplayName() cho ButtonCell
                }
            }
        });

        // Sự kiện khi click vào table_HD
        table_HD.setOnMouseClicked(event -> {
            HoaDon selectedHD = table_HD.getSelectionModel().getSelectedItem();
            if (selectedHD != null) {
                txt_MaHD.setText(selectedHD.getMaHD());
                txt_MaKH.setText(selectedHD.getMaKH());
                txt_MaNV.setText(selectedHD.getMaNV());
                txt_Pttt.setValue(selectedHD.getPhuongThucTT()); // Gán enum vào combo
                txt_SoSP.setText(String.valueOf(selectedHD.getTongSoLuongSP()));
                txt_ThanhTien.setText(String.valueOf(selectedHD.getThanhTien()));
                txt_ThoiGian.setText(selectedHD.getThoiGian().toString());
            }
        });
    }

}
