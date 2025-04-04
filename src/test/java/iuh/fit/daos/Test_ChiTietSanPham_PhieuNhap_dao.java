// src/test/java/iuh.fit.daos/Test_ChiTietSanPham_PhieuNhap_dao.java
package iuh.fit.daos;

import iuh.fit.entities.*;
import org.junit.jupiter.api.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_ChiTietSanPham_PhieuNhap_dao {

    private ChiTietSanPham_PhieuNhap_dao chiTietDao;
    private SanPham_dao sanPhamDao;
    private PhieuNhapHang_dao phieuNhapHangDao;
    private NhanVien_dao nhanVienDao;
    private SanPham sanPham;
    private PhieuNhapHang phieuNhapHang;
    private NhanVien nhanVien;

    @BeforeAll
    public void setup() {
        chiTietDao = new ChiTietSanPham_PhieuNhap_dao();
        sanPhamDao = new SanPham_dao();
        phieuNhapHangDao = new PhieuNhapHang_dao();
        nhanVienDao = new NhanVien_dao();

        // Create and persist SanPham entity
        sanPham = sanPhamDao.read("SP001");

        // Create and persist PhieuNhapHang entity
        phieuNhapHang = phieuNhapHangDao.read("PNH001");
    }

    @Test
    @Order(1)
    public void testCreate() {
        ChiTietSanPham_PhieuNhap chiTiet = new ChiTietSanPham_PhieuNhap();
        ChiTietSanPham_PhieuNhapId id = new ChiTietSanPham_PhieuNhapId();
        id.setMaPNH(phieuNhapHang.getMaPNH());
        id.setMaSP(sanPham.getMaSP());
        chiTiet.setId(id);
        chiTiet.setSoLuongSP(50);
        chiTiet.setDonGia(1000.0);
        chiTiet.setPhieuNhapHang(phieuNhapHang);
        chiTiet.setSanPham(sanPham);
        chiTietDao.create(chiTiet);

        ChiTietSanPham_PhieuNhap retrieved = chiTietDao.read(chiTiet.getId());
        assertNotNull(retrieved, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertEquals(chiTiet.getSoLuongSP(), retrieved.getSoLuongSP());
    }

    @Test
    @Order(2)
    public void testRead() {
        ChiTietSanPham_PhieuNhapId id = new ChiTietSanPham_PhieuNhapId();
        id.setMaPNH(phieuNhapHang.getMaPNH());
        id.setMaSP(sanPham.getMaSP());
        ChiTietSanPham_PhieuNhap chiTiet = chiTietDao.read(id);
        assertNotNull(chiTiet, "ChiTietSanPham_PhieuNhap should not be null");
        assertEquals(id, chiTiet.getId());
    }

    @Test
    @Order(3)
    public void testReadAll() {
        List<ChiTietSanPham_PhieuNhap> chiTiets = chiTietDao.readAll();
        assertNotNull(chiTiets, "ChiTietSanPham_PhieuNhap list should not be null");
        assertTrue(chiTiets.size() > 0, "ChiTietSanPham_PhieuNhap list should not be empty");
    }

    @Test
    @Order(4)
    public void testUpdate() {
        ChiTietSanPham_PhieuNhapId id = new ChiTietSanPham_PhieuNhapId();
        id.setMaPNH(phieuNhapHang.getMaPNH());
        id.setMaSP(sanPham.getMaSP());
        ChiTietSanPham_PhieuNhap chiTiet = chiTietDao.read(id);
        assertNotNull(chiTiet, "ChiTietSanPham_PhieuNhap should not be null");
        chiTiet.setSoLuongSP(60);
        chiTietDao.update(chiTiet);

        ChiTietSanPham_PhieuNhap updated = chiTietDao.read(id);
        assertEquals(chiTiet.getSoLuongSP(), updated.getSoLuongSP());
    }

    @Test
    @Order(5)
    public void testDelete() {
        ChiTietSanPham_PhieuNhapId id = new ChiTietSanPham_PhieuNhapId();
        id.setMaPNH(phieuNhapHang.getMaPNH());
        id.setMaSP(sanPham.getMaSP());
        chiTietDao.delete(id);
        ChiTietSanPham_PhieuNhap deleted = chiTietDao.read(id);
        assertNull(deleted, "ChiTietSanPham_PhieuNhap should be null after deletion");
    }
}