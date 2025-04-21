package iuh.fit.controller;

import iuh.fit.App;
import iuh.fit.interfaces.KhachHang_interface;
import iuh.fit.interfaces.NhanVien_interface;
import iuh.fit.entities.KhachHang;
import iuh.fit.entities.NhanVien;
import iuh.fit.entities.SanPham;
import iuh.fit.entities.TaiKhoan;
import iuh.fit.enums.ChucVu;
import iuh.fit.enums.LoaiHang;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static iuh.fit.App.loadFXML;

public class QL_NhanVien_controller implements Initializable {

    @FXML
    private VBox banHangSubMenuList;

    @FXML
    private VBox banHangSubVBox;

    @FXML
    private Button btnSua;

    @FXML
    private Button btnThem;

    @FXML
    private Button btnTimKiem;

    @FXML
    private Button btnXoa;

    @FXML
    private Button btnXoaTrang;

    @FXML
    private TableColumn<?, ?> cl_CCCD;

    @FXML
    private TableColumn<NhanVien, ChucVu> cl_chucVU;

    @FXML
    private TableColumn<?, ?> cl_email;

    @FXML
    private TableColumn<?, ?> cl_maNV;

    @FXML
    private TableColumn<?, ?> cl_ngaySinh;

    @FXML
    private TableColumn<?, ?> cl_tenNV;

    @FXML
    private TableColumn<NhanVien, Integer> cl_txt;

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
    private TableColumn<?, ?> lc_SDT;

    @FXML
    private Pane p_HoaDon;

    @FXML
    private Pane p_gioHang;

    @FXML
    private Pane p_hoaDon;
    @FXML
    private TableColumn<?, ?> cl_DiaChi;
    @FXML
    private TextField txt_DiaChi;
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
    private TextField txt_CCCD;


    @FXML
    private TextField txt_Email;

    @FXML
    private TextField txt_MaNV;

    @FXML
    private ComboBox<ChucVu> txt_chucVu;

    @FXML
    private DatePicker txt_ngaySinh;

    @FXML
    private TextField txt_SDT;

    @FXML
    private TextField txt_TenNV;

    @FXML
    private VBox vBox;

