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
public class NguoiThue {
    private String MaNT;
    private String HoTen;
    private Date NgaySinh;
    private String CCCD;
    private String SDT;
    private String Email;
    private Date NgayVaoO;
    private String MaPhong;

    public NguoiThue() {
    }

    public NguoiThue(String MaNT, String HoTen, Date NgaySinh, String CCCD, String SDT, String Email, Date NgayVaoO, String MaPhong) {
        this.MaNT = MaNT;
        this.HoTen = HoTen;
        this.NgaySinh = NgaySinh;
        this.CCCD = CCCD;
        this.SDT = SDT;
        this.Email = Email;
        this.NgayVaoO = NgayVaoO;
        this.MaPhong = MaPhong;
    }

    public String getMaNT() {
        return MaNT;
    }

    public void setMaNT(String MaNT) {
        this.MaNT = MaNT;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Date getNgayVaoO() {
        return NgayVaoO;
    }

    public void setNgayVaoO(Date NgayVaoO) {
        this.NgayVaoO = NgayVaoO;
    }

    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String MaPhong) {
        this.MaPhong = MaPhong;
    }

    @Override
    public String toString() {
        return "NguoiThue{" + "MaNT=" + MaNT + ", HoTen=" + HoTen + ", NgaySinh=" + NgaySinh + ", CCCD=" + CCCD + ", SDT=" + SDT + ", Email=" + Email + ", NgayVaoO=" + NgayVaoO + ", MaPhong=" + MaPhong + '}';
    }

}
