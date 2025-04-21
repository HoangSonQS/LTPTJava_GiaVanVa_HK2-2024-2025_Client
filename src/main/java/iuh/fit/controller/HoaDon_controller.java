package iuh.fit.controller;

import iuh.fit.entities.SanPham;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.ResourceBundle;

public class HoaDon_controller implements Initializable {

    @FXML
    private TableColumn<SanPham, Double> DonGia_col;

    @FXML
    private TableColumn<SanPham, Integer> SoLuong_Col;

    @FXML
    private TableColumn<SanPham, String> TenSP_col;

    @FXML
    private TableColumn<SanPham, Double> ThanhTien_col;

    @FXML
    private TableView<SanPham> table_ThongTinSP;

    @FXML
    private Text txtGiamGia;

    @FXML
    private Text txtKhachHang;

    @FXML
    private Text txtLuongGiamGia;

    @FXML
    private Text txtThanhToan;

    @FXML
    private Text txtThoiGianLap;

    @FXML
    private Text txtTienKhach;

    @FXML
    private Text txtTienThua;

    @FXML
    private Text txtTienThueVAT;

    @FXML
    private Text txtTongTien;

    @FXML
    private Text txt_HoaDon;

    @FXML
    private Text txt_NhanVien;

    private ObservableList<SanPham> cartItems;
    private Map<String, Integer> productQuantities;

    // Truyền dữ liệu giỏ hàng
    public void setCartData(ObservableList<SanPham> items, Map<String, Integer> quantities) {
        this.cartItems = items;
        this.productQuantities = quantities;

        if (table_ThongTinSP != null) {
            table_ThongTinSP.setItems(cartItems);

            TenSP_col.setCellValueFactory(new PropertyValueFactory<>("tenSP"));
            SoLuong_Col.setCellValueFactory(cellData -> {
                SanPham sp = cellData.getValue();
                int soLuong = productQuantities.getOrDefault(sp.getMaSP(), 0);
                return new ReadOnlyObjectWrapper<>(soLuong);
            });
            DonGia_col.setCellValueFactory(new PropertyValueFactory<>("giaBan"));
            ThanhTien_col.setCellValueFactory(cellData -> {
                SanPham sp = cellData.getValue();
                int soLuong = productQuantities.getOrDefault(sp.getMaSP(), 0);
                return new ReadOnlyObjectWrapper<>(soLuong * sp.getGiaBan());
            });

            table_ThongTinSP.refresh();
        }
    }

    // Truyền thông tin hóa đơn
    public void setHoaDonInfo(double tamTinh, double tongTien, double tienKhach, double tienThua, double giamGia,
                              String maHD, String maKH, String maNV) {
        txtTongTien.setText(String.format("%.2f",tamTinh));
        txtTienThueVAT.setText(String.format("%.2f",tamTinh * 0.08));
        txtThanhToan.setText(String.format("%.2f",tongTien));
        txtTienKhach.setText(String.format("%.2f",tienKhach));
        txtTienThua.setText(String.format("%.2f",tienThua));
        txtGiamGia.setText(String.format("%.2f", giamGia));
        txt_HoaDon.setText(maHD);
        txtKhachHang.setText(maKH);
        txt_NhanVien.setText(maNV);
        txtThoiGianLap.setText(String.valueOf(LocalDateTime.now()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Không lấy dữ liệu ở đây nữa để tránh lỗi null/static
        // Dữ liệu sẽ được truyền từ BanHang_controller sau khi load
    }
}
