package iuh.fit.daos;

import iuh.fit.entities.CaLam;
import iuh.fit.entities.HoaDon;
import iuh.fit.entities.KhachHang;
import iuh.fit.entities.NhanVien;
import iuh.fit.enums.PhuongThucThanhToan;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_HoaDon_dao {

    private HoaDon_dao hoaDonDao;
    private CaLam caLam;
    private KhachHang khachHang;
    private NhanVien nhanVien;

    @BeforeAll
    public void setup() {
        hoaDonDao = new HoaDon_dao();
        caLam = new CaLam_dao().read("CL001");
        khachHang = new KhachHang_dao().read("KH001");
        nhanVien = new NhanVien_dao().readNhanVien("NV001");
    }

    @Test
    @Order(1)
    public void testCreate() {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHD("HD001");
        hoaDon.setMaKH("KH001");
        hoaDon.setMaNV("NV001");
        hoaDon.setCaLam(caLam);
        hoaDon.setKhachHang(khachHang);
        hoaDon.setNhanVien(nhanVien);
        hoaDon.setThoiGian(LocalDateTime.now());
        hoaDon.setTongSoLuongSP(10);
        hoaDon.setPhuongThucTT(PhuongThucThanhToan.Tien_Mat);
        hoaDon.setThanhTien(100000.0);

        System.out.println("HoaDon: " + hoaDon);
        hoaDonDao.create(hoaDon);

        HoaDon retrieved = hoaDonDao.read("HD001");
        assertNotNull(retrieved);
        assertEquals("HD001", retrieved.getMaHD());
    }

    @Test
    @Order(2)
    public void testRead() {
        HoaDon hoaDon = hoaDonDao.read("HD001");
        assertNotNull(hoaDon);
        assertEquals("HD001", hoaDon.getMaHD());
    }

    @Test
    @Order(3)
    public void testReadAll() {
        List<HoaDon> hoaDons = hoaDonDao.readAll();
        assertNotNull(hoaDons);
        assertTrue(hoaDons.size() > 0);
    }

    @Test
    @Order(4)
    public void testUpdate() {
        HoaDon hoaDon = hoaDonDao.read("HD001");
        assertNotNull(hoaDon, "HoaDon should not be null");
        hoaDon.setTongSoLuongSP(20);
        hoaDonDao.update(hoaDon);

        HoaDon updated = hoaDonDao.read("HD001");
        assertEquals(20, updated.getTongSoLuongSP());
    }

    @Test
    @Order(5)
    public void testDelete() {
        hoaDonDao.delete("HD001");
        HoaDon deleted = hoaDonDao.read("HD001");
        assertNull(deleted);
    }
}