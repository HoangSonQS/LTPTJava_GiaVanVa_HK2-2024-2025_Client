package iuh.fit.daos;

import iuh.fit.entities.PhieuNhapHang;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_PhieuNhapHang_dao {

    private PhieuNhapHang_dao phieuNhapHangDao;

    @BeforeAll
    public void setup() {
        phieuNhapHangDao = new PhieuNhapHang_dao();
    }

    @Test
    @Order(1)
    public void testCreate() {
        PhieuNhapHang phieuNhapHang = new PhieuNhapHang();
        phieuNhapHang.setMaPNH("PNH001");
        phieuNhapHang.setMaNV("NV001");
        phieuNhapHang.setTenNV("Nguyen Van A");
        phieuNhapHang.setThoiGian(LocalDateTime.now());
        phieuNhapHang.setTongSoLuongSP(10);
        phieuNhapHang.setThanhTien(1000.0);

        phieuNhapHangDao.create(phieuNhapHang);

        PhieuNhapHang retrieved = phieuNhapHangDao.read("PNH001");
        assertNotNull(retrieved);
        assertEquals("PNH001", retrieved.getMaPNH());
    }

    @Test
    @Order(2)
    public void testRead() {
        PhieuNhapHang phieuNhapHang = phieuNhapHangDao.read("PNH001");
        assertNotNull(phieuNhapHang);
        assertEquals("PNH001", phieuNhapHang.getMaPNH());
    }

    @Test
    @Order(3)
    public void testReadAll() {
        List<PhieuNhapHang> phieuNhapHangs = phieuNhapHangDao.readAll();
        assertNotNull(phieuNhapHangs);
        assertTrue(phieuNhapHangs.size() > 0);
    }

    @Test
    @Order(4)
    public void testUpdate() {
        PhieuNhapHang phieuNhapHang = phieuNhapHangDao.read("PNH001");
        assertNotNull(phieuNhapHang, "PhieuNhapHang should not be null");
        phieuNhapHang.setTongSoLuongSP(20);
        phieuNhapHangDao.update(phieuNhapHang);

        PhieuNhapHang updated = phieuNhapHangDao.read("PNH001");
        assertEquals(20, updated.getTongSoLuongSP());
    }

    @Test
    @Order(5)
    public void testDelete() {
        phieuNhapHangDao.delete("PNH001");
        PhieuNhapHang deleted = phieuNhapHangDao.read("PNH001");
        assertNull(deleted);
    }
}