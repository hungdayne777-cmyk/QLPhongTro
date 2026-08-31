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
import model.Phong;
import util.DBConnection;

/**
 *
 * @author PC_33
 */
public class PhongDAO {
    public List<Phong> findAll() {
        List<Phong> ds = new ArrayList<>();
        String sql = "SELECT * FROM PHONG";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String maPhong = rs.getString("MaPhong");
                String tenPhong = rs.getString("TenPhong");
                int soNguoiToiDa = rs.getInt("SoNguoiToiDa");
                double giaPhong = rs.getDouble("GiaPhong");
                String loaiGia = rs.getString("LoaiGia");
                String trangThai = rs.getString("TrangThai");

                ds.add(new Phong(maPhong, tenPhong, soNguoiToiDa, giaPhong, loaiGia, trangThai));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi đọc dữ liệu: " + e.getMessage());
        }
        return ds;
    }

    public boolean delete(String maPhong) {
        String sql = "DELETE FROM PHONG WHERE MaPhong=?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maPhong);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa dữ liệu: " + e.getMessage());
        }
        return false;
    }

    public Phong findById(String maPhong) {
        String sql = "SELECT * FROM PHONG WHERE MaPhong=?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maPhong);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Phong(
                            rs.getString("MaPhong"),
                            rs.getString("TenPhong"),
                            rs.getInt("SoNguoiToiDa"),
                            rs.getDouble("GiaPhong"),
                            rs.getString("LoaiGia"),
                            rs.getString("TrangThai")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm: " + e.getMessage());
        }
        return null;
    }

    public boolean insert(Phong p) {
        String sql = "INSERT INTO PHONG VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getMaPhong());
            ps.setString(2, p.getTenPhong());
            ps.setInt(3, p.getSoNguoiToiDa());
            ps.setDouble(4, p.getGiaPhong());
            ps.setString(5, p.getLoaiGia());
            ps.setString(6, p.getTrangThai());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Lỗi khi thêm dữ liệu: " + e.getMessage());
        }
        return false;
    }

    public boolean update(Phong p) {
        String sql = "UPDATE PHONG SET TenPhong = ?, SoNguoiToiDa = ?, GiaPhong = ?, LoaiGia = ?, TrangThai = ? WHERE MaPhong = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getTenPhong());
            ps.setInt(2, p.getSoNguoiToiDa());
            ps.setDouble(3, p.getGiaPhong());
            ps.setString(4, p.getLoaiGia());
            ps.setString(5, p.getTrangThai());
            ps.setString(6, p.getMaPhong());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật dữ liệu: " + e.getMessage());
        }
        return false;
    }

    public int countTrangThai(String trangThai) {
        String sql = "SELECT COUNT(*) FROM PHONG WHERE TrangThai = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, trangThai);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Phong> findByName(String name) {
        List<Phong> ds = new ArrayList<>();
        String sql = "SELECT * FROM PHONG WHERE MaPhong LIKE ? OR TenPhong LIKE ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            String key = "%" + name + "%";
            ps.setString(1, key); // Gán cho MaPhong
            ps.setString(2, key); // Gán cho TenPhong          
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maPhong = rs.getString("MaPhong");
                    String tenPhong = rs.getString("TenPhong");
                    int soNguoiToiDa = rs.getInt("SoNguoiToiDa");
                    double giaPhong = rs.getDouble("GiaPhong");
                    String loaiGia = rs.getString("LoaiGia");
                    String trangThai = rs.getString("TrangThai");
                    ds.add(new Phong(maPhong, tenPhong, soNguoiToiDa, giaPhong, loaiGia, trangThai));
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm theo tên: " + e.getMessage());
        }
        return ds;
    }
}
