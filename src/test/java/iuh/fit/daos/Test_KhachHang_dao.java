/*
 *@ (#) Test_KhachHang_dao.java        1.0     1/21/2025
 *Copyright (c) 2025 IUH.All rights reserved.
 */

package iuh.fit.daos;/*
 *@descripsion:
 *@author : Khai Tien
 *@date: 1/21/2025
 *@version: 1.0
 */

import iuh.fit.entities.KhachHang;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_KhachHang_dao {

    private KhachHang_dao khachHangDao;

    @BeforeAll
    public void setup() {
        khachHangDao = new KhachHang_dao();
    }

    @Test
    @Order(1)
    public void testCreate() {
        KhachHang khachHang = new KhachHang();
        khachHang.setMaKH("KH001");
        khachHang.setTenKH("Nguyen Van A");
        khachHang.setSdt("0123456789");

        khachHangDao.create(khachHang);

        KhachHang retrieved = khachHangDao.read("KH001");
        assertNotNull(retrieved);
        assertEquals("KH001", retrieved.getMaKH());
    }

    @Test
    @Order(2)
    public void testRead() {
        KhachHang khachHang = khachHangDao.read("KH001");
        assertNotNull(khachHang);
        assertEquals("KH001", khachHang.getMaKH());
    }

    @Test
    @Order(3)
    public void testReadAll() {
        List<KhachHang> khachHangs = khachHangDao.readAll();
        assertNotNull(khachHangs);
        assertTrue(khachHangs.size() > 0);
    }

    @Test
    @Order(4)
    public void testUpdate() {
        KhachHang khachHang = khachHangDao.read("KH001");
        assertNotNull(khachHang, "KhachHang should not be null");
        khachHang.setTenKH("Nguyen Van B");
        khachHangDao.update(khachHang);

        KhachHang updated = khachHangDao.read("KH001");
        assertEquals("Nguyen Van B", updated.getTenKH());
    }

    @Test
    @Order(5)
    public void testDelete() {
        khachHangDao.delete("KH001");
        KhachHang deleted = khachHangDao.read("KH001");
        assertNull(deleted);
    }
}