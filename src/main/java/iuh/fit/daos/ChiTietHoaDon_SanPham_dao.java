package iuh.fit.daos;

import iuh.fit.entities.ChiTietHoaDon_SanPham;
import iuh.fit.entities.ChiTietHoaDon_SanPhamId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class ChiTietHoaDon_SanPham_dao {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("mariadb");

    public void create(ChiTietHoaDon_SanPham chiTiet) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(chiTiet); // Sử dụng merge thay vì persist
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

    public ChiTietHoaDon_SanPham read(ChiTietHoaDon_SanPhamId id) {
        EntityManager em = emf.createEntityManager();
        ChiTietHoaDon_SanPham chiTiet = em.find(ChiTietHoaDon_SanPham.class, id);
        System.out.println(chiTiet);
        em.close();
        return chiTiet;
    }

    public List<ChiTietHoaDon_SanPham> readAll() {
        EntityManager em = emf.createEntityManager();
        List<ChiTietHoaDon_SanPham> chiTiets = em.createQuery("SELECT c FROM ChiTietHoaDon_SanPham c", ChiTietHoaDon_SanPham.class).getResultList();
        em.close();
        return chiTiets;
    }

    public void update(ChiTietHoaDon_SanPham chiTiet) {
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

    public void delete(ChiTietHoaDon_SanPhamId id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            ChiTietHoaDon_SanPham chiTiet = em.find(ChiTietHoaDon_SanPham.class, id);
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