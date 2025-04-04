package iuh.fit.daos;

import iuh.fit.entities.SanPham;
import iuh.fit.enums.LoaiHang;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_SanPham_dao {

    private SanPham_dao sanPhamDao;

    @BeforeAll
    public void setup() {
        sanPhamDao = new SanPham_dao();
    }

    @Test
    @Order(1)
    public void testCreate() {
        SanPham sanPham = new SanPham();
        sanPham.setMaSP("SP001");
        sanPham.setTenSP("San Pham 1");
        sanPham.setNhaCC("Nha Cung Cap 1");
        sanPham.setSoLuongTon(100);
        sanPham.setGiaNhap(5000.0);
        sanPham.setGiaBan(7000.0);
        sanPham.setNgaySX(LocalDateTime.now().minusDays(10));
        sanPham.setHanSD(LocalDateTime.now().plusDays(365));
        sanPham.setThoiGianCapNhat(LocalDateTime.now());
        sanPham.setLoaiHang(LoaiHang.THUC_PHAM);

        sanPhamDao.create(sanPham);

        SanPham retrieved = sanPhamDao.read("SP001");
        assertNotNull(retrieved);
        assertEquals("SP001", retrieved.getMaSP());
    }

    @Test
    @Order(2)
    public void testRead() {
        SanPham sanPham = sanPhamDao.read("SP001");
        assertNotNull(sanPham);
        assertEquals("SP001", sanPham.getMaSP());
    }

    @Test
    @Order(3)
    public void testReadAll() {
        List<SanPham> sanPhams = sanPhamDao.readAll();
        assertNotNull(sanPhams);
        assertTrue(sanPhams.size() > 0);
    }

    @Test
    @Order(4)
    public void testUpdate() {
        SanPham sanPham = sanPhamDao.read("SP001");
        assertNotNull(sanPham, "SanPham should not be null");
        sanPham.setSoLuongTon(200);
        sanPhamDao.update(sanPham);

        SanPham updated = sanPhamDao.read("SP001");
        assertEquals(200, updated.getSoLuongTon());
    }

    @Test
    @Order(5)
    public void testDelete() {
        sanPhamDao.delete("SP001");
        SanPham deleted = sanPhamDao.read("SP001");
        assertNull(deleted);
    }
}