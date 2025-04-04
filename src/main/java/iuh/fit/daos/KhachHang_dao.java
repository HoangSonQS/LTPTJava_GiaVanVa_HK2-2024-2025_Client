/*
 *@ (#) KhachHang_dao.java        1.0     1/21/2025
 *Copyright (c) 2025 IUH.All rights reserved.
 */

package iuh.fit.daos;/*
 *@descripsion:
 *@author : Khai Tien
 *@date: 1/21/2025
 *@version: 1.0
 */

import iuh.fit.entities.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class KhachHang_dao {
    private EntityManager em;

    public KhachHang_dao() {
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }

    public void create(KhachHang khachHang) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(khachHang);
            tr.commit();
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            e.printStackTrace();
        }
    }

    public KhachHang read(String maKH) {
        return em.find(KhachHang.class, maKH);
    }

    public List<KhachHang> readAll() {
        return em.createQuery("SELECT k FROM KhachHang k", KhachHang.class).getResultList();
    }

    public void update(KhachHang khachHang) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(khachHang);
            tr.commit();
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(String maKH) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            KhachHang khachHang = em.find(KhachHang.class, maKH);
            if (khachHang != null) {
                em.remove(khachHang);
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