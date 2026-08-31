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
import model.HopDong;
import util.DBConnection;

/**
 *
 * @author PC_33
 */
public class HopDongDAO {

   public List<HopDong> findAll() {
        List<HopDong> ds = new ArrayList<>();
        String sql = "SELECT * FROM HOPDONG";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String maHD = rs.getString("MaHD");
                String maNT = rs.getString("MaNT");
                String maPhong = rs.getString("MaPhong");
                Date ngayBD = rs.getDate("NgayBatDau");
                Date ngayKT = rs.getDate("NgayKetThuc");
                double giaThue = rs.getDouble("GiaThue");

                String trangThai = rs.getString("TrangThai");

                ds.add(new HopDong(maHD, maNT, maPhong, ngayBD, ngayKT, giaThue, trangThai));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi đọc dữ liệu: " + e.getMessage());
        }
        return ds;
    }

    public boolean delete(String maHopDong) {
        String sql = "DELETE FROM HOPDONG WHERE MaHD=?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maHopDong);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa dữ liệu: " + e.getMessage());
        }
        return false;
    }

    public HopDong findById(String maHopDong) {
        String sql = "SELECT * FROM HOPDONG WHERE MaHD=?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maHopDong);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new HopDong(
                            rs.getString("MaHD"),
                            rs.getString("MaNT"),
                            rs.getString("MaPhong"),
                            rs.getDate("NgayBatDau"),
                            rs.getDate("NgayKetThuc"),
                            rs.getDouble("GiaThue"),
                            rs.getString("TrangThai")
                    );

                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm: " + e.getMessage());
        }
        return null;
    }

    public boolean insert(HopDong p) {
        String sql = "INSERT INTO HOPDONG VALUES (?, ?, ?, ?, ?, ?,?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

           ps.setString(1, p.getMaHD());
            ps.setString(2, p.getMaNT());
            ps.setString(3, p.getMaPhong());
            ps.setDate(4, p.getNgayBD() != null ? new java.sql.Date(p.getNgayBD().getTime()) : null);
            ps.setDate(5, p.getNgayKT() != null ? new java.sql.Date(p.getNgayKT().getTime()) : null);
            ps.setDouble(6, p.getGiaThue());
            ps.setString(7, p.getTrangThai());
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Lỗi khi thêm dữ liệu: " + e.getMessage());
        }
        return false;
    }

    public boolean update(HopDong p) {
        String sql = "UPDATE HOPDONG SET MaNT = ?,MaPhong = ?, NgayBatDau = ?,NgayKetThuc = ?, GiaThue = ?, TrangThai = ? WHERE MaHD = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getMaNT());
            ps.setString(2, p.getMaPhong());
            ps.setDate(3, new java.sql.Date(p.getNgayBD().getTime()));
            ps.setDate(4, new java.sql.Date(p.getNgayKT().getTime()));
            ps.setDouble(5, p.getGiaThue());
            ps.setString(6, p.getTrangThai());
            ps.setString(7, p.getMaHD());
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật dữ liệu: " + e.getMessage());
        }
        return false;
    }

    public List<HopDong> findSapHetHan(int soNgay) {
        List<HopDong> ds = new ArrayList<>();
    // Sửa N'Đang hiệu lực' đúng theo dữ liệu trong SQL
    // Dùng DATEDIFF để lọc các hợp đồng có Ngày Kết Thúc trong vòng N ngày tới hoặc đã quá hạn
    String sql = "SELECT * FROM HOPDONG WHERE TrangThai = N'Đang hiệu lực' "
               + "AND DATEDIFF(day, GETDATE(), NgayKetThuc) <= ?";
    
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, soNgay);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ds.add(new HopDong(
                    rs.getString("MaHD"),
                    rs.getString("MaNT"),
                    rs.getString("MaPhong"),
                    rs.getDate("NgayBatDau"),   // Đã khớp tên cột NgayBatDau
                    rs.getDate("NgayKetThuc"),  // Đã khớp tên cột NgayKetThuc
                    rs.getDouble("GiaThue"),
                    rs.getString("TrangThai")
                ));
            }
        }
    } catch (SQLException e) {
        System.out.println("Lỗi HopDongDAO.findSapHetHan: " + e.getMessage());
    }
    return ds;
    }

    public List<HopDong> findByName(String name) {
        List<HopDong> ds = new ArrayList<>();
        String sql = "SELECT * FROM HOPDONG WHERE MaHD LIKE ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maHD = rs.getString("MaHD");
                    String maNT = rs.getString("MaNT");

                    String maPhong = rs.getString("MaPhong");
                    Date ngayBD = rs.getDate("NgayBatDau");
                    Date ngayKT = rs.getDate("NgayKetThuc");
                    double giaThue = rs.getDouble("GiaThue");
                    String trangThai = rs.getString("TrangThai");

                    ds.add(new HopDong(maHD, maNT, maPhong, ngayBD, ngayKT, giaThue, trangThai));
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm theo tên: " + e.getMessage());
        }
        return ds;
    }
}
