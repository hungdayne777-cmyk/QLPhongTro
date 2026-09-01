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
public class HoaDon {

    private String MaHoaDon;
    private String MaHD;
    private int Thang;
    private int Nam;
    private double CSDienCu;
    private double CSDienMoi;
     private double GiaDien;
    private double CSNuocCu;
    private double CSNuocMoi;
   
    private double GiaNuoc;
    private double TienPhong;
    private double TongTien;
    private Date NgayHH;
    private String TrangThaiTT;

    public HoaDon() {
    }

    public HoaDon(String MaHoaDon, String MaHD, int Thang, int Nam, double CSDienCu, double CSDienMoi, double GiaDien, double CSNuocCu, double CSNuocMoi, double GiaNuoc, double TienPhong, double TongTien, Date NgayHH, String TrangThaiTT) {
        this.MaHoaDon = MaHoaDon;
        this.MaHD = MaHD;
        this.Thang = Thang;
        this.Nam = Nam;
        this.CSDienCu = CSDienCu;
        this.CSDienMoi = CSDienMoi;
        this.GiaDien = GiaDien;
        this.CSNuocCu = CSNuocCu;
        this.CSNuocMoi = CSNuocMoi;
        this.GiaNuoc = GiaNuoc;
        this.TienPhong = TienPhong;
        this.TongTien = TongTien;
        this.NgayHH = NgayHH;
        this.TrangThaiTT = TrangThaiTT;
    }

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public int getThang() {
        return Thang;
    }

    public void setThang(int Thang) {
        this.Thang = Thang;
    }

    public int getNam() {
        return Nam;
    }

    public void setNam(int Nam) {
        this.Nam = Nam;
    }

    public double getCSDienCu() {
        return CSDienCu;
    }

    public void setCSDienCu(double CSDienCu) {
        this.CSDienCu = CSDienCu;
    }

    public double getCSDienMoi() {
        return CSDienMoi;
    }

    public void setCSDienMoi(double CSDienMoi) {
        this.CSDienMoi = CSDienMoi;
    }

    public double getGiaDien() {
        return GiaDien;
    }

    public void setGiaDien(double GiaDien) {
        this.GiaDien = GiaDien;
    }

    public double getCSNuocCu() {
        return CSNuocCu;
    }

    public void setCSNuocCu(double CSNuocCu) {
        this.CSNuocCu = CSNuocCu;
    }

    public double getCSNuocMoi() {
        return CSNuocMoi;
    }

    public void setCSNuocMoi(double CSNuocMoi) {
        this.CSNuocMoi = CSNuocMoi;
    }

    public double getGiaNuoc() {
        return GiaNuoc;
    }

    public void setGiaNuoc(double GiaNuoc) {
        this.GiaNuoc = GiaNuoc;
    }

    public double getTienPhong() {
        return TienPhong;
    }

    public void setTienPhong(double TienPhong) {
        this.TienPhong = TienPhong;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    public Date getNgayHH() {
        return NgayHH;
    }

    public void setNgayHH(Date NgayHH) {
        this.NgayHH = NgayHH;
    }

    public String getTrangThaiTT() {
        return TrangThaiTT;
    }

    public void setTrangThaiTT(String TrangThaiTT) {
        this.TrangThaiTT = TrangThaiTT;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "MaHoaDon=" + MaHoaDon + ", MaHD=" + MaHD + ", Thang=" + Thang + ", Nam=" + Nam + ", CSDienCu=" + CSDienCu + ", CSDienMoi=" + CSDienMoi + ", GiaDien=" + GiaDien + ", CSNuocCu=" + CSNuocCu + ", CSNuocMoi=" + CSNuocMoi + ", GiaNuoc=" + GiaNuoc + ", TienPhong=" + TienPhong + ", TongTien=" + TongTien + ", NgayHH=" + NgayHH + ", TrangThaiTT=" + TrangThaiTT + '}';
    }

 
}
