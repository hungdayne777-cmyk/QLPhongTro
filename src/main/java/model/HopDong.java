/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author PC_33
 */
public class HopDong {

    private String MaHD;
    private String MaNT;
    private String MaPhong;
    private Date NgayBD;
    private Date NgayKT;
    private double GiaThue;
    private String TrangThai;

    public HopDong() {
    }

    public HopDong(String MaHD, String MaNT, String MaPhong, Date NgayBD, Date NgayKT, double GiaThue, String TrangThai) {
        this.MaHD = MaHD;
        this.MaNT = MaNT;
        this.MaPhong = MaPhong;
        this.NgayBD = NgayBD;
        this.NgayKT = NgayKT;
        this.GiaThue = GiaThue;
        this.TrangThai = TrangThai;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getMaNT() {
        return MaNT;
    }

    public void setMaNT(String MaNT) {
        this.MaNT = MaNT;
    }

    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String MaPhong) {
        this.MaPhong = MaPhong;
    }

    public Date getNgayBD() {
        return NgayBD;
    }

    public void setNgayBD(Date NgayBD) {
        this.NgayBD = NgayBD;
    }

    public Date getNgayKT() {
        return NgayKT;
    }

    public void setNgayKT(Date NgayKT) {
        this.NgayKT = NgayKT;
    }

    public double getGiaThue() {
        return GiaThue;
    }

    public void setGiaThue(double GiaThue) {
        this.GiaThue = GiaThue;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return "HopDong{" + "MaHD=" + MaHD + ", MaNT=" + MaNT + ", MaPhong=" + MaPhong + ", NgayBD=" + NgayBD + ", NgayKT=" + NgayKT + ", GiaThue=" + GiaThue + ", TrangThai=" + TrangThai + '}';
    }
    
}
