package iuh.fit.controller;

import iuh.fit.App;
import iuh.fit.interfaces.PhieuNhapHang_interface;
import iuh.fit.interfaces.SanPham_interface;
import iuh.fit.entities.NhanVien;
import iuh.fit.entities.PhieuNhapHang;
import iuh.fit.entities.SanPham;
import iuh.fit.entities.TaiKhoan;
import iuh.fit.enums.LoaiHang;
import iuh.fit.security.Permission;
import iuh.fit.security.PermissionChecker;
import iuh.fit.security.SecurityContext;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static iuh.fit.App.loadFXML;

public class QL_SanPham_controller implements Initializable {

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
    private TableColumn<SanPham, LoaiHang> cl_LoaiHang;

    @FXML
    private TableColumn<?, ?> cl_MaSP;

    @FXML
    private TableColumn<?, ?> cl_giaBan;

    @FXML
    private TableColumn<?, ?> cl_hanSD;

    @FXML
    private TableColumn<?, ?> cl_ngaySX;

    @FXML
    private TableColumn<?, ?> cl_nhaCC;

    @FXML
    private TableColumn<?, ?> cl_soLuongTon;

    @FXML
    private TableColumn<?, ?> cl_tenSP;

    @FXML
    private TableColumn<SanPham, Integer> cl_txt;

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
    private TableColumn<?, ?> lc_giaNhap;

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
    private TableView<SanPham> table_SP;

    @FXML
    private VBox thongKeSubMenuList;

    @FXML
    private VBox thongKeSubVBox;

    @FXML
    private VBox timKiemSubMenuList;

    @FXML
    private VBox timKiemSubVBox;

    @FXML
    private TextField txt_GiaBan;

    @FXML
    private TextField txt_GiaNhap;
    @FXML
    private DatePicker txt_hanSD;

    @FXML
    private ComboBox<LoaiHang> txt_loaiHang;

    @FXML
    private TextField txt_MaSP;

    @FXML
    private DatePicker txt_NgaySX;

    @FXML
    private TextField txt_NhaCC;

    @FXML
    private TextField txt_SoLuongTon;

    @FXML
    private TextField txt_tenSP;

    @FXML
    private VBox vBox;
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

    @FXML
    void moGiaoDienTimKiemSP(MouseEvent event) {
        try {
            loadFXML("/fxml/TraCuu_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện tra cứu!");
        }
    }

