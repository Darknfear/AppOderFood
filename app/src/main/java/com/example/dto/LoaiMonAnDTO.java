package com.example.dto;

import android.graphics.Bitmap;

public class LoaiMonAnDTO {
    private int maLoai;
    private String tenLoai;
    private byte[] hinhAnh;


    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public LoaiMonAnDTO(int maLoai, String tenLoai, byte[] hinhAnh) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.hinhAnh = hinhAnh;

    }

    public LoaiMonAnDTO() {
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
