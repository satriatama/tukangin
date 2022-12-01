package com.sae.tukangin.utils;

import java.security.PrivateKey;
import java.time.LocalDate;

public class OrderData {
    private String order_id;
    private String categoryService;
    private String name;
    private String address;
    private int cost;
    private int duration;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String paymentMethod;
    private boolean isDone;
    private boolean isPaid;


    private String kategori_name;
    private String layanan_name;
    private String order_end;

    public OrderData(String order_id, String categoryService, String name, String address, int duration, int cost, LocalDate dateEnd){
        this.order_id = order_id;
        this.categoryService = categoryService;
        this.name = name;
        this.address = address;
        this.cost = cost;
        this.duration = duration;
        //this.dateStart = null;
        this.dateEnd = dateEnd;
        this.paymentMethod = "cash";
        //this.isDone = false;
        //this.isPaid = false;
    }

    public OrderData(String kategori_name, String layanan_name, String order_end,String order_id) {
        this.kategori_name = kategori_name;
        this.layanan_name = layanan_name;
        this.order_end = order_end;
        this.order_id = order_id;
    }

    public String getKategori_name() {
        return kategori_name;
    }

    public void setKategori_name(String kategori_name) {
        this.kategori_name = kategori_name;
    }

    public String getLayanan_name() {
        return layanan_name;
    }

    public void setLayanan_name(String layanan_name) {
        this.layanan_name = layanan_name;
    }

    public String getOrder_end() {
        return order_end;
    }

    public void setOrder_end(String order_end) {
        this.order_end = order_end;
    }

    public String getId() {
        return order_id;
    }

    public void setId(String order_id) {
        this.order_id = order_id;
    }

    public String getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(String categoryService) {
        this.categoryService = categoryService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
