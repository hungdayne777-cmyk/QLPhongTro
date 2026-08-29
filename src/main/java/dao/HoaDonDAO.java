/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.HoaDon;
import util.DBConnection;

/**
 *
 * @author PC_33
 */
public class HoaDonDAO {

    public List<HoaDon> findAll() {
        List<HoaDon> ds = new ArrayList<>();
        String sql = "SELECT * FROM HOADON";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String maHoaDon = rs.getString("MaHoaDon");
                String maHD = rs.getString("MaHD");
                int thang = rs.getInt("Thang");
                int nam = rs.getInt("Nam");
                double csDienCu = rs.getDouble("CSDienCu");
                double csDienMoi = rs.getDouble("CSDienMoi");
                double dgDien = rs.getDouble("DonGiaDien");
                double csNuocCu = rs.getDouble("CSNuocCu");
                double csNuocMoi = rs.getDouble("CSNuocMoi");
                double dgNuoc = rs.getDouble("DonGiaNuoc");
                double tienPhong = rs.getDouble("TienPhong");

                double tongTien = rs.getDouble("TongTien");
                Date ngayHH = rs.getDate("NgayHetHan");
                String trangThaiTT = rs.getString("TrangThaiThanhToan");
                ds.add(new HoaDon(maHoaDon, maHD, thang, nam, csDienCu, csDienMoi, dgDien, csNuocCu, csNuocMoi, dgNuoc, tienPhong, tongTien, ngayHH, trangThaiTT));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi đọc dữ liệu: " + e.getMessage());
        }
        return ds;
    }

    public boolean delete(String maHoaDon) {
        String sql = "DELETE FROM HOADON WHERE MaHoaDon=?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maHoaDon);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa dữ liệu: " + e.getMessage());
        }
        return false;
    }

    public HoaDon findById(String maHoaDon) {
        String sql = "SELECT * FROM HOADON WHERE MaHoaDon=?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maHoaDon);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new HoaDon(
                            rs.getString("MaHoaDon"),
                            rs.getString("MaHD"),
                            rs.getInt("Thang"),
                            rs.getInt("Nam"),
                            rs.getDouble("CSDienCu"),
                            rs.getDouble("CSDienMoi"),
                            rs.getDouble("DonGiaDien"),
                            rs.getDouble("CSNuocCu"),
                            rs.getDouble("CSNuocMoi"),
                            rs.getDouble("DonGiaNuoc"),
                            rs.getDouble("TienPhong"),
                            rs.getDouble("TongTien"),
                            rs.getDate("NgayHetHan"),
                            rs.getString("TrangThaiThanhToan")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm: " + e.getMessage());
        }
        return null;
    }

    public boolean insert(HoaDon p) {
        String sql = "INSERT INTO HOADON VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getMaHoaDon());
            ps.setString(2, p.getMaHD());
            ps.setInt(3, p.getThang());
            ps.setInt(4, p.getNam());
            ps.setDouble(5, p.getCSDienCu());
            ps.setDouble(6, p.getCSDienMoi());
            ps.setDouble(7, p.getGiaDien());
            ps.setDouble(8, p.getCSNuocCu());
            ps.setDouble(9, p.getCSNuocMoi());
            ps.setDouble(10, p.getGiaNuoc());

            ps.setDouble(11, p.getTienPhong());

            ps.setDouble(12, p.getTongTien());
            ps.setDate(13, new java.sql.Date(p.getNgayHH().getTime()));

            ps.setString(14, p.getTrangThaiTT());
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Lỗi khi thêm dữ liệu: " + e.getMessage());
        }
        return false;
    }

    public boolean update(HoaDon p) {
        String sql = "UPDATE HOADON SET MaHD = ?,Thang = ?, Nam = ?,CSDienCu = ?, CSDienMoi = ?,DonGiaDien = ?,CSNuocCu = ?, CSNuocMoi = ?,DonGiaNuoc = ?,TienPhong = ?, TongTien = ?,NgayHetHan = ?, TrangThaiThanhToan = ? WHERE MaHoaDon = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getMaHD());
            ps.setInt(2, p.getThang());
            ps.setInt(3, p.getNam());
            ps.setDouble(4, p.getCSDienCu());
            ps.setDouble(5, p.getCSDienMoi());
            ps.setDouble(6, p.getGiaDien());
            ps.setDouble(7, p.getCSNuocCu());
            ps.setDouble(8, p.getCSNuocMoi());
            ps.setDouble(9, p.getGiaNuoc());

            ps.setDouble(10, p.getTienPhong());

            ps.setDouble(11, p.getTongTien());
            ps.setDate(12, new java.sql.Date(p.getNgayHH().getTime()));

            ps.setString(13, p.getTrangThaiTT());
            ps.setString(14, p.getMaHoaDon());
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật dữ liệu: " + e.getMessage());
        }
        return false;
    }

    public List<HoaDon> findByName(String name) {
        List<HoaDon> ds = new ArrayList<>();
        String sql = "SELECT * FROM HOADON WHERE MaHoaDon LIKE ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maHoaDon = rs.getString("MaHoaDon");
                    String maHD = rs.getString("MaHD");
                    int thang = rs.getInt("Thang");
                    int nam = rs.getInt("Nam");
                    double csDienCu = rs.getDouble("CSDienCu");
                    double csDienMoi = rs.getDouble("CSDienMoi");
                    double dgDien = rs.getDouble("DonGiaDien");
                    double csNuocCu = rs.getDouble("CSNuocCu");
                    double csNuocMoi = rs.getDouble("CSNuocMoi");
                    double dgNuoc = rs.getDouble("DonGiaNuoc");
                    double tienPhong = rs.getDouble("TienPhong");
                    double tongTien = rs.getDouble("TongTien");
                    Date ngayHH = rs.getDate("NgayHetHan");
                    String trangThaiTT = rs.getString("TrangThaiThanhToan");
                    ds.add(new HoaDon(maHoaDon, maHD, thang, nam, csDienCu, csDienMoi, dgDien, csNuocCu, csNuocMoi, dgNuoc, tienPhong, tongTien, ngayHH, trangThaiTT));
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm theo tên: " + e.getMessage());
        }
        return ds;
    }
    public double[] getChiSoMoiNhatByMaHD(String maHD) {
    double[] chiSo = null; 
    String sql = "SELECT TOP 1 CsDienMoi, CsNuocMoi FROM HoaDon " +
                 "WHERE MaHD = ? ORDER BY Nam DESC, Thang DESC, MaHoaDon DESC";
    
    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, maHD);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            chiSo = new double[]{
                rs.getDouble("CsDienMoi"), 
                rs.getDouble("CsNuocMoi")
            };
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return chiSo;
}


public double[] getChiSoBanDauTuHopDong(String maHD) {
    double[] chiSo = new double[]{0.0, 0.0};
    String sql = "SELECT DienBanDau, NuocBanDau FROM HopDong WHERE MaHD = ?";
    
    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, maHD);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            chiSo[0] = rs.getDouble("DienBanDau");
            chiSo[1] = rs.getDouble("NuocBanDau");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return chiSo;
}
public boolean capNhatTrangThaiThanhToan(String maHoaDon, String trangThaiMoi) {
    String sql = "UPDATE HOADON SET TrangThaiThanhToan = ? WHERE MaHoaDon = ?";
    
    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setString(1, trangThaiMoi);
        ps.setString(2, maHoaDon);
        
        return ps.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
}
