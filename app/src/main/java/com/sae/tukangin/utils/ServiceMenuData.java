package com.sae.tukangin.utils;

public class ServiceMenuData {
    private String title;
    private int imgid;
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public ServiceMenuData(String title, int imgid, int id) {
        this.title = title;
        this.imgid = imgid;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
