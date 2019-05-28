package com.example.dto;

public class BanAnDTO {
    private int maBan;
    private String tenBan;
    private boolean chon;

    public boolean isChon() {
        return chon;
    }

    public void setChon(boolean chon) {
        this.chon = chon;
    }

    public BanAnDTO(int maBan, String tenBan,boolean chon) {
        this.maBan = maBan;
        this.tenBan = tenBan;
        this.chon = chon;
    }

    public BanAnDTO() {
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }
}
