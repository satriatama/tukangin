package com.sae.tukangin.utils;

public class WorkerData {
    private int id;
    private String nama;
    private int jumlahLayanan;
    private int rating;
    private int imgId;
    private int order_id;

    public WorkerData(int id, String nama, int jumlahLayanan, int rating, int imgId, int order_id) {
        this.id = id;
        this.nama = nama;
        this.jumlahLayanan = jumlahLayanan;
        this.rating = rating;
        this.imgId = imgId;
        this.order_id = order_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getJumlahLayanan() {
        return jumlahLayanan;
    }

    public void setJumlahLayanan(int jumlahLayanan) {
        this.jumlahLayanan = jumlahLayanan;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
