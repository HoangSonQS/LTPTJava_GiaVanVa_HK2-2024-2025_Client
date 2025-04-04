package iuh.fit.daos;

import iuh.fit.entities.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class SanPham_dao {

    private EntityManager em;

    public SanPham_dao() {
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }

    public void create(SanPham sanPham) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(sanPham);
            tr.commit();
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            e.printStackTrace();
        }
    }

    public SanPham read(String maSP) {
        return em.find(SanPham.class, maSP);
    }

    public List<SanPham> readAll() {
        return em.createQuery("SELECT s FROM SanPham s", SanPham.class).getResultList();
    }

    public void update(SanPham sanPham) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(sanPham);
            tr.commit();
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(String maSP) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            SanPham sanPham = em.find(SanPham.class, maSP);
            if (sanPham != null) {
                em.remove(sanPham);
            }
            tr.commit();
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            e.printStackTrace();
        }
    }
}