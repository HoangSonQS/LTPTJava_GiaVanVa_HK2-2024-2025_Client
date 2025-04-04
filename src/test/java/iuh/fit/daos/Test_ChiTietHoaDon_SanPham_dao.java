package iuh.fit.daos;

import iuh.fit.entities.ChiTietHoaDon_SanPham;
import iuh.fit.entities.ChiTietHoaDon_SanPhamId;
import iuh.fit.entities.HoaDon;
import iuh.fit.entities.SanPham;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_ChiTietHoaDon_SanPham_dao {

    private ChiTietHoaDon_SanPham_dao chiTietDao;
    private HoaDon hoaDon;
    private SanPham sanPham;

    @BeforeAll
    public void setup() {
        chiTietDao = new ChiTietHoaDon_SanPham_dao();
        hoaDon = new HoaDon_dao().read("HD001");
        sanPham = new SanPham_dao().read("SP001");
    }

    @Test
    @Order(1)
    public void testCreate() {
        ChiTietHoaDon_SanPham chiTiet = new ChiTietHoaDon_SanPham();
        ChiTietHoaDon_SanPhamId id = new ChiTietHoaDon_SanPhamId("HD001", "SP001");
        chiTiet.setId(id);
        chiTiet.setSoLuongSP(10);
        chiTiet.setDonGia(1000.0);
        chiTiet.setHoaDon(hoaDon);
        chiTiet.setSanPham(sanPham);

        chiTietDao.create(chiTiet);

        ChiTietHoaDon_SanPham retrieved = chiTietDao.read(id);
//        assertNotNull(retrieved);
//        assertEquals(id, retrieved.getId());
    }

    @Test
    @Order(2)
    public void testRead() {
        ChiTietHoaDon_SanPhamId id = new ChiTietHoaDon_SanPhamId();
        id.setMaHD(hoaDon.getMaHD());
        id.setMaSP(sanPham.getMaSP());
        ChiTietHoaDon_SanPham chiTiet = chiTietDao.read(id);
        assertNotNull(chiTiet);
        assertEquals(id, chiTiet.getId());
    }

    @Test
    @Order(3)
    public void testReadAll() {
        List<ChiTietHoaDon_SanPham> chiTiets = chiTietDao.readAll();
        assertNotNull(chiTiets);
        assertTrue(chiTiets.size() > 0);
    }

    @Test
    @Order(4)
    public void testUpdate() {
        ChiTietHoaDon_SanPhamId id = new ChiTietHoaDon_SanPhamId();
        id.setMaHD(hoaDon.getMaHD());
        id.setMaSP(sanPham.getMaSP());
        ChiTietHoaDon_SanPham chiTiet = chiTietDao.read(id);
        assertNotNull(chiTiet, "ChiTietHoaDon_SanPham should not be null");
        chiTiet.setSoLuongSP(20);
        chiTietDao.update(chiTiet);

        ChiTietHoaDon_SanPham updated = chiTietDao.read(id);
        assertEquals(20, updated.getSoLuongSP());
    }

    @Test
    @Order(5)
    public void testDelete() {
        ChiTietHoaDon_SanPhamId id = new ChiTietHoaDon_SanPhamId("HD001", "SP001");
        chiTietDao.delete(id);
        ChiTietHoaDon_SanPham deleted = chiTietDao.read(id);
        assertNull(deleted);
    }
}