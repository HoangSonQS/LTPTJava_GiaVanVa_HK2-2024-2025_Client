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

public class DataFakerRunner {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb")
                .createEntityManager();

        EntityTransaction tr = em.getTransaction();
        Faker faker = new Faker();

        List<String> tenSanPhamVN = Arrays.asList(
                "Sữa tươi Vinamilk 1L",
                "Mì Hảo Hảo tôm chua cay",
                "Bánh Oreo socola",
                "Nước suối Lavie 1.5L",
                "Snack khoai tây Poca",
                "Cà phê G7 3in1",
                "Sữa đặc Ông Thọ",
                "Dầu ăn Tường An 1L",
                "Bột giặt Omo 2.7kg",
                "Khăn giấy BlessYou",
                "Sữa chua uống Probi",
                "Ngũ cốc Milo Nestlé",
                "Bánh mì sandwich ABC",
                "Thịt hộp Tulip",
                "Phô mai Con Bò Cười",
                "Nước ép cam Twister",
                "Trà Ô Long Tea+ Plus"
        );


        // Tạo danh sách để lưu trữ các entity đã tạo
        List<SanPham> sanPhams = new ArrayList<>();
        List<KhachHang> khachHangs = new ArrayList<>();
        List<NhanVien> nhanViens = new ArrayList<>();
        List<TaiKhoan> taiKhoans = new ArrayList<>();
        List<CaLam> caLams = new ArrayList<>();

        // Số lượng mỗi loại entity bạn muốn tạo
        int soLuongSanPham = 50;
        int soLuongKhachHang = 20;
        int soLuongNhanVien = 10;
        int soLuongHoaDon = 100;

        // 1. Tạo sản phẩm
        tr.begin();
        for (int i = 0; i < soLuongSanPham; i++) {
            SanPham sanPham = new SanPham();
            sanPham.setMaSP("SP" + faker.number().digits(6));
            String productName = tenSanPhamVN.get(faker.random().nextInt(tenSanPhamVN.size()));
            if (productName.length() > 100) {
                productName = productName.substring(0, 100);
            }
            sanPham.setTenSP(productName);
            sanPham.setNhaCC(faker.company().name());
            sanPham.setSoLuongTon(faker.number().numberBetween(1, 100));
            sanPham.setGiaNhap(faker.number().randomDouble(0, 5000, 100000));
            sanPham.setGiaBan(faker.number().randomDouble(0, 10000, 200000));
            sanPham.setThoiGianCapNhat(LocalDateTime.now());
            sanPham.setLoaiHang(faker.options().option(LoaiHang.class));

            em.persist(sanPham);
            sanPhams.add(sanPham);

            // Commit sau mỗi 20 sản phẩm để tránh transaction quá lớn
            if (i % 20 == 0 && i > 0) {
                tr.commit();
                tr.begin();
            }
        }
        tr.commit();

        // 2. Tạo khách hàng
        tr.begin();
        for (int i = 0; i < soLuongKhachHang; i++) {
            KhachHang khachHang = new KhachHang();
            khachHang.setMaKH("KH" + faker.number().digits(6));
            khachHang.setTenKH(faker.name().fullName());
            khachHang.setSdt(faker.phoneNumber().cellPhone());

            em.persist(khachHang);
            khachHangs.add(khachHang);
        }
        tr.commit();

