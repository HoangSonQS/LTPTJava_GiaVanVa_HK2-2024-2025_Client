package iuh.fit.controller;

import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import iuh.fit.App;
import iuh.fit.daos.ChiTietHoaDon_SanPham_dao;
import iuh.fit.daos.HoaDon_dao;
import iuh.fit.daos.KhachHang_dao;
import iuh.fit.daos.SanPham_dao;
import iuh.fit.entities.ChiTietHoaDon_SanPham;
import iuh.fit.entities.ChiTietHoaDon_SanPhamId;
import iuh.fit.entities.HoaDon;
import iuh.fit.entities.KhachHang;
import iuh.fit.entities.SanPham;
import iuh.fit.enums.PhuongThucThanhToan;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


public class BanHang_controller implements Initializable {

    @FXML
    private VBox banHangSubMenuList;

    @FXML
    private VBox banHangSubVBox;

    @FXML
    private Button btn_themSP;

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
    private Label lb_ngayLap;

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
    private Label lb_thoiGian;

    @FXML
    private Label lb_tongTien;

    @FXML
    private Button btn_thanhToan;

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
    private TableColumn<SanPham, Double> tcDonGia;

    @FXML
    private TableColumn<SanPham, String> tcMaSP;

    @FXML
    private TableColumn<SanPham, Integer> tcSTT;

    @FXML
    private TableColumn<SanPham, Integer> tcSoLuong;

    @FXML
    private TableColumn<SanPham, String> tcTenSP;

    @FXML
    private TableColumn<SanPham, Double> tcThanhTien;

    @FXML
    private TableView<SanPham> tableView;

    @FXML
    private VBox thongKeSubMenuList;

    @FXML
    private VBox thongKeSubVBox;

    @FXML
    private VBox timKiemSubMenuList;

    @FXML
    private VBox timKiemSubVBox;

    @FXML
    private TextField txt_nhapMa;

    @FXML
    private TextField txt_nhapSL;

    @FXML
    private TextField txt_sdt;

    @FXML
    private TextField txt_tenKH;

    @FXML
    private VBox vBox;

    Map<VBox,VBox> map = new HashMap<VBox,VBox>();

    // DAO để truy xuất dữ liệu
    private SanPham_dao sanPhamDao;
    private HoaDon_dao hoaDonDao;
    private KhachHang_dao khachHangDao;
    private ChiTietHoaDon_SanPham_dao chiTietHoaDonDao;

    // Danh sách các sản phẩm trong giỏ hàng
    private ObservableList<SanPham> cartItems;

    // Số lượng sản phẩm trong giỏ hàng
    private Map<String, Integer> productQuantities = new HashMap<>();

    public void initialize(URL location, ResourceBundle resources) {
        // Khởi tạo các DAO
        sanPhamDao = new SanPham_dao();
        hoaDonDao = new HoaDon_dao();
        khachHangDao = new KhachHang_dao();
        chiTietHoaDonDao = new ChiTietHoaDon_SanPham_dao();

        // Khởi tạo các menu
        addMenusToMap();

        // Hiển thị ngày và thời gian hiện tại
        displayCurrentDateTime();

        // Khởi tạo bảng giỏ hàng
        initializeTable();

        // Khởi tạo tổng tiền
        updateTotalAmount();
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

        // Hiển thị giao diện bán hàng chính
        showMainSalesInterface();
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

        // Hiển thị giao diện tìm kiếm
        showSearchInterface();
    }
    @FXML
    void themSanPham(MouseEvent event) {
        // Lấy mã sản phẩm và số lượng từ các trường nhập liệu
        String maSP = txt_nhapMa.getText().trim();
        String soLuongStr = txt_nhapSL.getText().trim();

        // Kiểm tra dữ liệu nhập vào
        if (maSP.isEmpty()) {
            showAlert(AlertType.WARNING, "Thông báo", "Vui lòng nhập mã sản phẩm!");
            txt_nhapMa.requestFocus();
            return;
        }

        if (soLuongStr.isEmpty()) {
            showAlert(AlertType.WARNING, "Thông báo", "Vui lòng nhập số lượng!");
            txt_nhapSL.requestFocus();
            return;
        }

        try {
            int soLuong = Integer.parseInt(soLuongStr);
            if (soLuong <= 0) {
                showAlert(AlertType.WARNING, "Thông báo", "Số lượng phải lớn hơn 0!");
                txt_nhapSL.requestFocus();
                return;
            }

            // Tìm kiếm sản phẩm theo mã và thêm vào giỏ hàng
            addProductToCart(maSP, soLuong);

            // Xóa dữ liệu trong các trường nhập liệu
            txt_nhapMa.clear();
            txt_nhapSL.clear();
            txt_nhapMa.requestFocus();

        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Lỗi", "Số lượng phải là số nguyên!");
            txt_nhapSL.requestFocus();
        }
    }

