package iuh.fit.daos;

import iuh.fit.entities.NhanVien;
import iuh.fit.enums.ChucVu;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_NhanVien_dao {

    private NhanVien_dao nhanVienDao;

    @BeforeAll
    public void setup() {
        nhanVienDao = new NhanVien_dao();
    }

    @Test
    @Order(1)
    public void testCreate() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV("NV001");
        nhanVien.setTenNV("Nguyen Van A");
        nhanVien.setCccd("123456789");
        nhanVien.setDiaChi("123 iuh.fit.Main St");
        nhanVien.setEmail("nguyenvana@example.com");
        nhanVien.setSdt("0123456789");
        nhanVien.setNgaySinh(LocalDate.of(1990, 1, 1));
        nhanVien.setChucVu(ChucVu.Nhan_Vien);

        nhanVienDao.createNhanVien(nhanVien);

        NhanVien retrieved = nhanVienDao.readNhanVien("NV001");
        assertNotNull(retrieved);
        assertEquals("NV001", retrieved.getMaNV());
    }

    @Test
    @Order(2)
    public void testRead() {
        NhanVien nhanVien = nhanVienDao.readNhanVien("NV001");
        assertNotNull(nhanVien);
        assertEquals("NV001", nhanVien.getMaNV());
    }

    @Test
    @Order(3)
    public void testReadAll() {
        List<NhanVien> nhanViens = nhanVienDao.readAllNhanVien();
        assertNotNull(nhanViens);
        assertTrue(nhanViens.size() > 0);
    }

    @Test
    @Order(4)
    public void testUpdate() {
        NhanVien nhanVien = nhanVienDao.readNhanVien("NV001");
        assertNotNull(nhanVien, "NhanVien should not be null");
        nhanVien.setDiaChi("456 New St");
        nhanVienDao.updateNhanVien(nhanVien);

        NhanVien updated = nhanVienDao.readNhanVien("NV001");
        assertEquals("456 New St", updated.getDiaChi());
    }

    @Test
    @Order(5)
    public void testDelete() {
        nhanVienDao.deleteNhanVien("NV001");
        NhanVien deleted = nhanVienDao.readNhanVien("NV001");
        assertNull(deleted);
    }
}