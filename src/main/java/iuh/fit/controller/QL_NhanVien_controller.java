package iuh.fit.controller;

import iuh.fit.daos.KhachHang_dao;
import iuh.fit.daos.NhanVien_dao;
import iuh.fit.entities.KhachHang;
import iuh.fit.entities.NhanVien;
import iuh.fit.entities.SanPham;
import iuh.fit.enums.ChucVu;
import iuh.fit.enums.LoaiHang;
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

import java.net.URL;
import java.time.LocalDate;
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
    void handleGioHangClick(MouseEvent event) {

    }

    @FXML
    void handleQuanLyClick(MouseEvent event) {

    }

    @FXML
    void handleThongKeClick(MouseEvent event) {

    }

    @FXML
    void handleTimKiemClick(MouseEvent event) {
        try {
            loadFXML("/fxml/TraCuu_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện tra cứu!");
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

            // Tạo DAO object
            NhanVien_dao nvDAO = new NhanVien_dao();

            // Cập nhật thông tin nhân viên vào database
            nvDAO.updateNhanVien(nv);

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

            // Tạo DAO object
            NhanVien_dao nvDAO = new NhanVien_dao();

            // Thêm nhân viên vào database
            nvDAO.createNhanVien(nv);

            // Cập nhật lại dữ liệu trong bảng
            loadTableData();
            showAlert(Alert.AlertType.INFORMATION, "Thành công", "Thêm nhân viên thành công!");
        }catch (Exception e){
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể thêm nhân viên!");
        }
    }

    @FXML
    void toQLHoaDon(MouseEvent event) {
        try {
            // Chuyển đến giao diện quản lý hóa đơn
            loadFXML("/fxml/QL_HoaDon_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý hóa đơn!");
        }
    }

    @FXML
    void toQLKhachHang(MouseEvent event) {
        try {
            // Chuyển đến giao diện quản lý khách hàng
            loadFXML("/fxml/QL_KhachHang_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý khách hàng!");
        }
    }

    @FXML
    void toQLNhanVien(MouseEvent event) {
        try {
            // Chuyển đến giao diện quản lý nhân viên
            loadFXML("/fxml/QL_NhanVien_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý nhân viên!");
        }
    }

    @FXML
    void toQLPhieuNhap(MouseEvent event) {
        try {
            // Chuyển đến giao diện quản lý phiếu nhập
            loadFXML("/fxml/QL_PhieuNhap_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý phiếu nhập!");
        }
    }

    @FXML
    void toQLSanPham(MouseEvent event) {
        try {
            // Chuyển đến giao diện quản lý sản phẩm
            loadFXML("/fxml/QL_SanPham_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý sản phẩm!");
        }
    }

    @FXML
    void toQLTaiKhoan(MouseEvent event) {
        try {
            // Chuyển đến giao diện quản lý tài khoản
            loadFXML("/fxml/QL_TaiKhoan_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý tài khoản!");
        }
    }

    @FXML
    void toTKDoanhThu(MouseEvent event) {
        try {
            // Chuyển đến giao diện thống kê doanh thu
            loadFXML("/fxml/ThongKeDoanhThu_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở giao diện thống kê doanh thu!");
        }
    }

    @FXML
    void toTKSanPham(MouseEvent event) {
        try {
            // Chuyển đến giao diện thống kê sản phẩm
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
    void xoaNV(MouseEvent event) {
        try{
            // Lấy mã nhân viên từ trường nhập liệu
            String maNV = txt_MaNV.getText();

            // Tạo DAO object
            NhanVien_dao nvDAO = new NhanVien_dao();

            // Xóa nhân viên khỏi database
            nvDAO.deleteNhanVien(maNV);

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
            // Tạo DAO object
            NhanVien_dao nvDAO = new NhanVien_dao();

            // Xóa dữ liệu cũ trong table
            table_NV.getItems().clear();

            // Lấy danh sách nhaan vieen từ database
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