    @FXML
    void toQLHoaDon(MouseEvent event) {
        try {
            // Chuyển đến giao diện quản lý hóa đơn
            loadFXML("/fxml/QuanLyHoaDon_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý hóa đơn!");
        }
    }

    @FXML
    void toQLKhachHang(MouseEvent event) {
        try {
            // Chuyển đến giao diện quản lý khách hàng
            loadFXML("/fxml/QuanLyKhachHang_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý khách hàng!");
        }
    }

    @FXML
    void toQLNhanVien(MouseEvent event) {
        try {
            // Chuyển đến giao diện quản lý nhân viên
            loadFXML("/fxml/QuanLyNhanVien_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý nhân viên!");
        }
    }

    @FXML
    void toQLPhieuNhap(MouseEvent event) {
        try {
            // Chuyển đến giao diện quản lý phiếu nhập
            loadFXML("/fxml/QuanLyPhieuNhap_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý phiếu nhập!");
        }
    }

    @FXML
    void toQLSanPham(MouseEvent event) {
        try {
            // Chuyển đến giao diện quản lý sản phẩm
            loadFXML("/fxml/QuanLySanPham_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý sản phẩm!");
        }
    }

    @FXML
    void toQLTaiKhoan(MouseEvent event) {
        try {
            // Chuyển đến giao diện quản lý tài khoản
            loadFXML("/fxml/QuanLyTaiKhoan_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý tài khoản!");
        }
    }

    @FXML
    void toTKDoanhThu(MouseEvent event) {
        try {
            // Chuyển đến giao diện thống kê doanh thu
            loadFXML("/fxml/ThongKeDoanhThu_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện thống kê doanh thu!");
        }
    }

    @FXML
    void toTKSanPham(MouseEvent event) {
        try {
            // Chuyển đến giao diện thống kê sản phẩm
            loadFXML("/fxml/ThongKeSanPham_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện thống kê sản phẩm!");
        }
    }

