package iuh.fit.daos;

import iuh.fit.entities.CaLam;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class CaLam_dao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("mariadb");

    public void create(CaLam caLam) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(caLam);
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

    public CaLam read(String maCa) {
        EntityManager em = emf.createEntityManager();
        CaLam caLam = null;
        try {
            caLam = em.find(CaLam.class, maCa);
        } finally {
            em.close();
        }
        return caLam;
    }

    public List<CaLam> readAll() {
        EntityManager em = emf.createEntityManager();
        List<CaLam> caLams = null;
        try {
            caLams = em.createQuery("SELECT c FROM CaLam c", CaLam.class).getResultList();
        } finally {
            em.close();
        }
        return caLams;
    }

    public void update(CaLam caLam) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(caLam);
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

    public void delete(String maCa) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            CaLam caLam = em.find(CaLam.class, maCa);
            if (caLam != null) {
                em.remove(caLam);
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