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
import model.NguoiThue;
import util.DBConnection;

/**
 *
 * @author PC_33
 */
public class NguoiThueDAO {

    public List<NguoiThue> findAll() {
        List<NguoiThue> ds = new ArrayList<>();
        String sql = "SELECT * FROM NGUOITHUE";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
              
                String maNT = rs.getString("MaNT");
                String hoTen = rs.getString("HoTen");
                Date ngaySinh = rs.getDate("NgaySinh");
                String cCCD = rs.getString("CCCD");         // Đổi thành String
                String sDT = rs.getString("SoDienThoai");     // Đổi thành String
                String eMail = rs.getString("Email");
                Date ngayVaoO = rs.getDate("NgayVaoO");
                String maPhong = rs.getString("MaPhong");

                ds.add(new NguoiThue(maNT, hoTen, ngaySinh, cCCD, sDT, eMail, ngayVaoO, maPhong));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi đọc dữ liệu: " + e.getMessage());
        }
        return ds;
    }

    public boolean delete(String maNguoiThue) {
        String sql = "DELETE FROM NGUOITHUE WHERE MaNT=?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maNguoiThue);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa dữ liệu: " + e.getMessage());
        }
        return false;
    }

    public NguoiThue findById(String maNguoiThue) {
        String sql = "SELECT * FROM NGUOITHUE WHERE MaNT=?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maNguoiThue);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new NguoiThue(
                            rs.getString("MaNT"),
                            rs.getString("HoTen"),
                            rs.getDate("NgaySinh"),
                            rs.getString("CCCD"),
                            rs.getString("SoDienThoai"),
                            rs.getString("Email"),
                            rs.getDate("NgayVaoO"),
                            rs.getString("MaPhong")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm: " + e.getMessage());
        }
        return null;
    }

    public boolean insert(NguoiThue p) {
        String sql = "INSERT INTO NGUOITHUE VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getMaNT());
            ps.setString(2, p.getHoTen());
            ps.setDate(3, p.getNgaySinh() != null ? new java.sql.Date(p.getNgaySinh().getTime()) : null);
            ps.setString(4, p.getCCCD());
            ps.setString(5, p.getSDT());
            ps.setString(6, p.getEmail());
            ps.setDate(7, p.getNgayVaoO() != null ? new java.sql.Date(p.getNgayVaoO().getTime()) : null);
            ps.setString(8, p.getMaPhong());
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Lỗi khi thêm dữ liệu: " + e.getMessage());
        }
        return false;
    }

    public boolean update(NguoiThue p) {
        String sql = "UPDATE NGUOITHUE SET  HoTen = ?, NgaySinh = ?, CCCD = ?,SoDienThoai = ?,Email=?,NgayVaoO=?, MaPhong = ? WHERE MaNT = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getHoTen());
            ps.setDate(2, new java.sql.Date(p.getNgaySinh().getTime()));
            ps.setString(3, p.getCCCD());
            ps.setString(4, p.getSDT());
            ps.setString(5, p.getEmail());
            ps.setDate(6, new java.sql.Date(p.getNgayVaoO().getTime()));
            ps.setString(7, p.getMaPhong());
            ps.setString(8, p.getMaNT());
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật dữ liệu: " + e.getMessage());
        }
        return false;
    }

    public List<NguoiThue> findByName(String name) {
        List<NguoiThue> ds = new ArrayList<>();
        String sql = "SELECT * FROM NGUOITHUE WHERE MaNT LIKE ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maNT = rs.getString("MaNT");
                String hoTen = rs.getString("HoTen");
                Date ngaySinh = rs.getDate("NgaySinh");
                String cCCD = rs.getString("CCCD");         // Đổi thành String
                String sDT = rs.getString("SoDienThoai");     // Đổi thành String
                String eMail = rs.getString("Email");
                Date ngayVaoO = rs.getDate("NgayVaoO");
                String maPhong = rs.getString("MaPhong");

                ds.add(new NguoiThue(maNT, hoTen, ngaySinh, cCCD, sDT, eMail, ngayVaoO, maPhong));
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm theo tên: " + e.getMessage());
        }
        return ds;
    }
}
