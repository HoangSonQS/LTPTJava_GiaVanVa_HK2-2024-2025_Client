package iuh.fit.daos;

import iuh.fit.entities.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class NhanVien_dao {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("mariadb");

    public void createNhanVien(NhanVien nhanVien) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(nhanVien);
        em.getTransaction().commit();
        em.close();
    }

    public NhanVien readNhanVien(String maNV) {
        EntityManager em = emf.createEntityManager();
        NhanVien nhanVien = em.find(NhanVien.class, maNV);
        em.close();
        return nhanVien;
    }

    public List<NhanVien> readAllNhanVien() {
        EntityManager em = emf.createEntityManager();
        List<NhanVien> nhanViens = em.createQuery("SELECT nv FROM NhanVien nv", NhanVien.class).getResultList();
        em.close();
        return nhanViens;
    }

    public void updateNhanVien(NhanVien nhanVien) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(nhanVien);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteNhanVien(String maNV) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        NhanVien nhanVien = em.find(NhanVien.class, maNV);
        if (nhanVien != null) {
            em.remove(nhanVien);
        }
        em.getTransaction().commit();
        em.close();
    }
}