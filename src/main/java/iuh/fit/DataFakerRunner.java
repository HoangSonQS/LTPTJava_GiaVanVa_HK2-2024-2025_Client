package iuh.fit;

import iuh.fit.entities.*;
import iuh.fit.enums.ChucVu;
import iuh.fit.enums.LoaiHang;
import iuh.fit.enums.PhuongThucThanhToan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class DataFakerRunner {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb")
                .createEntityManager();

        EntityTransaction tr = em.getTransaction();
        Faker faker = new Faker();

        for (int i = 0; i < 10; i++) {

            // SanPham entity
            SanPham sanPham = new SanPham();
            sanPham.setMaSP(faker.idNumber().valid()); // Set the identifier
            sanPham.setTenSP(faker.commerce().productName());
            sanPham.setNhaCC(faker.company().name());
            sanPham.setSoLuongTon(faker.number().numberBetween(1, 100));
            sanPham.setGiaNhap(faker.number().randomDouble(2, 1, 1000));
            sanPham.setGiaBan(faker.number().randomDouble(2, 1, 1000));
            sanPham.setThoiGianCapNhat(LocalDateTime.now());
            sanPham.setLoaiHang(faker.options().option(LoaiHang.class));

            // KhachHang entity
            KhachHang khachHang = new KhachHang();
            khachHang.setMaKH(faker.idNumber().valid()); // Set the identifier
            khachHang.setTenKH(faker.name().fullName());
            khachHang.setSdt(faker.phoneNumber().cellPhone());


            // NhanVien entity
            NhanVien nhanVien = new NhanVien();
            nhanVien.setMaNV(faker.idNumber().valid()); // Set the identifier
            nhanVien.setTenNV(faker.name().fullName());
            nhanVien.setCccd(faker.number().digits(12));
            nhanVien.setDiaChi(faker.address().fullAddress());
            nhanVien.setEmail(faker.internet().emailAddress());
            nhanVien.setSdt(faker.phoneNumber().cellPhone());
            nhanVien.setNgaySinh(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            nhanVien.setChucVu(faker.options().option(ChucVu.class));

            // TaiKhoan entity
            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setMaTaiKhoan(faker.idNumber().valid()); // Set the identifier
            taiKhoan.setTenDangNhap(faker.name().username());
            taiKhoan.setMatKhau(faker.internet().password());
            taiKhoan.setThoiGianDangNhap(LocalDateTime.now());
            taiKhoan.setNhanVien(nhanVien);
//            System.out.println(taiKhoan);

            // PhieuNhapHang entity
            PhieuNhapHang phieuNhapHang = new PhieuNhapHang();
            phieuNhapHang.setMaPNH(faker.idNumber().valid()); // Set the identifier
            phieuNhapHang.setMaNV(nhanVien.getMaNV());
            phieuNhapHang.setTenNV(nhanVien.getTenNV());
            phieuNhapHang.setThoiGian(LocalDateTime.now());
            phieuNhapHang.setTongSoLuongSP(faker.number().numberBetween(1, 100));
            phieuNhapHang.setThanhTien(faker.number().randomDouble(2, 1, 1000));



            // CaLam entity
            CaLam caLam = new CaLam();
            caLam.setMaCa(faker.idNumber().valid()); // Set the identifier
            caLam.setGioBatDau(LocalDateTime.now());
            caLam.setGioKetThuc(LocalDateTime.now().plusHours(8));
            caLam.setTrangThai(faker.bool().bool());
            caLam.setTaiKhoan(taiKhoan);

            // ChiTietSanPham_PhieuNhap entity
            ChiTietSanPham_PhieuNhap chiTietSanPhamPhieuNhap = new ChiTietSanPham_PhieuNhap();
            ChiTietSanPham_PhieuNhapId chiTietSanPhamPhieuNhapId = new ChiTietSanPham_PhieuNhapId();
            chiTietSanPhamPhieuNhapId.setMaPNH(phieuNhapHang.getMaPNH());
            chiTietSanPhamPhieuNhapId.setMaSP(sanPham.getMaSP());
            chiTietSanPhamPhieuNhap.setId(chiTietSanPhamPhieuNhapId);
            chiTietSanPhamPhieuNhap.setSoLuongSP(faker.number().numberBetween(1, 100));
            chiTietSanPhamPhieuNhap.setDonGia(faker.number().randomDouble(2, 1, 1000));
            chiTietSanPhamPhieuNhap.setPhieuNhapHang(phieuNhapHang);
            chiTietSanPhamPhieuNhap.setSanPham(sanPham);


            // HoaDon entity
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaHD(faker.idNumber().valid()); // Set the identifier
            hoaDon.setMaNV(nhanVien.getMaNV());
            hoaDon.setMaKH(khachHang.getMaKH());
            hoaDon.setThoiGian(LocalDateTime.now());
            hoaDon.setTongSoLuongSP(faker.number().numberBetween(1, 100));
            hoaDon.setPhuongThucTT(faker.options().option(PhuongThucThanhToan.class));
            hoaDon.setThanhTien(faker.number().randomDouble(2, 1, 1000));
            hoaDon.setNhanVien(nhanVien);
            hoaDon.setKhachHang(khachHang);
            hoaDon.setCaLam(caLam);


            // ChiTietHoaDon_SanPham entity
            ChiTietHoaDon_SanPham chiTietHoaDonSanPham = new ChiTietHoaDon_SanPham();
            ChiTietHoaDon_SanPhamId chiTietHoaDonSanPhamId = new ChiTietHoaDon_SanPhamId();
            chiTietHoaDonSanPhamId.setMaHD(hoaDon.getMaHD());
            chiTietHoaDonSanPhamId.setMaSP(sanPham.getMaSP());
            chiTietHoaDonSanPham.setId(chiTietHoaDonSanPhamId);
            chiTietHoaDonSanPham.setSoLuongSP(faker.number().numberBetween(1, 100));
            chiTietHoaDonSanPham.setDonGia(faker.number().randomDouble(2, 1, 1000));
            chiTietHoaDonSanPham.setHoaDon(hoaDon);
            chiTietHoaDonSanPham.setSanPham(sanPham);


            tr.begin();
            em.persist(sanPham);
            em.persist(khachHang);
            em.persist(nhanVien);
            em.persist(taiKhoan);
            em.persist(phieuNhapHang);
            em.persist(caLam);
            em.persist(chiTietSanPhamPhieuNhap);
            em.persist(hoaDon);
            em.persist(chiTietHoaDonSanPham);
            tr.commit();
        }

        em.close();
    }
}