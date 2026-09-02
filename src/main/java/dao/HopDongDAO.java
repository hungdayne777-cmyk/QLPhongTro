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
import model.Phong;
import util.DBConnection;

/**
 *
 * @author PC_33
 */
public class HopDongDAO {

    private PhongDAO pDAO = new PhongDAO();
    private NguoiThueDAO ntDAO = new NguoiThueDAO();

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
        String sql = "UPDATE HOPDONG SET MaNT = ?, MaPhong = ?, NgayBatDau = ?, NgayKetThuc = ?, GiaThue = ?, TrangThai = ? WHERE MaHD = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getMaNT());
            ps.setString(2, p.getMaPhong());

            // Kiểm tra tránh NullPointer nếu chưa chọn ngày
            if (p.getNgayBD() != null) {
                ps.setDate(3, new java.sql.Date(p.getNgayBD().getTime()));
            } else {
                ps.setNull(3, java.sql.Types.DATE);
            }

            if (p.getNgayKT() != null) {
                ps.setDate(4, new java.sql.Date(p.getNgayKT().getTime()));
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }

            ps.setDouble(5, p.getGiaThue());

            // Dùng setNString cho dữ liệu Tiếng Việt (NVARCHAR)
            ps.setNString(6, p.getTrangThai());
            ps.setString(7, p.getMaHD());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật dữ liệu: " + e.getMessage());
            e.printStackTrace(); // Nên in đầy đủ stack trace để dễ debug lỗi SQL
        }
        return false;
    }

    public List<HopDong> findSapHetHan(int soNgay) {
        List<HopDong> ds = new ArrayList<>();

        // Thêm điều kiện DATEDIFF >= 0 để chặn các hợp đồng đã hết hạn trong quá khứ
        String sql = "SELECT * FROM HOPDONG WHERE TrangThai = N'Đang hiệu lực' "
                + "AND DATEDIFF(day, GETDATE(), NgayKetThuc) BETWEEN 0 AND ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, soNgay);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ds.add(new HopDong(
                            rs.getString("MaHD"),
                            rs.getString("MaNT"),
                            rs.getString("MaPhong"),
                            rs.getDate("NgayBatDau"),
                            rs.getDate("NgayKetThuc"),
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
        String sql = "SELECT * FROM HOPDONG WHERE MaHD LIKE ? OR MaNT LIKE ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + name + "%");
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

    public boolean hasActiveContract(String maPhong) {
        String sql = "SELECT COUNT(*) FROM HOPDONG WHERE LTRIM(RTRIM(MaPhong)) = LTRIM(RTRIM(?)) AND TrangThai = N'Đang hiệu lực'";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maPhong.trim());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void capNhatGiaHopDongKhiThayDoiNguoi(String maPhong) {
        Phong phong = pDAO.findById(maPhong);
        if (phong == null) {
            return;
        }

        // Chỉ tính lại tiền nếu phòng đó đặt loại giá là "Theo đầu người"
        if ("Theo đầu người".equalsIgnoreCase(phong.getLoaiGia())) {
            int soNguoiO = ntDAO.countNguoiThueByMaPhong(maPhong);
            double giaThueMoi = phong.getGiaPhong() * soNguoiO;

            // Cập nhật lại hợp đồng đang hiệu lực của phòng này
            String sql = "UPDATE HOPDONG SET GiaThue = ? WHERE LTRIM(RTRIM(MaPhong)) = LTRIM(RTRIM(?)) AND TrangThai = N'Đang hiệu lực'";
            try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setDouble(1, giaThueMoi);
                ps.setString(2, maPhong.trim());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public List<HopDong> getDanhSachHopDongDangHieuLuc() {
        List<HopDong> list = new ArrayList<>();
        String sql = "SELECT * FROM HOPDONG WHERE TrangThai = N'Đang hiệu lực'";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HopDong hd = new HopDong();

                hd.setMaHD(rs.getString("MaHD"));
                hd.setMaPhong(rs.getString("MaPhong"));
                hd.setMaNT(rs.getString("MaNT"));
                hd.setGiaThue(rs.getDouble("GiaThue"));
                hd.setNgayBD(rs.getDate("NgayBatDau"));
                hd.setNgayKT(rs.getDate("NgayKetThuc"));
                hd.setTrangThai(rs.getString("TrangThai"));

                list.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean updateGiaThueByMaPhong(String maPhong, double giaThueMoi) {
    String sql = "UPDATE HOPDONG SET GiaThue = ? WHERE MaPhong = ? AND TrangThai = N'Đang hiệu lực'";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setDouble(1, giaThueMoi);
        ps.setString(2, maPhong);
        
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
    public boolean thanhLyHopDong(String maHD, String maPhong, String maNguoiThue) {
        String sqlUpdateHopDong = "UPDATE HOPDONG SET TrangThai = N'Đã thanh lý' WHERE MaHD = ?";
        String sqlUpdatePhong = "UPDATE PHONG SET TrangThai = N'Trống' WHERE MaPhong = ?";
        String sqlUpdateNguoiThue = "UPDATE NGUOITHUE SET MaPhong = NULL WHERE MaNT = ?";

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            // Tắt Auto Commit để thực thi chuỗi lệnh dạng Transaction
            conn.setAutoCommit(false);

            // 1. Cập nhật trạng thái Hợp đồng
            try (PreparedStatement pst1 = conn.prepareStatement(sqlUpdateHopDong)) {
                pst1.setString(1, maHD);
                pst1.executeUpdate();
            }

            // 2. Cập nhật trạng thái Phòng về 'Trống'
            try (PreparedStatement pst2 = conn.prepareStatement(sqlUpdatePhong)) {
                pst2.setString(1, maPhong);
                pst2.executeUpdate();
            }

            // 3. Xóa mã phòng gắn với Người thuê để có thể ký hợp đồng mới sau này
            try (PreparedStatement pst3 = conn.prepareStatement(sqlUpdateNguoiThue)) {
                pst3.setString(1, maNguoiThue);
                pst3.executeUpdate();
            }

            // Xác nhận thành công toàn bộ
            conn.commit();
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    // Hoàn tác nếu có lỗi xảy ra ở bất kỳ bước nào
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
