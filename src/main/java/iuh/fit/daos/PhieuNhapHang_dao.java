package iuh.fit.daos;

import iuh.fit.entities.PhieuNhapHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class PhieuNhapHang_dao {

    private EntityManager em;

    public PhieuNhapHang_dao() {
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }

    public void create(PhieuNhapHang phieuNhapHang) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(phieuNhapHang);
            tr.commit();
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            e.printStackTrace();
        }
    }

    public PhieuNhapHang read(String maPNH) {
        return em.find(PhieuNhapHang.class, maPNH);
    }

    public List<PhieuNhapHang> readAll() {
        return em.createQuery("SELECT p FROM PhieuNhapHang p", PhieuNhapHang.class).getResultList();
    }

    public void update(PhieuNhapHang phieuNhapHang) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(phieuNhapHang);
            tr.commit();
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(String maPNH) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            PhieuNhapHang phieuNhapHang = em.find(PhieuNhapHang.class, maPNH);
            if (phieuNhapHang != null) {
                em.remove(phieuNhapHang);
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