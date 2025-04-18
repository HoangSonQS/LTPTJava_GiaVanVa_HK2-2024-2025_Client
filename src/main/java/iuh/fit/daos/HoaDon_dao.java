package iuh.fit.daos;

import iuh.fit.entities.HoaDon;
import iuh.fit.enums.LoaiHang;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class HoaDon_dao {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("mariadb");

    public void create(HoaDon hoaDon) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(hoaDon);
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

    public HoaDon read(String maHD) {
        EntityManager em = emf.createEntityManager();
        HoaDon hoaDon = em.find(HoaDon.class, maHD);
        em.close();
        return hoaDon;
    }

    public List<HoaDon> readAll() {
        EntityManager em = emf.createEntityManager();
        List<HoaDon> hoaDons = em.createQuery("SELECT h FROM HoaDon h", HoaDon.class).getResultList();
        em.close();
        return hoaDons;
    }

    public void update(HoaDon hoaDon) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(hoaDon);
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

    public void delete(String maHD) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            HoaDon hoaDon = em.find(HoaDon.class, maHD);
            if (hoaDon != null) {
                em.remove(hoaDon);
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

    public List<Object[]> getThongKeSanPham(String nam, String loaiThongKe, String loaiHangDisplay) {
        EntityManager em = emf.createEntityManager();
        try {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT s.tenSP, ")
                    .append("COALESCE(SUM(ct.soLuongSP), 0) as soLuongBan, ")
                    .append("COALESCE(SUM(ct.soLuongSP * ct.donGia), 0) as doanhThu ")
                    .append("FROM SanPham s ")
                    .append("LEFT JOIN s.chiTietHoaDonSanPhams ct ")
                    .append("LEFT JOIN ct.hoaDon h ")
                    .append("WHERE 1=1 ");

            if (!"TẤT CẢ".equals(nam)) {
                queryBuilder.append("AND YEAR(h.thoiGian) = :nam ");

                if ("Theo ngày".equals(loaiThongKe)) {
                    queryBuilder.append("AND h.thoiGian IS NOT NULL ");
                } else if ("Theo quý".equals(loaiThongKe)) {
                    queryBuilder.append("AND QUARTER(h.thoiGian) <= QUARTER(CURRENT_DATE) ");
                }
            }

            if (!"TẤT CẢ".equals(loaiHangDisplay)) {
                queryBuilder.append("AND s.loaiHang = :loaiHang ");
            }

            queryBuilder.append("AND ct.soLuongSP IS NOT NULL ")
                    .append("GROUP BY s.tenSP ")
                    .append("ORDER BY soLuongBan DESC");

            Query query = em.createQuery(queryBuilder.toString());

            if (!"TẤT CẢ".equals(nam)) {
                query.setParameter("nam", Integer.parseInt(nam));
            }

            if (!"TẤT CẢ".equals(loaiHangDisplay)) {
                LoaiHang loaiHangEnum = Arrays.stream(LoaiHang.values())
                        .filter(lh -> lh.getLoaiHang().equals(loaiHangDisplay))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Invalid loai hang: " + loaiHangDisplay));
                query.setParameter("loaiHang", loaiHangEnum);
            }

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object[]> getLoaiHangDistribution() {
        EntityManager em = emf.createEntityManager();
        try {
            String checkQuery = "SELECT s.loaiHang, COUNT(s) FROM SanPham s GROUP BY s.loaiHang";
            return em.createQuery(checkQuery).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object[]> getSalesDistribution() {
        EntityManager em = emf.createEntityManager();
        try {
            String checkSalesQuery = "SELECT s.loaiHang, COUNT(ct) " +
                                   "FROM SanPham s " +
                                   "LEFT JOIN s.chiTietHoaDonSanPhams ct " +
                                   "GROUP BY s.loaiHang";
            return em.createQuery(checkSalesQuery).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object[]> getDoanhThuTheoThang(int nam) {
        EntityManager em = emf.createEntityManager();
        try {
            String query = "SELECT MONTH(h.thoiGian) as thang, SUM(h.thanhTien) as doanhThu " +
                          "FROM HoaDon h " +
                          "WHERE YEAR(h.thoiGian) = :nam " +
                          "GROUP BY MONTH(h.thoiGian) " +
                          "ORDER BY MONTH(h.thoiGian)";

            TypedQuery<Object[]> typedQuery = em.createQuery(query, Object[].class);
            typedQuery.setParameter("nam", nam);
            return typedQuery.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object[]> getDoanhThuTheoQuy(int nam) {
        EntityManager em = emf.createEntityManager();
        try {
            String query = "SELECT QUARTER(h.thoiGian) as quy, SUM(h.thanhTien) as doanhThu " +
                          "FROM HoaDon h " +
                          "WHERE YEAR(h.thoiGian) = :nam " +
                          "GROUP BY QUARTER(h.thoiGian) " +
                          "ORDER BY QUARTER(h.thoiGian)";

            TypedQuery<Object[]> typedQuery = em.createQuery(query, Object[].class);
            typedQuery.setParameter("nam", nam);
            return typedQuery.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object[]> getDoanhThuTheoNgay(LocalDateTime startDate, LocalDateTime endDate) {
        EntityManager em = emf.createEntityManager();
        try {
            String query = "SELECT DATE(h.thoiGian) as ngay, COUNT(h.maHD) as soHoaDon, " +
                          "SUM(h.thanhTien) as doanhThu " +
                          "FROM HoaDon h " +
                          "WHERE h.thoiGian BETWEEN :startDate AND :endDate " +
                          "GROUP BY DATE(h.thoiGian) " +
                          "ORDER BY DATE(h.thoiGian)";

            TypedQuery<Object[]> typedQuery = em.createQuery(query, Object[].class);
            typedQuery.setParameter("startDate", startDate);
            typedQuery.setParameter("endDate", endDate);
            return typedQuery.getResultList();
        } finally {
            em.close();
        }
    }

    public Map<Integer, Double> getDoanhThuMapTheoThang(int nam) {
        List<Object[]> results = getDoanhThuTheoThang(nam);
        Map<Integer, Double> doanhThuMap = new HashMap<>();
        
        // Khởi tạo dữ liệu cho 12 tháng
        for (int i = 1; i <= 12; i++) {
            doanhThuMap.put(i, 0.0);
        }
        
        // Cập nhật dữ liệu từ kết quả query
        for (Object[] result : results) {
            Integer thang = ((Number) result[0]).intValue();
            Double doanhThu = (result[1] != null) ? ((Number) result[1]).doubleValue() : 0.0;
            doanhThuMap.put(thang, doanhThu);
        }
        
        return doanhThuMap;
    }

    public Map<Integer, Double> getDoanhThuMapTheoQuy(int nam) {
        List<Object[]> results = getDoanhThuTheoQuy(nam);
        Map<Integer, Double> doanhThuMap = new HashMap<>();
        
        // Khởi tạo dữ liệu cho 4 quý
        for (int i = 1; i <= 4; i++) {
            doanhThuMap.put(i, 0.0);
        }
        
        // Cập nhật dữ liệu từ kết quả query
        for (Object[] result : results) {
            Integer quy = ((Number) result[0]).intValue();
            Double doanhThu = (result[1] != null) ? ((Number) result[1]).doubleValue() : 0.0;
            doanhThuMap.put(quy, doanhThu);
        }
        
        return doanhThuMap;
    }

    public Map<LocalDate, Double> getDoanhThuMapTheoNgay(LocalDateTime startDate, LocalDateTime endDate) {
        List<Object[]> results = getDoanhThuTheoNgay(startDate, endDate);
        Map<LocalDate, Double> doanhThuMap = new HashMap<>();
        
        // Khởi tạo map cho khoảng thời gian
        LocalDate currentDate = startDate.toLocalDate();
        while (!currentDate.isAfter(endDate.toLocalDate())) {
            doanhThuMap.put(currentDate, 0.0);
            currentDate = currentDate.plusDays(1);
        }
        
        // Cập nhật dữ liệu từ kết quả query
        for (Object[] result : results) {
            if (result[0] instanceof java.sql.Date) {
                java.sql.Date sqlDate = (java.sql.Date) result[0];
                LocalDate ngay = sqlDate.toLocalDate();
                Double doanhThu = (result[2] != null) ? ((Number) result[2]).doubleValue() : 0.0;
                doanhThuMap.put(ngay, doanhThu);
            }
        }
        
        return doanhThuMap;
    }
}
