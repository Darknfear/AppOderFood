package com.example.dto;

public class ChiTietHoaDonDTO {
    private int maMonAn,maHoaDon,soLuong;

    public int getMaMonAn() {
        return maMonAn;
    }

    public void setMaMonAn(int maMonAn) {
        this.maMonAn = maMonAn;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public ChiTietHoaDonDTO() {
    }

    public ChiTietHoaDonDTO(int maMonAn, int maHoaDon, int soLuong) {
        this.maMonAn = maMonAn;
        this.maHoaDon = maHoaDon;
        this.soLuong = soLuong;
    }
}