        // 3. Tạo nhân viên và tài khoản
        tr.begin();
        for (int i = 0; i < soLuongNhanVien; i++) {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setMaNV("NV" + faker.number().digits(6));
            nhanVien.setTenNV(faker.name().fullName());
            nhanVien.setCccd(faker.number().digits(12));
            nhanVien.setDiaChi(faker.address().fullAddress());
            nhanVien.setEmail(faker.internet().emailAddress());
            nhanVien.setSdt(faker.phoneNumber().cellPhone());
            nhanVien.setNgaySinh(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            nhanVien.setChucVu(faker.options().option(ChucVu.class));

            em.persist(nhanVien);
            nhanViens.add(nhanVien);

            // Tạo tài khoản cho nhân viên
            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setMaTaiKhoan("TK" + faker.number().digits(6));
            taiKhoan.setTenDangNhap(faker.name().username());
            taiKhoan.setMatKhau(faker.internet().password());
            taiKhoan.setThoiGianDangNhap(LocalDateTime.now());
            taiKhoan.setNhanVien(nhanVien);

            em.persist(taiKhoan);
            taiKhoans.add(taiKhoan);

            // Tạo ca làm cho nhân viên
            CaLam caLam = new CaLam();
            caLam.setMaCa("CA" + faker.number().digits(6));
            caLam.setGioBatDau(LocalDateTime.now());
            caLam.setGioKetThuc(LocalDateTime.now().plusHours(8));
            caLam.setTrangThai(faker.bool().bool());
            caLam.setTaiKhoan(taiKhoan);

            em.persist(caLam);
            caLams.add(caLam);
        }
        tr.commit();

        // 4. Tạo phiếu nhập hàng
        tr.begin();
        for (int i = 0; i < 20; i++) {
            NhanVien randomNV = nhanViens.get(faker.random().nextInt(nhanViens.size()));

            PhieuNhapHang phieuNhapHang = new PhieuNhapHang();
            phieuNhapHang.setMaPNH("PNH" + faker.number().digits(6));
            phieuNhapHang.setMaNV(randomNV.getMaNV());
            phieuNhapHang.setTenNV(randomNV.getTenNV());
            phieuNhapHang.setThoiGian(LocalDateTime.now().minusDays(faker.number().numberBetween(1, 30)));
            phieuNhapHang.setTongSoLuongSP(faker.number().numberBetween(10, 100));
            phieuNhapHang.setThanhTien(faker.number().randomDouble(2, 500000, 10000000));

            em.persist(phieuNhapHang);

            // Tạo chi tiết phiếu nhập cho 5-10 sản phẩm ngẫu nhiên
            int numProducts = faker.number().numberBetween(5, 10);
            List<SanPham> randomSPs = new ArrayList<>(sanPhams);
            Collections.shuffle(randomSPs); // xáo trộn để lấy ngẫu nhiên
            for (int j = 0; j < numProducts; j++) {
                SanPham randomSP = randomSPs.get(j);

                ChiTietSanPham_PhieuNhap chiTiet = new ChiTietSanPham_PhieuNhap();
                ChiTietSanPham_PhieuNhapId chiTietId = new ChiTietSanPham_PhieuNhapId();
                chiTietId.setMaPNH(phieuNhapHang.getMaPNH());
                chiTietId.setMaSP(randomSP.getMaSP());
                chiTiet.setId(chiTietId);
                chiTiet.setSoLuongSP(faker.number().numberBetween(5, 50));
                chiTiet.setDonGia(randomSP.getGiaNhap());
                chiTiet.setPhieuNhapHang(phieuNhapHang);
                chiTiet.setSanPham(randomSP);

                em.merge(chiTiet);
            }

            // Commit sau mỗi phiếu nhập để tránh transaction quá lớn
            tr.commit();
            if (i < 19) tr.begin();
        }

        // 5. Tạo hóa đơn và chi tiết hóa đơn
        tr.begin();
        for (int i = 0; i < soLuongHoaDon; i++) {
            // Chọn ngẫu nhiên từ danh sách đã tạo
            NhanVien randomNV = nhanViens.get(faker.random().nextInt(nhanViens.size()));
            KhachHang randomKH = khachHangs.get(faker.random().nextInt(khachHangs.size()));
            CaLam randomCaLam = caLams.get(faker.random().nextInt(caLams.size()));

            // Tạo thời gian ngẫu nhiên từ đầu 2024 đến hiện tại
            LocalDate startDate = LocalDate.of(2024, 1, 1);
            LocalDate endDate = LocalDate.now();
            long days = ChronoUnit.DAYS.between(startDate, endDate);
            long randomDays = faker.number().numberBetween(0L, days);
            LocalDate randomDate = startDate.plusDays(randomDays);

            // Tạo giờ ngẫu nhiên trong ngày
            int hour = faker.number().numberBetween(8, 22);
            int minute = faker.number().numberBetween(0, 60);
            LocalDateTime randomDateTime = randomDate.atTime(hour, minute);

            // Tạo hóa đơn
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaHD("HD" + faker.number().digits(6));
            hoaDon.setMaNV(randomNV.getMaNV());
            hoaDon.setMaKH(randomKH.getMaKH());
            hoaDon.setThoiGian(randomDateTime);
            hoaDon.setNhanVien(randomNV);
            hoaDon.setKhachHang(randomKH);
            hoaDon.setCaLam(randomCaLam);
            hoaDon.setPhuongThucTT(faker.options().option(PhuongThucThanhToan.class));

            // Persist hóa đơn trước
            em.persist(hoaDon);
            em.flush(); // Đảm bảo hóa đơn được lưu vào DB

            // Tạo chi tiết hóa đơn với 1-5 sản phẩm
            int soLuongSP = faker.number().numberBetween(1, 6);
            double tongTien = 0;
            int tongSoLuong = 0;

            for (int j = 0; j < soLuongSP; j++) {
                SanPham randomSP = em.merge(sanPhams.get(faker.random().nextInt(sanPhams.size()))); // Merge sản phẩm
                int soLuong = faker.number().numberBetween(1, 10);
                double donGia = randomSP.getGiaBan();

                ChiTietHoaDon_SanPham chiTiet = new ChiTietHoaDon_SanPham();
                ChiTietHoaDon_SanPhamId chiTietId = new ChiTietHoaDon_SanPhamId();
                chiTietId.setMaHD(hoaDon.getMaHD());
                chiTietId.setMaSP(randomSP.getMaSP());
                chiTiet.setId(chiTietId);
                chiTiet.setSoLuongSP(soLuong);
                chiTiet.setDonGia(donGia);
                chiTiet.setHoaDon(hoaDon);
                chiTiet.setSanPham(randomSP);

                tongTien += soLuong * donGia;
                tongSoLuong += soLuong;

                em.merge(chiTiet); // Sử dụng merge thay vì persist
            }

            // Cập nhật tổng tiền và số lượng
            hoaDon.setThanhTien(tongTien);
            hoaDon.setTongSoLuongSP(tongSoLuong);
            em.merge(hoaDon); // Merge lại hóa đơn sau khi cập nhật

            // Commit sau mỗi 20 hóa đơn để tránh transaction quá lớn
            if (i % 20 == 0 && i > 0) {
                tr.commit();
                em.clear(); // Clear session sau mỗi commit
                if (i < soLuongHoaDon - 1) tr.begin();
            }
        }

        // Commit nếu transaction chưa được commit
        if (tr.isActive()) {
            tr.commit();
        }

        em.close();
        System.out.println("Đã tạo xong dữ liệu!");
    }
}