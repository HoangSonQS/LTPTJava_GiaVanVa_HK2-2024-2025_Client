// src/main/java/iuh.fit.daos/ChiTietSanPham_PhieuNhap_dao.java
package iuh.fit.daos;

import iuh.fit.entities.ChiTietSanPham_PhieuNhap;
import iuh.fit.entities.ChiTietSanPham_PhieuNhapId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.logging.Logger;

public class ChiTietSanPham_PhieuNhap_dao {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("mariadb");
    private static final Logger logger = Logger.getLogger(ChiTietSanPham_PhieuNhap_dao.class.getName());

    public void create(ChiTietSanPham_PhieuNhap chiTiet) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(chiTiet); // Sử dụng merge thay vì persist
            tr.commit();
            logger.info("Created ChiTietSanPham_PhieuNhap: " + chiTiet);
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public ChiTietSanPham_PhieuNhap read(ChiTietSanPham_PhieuNhapId id) {
        EntityManager em = emf.createEntityManager();
        ChiTietSanPham_PhieuNhap chiTiet = em.find(ChiTietSanPham_PhieuNhap.class, id);
        logger.info("Read ChiTietSanPham_PhieuNhap: " + chiTiet);
        em.close();
        return chiTiet;
    }

    public List<ChiTietSanPham_PhieuNhap> readAll() {
        EntityManager em = emf.createEntityManager();
        List<ChiTietSanPham_PhieuNhap> chiTiets = em.createQuery("SELECT c FROM ChiTietSanPham_PhieuNhap c", ChiTietSanPham_PhieuNhap.class).getResultList();
        em.close();
        return chiTiets;
    }

    public void update(ChiTietSanPham_PhieuNhap chiTiet) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(chiTiet);
            tr.commit();
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void delete(ChiTietSanPham_PhieuNhapId id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            ChiTietSanPham_PhieuNhap chiTiet = em.find(ChiTietSanPham_PhieuNhap.class, id);
            if (chiTiet != null) {
                em.remove(chiTiet);
            }
            tr.commit();
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}