    @FXML
    private TableView<NhanVien> table_NV;
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
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện tra cứu: " + e.getMessage());
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
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý hóa đơn!");
        }
    }

    @FXML
    void toQLKhachHang(MouseEvent event) {
        try {
            loadFXML("/fxml/QL_KhachHang_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý khách hàng!");
        }
    }

    @FXML
    void toQLNhanVien(MouseEvent event) {
        try {
            loadFXML("/fxml/QL_NhanVien_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý nhân viên!");
        }
    }

    @FXML
    void toQLPhieuNhap(MouseEvent event) {
        try {
            loadFXML("/fxml/QL_PhieuNhap_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý phiếu nhập!");
        }
    }

    @FXML
    void toQLSanPham(MouseEvent event) {
        try {
            loadFXML("/fxml/QL_SanPham_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý sản phẩm!");
        }
    }

    @FXML
    void toQLTaiKhoan(MouseEvent event) {
        try {
            loadFXML("/fxml/QL_TaiKhoan_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý tài khoản!");
        }
    }

    @FXML
    void toTKDoanhThu(MouseEvent event) {
        try {
            loadFXML("/fxml/ThongKeDoanhThu_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện thống kê doanh thu!");
        }
    }

    @FXML
    void toTKSanPham(MouseEvent event) {
        try {
            loadFXML("/fxml/ThongKeSanPham_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện thống kê sản phẩm!");
        }
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


    @FXML
    void moGiaoDienTimKiemNV(MouseEvent event) {
        try {
            loadFXML("/fxml/TraCuu_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện tra cứu!");
        }
    }

    @FXML
    void suaTTNV(MouseEvent event) {
        try{
            // Lấy thông tin nhân viên từ các trường nhập liệu
            String maNV = txt_MaNV.getText();
            String tenNV = txt_TenNV.getText();
            String cccd = txt_CCCD.getText();
            String sdt = txt_SDT.getText();
            String email = txt_Email.getText();
            String ngaySinh = txt_ngaySinh.getValue().toString();
            ChucVu chucVu = txt_chucVu.getValue();
            String diaChi = txt_DiaChi.getText();
            // Tạo đối tượng NhanVien mới
            NhanVien nv = new NhanVien(maNV, tenNV, cccd, diaChi, email, sdt, LocalDate.parse(ngaySinh), chucVu, null);

            // Sử dụng DAO interface
            try {
                System.setProperty("java.security.policy", "rmi.policy");
                System.setProperty("java.rmi.server.hostname", "LAPTOP-O8OOBHDK");
                java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
                NhanVien_interface nvDAO = (NhanVien_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/nhanVienDAO");

                // Cập nhật thông tin nhân viên vào database
                nvDAO.updateNhanVien(nv);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Không thể kết nối đến server", e);
            }

            // Cập nhật lại dữ liệu trong bảng
            loadTableData();
            showAlert(Alert.AlertType.INFORMATION, "Thành công", "Cập nhật thông tin nhân viên thành công!");

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể cập nhật thông tin nhân viên!");
        }
    }

    @FXML
    void themNV(MouseEvent event) {
        try{
            // Lấy thông tin nhân viên từ các trường nhập liệu
            String maNV = txt_MaNV.getText();
            String tenNV = txt_TenNV.getText();
            String cccd = txt_CCCD.getText();
            String sdt = txt_SDT.getText();
            String email = txt_Email.getText();
            String ngaySinh = txt_ngaySinh.getValue().toString();
            ChucVu chucVu = txt_chucVu.getValue();
            String diaChi = txt_DiaChi.getText();

            // Tạo đối tượng NhanVien mới
            NhanVien nv = new NhanVien(maNV, tenNV, cccd, diaChi, email, sdt, LocalDate.parse(ngaySinh), chucVu, null);

            // Sử dụng DAO interface
            try {
                System.setProperty("java.security.policy", "rmi.policy");
                System.setProperty("java.rmi.server.hostname", "LAPTOP-O8OOBHDK");
                java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
                NhanVien_interface nvDAO = (NhanVien_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/nhanVienDAO");

                // Thêm nhân viên vào database
                nvDAO.createNhanVien(nv);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Không thể kết nối đến server", e);
            }

            // Cập nhật lại dữ liệu trong bảng
            loadTableData();
            showAlert(Alert.AlertType.INFORMATION, "Thành công", "Thêm nhân viên thành công!");
        }catch (Exception e){
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể thêm nhân viên!");
        }
    }


    @FXML
    void xoaNV(MouseEvent event) {
        try{
            // Lấy mã nhân viên từ trường nhập liệu
            String maNV = txt_MaNV.getText();

            // Sử dụng DAO interface
            try {
                System.setProperty("java.security.policy", "rmi.policy");
                System.setProperty("java.rmi.server.hostname", "LAPTOP-O8OOBHDK");
                java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
                NhanVien_interface nvDAO = (NhanVien_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/nhanVienDAO");

                // Xóa nhân viên khỏi database
                nvDAO.deleteNhanVien(maNV);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Không thể kết nối đến server", e);
            }

            // Cập nhật lại dữ liệu trong bảng
            loadTableData();
            showAlert(Alert.AlertType.INFORMATION, "Thành công", "Xóa nhân viên thành công!");
        }catch (Exception e){
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể xóa nhân viên!");
        }
    }

    @FXML
    void xoaTrang(MouseEvent event) {
        txt_MaNV.setText("");
        txt_TenNV.setText("");
        txt_CCCD.setText("");
        txt_SDT.setText("");
        txt_Email.setText("");
        txt_ngaySinh.setValue(null);
        txt_chucVu.setValue(null);
        txt_DiaChi.setText("");
        // Xóa lựa chọn trong bảng
        table_NV.getSelectionModel().clearSelection();
    }
    private void loadFXML(String fxmlPath) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene scene = new Scene(root);
        Stage stage = (Stage) p_gioHang.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    private void loadTableData() {
        try {
            // Sử dụng DAO interface
            System.setProperty("java.security.policy", "rmi.policy");
            System.setProperty("java.rmi.server.hostname", "LAPTOP-O8OOBHDK");
            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
            NhanVien_interface nvDAO = (NhanVien_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/nhanVienDAO");

            // Xóa dữ liệu cũ trong table
            table_NV.getItems().clear();

            // Lấy danh sách nhân viên từ database
            ObservableList<NhanVien> listNV = FXCollections.observableArrayList(nvDAO.readAllNhanVien());
            // Thiết lập cell value factory cho các cột
            cl_maNV.setCellValueFactory(new PropertyValueFactory<>("maNV"));
            cl_DiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
            cl_tenNV.setCellValueFactory(new PropertyValueFactory<>("tenNV"));
            cl_CCCD.setCellValueFactory(new PropertyValueFactory<>("cccd"));
            lc_SDT.setCellValueFactory(new PropertyValueFactory<>("sdt"));
            cl_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            cl_ngaySinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
            cl_chucVU.setCellValueFactory(new PropertyValueFactory<>("chucVu"));
            cl_chucVU.setCellFactory(column -> new TableCell<NhanVien, ChucVu>() {
                @Override
                protected void updateItem(ChucVu item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getChucVu()); // gọi method trả về tên đẹp
                    }
                }
            });



            // Gán STT tự động
            cl_txt.setCellFactory(col -> new TableCell<NhanVien, Integer>() {
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
            table_NV.setItems(listNV);

            // Refresh table view
            table_NV.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addMenusToMap();
        initializeNhanVien();
        loadTableData();
        // Thiết lập các giá trị cho ComboBox
        txt_chucVu.getItems().addAll(ChucVu.values());
        txt_chucVu.setCellFactory(lv -> new ListCell<ChucVu>() {
            @Override
            protected void updateItem(ChucVu item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getChucVu());  // Hiển thị giá trị đẹp
                }
            }
        });

// Sử dụng `setButtonCell` để hiển thị giá trị đẹp trên nút combo khi chọn
        txt_chucVu.setButtonCell(new ListCell<ChucVu>() {
            @Override
            protected void updateItem(ChucVu item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getChucVu());  // Hiển thị giá trị đẹp
                }
            }
        });
        // Thiết lập sự kiện khi người dùng chọn một hàng trong bảng
        table_NV.setOnMouseClicked(event -> {

                NhanVien selectedNV = table_NV.getSelectionModel().getSelectedItem();
                if (selectedNV != null) {
                    txt_MaNV.setText(selectedNV.getMaNV());
                    txt_TenNV.setText(selectedNV.getTenNV());
                    txt_CCCD.setText(selectedNV.getCccd());
                    txt_SDT.setText(selectedNV.getSdt());
                    txt_Email.setText(selectedNV.getEmail());
                    txt_ngaySinh.setValue(selectedNV.getNgaySinh());
                    txt_chucVu.setValue(selectedNV.getChucVu());
                    txt_DiaChi.setText(selectedNV.getDiaChi());
                }
        });
    }
}