    /**
     * Hiển thị ngày và thời gian hiện tại
     */
    private void displayCurrentDateTime() {
        // Hiển thị ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        lb_ngayLap.setText(currentDate.format(dateFormatter));

        // Hiển thị và cập nhật thời gian hiện tại mỗi giây
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            lb_thoiGian.setText(currentTime.format(timeFormatter));
        }), new KeyFrame(Duration.seconds(1)));

        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }

    /**
     * Khởi tạo bảng giỏ hàng
     */
    private void initializeTable() {
        try {
            // Khởi tạo các cột cho bảng giỏ hàng
            tcSTT.setCellValueFactory(cellData -> {
                int index = cartItems.indexOf(cellData.getValue()) + 1;
                return javafx.beans.binding.Bindings.createObjectBinding(() -> index);
            });

            tcMaSP.setCellValueFactory(new PropertyValueFactory<>("maSP"));
            tcTenSP.setCellValueFactory(new PropertyValueFactory<>("tenSP"));

            // Số lượng sản phẩm trong giỏ hàng
            tcSoLuong.setCellValueFactory(cellData -> {
                SanPham sp = cellData.getValue();
                Integer quantity = productQuantities.getOrDefault(sp.getMaSP(), 0);
                return javafx.beans.binding.Bindings.createObjectBinding(() -> quantity);
            });

            tcDonGia.setCellValueFactory(new PropertyValueFactory<>("giaBan"));

            // Thành tiền = giá bán * số lượng
            tcThanhTien.setCellValueFactory(cellData -> {
                SanPham sp = cellData.getValue();
                Integer quantity = productQuantities.getOrDefault(sp.getMaSP(), 0);
                Double thanhTien = sp.getGiaBan() * quantity;
                return javafx.beans.binding.Bindings.createObjectBinding(() -> thanhTien);
            });

            // Định dạng hiển thị tiền tệ cho cột đơn giá và thành tiền
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

            tcDonGia.setCellFactory(column -> new javafx.scene.control.TableCell<SanPham, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(currencyFormat.format(item));
                    }
                }
            });

            tcThanhTien.setCellFactory(column -> new javafx.scene.control.TableCell<SanPham, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(currencyFormat.format(item));
                    }
                }
            });

            // Khởi tạo danh sách giỏ hàng trống
            cartItems = FXCollections.observableArrayList();
            if (tableView != null) {
                tableView.setItems(cartItems);
            } else {
                System.out.println("TableView is null. Check your FXML file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error initializing table: " + e.getMessage());
        }
    }

    /**
     * Thêm sản phẩm vào giỏ hàng
     */
    private void addProductToCart(String maSP, int soLuong) {
        try {
            // Tìm kiếm sản phẩm theo mã
            SanPham sanPham = sanPhamDao.read(maSP);

            if (sanPham == null) {
                showAlert(AlertType.ERROR, "Lỗi", "Không tìm thấy sản phẩm có mã " + maSP);
                return;
            }

            // Kiểm tra số lượng tồn kho
            if (sanPham.getSoLuongTon() < soLuong) {
                showAlert(AlertType.WARNING, "Cảnh báo", "Số lượng sản phẩm trong kho không đủ (còn " + sanPham.getSoLuongTon() + ")");
                return;
            }

            // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
            boolean productExists = false;

            for (SanPham sp : cartItems) {
                if (sp.getMaSP().equals(maSP)) {
                    // Nếu sản phẩm đã tồn tại, tăng số lượng
                    int currentQuantity = productQuantities.getOrDefault(maSP, 0);
                    productQuantities.put(maSP, currentQuantity + soLuong);
                    productExists = true;
                    break;
                }
            }

            // Nếu sản phẩm chưa có trong giỏ hàng, thêm mới
            if (!productExists) {
                cartItems.add(sanPham);
                productQuantities.put(maSP, soLuong);
            }

            // Cập nhật lại bảng
            tableView.refresh();

            // Cập nhật tổng tiền
            updateTotalAmount();

            // Hiển thị thông báo thành công
            showAlert(AlertType.INFORMATION, "Thông báo", "Đã thêm sản phẩm vào giỏ hàng!");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể thêm sản phẩm vào giỏ hàng: " + e.getMessage());
        }
    }

    /**
     * Hiển thị giao diện bán hàng chính
     */
    private void showMainSalesInterface() {
        // Làm mới form nhập liệu
        txt_nhapMa.clear();
        txt_nhapSL.clear();
        txt_tenKH.clear();
        txt_sdt.clear();

        // Focus vào trường nhập mã sản phẩm
        txt_nhapMa.requestFocus();
    }

    /**
     * Xóa sản phẩm khỏi giỏ hàng
     */
    private void removeProductFromCart(String maSP) {
        try {
            // Tìm sản phẩm trong giỏ hàng
            SanPham productToRemove = null;
            for (SanPham sp : cartItems) {
                if (sp.getMaSP().equals(maSP)) {
                    productToRemove = sp;
                    break;
                }
            }

            // Nếu tìm thấy sản phẩm, xóa khỏi giỏ hàng
            if (productToRemove != null) {
                cartItems.remove(productToRemove);
                productQuantities.remove(maSP);
                tableView.refresh();

                // Cập nhật tổng tiền
                updateTotalAmount();

                showAlert(AlertType.INFORMATION, "Thông báo", "Đã xóa sản phẩm khỏi giỏ hàng!");
            } else {
                showAlert(AlertType.WARNING, "Thông báo", "Không tìm thấy sản phẩm trong giỏ hàng!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể xóa sản phẩm khỏi giỏ hàng: " + e.getMessage());
        }
    }

    /**
     * Tính tổng tiền của giỏ hàng
     */
    private double calculateTotal() {
        double total = 0;
        for (SanPham sp : cartItems) {
            int quantity = productQuantities.getOrDefault(sp.getMaSP(), 0);
            total += sp.getGiaBan() * quantity;
        }
        return total;
    }

    /**
     * Tạo hóa đơn từ giỏ hàng
     */
    private void createInvoice() {
        try {
            // Kiểm tra giỏ hàng có sản phẩm không
            if (cartItems.isEmpty()) {
                showAlert(AlertType.WARNING, "Thông báo", "Giỏ hàng trống!");
                return;
            }

            // Lấy thông tin khách hàng
            String tenKH = txt_tenKH.getText().trim();
            String sdt = txt_sdt.getText().trim();

            // Tạo mã hóa đơn mới
            String maHD = "HD" + System.currentTimeMillis();

            // Tạo hóa đơn mới
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaHD(maHD);
            hoaDon.setMaNV("NV001"); // Mã nhân viên mặc định
            hoaDon.setThoiGian(java.time.LocalDateTime.now());

            // Tính tổng số lượng sản phẩm
            int tongSoLuong = 0;
            for (Integer quantity : productQuantities.values()) {
                tongSoLuong += quantity;
            }
            hoaDon.setTongSoLuongSP(tongSoLuong);

            // Tính tổng tiền
            double tongTien = calculateTotal();
            hoaDon.setThanhTien(tongTien);

            // Đặt phương thức thanh toán mặc định
            hoaDon.setPhuongThucTT(PhuongThucThanhToan.Tien_Mat);

            // Kiểm tra và tạo khách hàng nếu có thông tin
            if (!tenKH.isEmpty() && !sdt.isEmpty()) {
                // Tìm kiếm khách hàng theo số điện thoại
                KhachHang khachHang = khachHangDao.findByPhone(sdt);

                // Nếu khách hàng chưa tồn tại, tạo mới
                if (khachHang == null) {
                    khachHang = new KhachHang();
                    khachHang.setMaKH("KH" + System.currentTimeMillis());
                    khachHang.setTenKH(tenKH);
                    khachHang.setSdt(sdt);
                    khachHangDao.create(khachHang);
                }

                // Gán mã khách hàng cho hóa đơn
                hoaDon.setMaKH(khachHang.getMaKH());
            }

            // Lưu hóa đơn vào database
            hoaDonDao.create(hoaDon);

            // Tạo chi tiết hóa đơn cho từng sản phẩm
            for (SanPham sp : cartItems) {
                int soLuong = productQuantities.getOrDefault(sp.getMaSP(), 0);

                // Tạo ID cho chi tiết hóa đơn
                ChiTietHoaDon_SanPhamId chiTietId = new ChiTietHoaDon_SanPhamId();
                chiTietId.setMaHD(maHD);
                chiTietId.setMaSP(sp.getMaSP());

                // Tạo chi tiết hóa đơn
                ChiTietHoaDon_SanPham chiTiet = new ChiTietHoaDon_SanPham();
                chiTiet.setId(chiTietId);
                chiTiet.setSoLuongSP(soLuong);
                chiTiet.setDonGia(sp.getGiaBan());
                chiTiet.setHoaDon(hoaDon);
                chiTiet.setSanPham(sp);

                // Lưu chi tiết hóa đơn
                chiTietHoaDonDao.create(chiTiet);

                // Cập nhật số lượng tồn kho
                sp.setSoLuongTon(sp.getSoLuongTon() - soLuong);
                sanPhamDao.update(sp);
            }

            // Xóa giỏ hàng sau khi tạo hóa đơn
            cartItems.clear();
            productQuantities.clear();
            tableView.refresh();

            // Làm mới form
            txt_tenKH.clear();
            txt_sdt.clear();
            txt_nhapMa.clear();
            txt_nhapSL.clear();

            // Hiển thị thông báo thành công
            showAlert(AlertType.INFORMATION, "Thành công", "Đã tạo hóa đơn thành công!");

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể tạo hóa đơn: " + e.getMessage());
        }
    }

    /**
     * Hiển thị giao diện tìm kiếm
     */
    private void showSearchInterface() {
        // TODO: Hiển thị giao diện tìm kiếm
    }

    /**
     * Cập nhật tổng tiền trong giỏ hàng
     */
    private void updateTotalAmount() {
        try {
            double total = calculateTotal();
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            lb_tongTien.setText(currencyFormat.format(total));
        } catch (Exception e) {
            e.printStackTrace();
            lb_tongTien.setText("0 VNĐ");
        }
    }

    /**
     * Xử lý sự kiện khi nhấn nút thanh toán
     */
    @FXML
    void thanhToan(MouseEvent event) {
        if (cartItems.isEmpty()) {
            showAlert(AlertType.WARNING, "Thông báo", "Giỏ hàng trống!");
            return;
        }

        // Xác nhận thanh toán
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận thanh toán");
        alert.setHeaderText(null);
        alert.setContentText("Bạn có chắc chắn muốn thanh toán?");

        if (alert.showAndWait().get() == javafx.scene.control.ButtonType.OK) {
            createInvoice();
        }
    }

    /**
     * Hiển thị thông báo
     */
    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Load FXML file
     */
    private void loadFXML(String fxmlPath) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene scene = new Scene(root);
        Stage stage = (Stage) p_gioHang.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
