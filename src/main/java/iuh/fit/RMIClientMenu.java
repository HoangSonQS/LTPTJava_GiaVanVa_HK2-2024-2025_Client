package iuh.fit;

import iuh.fit.entities.*;
import iuh.fit.interfaces.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class RMIClientMenu {
    private static Registry registry;
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // DAO interfaces
    private static TaiKhoan_interface taiKhoanDAO;
    private static CaLam_interface caLamDAO;
    private static HoaDon_interface hoaDonDAO;
    private static SanPham_interface sanPhamDAO;
    private static KhachHang_interface khachHangDAO;
    private static PhieuNhapHang_interface phieuNhapHangDAO;
    private static ChiTietHoaDon_SanPham_interface chiTietHoaDonSanPhamDAO;
    private static ChiTietSanPham_PhieuNhap_interface chiTietSanPhamPhieuNhapDAO;

    public static void main(String[] args) {
        try {
            // Set system properties for RMI
            System.setProperty("java.security.policy", "rmi.policy");
            System.setProperty("java.rmi.server.hostname", "localhost");

            // Get the registry
            registry = LocateRegistry.getRegistry("localhost", 9090);

            // Look up the DAO services from the registry
            taiKhoanDAO = (TaiKhoan_interface) registry.lookup("taiKhoanDAO");
            caLamDAO = (CaLam_interface) registry.lookup("caLamDAO");
            hoaDonDAO = (HoaDon_interface) registry.lookup("hoaDonDAO");
            sanPhamDAO = (SanPham_interface) registry.lookup("sanPhamDAO");
            khachHangDAO = (KhachHang_interface) registry.lookup("khachHangDAO");
            phieuNhapHangDAO = (PhieuNhapHang_interface) registry.lookup("phieuNhapHangDAO");
            chiTietHoaDonSanPhamDAO = (ChiTietHoaDon_SanPham_interface) registry.lookup("chiTietHoaDonSanPhamDAO");
            chiTietSanPhamPhieuNhapDAO = (ChiTietSanPham_PhieuNhap_interface) registry.lookup("chiTietSanPhamPhieuNhapDAO");

            System.out.println("Connected to RMI Server successfully!");

            // Display the main menu
            showMainMenu();

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    private static void showMainMenu() {
        int choice = 0;

        do {
            System.out.println("\n===== QUAN LY CUA HANG =====");
            System.out.println("1. Quan ly Tai Khoan");
            System.out.println("2. Quan ly Ca Lam");
            System.out.println("3. Quan ly Hoa Don");
            System.out.println("4. Quan ly San Pham");
            System.out.println("5. Quan ly Khach Hang");
            System.out.println("6. Quan ly Phieu Nhap Hang");
            System.out.println("7. Quan ly Chi Tiet Hoa Don - San Pham");
            System.out.println("8. Quan ly Chi Tiet San Pham - Phieu Nhap");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        manageTaiKhoan();
                        break;
                    case 2:
                        manageCaLam();
                        break;
                    case 3:
                        manageHoaDon();
                        break;
                    case 4:
                        manageSanPham();
                        break;
                    case 5:
                        manageKhachHang();
                        break;
                    case 6:
                        managePhieuNhapHang();
                        break;
                    case 7:
                        manageChiTietHoaDonSanPham();
                        break;
                    case 8:
                        manageChiTietSanPhamPhieuNhap();
                        break;
                    case 0:
                        System.out.println("Cam on ban da su dung chuong trinh!");
                        break;
                    default:
                        System.out.println("Lua chon khong hop le. Vui long chon lai!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so!");
                choice = -1;
            } catch (Exception e) {
                System.err.println("Loi: " + e.getMessage());
                e.printStackTrace();
                choice = -1;
            }
        } while (choice != 0);
    }

    // Quản lý Tài Khoản
    private static void manageTaiKhoan() throws Exception {
        int choice = 0;

        do {
            System.out.println("\n===== QUAN LY TAI KHOAN =====");
            System.out.println("1. Xem danh sach tai khoan");
            System.out.println("2. Tim tai khoan theo ma");
            System.out.println("3. Them tai khoan moi");
            System.out.println("4. Cap nhat tai khoan");
            System.out.println("5. Xoa tai khoan");
            System.out.println("0. Quay lai");
            System.out.print("Chon chuc nang: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    // Xem danh sách tài khoản
                    List<TaiKhoan> taiKhoans = taiKhoanDAO.readAll();
                    System.out.println("\nDanh sach tai khoan:");
                    for (TaiKhoan tk : taiKhoans) {
                        System.out.println(tk);
                    }
                    break;
                case 2:
                    // Tìm tài khoản theo mã
                    System.out.print("Nhap ma tai khoan: ");
                    String maTK = scanner.nextLine();
                    TaiKhoan tk = taiKhoanDAO.read(maTK);
                    if (tk != null) {
                        System.out.println("Thong tin tai khoan: " + tk);
                    } else {
                        System.out.println("Khong tim thay tai khoan voi ma " + maTK);
                    }
                    break;
                case 0:
                    System.out.println("Quay lai menu chinh");
                    break;
                default:
                    System.out.println("Chuc nang dang phat trien!");
            }
        } while (choice != 0);
    }

    // Quản lý Ca Làm
    private static void manageCaLam() throws Exception {
        int choice = 0;

        do {
            System.out.println("\n===== QUAN LY CA LAM =====");
            System.out.println("1. Xem danh sach ca lam");
            System.out.println("2. Tim ca lam theo ma");
            System.out.println("3. Them ca lam moi");
            System.out.println("4. Cap nhat ca lam");
            System.out.println("5. Xoa ca lam");
            System.out.println("0. Quay lai");
            System.out.print("Chon chuc nang: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    // Xem danh sách ca làm
                    List<CaLam> caLams = caLamDAO.readAll();
                    System.out.println("\nDanh sach ca lam:");
                    for (CaLam cl : caLams) {
                        System.out.println(cl);
                    }
                    break;
                case 2:
                    // Tìm ca làm theo mã
                    System.out.print("Nhap ma ca lam: ");
                    String maCa = scanner.nextLine();
                    CaLam cl = caLamDAO.read(maCa);
                    if (cl != null) {
                        System.out.println("Thong tin ca lam: " + cl);
                    } else {
                        System.out.println("Khong tim thay ca lam voi ma " + maCa);
                    }
                    break;
                case 3:
                    // Thêm ca làm mới
                    CaLam newCaLam = createCaLam();
                    if (newCaLam != null) {
                        caLamDAO.create(newCaLam);
                        System.out.println("Them ca lam thanh cong!");
                    }
                    break;
                case 0:
                    System.out.println("Quay lai menu chinh");
                    break;
                default:
                    System.out.println("Chuc nang dang phat trien!");
            }
        } while (choice != 0);
    }

    // Quản lý Hóa Đơn
    private static void manageHoaDon() throws Exception {
        int choice = 0;

        do {
            System.out.println("\n===== QUAN LY HOA DON =====");
            System.out.println("1. Xem danh sach hoa don");
            System.out.println("2. Tim hoa don theo ma");
            System.out.println("0. Quay lai");
            System.out.print("Chon chuc nang: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    // Xem danh sách hóa đơn
                    List<HoaDon> hoaDons = hoaDonDAO.readAll();
                    System.out.println("\nDanh sach hoa don:");
                    for (HoaDon hd : hoaDons) {
                        System.out.println(hd);
                    }
                    break;
                case 2:
                    // Tìm hóa đơn theo mã
                    System.out.print("Nhap ma hoa don: ");
                    String maHD = scanner.nextLine();
                    HoaDon hd = hoaDonDAO.read(maHD);
                    if (hd != null) {
                        System.out.println("Thong tin hoa don: " + hd);
                    } else {
                        System.out.println("Khong tim thay hoa don voi ma " + maHD);
                    }
                    break;
                case 0:
                    System.out.println("Quay lai menu chinh");
                    break;
                default:
                    System.out.println("Chuc nang dang phat trien!");
            }
        } while (choice != 0);
    }

    // Quản lý Sản Phẩm
    private static void manageSanPham() throws Exception {
        int choice = 0;

        do {
            System.out.println("\n===== QUAN LY SAN PHAM =====");
            System.out.println("1. Xem danh sach san pham");
            System.out.println("2. Tim san pham theo ma");
            System.out.println("0. Quay lai");
            System.out.print("Chon chuc nang: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    // Xem danh sách sản phẩm
                    List<SanPham> sanPhams = sanPhamDAO.readAll();
                    System.out.println("\nDanh sach san pham:");
                    for (SanPham sp : sanPhams) {
                        System.out.println(sp);
                    }
                    break;
                case 2:
                    // Tìm sản phẩm theo mã
                    System.out.print("Nhap ma san pham: ");
                    String maSP = scanner.nextLine();
                    SanPham sp = sanPhamDAO.read(maSP);
                    if (sp != null) {
                        System.out.println("Thong tin san pham: " + sp);
                    } else {
                        System.out.println("Khong tim thay san pham voi ma " + maSP);
                    }
                    break;
                case 0:
                    System.out.println("Quay lai menu chinh");
                    break;
                default:
                    System.out.println("Chuc nang dang phat trien!");
            }
        } while (choice != 0);
    }

    // Quản lý Khách Hàng
    private static void manageKhachHang() throws Exception {
        int choice = 0;

        do {
            System.out.println("\n===== QUAN LY KHACH HANG =====");
            System.out.println("1. Xem danh sach khach hang");
            System.out.println("2. Tim khach hang theo ma");
            System.out.println("0. Quay lai");
            System.out.print("Chon chuc nang: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    // Xem danh sách khách hàng
                    List<KhachHang> khachHangs = khachHangDAO.readAll();
                    System.out.println("\nDanh sach khach hang:");
                    for (KhachHang kh : khachHangs) {
                        System.out.println(kh);
                    }
                    break;
                case 2:
                    // Tìm khách hàng theo mã
                    System.out.print("Nhap ma khach hang: ");
                    String maKH = scanner.nextLine();
                    KhachHang kh = khachHangDAO.read(maKH);
                    if (kh != null) {
                        System.out.println("Thong tin khach hang: " + kh);
                    } else {
                        System.out.println("Khong tim thay khach hang voi ma " + maKH);
                    }
                    break;
                case 0:
                    System.out.println("Quay lai menu chinh");
                    break;
                default:
                    System.out.println("Chuc nang dang phat trien!");
            }
        } while (choice != 0);
    }

    // Quản lý Phiếu Nhập Hàng
    private static void managePhieuNhapHang() throws Exception {
        int choice = 0;

        do {
            System.out.println("\n===== QUAN LY PHIEU NHAP HANG =====");
            System.out.println("1. Xem danh sach phieu nhap hang");
            System.out.println("2. Tim phieu nhap hang theo ma");
            System.out.println("0. Quay lai");
            System.out.print("Chon chuc nang: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    // Xem danh sách phiếu nhập hàng
                    List<PhieuNhapHang> phieuNhapHangs = phieuNhapHangDAO.readAll();
                    System.out.println("\nDanh sach phieu nhap hang:");
                    for (PhieuNhapHang pnh : phieuNhapHangs) {
                        System.out.println(pnh);
                    }
                    break;
                case 2:
                    // Tìm phiếu nhập hàng theo mã
                    System.out.print("Nhap ma phieu nhap hang: ");
                    String maPNH = scanner.nextLine();
                    PhieuNhapHang pnh = phieuNhapHangDAO.read(maPNH);
                    if (pnh != null) {
                        System.out.println("Thong tin phieu nhap hang: " + pnh);
                    } else {
                        System.out.println("Khong tim thay phieu nhap hang voi ma " + maPNH);
                    }
                    break;
                case 0:
                    System.out.println("Quay lai menu chinh");
                    break;
                default:
                    System.out.println("Chuc nang dang phat trien!");
            }
        } while (choice != 0);
    }

    // Quản lý Chi Tiết Hóa Đơn - Sản Phẩm
    private static void manageChiTietHoaDonSanPham() {
        System.out.println("\n===== QUAN LY CHI TIET HOA DON - SAN PHAM =====");
        System.out.println("Chuc nang dang phat trien!");
        pressEnterToContinue();
    }

    // Quản lý Chi Tiết Sản Phẩm - Phiếu Nhập
    private static void manageChiTietSanPhamPhieuNhap() {
        System.out.println("\n===== QUAN LY CHI TIET SAN PHAM - PHIEU NHAP =====");
        System.out.println("Chuc nang dang phat trien!");
        pressEnterToContinue();
    }

    // Helper method to create a new CaLam
    private static CaLam createCaLam() throws Exception {
        CaLam caLam = new CaLam();

        System.out.println("\n===== THEM CA LAM MOI =====");

        System.out.print("Nhap ma ca: ");
        String maCa = scanner.nextLine();
        caLam.setMaCa(maCa);

        System.out.print("Nhap gio bat dau (yyyy-MM-dd HH:mm:ss): ");
        String gioBatDauStr = scanner.nextLine();
        LocalDateTime gioBatDau = LocalDateTime.parse(gioBatDauStr, formatter);
        caLam.setGioBatDau(gioBatDau);

        System.out.print("Nhap gio ket thuc (yyyy-MM-dd HH:mm:ss): ");
        String gioKetThucStr = scanner.nextLine();
        LocalDateTime gioKetThuc = LocalDateTime.parse(gioKetThucStr, formatter);
        caLam.setGioKetThuc(gioKetThuc);

        System.out.print("Nhap trang thai (true/false): ");
        boolean trangThai = Boolean.parseBoolean(scanner.nextLine());
        caLam.setTrangThai(trangThai);

        System.out.print("Nhap ma tai khoan: ");
        String maTaiKhoan = scanner.nextLine();
        TaiKhoan taiKhoan = taiKhoanDAO.read(maTaiKhoan);

        if (taiKhoan == null) {
            System.out.println("Khong tim thay tai khoan voi ma " + maTaiKhoan);
            return null;
        }

        caLam.setTaiKhoan(taiKhoan);

        return caLam;
    }

    private static void pressEnterToContinue() {
        System.out.println("\nNhan Enter de tiep tuc...");
        scanner.nextLine();
    }
}