    @FXML
    void suaSP(MouseEvent event) {
        try {
            // Lấy thông tin từ các trường nhập liệu
            String maSP = txt_MaSP.getText();
            String tenSP = txt_tenSP.getText();
            if (tenSP.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng nhập tên sản phẩm!");
                return;
            }
            LoaiHang loaiHang = txt_loaiHang.getValue();
            if (loaiHang == null) {
                showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng chọn loại hàng!");
                return;
            }
            String nhaCC = txt_NhaCC.getText();
            if (nhaCC.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng nhập nhà cung cấp!");
                return;
            }
            double giaNhap = Double.parseDouble(txt_GiaNhap.getText());
            if (giaNhap <= 0) {
                showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Giá nhập phải lớn hơn 0!");
                return;
            }
            double giaBan = Double.parseDouble(txt_GiaBan.getText());
            if (giaBan <= 0) {
                showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Giá bán phải lớn hơn 0!");
                return;
            } else if (giaBan < giaNhap) {
                showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Giá bán phải lớn hơn giá nhập!");
                return;
            }

            // Ngày sản xuất và hạn sử dụng
            LocalDateTime ngaySXWithTime = txt_NgaySX.getValue().atStartOfDay();
            if (ngaySXWithTime.isAfter(LocalDateTime.now())) {
                showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Ngày sản xuất không được lớn hơn ngày hiện tại!");
                return;
            }
            LocalDateTime hanSDWithTime = txt_hanSD.getValue().atStartOfDay();
            if (hanSDWithTime.isBefore(ngaySXWithTime)) {
                showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Hạn sử dụng không được nhỏ hơn ngày sản xuất!");
                return;
            }

            int soLuongTon = Integer.parseInt(txt_SoLuongTon.getText());
            if (soLuongTon < 0) {
                showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Số lượng tồn phải lớn hơn hoặc bằng 0!");
                return;
            }

            // Tạo sản phẩm mới
            SanPham sp = new SanPham(maSP, tenSP, nhaCC, soLuongTon, giaNhap, giaBan,
                    ngaySXWithTime, hanSDWithTime, LocalDateTime.now(), loaiHang);

            // Gọi DAO interface để cập nhật
            try {
                System.setProperty("java.security.policy", "rmi.policy");
                System.setProperty("java.rmi.server.hostname", "LAPTOP-O8OOBHDK");
                java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
                SanPham_interface dao = (SanPham_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/sanPhamDAO");
                dao.update(sp);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Không thể kết nối đến server", e);
            }

            // Load lại bảng
            loadTableData();
            showAlert(Alert.AlertType.INFORMATION, "Thành công", "Sản phẩm đã được cập nhật!");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể sửa sản phẩm!");
        }
    }


    @FXML
    void themSP(MouseEvent event) {
        try {
            // Lấy thông tin từ các trường nhập liệu
            String maSP = taomaSP();
            String tenSP = txt_tenSP.getText();
            if (tenSP.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng nhập tên sản phẩm!");
                return;
            }
            LoaiHang loaiHang = txt_loaiHang.getValue();
            if (loaiHang == null) {
                showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng chọn loại hàng!");
                return;
            }
            String nhaCC = txt_NhaCC.getText();
            if (nhaCC.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng nhập nhà cung cấp!");
                return;
            }
            double giaNhap = Double.parseDouble(txt_GiaNhap.getText());
            if (giaNhap <= 0) {
                showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Giá nhập phải lớn hơn 0!");
                return;
            }
            double giaBan = Double.parseDouble(txt_GiaBan.getText());
            if (giaBan <= 0) {
                showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Giá bán phải lớn hơn 0!");
                return;
            }else if (giaBan < giaNhap) {
                showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Giá bán phải lớn hơn giá nhập!");
                return;
            }

            String textNgaySX = txt_NgaySX.getValue().toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate ngaySX = LocalDate.parse(textNgaySX, formatter); // Sử dụng LocalDate
            LocalDateTime ngaySXWithTime = ngaySX.atStartOfDay(); // Chuyển đổi thành LocalDateTime, giờ mặc định là 00:00:00
            if (ngaySX.isAfter(LocalDate.now())) {
                showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Ngày sản xuất không được lớn hơn ngày hiện tại!");
                return;
            }

            String textHanSD = txt_hanSD.getValue().toString();
            LocalDate hanSD = LocalDate.parse(textHanSD, formatter); // Sử dụng LocalDate
            LocalDateTime hanSDWithTime = hanSD.atStartOfDay(); // Chuyển đổi thành LocalDateTime, giờ mặc định là 00:00:00
            if (hanSD.isBefore(ngaySX)) {
                showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Hạn sử dụng không được nhỏ hơn ngày sản xuất!");
                return;
            }


            int soLuongTon = Integer.parseInt(txt_SoLuongTon.getText());
            if (soLuongTon < 0) {
                showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Số lượng tồn phải lớn hơn hoặc bằng 0!");
                return;
            }

            // Tạo đối tượng SanPham mới
            SanPham sanPham = new SanPham(maSP, tenSP, nhaCC, soLuongTon, giaNhap, giaBan, ngaySXWithTime, hanSDWithTime, LocalDateTime.now(),loaiHang);

            // Sử dụng DAO interface
            try {
                System.setProperty("java.security.policy", "rmi.policy");
                System.setProperty("java.rmi.server.hostname", "LAPTOP-O8OOBHDK");
                java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
                SanPham_interface spDAO = (SanPham_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/sanPhamDAO");

                // Thêm sản phẩm vào database
                spDAO.create(sanPham);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Không thể kết nối đến server", e);
            }
            System.out.println("Thêm sản phẩm thành công!");
            // Cập nhật lại dữ liệu trong bảng
            loadTableData();
        }catch (Exception e){
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể thêm sản phẩm!");
        }
    }

    private String taomaSP() {
        LocalDate today = LocalDate.now();
        String day = String.format("%02d", today.getDayOfMonth());
        String month = String.format("%02d", today.getMonthValue());
        String year = String.valueOf(today.getYear()).substring(2); // 2 số cuối năm

        String prefix = "SP" + year + month + day;

        Random rand = new Random();
        int soNgauNhien = 100000 + rand.nextInt(900000); // Tạo số ngẫu nhiên 6 chữ số

        String maKH = prefix + soNgauNhien;

        return maKH;
    }

    @FXML
    void xoaSP(MouseEvent event) {
        try{
            // Lấy mã sản phẩm từ trường nhập liệu
            String maSP = txt_MaSP.getText();

            // Sử dụng DAO interface
            try {
                System.setProperty("java.security.policy", "rmi.policy");
                System.setProperty("java.rmi.server.hostname", "LAPTOP-O8OOBHDK");
                java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
                SanPham_interface spDAO = (SanPham_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/sanPhamDAO");

                // Xóa sản phẩm khỏi database
                spDAO.delete(maSP);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Không thể kết nối đến server", e);
            }
            System.out.println("Xóa sản phẩm thành công!");

            // Cập nhật lại dữ liệu trong bảng
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể xóa sản phẩm!");
        }
    }

    @FXML
    void xoaTrang(MouseEvent event) {
        txt_tenSP.setText("");
        txt_MaSP.setText("");
        txt_loaiHang.setValue(null);
        txt_NhaCC.setText("");
        txt_GiaNhap.setText("");
        txt_GiaBan.setText("");
        txt_NgaySX.setValue(null);
        txt_hanSD.setValue(null);
        txt_SoLuongTon.setText("");

        // Xóa dữ liệu trong bảng
        table_SP.getItems().clear();
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
            SanPham_interface spDAO = (SanPham_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/sanPhamDAO");
            List<SanPham> ds = spDAO.readAll();
            // Xóa dữ liệu cũ trong table
            table_SP.getItems().clear();

            // Lấy danh sách phiếu nhập từ database
            ObservableList<SanPham> listSP = FXCollections.observableArrayList(ds);
            // Thiết lập cell value factory cho các cột
            cl_MaSP.setCellValueFactory(new PropertyValueFactory<>("maSP"));
            cl_tenSP.setCellValueFactory(new PropertyValueFactory<>("tenSP"));
            cl_LoaiHang.setCellValueFactory(new PropertyValueFactory<>("loaiHang"));
            cl_nhaCC.setCellValueFactory(new PropertyValueFactory<>("nhaCC"));
            lc_giaNhap.setCellValueFactory(new PropertyValueFactory<>("giaNhap"));
            cl_giaBan.setCellValueFactory(new PropertyValueFactory<>("giaBan"));
            cl_ngaySX.setCellValueFactory(new PropertyValueFactory<>("ngaySX"));
            cl_hanSD.setCellValueFactory(new PropertyValueFactory<>("hanSD"));
            cl_soLuongTon.setCellValueFactory(new PropertyValueFactory<>("soLuongTon"));

            cl_LoaiHang.setCellFactory(column -> new TableCell<SanPham, LoaiHang>() {
                @Override
                protected void updateItem(LoaiHang item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getLoaiHang()); // gọi method trả về tên đẹp
                    }
                }
            });
            // Gán STT tự động
            cl_txt.setCellFactory(col -> new TableCell<SanPham, Integer>() {
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
            table_SP.setItems(listSP);

            // Refresh table view
            table_SP.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txt_MaSP.setEditable(false);
        addMenusToMap();
        initializeNhanVien();
        loadTableData();
        txt_loaiHang.getItems().setAll(LoaiHang.values());
        txt_loaiHang.setCellFactory(lv -> new ListCell<LoaiHang>() {
            @Override
            protected void updateItem(LoaiHang item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getLoaiHang());  // Hiển thị giá trị đẹp
                }
            }
        });

// Sử dụng `setButtonCell` để hiển thị giá trị đẹp trên nút combo khi chọn
        txt_loaiHang.setButtonCell(new ListCell<LoaiHang>() {
            @Override
            protected void updateItem(LoaiHang item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getLoaiHang());  // Hiển thị giá trị đẹp
                }
            }
        });
        table_SP.setOnMouseClicked(event -> {
            SanPham selectedSP = table_SP.getSelectionModel().getSelectedItem();
            if (selectedSP != null) {
                txt_MaSP.setText(selectedSP.getMaSP());
                txt_tenSP.setText(selectedSP.getTenSP());
                txt_loaiHang.setValue(selectedSP.getLoaiHang());
                txt_NhaCC.setText(selectedSP.getNhaCC());
                txt_GiaNhap.setText(String.valueOf(selectedSP.getGiaNhap()));
                txt_GiaBan.setText(String.valueOf(selectedSP.getGiaBan()));
                txt_NgaySX.setValue(LocalDate.from(selectedSP.getNgaySX()));
                txt_hanSD.setValue(LocalDate.from(selectedSP.getHanSD()));
                txt_SoLuongTon.setText(String.valueOf(selectedSP.getSoLuongTon()));
            }
        });
    }
}
