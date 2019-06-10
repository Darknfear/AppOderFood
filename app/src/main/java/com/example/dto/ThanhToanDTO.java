package com.example.dto;

public class ThanhToanDTO {
    private String tenMon;
    private int soLuong;
    private int giaTien;

    public ThanhToanDTO() {
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public ThanhToanDTO(String tenMon, int soLuong, int giaTien) {
        this.tenMon = tenMon;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
    }
}
