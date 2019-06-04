package com.example.dto;

public class MonAnDTO {
    private int id;
    private String tenMon;
    private String gia;
    private int idLoaiMon;
    private byte[] hinhAnh;

    public MonAnDTO() {
    }

    public MonAnDTO(int id, String tenMon, String gia, int idLoaiMon,byte[] hinhAnh) {
        this.id = id;
        this.tenMon = tenMon;
        this.gia = gia;
        this.idLoaiMon = idLoaiMon;
        this.hinhAnh = hinhAnh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public int getIdLoaiMon() {
        return idLoaiMon;
    }

    public void setIdLoaiMon(int idLoaiMon) {
        this.idLoaiMon = idLoaiMon;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
