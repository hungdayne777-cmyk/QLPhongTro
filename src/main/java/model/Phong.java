/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC_33
 */
public class Phong {
    private String MaPhong;
    private String TenPhong;
    private int SoNguoiToiDa;
    private double GiaPhong;
    private String LoaiGia;
    private String TrangThai;

    public Phong() {
    }

    public Phong(String MaPhong, String TenPhong, int SoNguoiToiDa, double GiaPhong, String LoaiGia, String TrangThai) {
        this.MaPhong = MaPhong;
        this.TenPhong = TenPhong;
        this.SoNguoiToiDa = SoNguoiToiDa;
        this.GiaPhong = GiaPhong;
        this.LoaiGia = LoaiGia;
        this.TrangThai = TrangThai;
    }

    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String MaPhong) {
        this.MaPhong = MaPhong;
    }

    public String getTenPhong() {
        return TenPhong;
    }

    public void setTenPhong(String TenPhong) {
        this.TenPhong = TenPhong;
    }

    public int getSoNguoiToiDa() {
        return SoNguoiToiDa;
    }

    public void setSoNguoiToiDa(int SoNguoiToiDa) {
        this.SoNguoiToiDa = SoNguoiToiDa;
    }

    public double getGiaPhong() {
        return GiaPhong;
    }

    public void setGiaPhong(double GiaPhong) {
        this.GiaPhong = GiaPhong;
    }

    public String getLoaiGia() {
        return LoaiGia;
    }

    public void setLoaiGia(String LoaiGia) {
        this.LoaiGia = LoaiGia;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return "Phong{" + "MaPhong=" + MaPhong + ", TenPhong=" + TenPhong + ", SoNguoiToiDa=" + SoNguoiToiDa + ", GiaPhong=" + GiaPhong + ", Lo\u1ea1iGia=" + LoaiGia + ", TrangThai=" + TrangThai + '}';
    }
    
}
