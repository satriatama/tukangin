package com.sae.tukangin.utils;

import java.time.LocalDate;

public class OrderData {
    private String id;
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

    public OrderData(String id, String categoryService, String name, String address, int duration, int cost, LocalDate dateEnd){
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
