package iuh.fit.daos;

import iuh.fit.entities.TaiKhoan;
import iuh.fit.entities.NhanVien;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_TaiKhoan_dao {

    private TaiKhoan_dao taiKhoanDao;
    private NhanVien_dao nhanVienDao;

    @BeforeAll
    public void setup() {
        taiKhoanDao = new TaiKhoan_dao();
        nhanVienDao = new NhanVien_dao();
    }

    @Test
    @Order(1)
    public void testCreate() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV("NV001");
        nhanVien.setTenNV("Nguyen Van A");
        nhanVienDao.createNhanVien(nhanVien);

        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setMaTaiKhoan("TK001");
        taiKhoan.setTenDangNhap("user1");
        taiKhoan.setMatKhau("password1");
        taiKhoan.setThoiGianDangNhap(LocalDateTime.now());
        taiKhoan.setNhanVien(nhanVien);

        taiKhoanDao.create(taiKhoan);

        TaiKhoan retrieved = taiKhoanDao.read("TK001");
        assertNotNull(retrieved);
        assertEquals("TK001", retrieved.getMaTaiKhoan());
    }

    @Test
    @Order(2)
    public void testRead() {
        TaiKhoan taiKhoan = taiKhoanDao.read("TK001");
        assertNotNull(taiKhoan);
        assertEquals("TK001", taiKhoan.getMaTaiKhoan());
    }

    @Test
    @Order(3)
    public void testReadAll() {
        List<TaiKhoan> taiKhoans = taiKhoanDao.readAll();
        assertNotNull(taiKhoans);
        assertTrue(taiKhoans.size() > 0);
    }

    @Test
    @Order(4)
    public void testUpdate() {
        TaiKhoan taiKhoan = taiKhoanDao.read("TK001");
        assertNotNull(taiKhoan, "TaiKhoan should not be null");
        taiKhoan.setMatKhau("newpassword1");
        taiKhoanDao.update(taiKhoan);

        TaiKhoan updated = taiKhoanDao.read("TK001");
        assertEquals("newpassword1", updated.getMatKhau());
    }

    @Test
    @Order(5)
    public void testDelete() {
        taiKhoanDao.delete("TK001");
        TaiKhoan deleted = taiKhoanDao.read("TK001");
        assertNull(deleted);
    }
}