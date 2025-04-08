package iuh.fit.controller;

import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import iuh.fit.App;
import iuh.fit.daos.CaLam_dao;
import iuh.fit.daos.ChiTietHoaDon_SanPham_dao;
import iuh.fit.daos.HoaDon_dao;
import iuh.fit.daos.KhachHang_dao;
import iuh.fit.daos.NhanVien_dao;
import iuh.fit.daos.SanPham_dao;
import iuh.fit.entities.CaLam;
import iuh.fit.entities.ChiTietHoaDon_SanPham;
import iuh.fit.entities.ChiTietHoaDon_SanPhamId;
import iuh.fit.entities.HoaDon;
import iuh.fit.entities.KhachHang;
import iuh.fit.entities.NhanVien;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
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
    private Button btn_dangXuat;

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
    private TextField txt_timKiem;

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
    private CaLam_dao caLamDao;
    private NhanVien_dao nhanVienDao;

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
        caLamDao = new CaLam_dao();
        nhanVienDao = new NhanVien_dao();

        // Khởi tạo các menu
        addMenusToMap();

        // Hiển thị ngày và thời gian hiện tại
        displayCurrentDateTime();

        // Khởi tạo bảng giỏ hàng
        initializeTable();

        // Khởi tạo tổng tiền
        updateTotalAmount();
        System.out.println(App.user);
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
        try {
            // Hiển thị menu bán hàng
            if (banHangSubVBox != null && banHangSubMenuList != null) {
                toolsSlider(banHangSubVBox, banHangSubMenuList);
                removeOtherMenus(banHangSubVBox);
            }

            // Hiển thị giao diện bán hàng chính
            showMainSalesInterface();

            // Làm mới giỏ hàng
            updateTotalAmount();
        } catch (Exception e) {
            System.err.println("Lỗi trong handleGioHangClick: " + e.getMessage());
            e.printStackTrace();
        }
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
        try {
            // Chuyển đến giao diện TraCuu_gui.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TraCuu_gui.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Lấy stage hiện tại
            Stage stage = (Stage) p_gioHang.getScene().getWindow();

            // Thiết lập scene mới
            stage.setScene(scene);
            stage.setTitle("Tra cứu sản phẩm");

            // Hiển thị stage
            stage.show();

            System.out.println("Chuyển đến giao diện tra cứu thành công");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện tra cứu: " + e.getMessage());

            // Nếu không thể mở giao diện tra cứu, hiển thị giao diện tìm kiếm trên giao diện hiện tại
            toolsSlider(timKiemSubVBox, timKiemSubMenuList);
            removeOtherMenus(timKiemSubVBox);

            // Hiển thị giao diện tìm kiếm
            showSearchInterface();
        }
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

                // Thêm sự kiện nhấp đúp vào bảng
                tableView.setOnMouseClicked(this::handleSearchResultDoubleClick);

                // Thêm context menu cho bảng
                setupTableContextMenu();
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
            // Check if the cart is empty
            if (cartItems.isEmpty()) {
                showAlert(AlertType.WARNING, "Thông báo", "Giỏ hàng trống!");
                return;
            }

            // Validate customer information
            String tenKH = txt_tenKH.getText().trim();
            String sdt = txt_sdt.getText().trim();

            // Generate invoice ID
            String maHD = "HD" + System.currentTimeMillis();

            // Handle shift (CaLam)
            String maCa = "CA" + System.currentTimeMillis();
            CaLam caLam = caLamDao.read("CA001");
            if (caLam == null) {
                caLam = new CaLam();
                caLam.setMaCa(maCa);
                caLam.setGioBatDau(java.time.LocalDateTime.now());
                caLam.setGioKetThuc(java.time.LocalDateTime.now().plusHours(8));
                caLam.setTrangThai(true);

                if (App.taiKhoan != null) {
                    caLam.setTaiKhoan(App.taiKhoan);
                } else {
                    showAlert(AlertType.WARNING, "Cảnh báo", "Không có tài khoản đăng nhập, sử dụng tài khoản mặc định");
                    return;
                }

                caLamDao.create(caLam);
            }

            // Handle customer (KhachHang)
            KhachHang khachHang = null;
            if (!tenKH.isEmpty() && !sdt.isEmpty()) {
                khachHang = khachHangDao.findByPhone(sdt);
                if (khachHang == null) {
                    khachHang = new KhachHang();
                    khachHang.setMaKH("KH" + System.currentTimeMillis());
                    khachHang.setTenKH(tenKH);
                    khachHang.setSdt(sdt);
                    khachHangDao.create(khachHang);
                }
            } else {
                khachHang = khachHangDao.read("KH001");
                if (khachHang == null) {
                    khachHang = new KhachHang();
                    khachHang.setMaKH("KH001");
                    khachHang.setTenKH("Khách hàng vãng lai");
                    khachHang.setSdt("0000000000");
                    khachHangDao.create(khachHang);
                }
            }

            // Create invoice (HoaDon)
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaHD(maHD);
            hoaDon.setThoiGian(java.time.LocalDateTime.now());
            hoaDon.setTongSoLuongSP(productQuantities.values().stream().mapToInt(Integer::intValue).sum());
            hoaDon.setThanhTien(calculateTotal());
            hoaDon.setPhuongThucTT(PhuongThucThanhToan.Tien_Mat);
            hoaDon.setCaLam(caLam);
            hoaDon.setKhachHang(khachHang);
            hoaDonDao.create(hoaDon);

            // Create invoice details (ChiTietHoaDon_SanPham)
            for (SanPham sp : cartItems) {
                int soLuong = productQuantities.getOrDefault(sp.getMaSP(), 0);

                // Check stock availability
                if (sp.getSoLuongTon() < soLuong) {
                    showAlert(AlertType.WARNING, "Thông báo", "Sản phẩm " + sp.getTenSP() + " không đủ số lượng trong kho!");
                    return;
                }

                ChiTietHoaDon_SanPhamId chiTietId = new ChiTietHoaDon_SanPhamId(maHD, sp.getMaSP());
                ChiTietHoaDon_SanPham chiTiet = new ChiTietHoaDon_SanPham();
                chiTiet.setId(chiTietId);
                chiTiet.setSoLuongSP(soLuong);
                chiTiet.setDonGia(sp.getGiaBan());
                chiTiet.setHoaDon(hoaDon);
                chiTiet.setSanPham(sp);
                chiTietHoaDonDao.create(chiTiet);

                // Update stock
                sp.setSoLuongTon(sp.getSoLuongTon() - soLuong);
                sanPhamDao.update(sp);
            }

            // Clear cart and refresh UI
            cartItems.clear();
            productQuantities.clear();
            tableView.refresh();
            txt_tenKH.clear();
            txt_sdt.clear();
            txt_nhapMa.clear();
            txt_nhapSL.clear();
            updateTotalAmount();

            // Show success message
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
        try {
            // Làm mới form tìm kiếm
            txt_timKiem.clear();
            txt_timKiem.requestFocus();

            // Khởi tạo dữ liệu tìm kiếm nếu cần
            initializeSearchData();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể hiển thị giao diện tìm kiếm: " + e.getMessage());
        }
    }

    /**
     * Khởi tạo dữ liệu tìm kiếm
     */
    private void initializeSearchData() {
        try {
            // Tạo danh sách sản phẩm cho tìm kiếm
            ObservableList<SanPham> searchResults = FXCollections.observableArrayList();

            // Lấy tất cả sản phẩm từ database
            List<SanPham> allProducts = sanPhamDao.readAll();
            searchResults.addAll(allProducts);

            // Hiển thị kết quả tìm kiếm trong bảng
            // Sử dụng bảng hiện tại để hiển thị kết quả tìm kiếm
            tableView.setItems(searchResults);


            // Thêm sự kiện cho trường tìm kiếm
            setupSearchField();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể khởi tạo dữ liệu tìm kiếm: " + e.getMessage());
        }
    }

    /**
     * Thiết lập sự kiện cho trường tìm kiếm
     */
    private void setupSearchField() {
        // Thêm sự kiện khi nhập vào trường tìm kiếm
        txt_timKiem.textProperty().addListener((observable, oldValue, newValue) -> {
            performSearch(newValue);
        });
    }

    /**
     * Thực hiện tìm kiếm sản phẩm
     */
    private void performSearch(String keyword) {
        try {
            if (keyword == null || keyword.trim().isEmpty()) {
                // Nếu từ khóa trống, hiển thị tất cả sản phẩm
                List<SanPham> allProducts = sanPhamDao.readAll();
                tableView.setItems(FXCollections.observableArrayList(allProducts));
                return;
            }

            // Tìm kiếm sản phẩm theo từ khóa
            List<SanPham> searchResults = new ArrayList<>();
            List<SanPham> allProducts = sanPhamDao.readAll();

            // Lọc sản phẩm theo từ khóa (mã hoặc tên)
            String keywordLower = keyword.toLowerCase();
            for (SanPham sp : allProducts) {
                if (sp.getMaSP().toLowerCase().contains(keywordLower) ||
                    sp.getTenSP().toLowerCase().contains(keywordLower)) {
                    searchResults.add(sp);
                }
            }

            // Cập nhật bảng và trạng thái
            tableView.setItems(FXCollections.observableArrayList(searchResults));
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể thực hiện tìm kiếm: " + e.getMessage());
        }
    }

    /**
     * Xử lý sự kiện khi nhấp đúp vào sản phẩm trong kết quả tìm kiếm
     */
    @FXML
    private void handleSearchResultDoubleClick(MouseEvent event) {
        if (event.getClickCount() == 2) { // Nhấp đúp
            SanPham selectedProduct = tableView.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                // Chuyển sang giao diện bán hàng và thêm sản phẩm vào giỏ hàng
                handleGioHangClick(null);

                // Điền thông tin sản phẩm vào form
                txt_nhapMa.setText(selectedProduct.getMaSP());
                txt_nhapSL.setText("1"); // Mặc định số lượng là 1
                txt_nhapSL.requestFocus();
            }
        }
    }

    /**
     * Thiết lập context menu cho bảng giỏ hàng
     */
    private void setupTableContextMenu() {
        // Tạo context menu
        ContextMenu contextMenu = new ContextMenu();

        // Tạo menu item Xóa
        MenuItem deleteItem = new MenuItem("Xóa sản phẩm");
        deleteItem.setOnAction(event -> {
            // Lấy sản phẩm được chọn
            SanPham selectedProduct = tableView.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                // Xóa sản phẩm khỏi giỏ hàng
                removeProductFromCart(selectedProduct.getMaSP());
            }
        });

        // Tạo menu item Xóa tất cả
        MenuItem clearAllItem = new MenuItem("Xóa tất cả");
        clearAllItem.setOnAction(event -> {
            // Xóa tất cả sản phẩm trong giỏ hàng
            clearCart();
        });

        // Tạo menu item Sửa số lượng
        MenuItem editItem = new MenuItem("Sửa số lượng");
        editItem.setOnAction(event -> {
            // Lấy sản phẩm được chọn
            SanPham selectedProduct = tableView.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                // Hiển thị hộp thoại nhập số lượng mới
                editProductQuantity(selectedProduct);
            }
        });

        // Thêm các menu item vào context menu
        contextMenu.getItems().addAll(deleteItem, editItem, clearAllItem);

        // Gán context menu cho bảng
        tableView.setContextMenu(contextMenu);
    }

    /**
     * Sửa số lượng sản phẩm trong giỏ hàng
     */
    private void editProductQuantity(SanPham product) {
        try {
            // Tạo dialog để nhập số lượng mới
            TextInputDialog dialog = new TextInputDialog(productQuantities.getOrDefault(product.getMaSP(), 1).toString());
            dialog.setTitle("Sửa số lượng");
            dialog.setHeaderText("Sản phẩm: " + product.getTenSP());
            dialog.setContentText("Nhập số lượng mới:");

            // Hiển thị dialog và đợi kết quả
            dialog.showAndWait().ifPresent(result -> {
                try {
                    // Chuyển kết quả thành số
                    int newQuantity = Integer.parseInt(result);

                    // Kiểm tra số lượng hợp lệ
                    if (newQuantity <= 0) {
                        showAlert(AlertType.WARNING, "Cảnh báo", "Số lượng phải lớn hơn 0!");
                        return;
                    }

                    // Kiểm tra số lượng tồn kho
                    if (newQuantity > product.getSoLuongTon()) {
                        showAlert(AlertType.WARNING, "Cảnh báo", "Số lượng vượt quá số lượng tồn kho (" + product.getSoLuongTon() + ")!");
                        return;
                    }

                    // Cập nhật số lượng
                    productQuantities.put(product.getMaSP(), newQuantity);

                    // Cập nhật bảng và tổng tiền
                    tableView.refresh();
                    updateTotalAmount();

                    showAlert(AlertType.INFORMATION, "Thông báo", "Đã cập nhật số lượng sản phẩm!");
                } catch (NumberFormatException e) {
                    showAlert(AlertType.ERROR, "Lỗi", "Số lượng phải là số nguyên!");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể sửa số lượng sản phẩm: " + e.getMessage());
        }
    }

    /**
     * Xóa tất cả sản phẩm trong giỏ hàng
     */
    private void clearCart() {
        try {
            // Kiểm tra xem giỏ hàng có trống không
            if (cartItems.isEmpty()) {
                showAlert(AlertType.INFORMATION, "Thông báo", "Giỏ hàng đã trống!");
                return;
            }

            // Hiển thị hộp thoại xác nhận
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận xóa");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc chắn muốn xóa tất cả sản phẩm trong giỏ hàng?");

            // Nếu người dùng nhấn OK
            if (alert.showAndWait().get() == javafx.scene.control.ButtonType.OK) {
                // Xóa tất cả sản phẩm trong giỏ hàng
                cartItems.clear();
                productQuantities.clear();
                tableView.refresh();

                // Cập nhật tổng tiền
                updateTotalAmount();

                showAlert(AlertType.INFORMATION, "Thông báo", "Đã xóa tất cả sản phẩm trong giỏ hàng!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể xóa giỏ hàng: " + e.getMessage());
        }
    }

    /**
     * Xử lý sự kiện khi nhấn nút đăng xuất
     */
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

    /**
     * Cập nhật tổng tiền trong giỏ hàng
     */
    private void updateTotalAmount() {
        try {
            double total = calculateTotal();
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            if (lb_tongTien != null) {
                lb_tongTien.setText(currencyFormat.format(total));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (lb_tongTien != null) {
                lb_tongTien.setText("0 VNĐ");
            }
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
