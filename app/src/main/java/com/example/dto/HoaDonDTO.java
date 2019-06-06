package com.example.dto;

public class HoaDonDTO {
    private int maHoaDon;
    private int maBan;
    private int maNhanVien;
    private String ngayGoi,tinhTrang;

    public HoaDonDTO() {
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getNgayGoi() {
        return ngayGoi;
    }

    public void setNgayGoi(String ngayGoi) {
        this.ngayGoi = ngayGoi;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public HoaDonDTO(int maHoaDon, int maBan, int maNhanVien, String ngayGoi, String tinhTrang) {
        this.maHoaDon = maHoaDon;
        this.maBan = maBan;
        this.maNhanVien = maNhanVien;
        this.ngayGoi = ngayGoi;
        this.tinhTrang = tinhTrang;
    }
}
