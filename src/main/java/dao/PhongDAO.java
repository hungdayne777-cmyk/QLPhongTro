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
   
    String sql = "SELECT * FROM PHONG WHERE TrangThai IS NULL OR TrangThai != N'Đã xóa'";

    try (Connection conn = DBConnection.getConnection(); 
         PreparedStatement ps = conn.prepareStatement(sql); 
         ResultSet rs = ps.executeQuery()) {

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
     // 1. Kiểm tra phòng nếu đang có hợp đồng còn hiệu lực thì KHÔNG cho xóa
    if (isPhongDangThue(maPhong)) {
        System.out.println("Không thể xóa: Phòng " + maPhong + " đang có hợp đồng còn hiệu lực!");
        return false;
    }

    // 2. Chuyển trạng thái phòng sang 'Đã xóa' thay vì dùng lệnh DELETE
    String sql = "UPDATE PHONG SET TrangThai = N'Đã xóa' WHERE MaPhong = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, maPhong);
        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Lỗi khi xóa mềm phòng: " + e.getMessage());
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
    public boolean thanhLyHopDong(String maHopDong, String maPhong) {
   String sqlUpdateHopDong = "UPDATE HOPDONG SET TrangThai = N'Đã thanh lý' WHERE MaHopDong = ?";
    // Gỡ người thuê ra khỏi phòng (gán MaPhong về NULL) thay vì DELETE hẳn người thuê
    String sqlUpdateNguoiThue = "UPDATE NGUOITHUE SET MaPhong = NULL WHERE MaPhong = ?";
    String sqlUpdatePhong = "UPDATE PHONG SET TrangThai = N'Trống' WHERE MaPhong = ?";

    Connection conn = null;
    try {
        conn = DBConnection.getConnection();
        conn.setAutoCommit(false); // Bắt đầu Transaction

        // 1. Cập nhật trạng thái hợp đồng
        try (PreparedStatement ps1 = conn.prepareStatement(sqlUpdateHopDong)) {
            ps1.setNString(1, maHopDong);
            ps1.executeUpdate();
        }

        // 2. Gỡ mã phòng của người thuê
        try (PreparedStatement ps2 = conn.prepareStatement(sqlUpdateNguoiThue)) {
            ps2.setNString(1, maPhong);
            ps2.executeUpdate();
        }

        // 3. Chuyển trạng thái phòng về 'Trống'
        try (PreparedStatement ps3 = conn.prepareStatement(sqlUpdatePhong)) {
            ps3.setNString(1, maPhong);
            ps3.executeUpdate();
        }

        conn.commit(); // Xác nhận lưu thay đổi
        return true;

    } catch (SQLException e) {
        if (conn != null) {
            try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        }
        e.printStackTrace();
    }
    return false;
}

    public List<Phong> findByName(String name) {
        List<Phong> ds = new ArrayList<>();
    String sql = "SELECT * FROM PHONG WHERE (MaPhong LIKE ? OR TenPhong LIKE ?) AND (TrangThai IS NULL OR TrangThai != N'Đã xóa')";

    try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

        String key = "%" + name + "%";
        ps.setString(1, key);
        ps.setString(2, key);          
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
    public boolean updateTrangThai(String maPhong, String trangThai) {
    String sql = "UPDATE PHONG SET TrangThai = ? WHERE MaPhong = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setNString(1, trangThai);
        ps.setString(2, maPhong);
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
  public boolean isPhongDangThue(String maPhong) {
    // Kiểm tra trong bảng HOPDONG (hoặc có thể kiểm tra TrangThai = N'Đã thuê')
    String sql = "SELECT COUNT(*) FROM HOPDONG WHERE MaPhong = ? AND TrangThai = N'Đang hiệu lực'";
    
    try (Connection conn = DBConnection.getConnection(); 
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setString(1, maPhong);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu phòng đang có hợp đồng hiệu lực
            }
        }
    } catch (SQLException e) {
        System.out.println("Lỗi kiểm tra ràng buộc phòng: " + e.getMessage());
    }
    return false;
}
}
