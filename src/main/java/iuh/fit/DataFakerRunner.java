package iuh.fit;

import iuh.fit.entities.*;
import iuh.fit.enums.ChucVu;
import iuh.fit.enums.LoaiHang;
import iuh.fit.enums.PhuongThucThanhToan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class DataFakerRunner {
    public static void main(String[] args) {
        try {
            EntityManager em = Persistence.createEntityManagerFactory("mariadb")
                    .createEntityManager();

            EntityTransaction tr = em.getTransaction();
            Faker faker = new Faker(new Locale("vi"));

            List<String[]> danhSachSanPham = Arrays.asList(
                    new String[] {"Sữa tươi Vinamilk 100% Organic 1L", "Công ty CP Vinamilk", "28000", "32000", "THUC_PHAM"},
                    new String[] {"Mì Hảo Hảo tôm chua cay gói", "Công ty Acecook Việt Nam", "3500", "4000", "THUC_PHAM"},
                    new String[] {"Bánh Custas kem trứng 470g", "Công ty Mondelez Kinh Đô", "45000", "55000", "THUC_PHAM"},
                    new String[] {"Nước suối Aquafina 1.5L", "Công ty TNHH Nước giải khát Suntory PepsiCo VN", "7000", "10000", "THUC_PHAM"},
                    new String[] {"Snack khoai tây Lay's vị tự nhiên 95g", "Công ty PepsiCo Việt Nam", "12000", "15000", "THUC_PHAM"},
                    new String[] {"Cà phê hòa tan G7 3in1 50 gói", "Công ty CP Trung Nguyên", "55000", "65000", "THUC_PHAM"},
                    new String[] {"Sữa đặc Ông Thọ trắng 380g", "Công ty CP Vinamilk", "25000", "28000", "THUC_PHAM"},
                    new String[] {"Dầu ăn Simply 1L", "Công ty CP Dầu thực vật Tường An", "45000", "50000", "THUC_PHAM"},
                    new String[] {"Nước giặt Omo Matic 2.7kg", "Công ty TNHH Unilever Việt Nam", "145000", "165000", "DO_GIA_DUNG"},
                    new String[] {"Khăn giấy Pulppy 2 lớp", "Công ty CP Giấy Sài Gòn", "22000", "25000", "DO_GIA_DUNG"},
                    new String[] {"Trà xanh C2 chai 455ml", "Công ty TNHH URC Việt Nam", "6000", "8000", "THUC_PHAM"},
                    new String[] {"Bánh quy Cosy Marie 144g", "Công ty Mondelez Kinh Đô", "12000", "15000", "THUC_PHAM"},
                    new String[] {"Nước ngọt Coca-Cola lon 330ml", "Công ty TNHH Nước giải khát Coca-Cola VN", "8000", "10000", "THUC_PHAM"},
                    new String[] {"Sữa chua Vinamilk lốc 4 hộp", "Công ty CP Vinamilk", "22000", "26000", "THUC_PHAM"},
                    new String[] {"Mì Omachi bò hầm gói 80g", "Công ty Acecook Việt Nam", "6000", "7000", "THUC_PHAM"},
                    new String[] {"Bột giặt Tide 2.5kg", "Công ty P&G Việt Nam", "130000", "150000", "DO_GIA_DUNG"},
                    new String[] {"Nước rửa chén Sunlight 750ml", "Công ty TNHH Unilever Việt Nam", "25000", "30000", "DO_GIA_DUNG"},
                    new String[] {"Phở gà Vifon gói 65g", "Công ty CP Kỹ nghệ thực phẩm Việt Nam", "5000", "6000", "THUC_PHAM"},
                    new String[] {"Nước tăng lực Red Bull lon 250ml", "Công ty TCP Việt Nam", "12000", "15000", "THUC_PHAM"},
                    new String[] {"Bánh Choco-pie Orion hộp 12 cái", "Công ty Mondelez Kinh Đô", "45000", "50000", "THUC_PHAM"},
                    new String[] {"Nồi cơm điện Sunhouse 1.8L", "Công ty TNHH Sunhouse Việt Nam", "300000", "350000", "DO_GIA_DUNG"},
                    new String[] {"Bàn ủi hơi nước Philips 2000W", "Công ty Philips Việt Nam", "450000", "500000", "DO_GIA_DUNG"},
                    new String[] {"Bình giữ nhiệt Lock&Lock 500ml", "Công ty Lock&Lock Việt Nam", "150000", "180000", "DO_GIA_DUNG"},
                    new String[] {"Chảo chống dính Supor 24cm", "Công ty Supor Việt Nam", "120000", "150000", "DO_GIA_DUNG"},
                    new String[] {"Máy xay sinh tố Sunhouse 1.5L", "Công ty TNHH Sunhouse Việt Nam", "400000", "450000", "DO_GIA_DUNG"},
                    new String[] {"Bộ hộp đựng thực phẩm Lock&Lock 3 cái", "Công ty Lock&Lock Việt Nam", "100000", "120000", "DO_GIA_DUNG"},
                    new String[] {"Quạt điện Senko 3 cánh 40cm", "Công ty TNHH Senko Việt Nam", "250000", "300000", "DO_GIA_DUNG"},
                    new String[] {"Áo sơ mi nam Việt Tiến", "Công ty CP May Việt Tiến", "200000", "250000", "THOI_TRANG_VA_PHU_KIEN"},
                    new String[] {"Quần jeans nam Blue Exchange", "Công ty TNHH Blue Exchange", "300000", "350000", "THOI_TRANG_VA_PHU_KIEN"},
                    new String[] {"Áo thun nữ Canifa", "Công ty CP Canifa", "150000", "180000", "THOI_TRANG_VA_PHU_KIEN"},
                    new String[] {"Váy liền nữ K&K Fashion", "Công ty TNHH K&K Fashion", "250000", "300000", "THOI_TRANG_VA_PHU_KIEN"},
                    new String[] {"Giày thể thao Biti's Hunter", "Công ty CP Biti's", "400000", "450000", "THOI_TRANG_VA_PHU_KIEN"},
                    new String[] {"Túi xách nữ Juno", "Công ty CP Juno", "350000", "400000", "THOI_TRANG_VA_PHU_KIEN"},
                    new String[] {"Nón kết thời trang Novelty", "Công ty TNHH Novelty", "80000", "100000", "THOI_TRANG_VA_PHU_KIEN"},
                    new String[] {"Thắt lưng nam Pierre Cardin", "Công ty TNHH Pierre Cardin VN", "200000", "250000", "THOI_TRANG_VA_PHU_KIEN"},
                    new String[] {"Đồng hồ nữ Casio", "Công ty TNHH Casio Việt Nam", "500000", "600000", "THOI_TRANG_VA_PHU_KIEN"},
                    new String[] {"Sữa tắm Dove 900ml", "Công ty TNHH Unilever Việt Nam", "120000", "140000", "DO_GIA_DUNG"},
                    new String[] {"Dầu gội Head & Shoulders 650ml", "Công ty P&G Việt Nam", "100000", "120000", "DO_GIA_DUNG"},
                    new String[] {"Kem đánh răng Colgate 180g", "Công ty TNHH Colgate-Palmolive VN", "25000", "30000", "DO_GIA_DUNG"},
                    new String[] {"Nước xả vải Downy 2.2L", "Công ty P&G Việt Nam", "110000", "130000", "DO_GIA_DUNG"},
                    new String[] {"Trà Lipton túi lọc 25 gói", "Công ty TNHH Unilever Việt Nam", "35000", "40000", "THUC_PHAM"},
                    new String[] {"Milo hộp 400g", "Công ty CP Nestlé Việt Nam", "60000", "70000", "THUC_PHAM"},
                    new String[] {"Nước mắm Nam Ngư 900ml", "Công ty CP Hàng tiêu dùng Masan", "35000", "40000", "THUC_PHAM"},
                    new String[] {"Bột ngọt Ajinomoto 400g", "Công ty TNHH Ajinomoto Việt Nam", "30000", "35000", "THUC_PHAM"},
                    new String[] {"Bánh trung thu Kinh Đô nhân đậu xanh", "Công ty Mondelez Kinh Đô", "50000", "60000", "THUC_PHAM"},
                    new String[] {"Nước ngọt Pepsi lon 330ml", "Công ty TNHH Nước giải khát Suntory PepsiCo VN", "8000", "10000", "THUC_PHAM"},
                    new String[] {"Sữa chua uống Yakult lốc 5 chai", "Công ty TNHH Yakult Việt Nam", "35000", "40000", "THUC_PHAM"},
                    new String[] {"Bộ dao thớt nhà bếp Sunhouse", "Công ty TNHH Sunhouse Việt Nam", "80000", "100000", "DO_GIA_DUNG"},
                    new String[] {"Áo khoác nam Owen", "Công ty CP Owen", "350000", "400000", "THOI_TRANG_VA_PHU_KIEN"},
                    new String[] {"Khăn tắm cotton GHome", "Công ty TNHH GHome Việt Nam", "100000", "120000", "DO_GIA_DUNG"}
            );


            // Danh sách tên Việt Nam thực tế
            List<String[]> danhSachNhanVien = Arrays.asList(
                    // Format: Họ tên, Email, Địa chỉ, Chức vụ
                    new String[] {"Nguyễn Văn An", "annv@gmail.com", "123 Lê Lợi, Q.1, TP.HCM", "Nguoi_Quan_Ly"},
                    new String[] {"Trần Thị Bình", "binhtt@gmail.com", "456 Nguyễn Huệ, Q.1, TP.HCM", "Nhan_Vien"},
                    new String[] {"Lê Hoàng Nam", "namlh@gmail.com", "789 Cách Mạng Tháng 8, Q.3, TP.HCM", "Nhan_Vien"},
                    new String[] {"Phạm Thị Dung", "dungpt@gmail.com", "101 Nguyễn Thị Minh Khai, Q.3, TP.HCM", "Nhan_Vien"},
                    new String[] {"Hoàng Văn Em", "emhv@gmail.com", "202 Trần Hưng Đạo, Q.5, TP.HCM", "Nguoi_Quan_Ly"},
                    new String[] {"Ngô Thị Phương", "phuongnt@gmail.com", "303 Lý Thường Kiệt, Q.10, TP.HCM", "Nhan_Vien"},
                    new String[] {"Vũ Văn Giang", "giangvv@gmail.com", "404 Võ Văn Tần, Q.3, TP.HCM", "Nhan_Vien"},
                    new String[] {"Đặng Thị Hoa", "hoadt@gmail.com", "505 Nguyễn Trãi, Q.5, TP.HCM", "Nhan_Vien"},
                    new String[] {"Bùi Văn Inh", "inhbv@gmail.com", "606 Đinh Tiên Hoàng, Q.Bình Thạnh, TP.HCM", "Nhan_Vien"},
                    new String[] {"Đỗ Thị Kim", "kimdt@gmail.com", "707 Phan Đình Phùng, Q.3, TP.HCM", "Nguoi_Quan_Ly"}
            );


            // Danh sách khách hàng thực tế
            List<String[]> danhSachKhachHang = Arrays.asList(
                    // Format: Họ tên, Số điện thoại
                    new String[] {"Phạm Văn Cường", "0901234567"},
                    new String[] {"Nguyễn Thị Dung", "0912345678"},
                    new String[] {"Trần Văn Em", "0923456789"},
                    new String[] {"Hoàng Thị Phương", "0934567890"},
                    new String[] {"Lê Văn Giang", "0945678901"},
                    new String[] {"Vũ Thị Hoa", "0956789012"},
                    new String[] {"Đặng Văn Inh", "0967890123"},
                    new String[] {"Bùi Thị Kim", "0978901234"},
                    new String[] {"Đỗ Văn Lâm", "0989012345"},
                    new String[] {"Lý Thị Minh", "0990123456"},
                    new String[] {"Phan Văn Nam", "0901234568"},
                    new String[] {"Trương Thị Oanh", "0912345679"},
                    new String[] {"Huỳnh Văn Phúc", "0923456780"},
                    new String[] {"Võ Thị Quỳnh", "0934567891"},
                    new String[] {"Nguyễn Văn Sơn", "0945678902"},
                    new String[] {"Lê Thị Trang", "0956789013"},
                    new String[] {"Trần Văn Uy", "0967890124"},
                    new String[] {"Phan Thị Vân", "0978901235"},
                    new String[] {"Ngô Văn Xuân", "0989012346"},
                    new String[] {"Mai Thị Yến", "0990123457"}
            );


            // Tạo danh sách để lưu trữ các entity
            List<SanPham> sanPhams = new ArrayList<>();
            List<KhachHang> khachHangs = new ArrayList<>();
            List<NhanVien> nhanViens = new ArrayList<>();
            List<TaiKhoan> taiKhoans = new ArrayList<>();
            List<CaLam> caLams = new ArrayList<>();

            // 1. Tạo sản phẩm với dữ liệu thực tế
            tr.begin();
            for (String[] spInfo : danhSachSanPham) {
                SanPham sanPham = new SanPham();
                sanPham.setMaSP("SP" + faker.number().digits(6));
                sanPham.setTenSP(spInfo[0]);
                sanPham.setNhaCC(spInfo[1]);
                sanPham.setSoLuongTon(faker.number().numberBetween(10, 200));
                sanPham.setGiaNhap(Double.parseDouble(spInfo[2]));
                sanPham.setGiaBan(Double.parseDouble(spInfo[3]));
                sanPham.setThoiGianCapNhat(LocalDateTime.now());
                sanPham.setLoaiHang(LoaiHang.valueOf(spInfo[4]));
                // Thêm ngày sản xuất và hạn sử dụng
                LocalDateTime now = LocalDateTime.now();
                sanPham.setNgaySX(now.minusMonths(1));
                sanPham.setHanSD(now.plusMonths(6));

                em.persist(sanPham);
                sanPhams.add(sanPham);
            }
            tr.commit();

            // 2. Tạo khách hàng với dữ liệu thực tế
            tr.begin();
            for (String[] khInfo : danhSachKhachHang) {
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH("KH" + faker.number().digits(6));
                khachHang.setTenKH(khInfo[0]);
                khachHang.setSdt(khInfo[1]);

                em.persist(khachHang);
                khachHangs.add(khachHang);
            }
            tr.commit();

            // 3. Tạo nhân viên, tài khoản và ca làm
            tr.begin();
            for (String[] nvInfo : danhSachNhanVien) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV("NV" + faker.number().digits(6));
                nhanVien.setTenNV(nvInfo[0]);
                nhanVien.setEmail(nvInfo[1]);
                nhanVien.setDiaChi(nvInfo[2]);
                nhanVien.setCccd(faker.number().digits(12));
                nhanVien.setSdt("09" + faker.number().digits(8));
                nhanVien.setNgaySinh(LocalDate.now().minusYears(faker.number().numberBetween(20, 40)));
                nhanVien.setChucVu(ChucVu.valueOf(nvInfo[3]));

                em.persist(nhanVien);
                nhanViens.add(nhanVien);

                // Tạo tài khoản
                TaiKhoan taiKhoan = new TaiKhoan();
                taiKhoan.setMaTaiKhoan("TK" + faker.number().digits(6));
                taiKhoan.setTenDangNhap(nvInfo[1].split("@")[0]);
                taiKhoan.setMatKhau("123456");
                taiKhoan.setThoiGianDangNhap(LocalDateTime.now());
                taiKhoan.setNhanVien(nhanVien);

                em.persist(taiKhoan);
                taiKhoans.add(taiKhoan);

                // Tạo ca làm
                CaLam caLam = new CaLam();
                caLam.setMaCa("CA" + faker.number().digits(6));
                caLam.setGioBatDau(LocalDateTime.now().withHour(8).withMinute(0));
                caLam.setGioKetThuc(LocalDateTime.now().withHour(17).withMinute(0));
                caLam.setTrangThai(true);
                caLam.setTaiKhoan(taiKhoan);

                em.persist(caLam);
                caLams.add(caLam);
            }
            tr.commit();

            // 4. Tạo hóa đơn với dữ liệu từ 2024 đến nay
            tr.begin();
            LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 8, 0);
            LocalDateTime endDate = LocalDateTime.now();
            long daysBetween = ChronoUnit.DAYS.between(startDate.toLocalDate(), endDate.toLocalDate());

            for (int i = 0; i < 100; i++) {
                // Chọn ngẫu nhiên thời gian trong khoảng từ 1/1/2024 đến hiện tại
                long randomDays = faker.number().numberBetween(0, daysBetween);
                LocalDateTime randomDateTime = startDate.plusDays(randomDays)
                        .withHour(faker.number().numberBetween(8, 22))
                        .withMinute(faker.number().numberBetween(0, 59));

                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHD("HD" + faker.number().digits(6));

                // Chọn ngẫu nhiên nhân viên và khách hàng
                NhanVien randomNV = nhanViens.get(faker.random().nextInt(nhanViens.size()));
                KhachHang randomKH = khachHangs.get(faker.random().nextInt(khachHangs.size()));
                CaLam randomCa = caLams.get(faker.random().nextInt(caLams.size()));

                hoaDon.setMaNV(randomNV.getMaNV());
                hoaDon.setMaKH(randomKH.getMaKH());
                hoaDon.setThoiGian(randomDateTime);
                hoaDon.setNhanVien(randomNV);
                hoaDon.setKhachHang(randomKH);
                hoaDon.setCaLam(randomCa);
                hoaDon.setPhuongThucTT(faker.options().option(PhuongThucThanhToan.class));

                em.persist(hoaDon);

                // Tạo chi tiết hóa đơn (1-5 sản phẩm mỗi hóa đơn)
                int soLuongSP = faker.number().numberBetween(1, 6);
                double tongTien = 0;
                int tongSoLuong = 0;

                List<SanPham> randomSPs = new ArrayList<>(sanPhams);
                Collections.shuffle(randomSPs);

                for (int j = 0; j < soLuongSP; j++) {
                    SanPham sp = randomSPs.get(j);
                    int soLuong = faker.number().numberBetween(1, 5);

                    ChiTietHoaDon_SanPham chiTiet = new ChiTietHoaDon_SanPham();
                    ChiTietHoaDon_SanPhamId chiTietId = new ChiTietHoaDon_SanPhamId();
                    chiTietId.setMaHD(hoaDon.getMaHD());
                    chiTietId.setMaSP(sp.getMaSP());
                    chiTiet.setId(chiTietId);
                    chiTiet.setSoLuongSP(soLuong);
                    chiTiet.setDonGia(sp.getGiaBan());
                    chiTiet.setHoaDon(hoaDon);
                    chiTiet.setSanPham(sp);

                    tongTien += soLuong * sp.getGiaBan();
                    tongSoLuong += soLuong;

                    em.persist(chiTiet);
                }

                hoaDon.setThanhTien(tongTien);
                hoaDon.setTongSoLuongSP(tongSoLuong);
                em.merge(hoaDon);

                if (i % 20 == 0) {
                    tr.commit();
                    tr.begin();
                }
            }
            tr.commit();

            em.close();
            System.out.println("Đã tạo xong dữ liệu mẫu!");
        } catch (Exception e) {
            System.err.println("Error occurred: ");
            e.printStackTrace();
        }
    }
}